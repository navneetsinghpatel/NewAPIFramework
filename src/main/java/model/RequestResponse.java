package model;

import lombok.Data;

@Data
public class RequestResponse {
    @Data
    public static class InputRequest {
        String url;
        String method;
        String payload;
        String headers;
    }

    @Data
    public static class ExpectedResponse {
        Integer statusCode;
        String body;
    }

    @Data
    public static class Role {
        String roleType;
    }

    InputRequest inputRequest;
    ExpectedResponse expectedResponse;
    Role role;
}