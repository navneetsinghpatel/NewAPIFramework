package utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.HashMap;

public class RestUtils {

    private String baseURI = "https://restful-booker.herokuapp.com/";
    private String loginURL = "api/login";

    public Response get(String endPoint, HashMap<String, String> auth){
        HashMap<String, String> headers = new HashMap<>();
        headers.put("requestid", String.valueOf(Math.random()));
        RequestSpecification req = getRequestSpecification(headers, baseURI, "application/json", null);
        return req./*auth().oauth2(getAuth(req, auth)).*/get(endPoint);
    }

    public Response post(String endPoint, String payload){
        HashMap<String, String> headers = new HashMap<>();
        //headers.put("requestid", String.valueOf(Math.random()));
        RequestSpecification req = getRequestSpecification(headers, baseURI, "application/json", payload);
        return req.post(endPoint);
    }

    public RequestSpecification getRequestSpecification(HashMap<String, String> headers, String baseURI, String contentType, String body){
        RequestSpecification requestSpecification = RestAssured.given()
                .baseUri(baseURI)
                .headers(headers)
                .contentType(contentType);
        if( body!= null) {
            requestSpecification.body(body);
        }
        return requestSpecification;
    }

    private String getAuth(RequestSpecification rs, HashMap<String, String> auth) {
        String authToken;
        JSONObject cred = new JSONObject(auth);
        Response response = rs.body(cred.toString()).post(loginURL);
        JsonPath jsonPathEvaluator = response.jsonPath();
        authToken = jsonPathEvaluator.get("token");
        System.out.println("AuthToken: " + authToken);
        return authToken;
    }


}
