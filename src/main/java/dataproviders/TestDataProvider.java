package dataproviders;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.specification.RequestSpecification;
import model.RequestResponse;
import org.codehaus.groovy.control.io.ReaderSource;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.module.ResolutionException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestDataProvider {

    public static final String json1 = "src/main/resources/jsons/json1.json";
    public static final String json2 = "src/main/resources/jsons/json2.json";


    public static final String[] TEST_405_API_JSON_FILE_PATHS = {
            json1, json2
    };

    @DataProvider(name="all-apis", parallel = true)
    public static Object[] getAllApis() throws IOException {
        return readAndReturnApis(TEST_405_API_JSON_FILE_PATHS);
    }

    public static Object[] readAndReturnApis(String[] filePath) throws IOException {
        RequestResponse[] requestResponses = new RequestResponse[filePath.length];
        for(String oneFile : filePath){
            requestResponses = readFileContent(oneFile, RequestResponse[].class);
        }
        for (Object r : requestResponses){
            System.out.println("++++++++++++==================+++++++++++");
            System.out.println(r.toString());
        }
        return requestResponses;
    }

    public static<T> T readFileContent(String filePath, Class<T> clazz) throws IOException {
        //read json file data to String
        byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonData, clazz);
    }

}
