package basicRestAssuredTestScrips;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC003_GET_Request {
    @Test
    public void GetHeader(){
        //specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        //Request object
        RequestSpecification httpRequest = RestAssured.given();
        //Response object
        Response response = httpRequest.request(Method.GET,"/Dhaka");
        //Print request in the console to the result
        String getBodyResponse = response.getBody().asString();
        System.out.println(getBodyResponse);

        //Capture details of header
        String contentType = response.header("Content-Type");//capture content type of header
        System.out.println(contentType);
        Assert.assertEquals(contentType,"application/json");
    }
}
