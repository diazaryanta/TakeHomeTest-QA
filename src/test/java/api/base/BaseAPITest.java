package api.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import utils.ConfigReader;

public class BaseAPITest {
    protected RequestSpecification baseRequestSpec;

    @BeforeClass
    public void setup() {
        String baseUrl = ConfigReader.getProperty("api.base.url");
        String apiKey = ConfigReader.getProperty("api.key");

        RestAssured.baseURI = baseUrl;

        baseRequestSpec = new RequestSpecBuilder()
                .setContentType("application/json")
                .setAccept("application/json")
                .addHeader("x-api-key", apiKey)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }
}