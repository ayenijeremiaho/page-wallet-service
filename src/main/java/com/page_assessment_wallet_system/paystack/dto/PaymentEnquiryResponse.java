package com.page_assessment_wallet_system.paystack.dto;

import com.page_assessment_wallet_system.util.GeneralService;
import lombok.Data;

@Data
public class PaymentEnquiryResponse {
    private boolean status;
    private String message;
    private PaymentEnquiryResponseData data;

    @Data
    public static class PaymentEnquiryResponseData {
        private double amount;
        private String currency;
        private String transaction_date;
        private String status;
        private String reference;
        private String domain;
        private String metadata;
        private String gateway_response;
        private String message;
        private String channel;
        private String ip_address;

        //defaults kobo amount to Naira
        public double getAmount() {
            return GeneralService.getAsNaira(amount);
        }
    }
}
