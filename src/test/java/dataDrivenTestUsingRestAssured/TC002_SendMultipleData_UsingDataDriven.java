package dataDrivenTestUsingRestAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC002_SendMultipleData_UsingDataDriven {

    @Test(dataProvider = "empdataprovider")
    public void SendDataUsingDataDriven(String empName, String empSalary, String empAge){
        //specify base URI
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        //request payload sending along with post request
        JSONObject requestParam = new JSONObject();
        requestParam.put("name",empName);
        requestParam.put("salary",empSalary);
        requestParam.put("age",empAge);

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(requestParam.toJSONString());

        //Response object
        Response response = httpRequest.request(Method.POST,"/create");

        //Print request in the console to the result
        String getBodyResponse = response.getBody().asString();
        System.out.println("Employees Details : "+getBodyResponse);
        Assert.assertEquals(getBodyResponse.contains(empName),true);
        Assert.assertEquals(getBodyResponse.contains(empSalary),true);
        Assert.assertEquals(getBodyResponse.contains(empAge),true);

        //status code verification
        int statusCode = response.statusCode();
        System.out.println("Status Code :"+statusCode);
        Assert.assertEquals(statusCode,200);
    }

    @DataProvider(name = "empdataprovider")
    Object[][] getData(){
        String [][]empData = {{"abc566","343455","23"},{"xyx7777","56656","34"},{"dfdfdfs","34434","45"}};
        return (empData);
    }
}
