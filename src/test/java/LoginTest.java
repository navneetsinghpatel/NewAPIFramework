import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion;
import io.restassured.response.Response;
import model.UsersResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RestUtils;
import utils.URLConfig;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import static dataproviders.TestDataProvider.*;
import static utils.GenericUtils.getPojoResponse;


public class LoginTest {

    RestUtils restUtils = new RestUtils();
    URLConfig config = new URLConfig();

    public LoginTest() throws IOException {
    }

    @Test(enabled = false)
    public void loginTest() throws IOException {

        HashMap<String, String> creds = new HashMap<>();
        creds.put("email", "eve.holt@reqres.in");
        creds.put("password", "cityslicka");
        Response response = restUtils.get(config.getValidUserURL(), creds);
        UsersResponse response1 = getPojoResponse(response, UsersResponse.class);
        System.out.println("Before : ");
        response1.getData().forEach(System.out::println);
        Collections.sort(response1.getData(), Comparator.comparing(UsersResponse.Data::getFirst_name).reversed());
        System.out.println("After : ");
        response1.getData().forEach(System.out::println);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(enabled = false)
    public void create(){
        HashMap<String, String> payload = new HashMap<>();
        payload.put("name", "Navneet");
        payload.put("job", "Supreme Leader");
        System.out.println("Valid URL: "+ config.getValidUserURL());
        Response response = restUtils.post(config.getValidUserURL(), new JSONObject(payload).toString());
        System.out.println("Response as Body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Test(enabled = false)
    public void getUser(){
        HashMap<String, String> creds = new HashMap<>();
        creds.put("email", "eve.holt@reqres.in");
        creds.put("password", "cityslicka");
        System.out.println("Valid URL: "+ config.getInvalidUserURL());
        Response response = restUtils.get(config.getInvalidUserURL(), creds);
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(enabled = true)
    public void createBooking(){
        HashMap<String, String> payload = new HashMap<>();
        System.out.println("Valid URL: "+ config.getValidUserURL());
        Response response = restUtils.post(config.getValidUserURL(), new JSONObject(payload).toString());
        System.out.println("Response as Body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 201);
    }
}
