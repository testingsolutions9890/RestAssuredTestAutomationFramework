package api.test.userModule;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static api.endPoints.EndPoints.createUser;
import static api.endPoints.EndPoints.getUser;
import static io.restassured.RestAssured.given;

public class CreateUser {

    @Test
    void testCreateUser(ITestContext context) {
        int id;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("first_name", "Shree8");
        jsonObject.put("last_name", "Samarth8");
        jsonObject.put("username", "any8@gmail.com");
        jsonObject.put("contact_number", "885263543453");
        jsonObject.put("contact_country_code", "1");
        jsonObject.put("password", "gerye");
        jsonObject.put("user_type_id", 1);
        jsonObject.put("otp", "null");
        jsonObject.put("is_archived", false);
        jsonObject.put("created_by", "mayur");
        System.out.println(jsonObject.toString());

        Response createUserResponse = createUser(jsonObject);

        System.out.println("Create User>>>" + createUserResponse.getBody().asString());
        Assert.assertEquals(createUserResponse.getStatusCode(),201,"Verifying Status Code");

        JsonPath jsonPathEvaluator = createUserResponse.jsonPath();
        Assert.assertEquals(jsonPathEvaluator.get("data.first_name"),jsonObject.get("first_name"),"Verifying first_name");
        Assert.assertEquals(jsonPathEvaluator.get("data.last_name"),jsonObject.get("last_name"),"Verifying last_name");
        Assert.assertEquals(jsonPathEvaluator.get("data.username"),jsonObject.get("username"),"Verifying username");
        Assert.assertEquals(jsonPathEvaluator.get("data.contact_number"),jsonObject.get("contact_number"),"Verifying contact_number");
        Assert.assertEquals(jsonPathEvaluator.get("data.contact_country_code"),jsonObject.get("contact_country_code"),"Verifying contact_country_code");

        String userID = jsonPathEvaluator.get("data.user_id");
        Assert.assertNotNull(userID,"Verifying user ID is not null");
        System.out.println("userID is " + userID);
        Response getUserResponse = getUser(userID);
        Assert.assertEquals(getUserResponse.getStatusCode(),200,"Verifying Status Code");

        System.out.println("get UserByID>>>" + getUserResponse.getBody().asString());
        JsonPath jsonPathEvaluatorGetUser = createUserResponse.jsonPath();
        Assert.assertEquals(jsonPathEvaluatorGetUser.get("data.first_name"),jsonObject.get("first_name"),"Verifying first_name");
        Assert.assertEquals(jsonPathEvaluatorGetUser.get("data.last_name"),jsonObject.get("last_name"),"Verifying last_name");
        Assert.assertEquals(jsonPathEvaluatorGetUser.get("data.username"),jsonObject.get("username"),"Verifying username");
        Assert.assertEquals(jsonPathEvaluatorGetUser.get("data.contact_number"),jsonObject.get("contact_number"),"Verifying contact_number");
        Assert.assertEquals(jsonPathEvaluatorGetUser.get("data.contact_country_code"),jsonObject.get("contact_country_code"),"Verifying contact_country_code");


    }
}
