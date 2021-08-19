package com.page_assessment_wallet_system.paystack.dto;

import lombok.Data;

@Data
public class PaymentResponse {
    private boolean status;
    private String message;
    private PaymentResponseData data;

    @Data
    public static class PaymentResponseData {
        public String authorization_url;
        public String access_code;
        public String reference;
    }
}
