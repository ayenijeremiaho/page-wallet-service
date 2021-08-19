package com.page_assessment_wallet_system.paystack.service;

import com.page_assessment_wallet_system.controller.dto.TransferRequest;
import com.page_assessment_wallet_system.paystack.dto.*;

public interface PaystackService {
    PaymentResponse initiatePayment(TransferRequest request);

    PaymentEnquiryResponse confirmPayment(String reference);

    BalanceEnquiryResponse getAccountBalance();

    TransactionHistoryResponse getTransactionHistory();
}
