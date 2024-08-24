package api.endPoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.Locale;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.*;

/*
created to perform the CRUD operations
create, read/get, update, delete
*/
public class EndPoints {

    public static ResourceBundle config(){
        ResourceBundle resourceBundle =ResourceBundle.getBundle("BaseURL.properties", Locale.US); //load the properties file
        return resourceBundle;
    }


    public static Response createUser( JSONObject jsonObject){
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(jsonObject.toString())
                        .when()
                        .post(BaseURL.create_user);
        //.post(config().getString("create_user"));
        return response;
    }
    public static Response getUser(String userId){
        Response response =
                given()
                        .pathParam("userId",userId)
               .when()
                        //.get(config().getString("get_user"));
                        .get(BaseURL.get_user);
        return response;
    }

    public static Response updateUser(JSONObject jsonObject,String userId){
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .pathParam("userId",userId)
                        .body(jsonObject.toString())
               .when()
                        //.put(config().getString("update_user"));
                        .put(BaseURL.update_user);
        return response;
    }

    public static Response deleteUser(String userId){
        Response response =
                given()
                        .pathParam("userId",userId)
               .when()
                        //.delete(config().getString("delete_user"));
                        .put(BaseURL.delete_user);
        return response;
    }
}
