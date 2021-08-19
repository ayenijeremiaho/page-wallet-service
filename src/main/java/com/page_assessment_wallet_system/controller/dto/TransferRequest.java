package com.page_assessment_wallet_system.controller.dto;

import lombok.Data;

@Data
public class TransferRequest {
    private String email;
    private String amount; // in kobo
}
