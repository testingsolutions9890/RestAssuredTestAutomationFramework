package api.test.userModule;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static api.endPoints.EndPoints.*;
import static io.restassured.RestAssured.given;

public class UserModule_CRUD_TestDataInTC {
    JSONObject userDetails = new JSONObject();
    @Test(priority=0)
    void testCreateUser(ITestContext context) {

        userDetails.put("first_name", "Shre55");
        userDetails.put("last_name", "Samart55");
        userDetails.put("username", "any55@gmail.com");
        userDetails.put("contact_number", "550063543453");
        userDetails.put("contact_country_code", "1");
        userDetails.put("password", "gerye");
        userDetails.put("user_type_id", 1);
        userDetails.put("otp", "null");
        userDetails.put("is_archived", false);
        userDetails.put("created_by", "mayur");
        System.out.println(userDetails.toString());

        Response createUserResponse = createUser(userDetails);

        System.out.println("Create User>>>" + createUserResponse.getBody().asString());
        Assert.assertEquals(createUserResponse.getStatusCode(), 201, "Verifying Status Code");

        JsonPath jsonPathEvaluator = createUserResponse.jsonPath();
        Assert.assertEquals(jsonPathEvaluator.get("data.first_name"), userDetails.get("first_name"), "Verifying first_name");
        Assert.assertEquals(jsonPathEvaluator.get("data.last_name"), userDetails.get("last_name"), "Verifying last_name");
        Assert.assertEquals(jsonPathEvaluator.get("data.username"), userDetails.get("username"), "Verifying username");
        Assert.assertEquals(jsonPathEvaluator.get("data.contact_number"), userDetails.get("contact_number"), "Verifying contact_number");
        Assert.assertEquals(jsonPathEvaluator.get("data.contact_country_code"), userDetails.get("contact_country_code"), "Verifying contact_country_code");

        String userID = jsonPathEvaluator.get("data.user_id");
        Assert.assertNotNull(userID, "Verifying user ID is not null");
        context.setAttribute("userID", userID);
        System.out.println("userID is " + userID);
        Response getUserResponse = getUser(userID);
        Assert.assertEquals(getUserResponse.getStatusCode(), 200, "Verifying Status Code");

        System.out.println("get UserByID>>>" + getUserResponse.getBody().asString());
        JsonPath jsonPathEvaluatorGetUser = createUserResponse.jsonPath();
        Assert.assertEquals(jsonPathEvaluatorGetUser.get("data.first_name"), userDetails.get("first_name"), "Verifying first_name");
        Assert.assertEquals(jsonPathEvaluatorGetUser.get("data.last_name"), userDetails.get("last_name"), "Verifying last_name");
        Assert.assertEquals(jsonPathEvaluatorGetUser.get("data.username"), userDetails.get("username"), "Verifying username");
        Assert.assertEquals(jsonPathEvaluatorGetUser.get("data.contact_number"), userDetails.get("contact_number"), "Verifying contact_number");
        Assert.assertEquals(jsonPathEvaluatorGetUser.get("data.contact_country_code"), userDetails.get("contact_country_code"), "Verifying contact_country_code");


    }

    @Test(priority=1,dependsOnMethods = {"testCreateUser"})
    void testUpdateUser(ITestContext context) {

        userDetails.put("password", "Mayur1234");
        userDetails.put("updated_by", "Mayur");
        System.out.println(userDetails.toString());

        String Customer_id = (String) context.getAttribute("userID");
        Response updateUserResponse = updateUser(userDetails, Customer_id);

        System.out.println("Update User>>>" + updateUserResponse.getBody().asString());
        Assert.assertEquals(updateUserResponse.getStatusCode(), 200, "Verifying Status Code");

        JsonPath jsonPathEvaluator = updateUserResponse.jsonPath();
        Assert.assertEquals(jsonPathEvaluator.get("data.first_name"), userDetails.get("first_name"), "Verifying first_name");
        Assert.assertEquals(jsonPathEvaluator.get("data.last_name"), userDetails.get("last_name"), "Verifying last_name");
        Assert.assertEquals(jsonPathEvaluator.get("data.contact_country_code"), userDetails.get("contact_country_code"), "Verifying contact_country_code");

        String userID = jsonPathEvaluator.get("data.user_id").toString();
        Assert.assertNotNull(userID, "Verifying user ID is not null");
        Assert.assertEquals(userID, Customer_id, "Verifying if user ID is same");
        Assert.assertEquals(jsonPathEvaluator.get("data.updated_by"), userDetails.get("updated_by"), "Verifying  updated by field");
        System.out.println("userID is " + userID);
        Response getUserResponse = getUser(userID);
        Assert.assertEquals(getUserResponse.getStatusCode(), 200, "Verifying Status Code");

        System.out.println("get UserByID>>>" + getUserResponse.getBody().asString());
        JsonPath jsonPathEvaluatorGetUser = getUserResponse.jsonPath();
        Assert.assertEquals(jsonPathEvaluatorGetUser.get("data.updated_by"), userDetails.get("updated_by"), "Verifying  updated by field");
    }

    @Test(priority=2,dependsOnMethods = {"testCreateUser"})
    void testDeleteUser(ITestContext context) {
        String Customer_id = (String) context.getAttribute("userID");
        Response deleteUserResponse = deleteUser(Customer_id);

        System.out.println("Delete User>>>" + deleteUserResponse.getBody().asString());
        Assert.assertEquals(deleteUserResponse.getStatusCode(), 200, "Verifying Status Code");

        JsonPath jsonPathEvaluator = deleteUserResponse.jsonPath();
        String userID = jsonPathEvaluator.get("data.user_id").toString();
        Assert.assertNotNull(userID, "Verifying user ID is not null");
        Assert.assertEquals(userID, Customer_id, "Verifying if user ID is same");

        Boolean is_archived = jsonPathEvaluator.get("data.is_archived");
        Assert.assertTrue(is_archived);

        Response getUserResponse = getUser(userID);
        Assert.assertEquals(getUserResponse.getStatusCode(), 200, "Verifying Status Code");

        System.out.println("get UserByID>>>" + getUserResponse.getBody().asString());
        JsonPath jsonPathEvaluatorGetUser = getUserResponse.jsonPath();
        Assert.assertTrue(jsonPathEvaluatorGetUser.get("data.is_archived"));
    }
}
