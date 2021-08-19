package com.page_assessment_wallet_system.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private String responseCode;
    private String status;
    private Object data;
}

