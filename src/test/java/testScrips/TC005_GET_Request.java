package testScrips;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC005_GET_Request {
    //some times response body are not correct instead of header is correct.
    //in that case we need to validation response body
    @Test
    public void validationJSONResponse(){
        //specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        //Request object
        RequestSpecification httpRequest = RestAssured.given();
        //Response object
        Response response = httpRequest.request(Method.GET,"/Dhaka");
        //Print request in the console to the result
        String getBodyResponse = response.getBody().asString();
        System.out.println(getBodyResponse);

        //validating response body
        Assert.assertEquals(getBodyResponse.contains("Dhaka"),true);
    }
}
