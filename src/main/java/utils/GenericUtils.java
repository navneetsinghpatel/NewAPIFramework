package utils;

import io.restassured.response.Response;

public class GenericUtils {

    public static<T> T getPojoResponse(Response response, Class<T> clazz){
        return response.as(clazz);
    }
}
