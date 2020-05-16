package dataDrivenTestUsingRestAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_Send_Single_Data_To_Server {
    @Test
    public void sendSingleDataToServer(){
        //specify base URI
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        //request payload sending along with post request
        JSONObject requestParam = new JSONObject();
        requestParam.put("name","honeyLink");
        requestParam.put("salary","PiyuLink");
        requestParam.put("age","30");

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(requestParam.toJSONString());

        //Response object
        Response response = httpRequest.request(Method.POST,"/create");

        //Print request in the console to the result
        String getBodyResponse = response.getBody().asString();
        Assert.assertEquals(getBodyResponse.contains("honeyLink"),true);
        Assert.assertEquals(getBodyResponse.contains("PiyuLink"),true);
        Assert.assertEquals(getBodyResponse.contains("30"),true);

        //status code verification
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode,200);
    }
}
