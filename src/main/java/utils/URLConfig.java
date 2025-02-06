package utils;

import lombok.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
@Data
public class URLConfig {
    private String validUserURL;
    private String invalidUserURL;

    public URLConfig() throws IOException {
        Properties config = new Properties();
        InputStream input = new FileInputStream("src/main/resources/application.properties");
        config.load(input);
        this.validUserURL = config.getProperty("validUserURL");
        this.invalidUserURL = config.getProperty("invalidUserURL");
    }
}
