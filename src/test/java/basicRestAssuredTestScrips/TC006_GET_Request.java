package basicRestAssuredTestScrips;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class TC006_GET_Request {
    @Test
    public void valuesFromEachNodeInJson(){
        //specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        //Request object
        RequestSpecification httpRequest = RestAssured.given();
        //Response object
        Response response = httpRequest.request(Method.GET,"/Dhaka");
        JsonPath jsonPath = response.jsonPath();

        /*System.out.println(jsonPath.get("City"));
        System.out.println(jsonPath.get("Temperature"));
        System.out.println(jsonPath.get("Humidity"));
        System.out.println(jsonPath.get("WeatherDescription"));
        System.out.println(jsonPath.get("WindSpeed"));
        System.out.println(jsonPath.get("WindDirectionDegree"));*/
    }
}
