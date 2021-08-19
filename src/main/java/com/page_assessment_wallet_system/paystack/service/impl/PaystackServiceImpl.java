package com.page_assessment_wallet_system.paystack.service.impl;

import com.google.gson.Gson;
import com.page_assessment_wallet_system.config.EnvironmentConfig;
import com.page_assessment_wallet_system.controller.dto.TransferRequest;
import com.page_assessment_wallet_system.exception.WalletException;
import com.page_assessment_wallet_system.http.HttpService;
import com.page_assessment_wallet_system.paystack.dto.*;
import com.page_assessment_wallet_system.paystack.service.PaystackService;
import com.page_assessment_wallet_system.util.GeneralService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaystackServiceImpl implements PaystackService {

    public static final String INITIAL = "INITIAL";
    public static final String VERIFY = "VERIFY";
    public static final String BALANCE = "BALANCE";

    private final Gson gson;
    private final HttpService httpService;
    private final EnvironmentConfig environmentConfig;

    public PaystackServiceImpl(Gson gson, HttpService httpService, EnvironmentConfig environmentConfig) {
        this.gson = gson;
        this.httpService = httpService;
        this.environmentConfig = environmentConfig;
    }

    @Override
    public PaymentResponse initiatePayment(TransferRequest request) {
        String payload = GeneralService.prepareRequest(getPaymentRequest(request), gson);

        String response = GeneralService.getResponseAsString(httpService.post(getHeaders(), payload, getUrl(INITIAL)));

        PaymentResponse paymentResponse = gson.fromJson(response, PaymentResponse.class);

        if (paymentResponse.isStatus()) {
            return paymentResponse;
        } else {
            throw new WalletException(paymentResponse.getMessage());
        }
    }

    @Override
    public PaymentEnquiryResponse confirmPayment(String reference) {
        String url = getUrl(VERIFY) + "/" + reference;
        String response = GeneralService.getResponseAsString(httpService.get(getHeaders(), null, url));

        return gson.fromJson(response, PaymentEnquiryResponse.class);
    }

    @Override
    public BalanceEnquiryResponse getAccountBalance() {
        String response = GeneralService.getResponseAsString(httpService.get(getHeaders(), null, getUrl(BALANCE)));

        return gson.fromJson(response, BalanceEnquiryResponse.class);
    }

    @Override
    public TransactionHistoryResponse getTransactionHistory() {
        String response = GeneralService.getResponseAsString(httpService.get(getHeaders(), null, getUrl("BASE")));

        return gson.fromJson(response, TransactionHistoryResponse.class);
    }

    private PaymentRequest getPaymentRequest(TransferRequest transferRequest){
        return PaymentRequest.builder().email(transferRequest.getEmail())
                .amount(toKobo(transferRequest.getAmount()))
                .channels(new String[]{"card", "bank", "ussd", "qr", "mobile_money", "bank_transfer"})
                .build();
    }

    private String toKobo(String amount) {
        try {
            int amountInInt = Integer.parseInt(amount);
            if (amountInInt < 1) {
                throw new WalletException("Amount cannot be less than 1 Naira");
            }
            return String.valueOf(GeneralService.getAsKobo(amountInInt));
        } catch (Exception e) {
            throw new WalletException("Invalid Amount");
        }
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();

        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + environmentConfig.getProperty("PAYSTACK_KEY"));

        return headers;
    }

    private String getUrl(String action) {
        String baseUrl = environmentConfig.getProperty("PAYSTACK_TRANSACTION_BASE_URL");
        switch (action) {
            case INITIAL:
                return baseUrl + environmentConfig.getProperty("PAYSTACK_INITIALIZE_URL");
            case VERIFY:
                return baseUrl + environmentConfig.getProperty("PAYSTACK_VERIFY_URL");
            case BALANCE:
                return baseUrl + environmentConfig.getProperty("PAYSTACK_BALANCE_URL");
            default:
                return baseUrl;

        }
    }
}
