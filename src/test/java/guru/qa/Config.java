package guru.qa;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.http.ContentType.JSON;


public class Config {

   @BeforeAll
    public static void configure(){

        RequestSpecification reqSpecification = new RequestSpecBuilder()
                .addHeader("Content-Type","application/json")
                .setContentType(JSON)
                .setBaseUri("https://reqres.in/")
                .build();

        RestAssured.requestSpecification = reqSpecification;
    }

}
