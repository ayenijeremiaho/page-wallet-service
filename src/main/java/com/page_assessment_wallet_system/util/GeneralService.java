package com.page_assessment_wallet_system.util;

import com.google.gson.Gson;
import com.page_assessment_wallet_system.controller.dto.Response;
import com.page_assessment_wallet_system.exception.WalletException;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class GeneralService {

    public static double getAsNaira(double amount){
        return amount / 100;
    }

    public static double getAsKobo(double amount){
        return amount * 100;
    }

    public static String prepareRequest(Object o, Gson gson) {
        return gson.toJson(o);
    }

    public static String getResponseAsString(HttpResponse<JsonNode> response) {
        if (Objects.nonNull(response) && Objects.nonNull(response.getBody())) {
            String body = response.getBody().toPrettyString();
            log.info(body);
            return body;
        }
        throw new WalletException("Error from host");
    }

    public static Response prepareResponse(String responseCode, String status) {
        return getResponse(responseCode, status, null);
    }

    public static Response prepareResponse(Object data) {
        return getResponse("00", "Success", data);
    }

    private static Response getResponse(String responseCode, String status, Object data) {
        return Response.builder()
                .responseCode(responseCode)
                .status(status)
                .data(data)
                .build();
    }
}
