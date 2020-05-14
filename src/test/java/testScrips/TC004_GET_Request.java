package testScrips;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC004_GET_Request {
    @Test
    public void getAllHeaders(){
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
        Headers allHeaders = response.headers();//capture all headers from response.
        for (Header headers : allHeaders) {//foreach will return us key value pares.
            System.out.println(headers.getName()+":    "+headers.getValue());
        }


        //
    }
}
