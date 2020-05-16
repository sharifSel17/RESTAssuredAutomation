package dataDrivenTestUsingRestAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.UtilityClass;

import java.io.IOException;

public class TC003_SendDataThroughExcel{

    @Test(dataProvider = "empdataprovider")
    void SendDataThroughExcel(String empName, String empSalary, String empAge){
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
    Object[][] getData() throws IOException {
        //read data from excel
        String path = System.getProperty("user.dir")+"\\src\\test\\java\\utilities\\empdata.xlsx";
        System.out.println(path);
        int rowNum = UtilityClass.getRowCount(path,"Sheet1");
        int colCount = UtilityClass.getCellCount(path,"Sheet1",1);

        String empdata[][] = new String[rowNum][colCount];
        for (int i =1;i<=rowNum;i++){
            for (int j = 0 ;j<colCount;j++){
                empdata[i-1][j] = UtilityClass.getCellData(path,"Sheet1",i,j);
            }
        }
        return (empdata);
    }
}
