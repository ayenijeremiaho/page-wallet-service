package com.page_assessment_wallet_system.http;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

import java.util.Map;

public interface HttpService {
    HttpResponse<JsonNode> post(Map<String, String> headerList, String jsonPayload, String url);

    HttpResponse<JsonNode> get(Map<String, String> headerList, Map<String, Object> params, String url);
}
