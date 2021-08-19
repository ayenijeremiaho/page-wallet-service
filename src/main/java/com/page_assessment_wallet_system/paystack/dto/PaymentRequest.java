package com.page_assessment_wallet_system.paystack.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentRequest {
    private String email;
    private String amount; // in kobo
    private String[] channels = {"card", "bank", "ussd", "qr", "mobile_money", "bank_transfer"};
}
