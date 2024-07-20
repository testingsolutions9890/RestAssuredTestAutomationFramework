package api.test.userModule;

import api.Utilities.DataProvider;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static api.endPoints.EndPoints.createUser;

public class UserModule_CRUD_TestDataFromExcel {

    @Test(priority = 1, dataProvider = "UserData", dataProviderClass = DataProvider.class)
    void testDatafromExcel(String first_name, String last_name, String username, String contact_number, String contact_country_code, String password) {
        JSONObject userDetails = new JSONObject();
        userDetails.put("first_name", last_name);
        userDetails.put("last_name", last_name);
        userDetails.put("username", username);
        userDetails.put("contact_number", contact_number);
        userDetails.put("contact_country_code", contact_country_code);
        userDetails.put("password", password);
        userDetails.put("user_type_id", 1);
        userDetails.put("otp", "null");
        userDetails.put("is_archived", false);
        userDetails.put("created_by", "mayur");
        System.out.println(userDetails);
        Response createUserResponse = createUser(userDetails);
        createUserResponse.then().log().all();

    }
}
