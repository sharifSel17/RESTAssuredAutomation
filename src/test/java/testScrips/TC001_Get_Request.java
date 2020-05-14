package testScrips;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class TC001_Get_Request {
    @Test
    public void getWeatherDetails(){
        //specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        //Request object
        RequestSpecification httpRequest = RestAssured.given();
        //Response object
        Response response = httpRequest.request(Method.GET,"/Dhaka");
        //Print request in the console to the result
        String getBodyResponse = response.getBody().asString();
        System.out.println(getBodyResponse);

        //status code varification

    }
}
