package com.page_assessment_wallet_system.paystack.dto;

import com.page_assessment_wallet_system.util.GeneralService;
import lombok.Data;

@Data
public class BalanceEnquiryResponse {
    private boolean status;
    private String message;
    private BalanceEnquiryResponseData data;

    @Data
    public static class BalanceEnquiryResponseData {
        private int total_transactions;
        private double total_volume;
        private int unique_customers;

        //defaults kobo volume to Naira
        public double getTotal_volume() {
            return GeneralService.getAsNaira(total_volume);
        }

    }

}
