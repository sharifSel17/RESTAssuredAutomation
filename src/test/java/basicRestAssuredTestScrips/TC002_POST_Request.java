package basicRestAssuredTestScrips;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_POST_Request {
    @Test
    public void sendRequest(){
        //specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";
        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        //request payload sending along with post request
        JSONObject requestParam = new JSONObject();
        requestParam.put("FirstName","Johnkl");
        requestParam.put("LastName","Uddinpo");
        requestParam.put("UserName","jadam345");
        requestParam.put("Password","admin12344");
        requestParam.put("Email","abc89@gmail.com");

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(requestParam.toJSONString());

        //Response object
        Response response = httpRequest.request(Method.POST,"/register");

        //Print request in the console to the result
        String getBodyResponse = response.getBody().asString();
        System.out.println(getBodyResponse);

        //status code verification
        int statusCode = response.statusCode();
        System.out.println("Status code : "+statusCode);
        Assert.assertEquals(statusCode,201);

        //success code validation
        String successMessage = response.jsonPath().get("SuccessCode");
        Assert.assertEquals(successMessage,"OPERATION_SUCCESS");

    }
}
