package basicRestAssuredTestScrips;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC007_GET_Request {
    @Test
    public void requestAuthorization(){
        //specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";
        //Basic authentication
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("ToolsQA");
        authScheme.setPassword("TestPassword");
        RestAssured.authentication = authScheme;
        //Request object
        RequestSpecification httpRequest = RestAssured.given();
        //Response object
        Response response = httpRequest.request(Method.GET,"/");
        String getBodyResponse = response.getBody().asString();
        System.out.println(getBodyResponse);

        //status code verification
        int statusCode = response.statusCode();
        System.out.println("Status code : "+statusCode);
        Assert.assertEquals(statusCode,200);
    }
}
