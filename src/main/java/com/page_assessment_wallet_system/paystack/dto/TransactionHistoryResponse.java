package com.page_assessment_wallet_system.paystack.dto;

import com.page_assessment_wallet_system.util.GeneralService;
import lombok.Data;

import java.util.List;

@Data
public class TransactionHistoryResponse {
    private boolean status;
    private String message;
    private List<TransactionResponse> data;
    private Meta meta;

    @Data
    public static class TransactionResponse {
        private long id;
        private String domain;
        private String status;
        private String reference;
        private double amount;
        private String message;
        private String gateway_response;
        private String paid_at;
        private String created_at;
        private String channel;
        private String currency;
        private String ip_address;
        private String metadata;
        private String timeline;
        private Customer customer;

        //defaults kobo amount to Naira
        public double getAmount() {
            return GeneralService.getAsNaira(amount);
        }

        @Data
        public static class Customer {
            private String first_name;
            private String last_name;
            private String email;
            private String phone;
            private String metadata;
            private String customer_code;
        }
    }

    @Data
    public class Meta {
        private int total;
        private int skipped;
        private int perPage;
        private int page;
        private int pageCount;
    }
}
