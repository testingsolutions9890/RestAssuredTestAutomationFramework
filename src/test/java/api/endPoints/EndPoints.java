package api.endPoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.io.File;
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

    // Create Activity
    public static Response createActivity(JSONObject jsonObject){
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(jsonObject.toString())
                        .when()
                        .post(BaseURL.create_Activity);
        return response;
    }

    public static Response getActivity(String userId){
        Response response =
                given()
                        .pathParam("userId",userId)
                        .when()
                        .get(BaseURL.getAllCustomActivities);
        return response;
    }

    public static Response assignCustomActivitySchedule(JSONObject jsonObject){
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(jsonObject.toString())
                        .when()
                        .post(BaseURL.Assign_Custom_Activity_Schedule);
        return response;
    }

    // Add Achievement
    public static Response addChildAchievement(JSONObject jsonObject)
    {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(jsonObject.toString())
                        .when()
                        .post(BaseURL.Add_Child_Achievement);
        return response;
    }

    public static Response getChildAchievementByChildID(String child_Id){
        Response response =
                given()
                        .pathParam("child_Id",child_Id)
                        .when()
                        .get(BaseURL.get_Child_Achievement_By_ChildID);
        return response;
    }

    public static Response updateChildAchievement(JSONObject jsonObject,String child_achievement_id){
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .pathParam("child_achievement_id",child_achievement_id)
                        .body(jsonObject.toString())
                        .when()
                        .put(BaseURL.update_Child_Achievement);
        return response;

    }

    public static Response getAchievementCategory(){
        Response response =
                given()
                       // .pathParam("achievement_Id",achievement_Id)
                        .when()
                        .get(BaseURL.get_Achievement_Category);
        return response;
    }

    public static Response getAchievementProficiency(){
        Response response =
                given()
                        // .pathParam("achievement_Id",achievement_Id)
                        .when()
                        .get(BaseURL.get_Achievement_Proficiency);
        return response;
    }

    public static Response getAchievementFromAllCategory(){
        Response response =
                given()
                        // .pathParam("achievement_Id",achievement_Id)
                        .when()
                        .get(BaseURL.get_Achievement_From_All_Category);
        return response;
    }

    public static Response addMediaInAchievement(ContentType contentType,String file_path,String parent_id,String comment,String ai_confidence,String ai_approval,String created_by,String revision,String child_achievement_id,String child_id)
    {
        String mimeType;
        if (file_path.endsWith(".png") || file_path.endsWith(".jpg") || file_path.endsWith(".jpeg")) {
            mimeType = "image/png";  // You can adjust based on the actual file extension
        } else if (file_path.endsWith(".mp4") || file_path.endsWith(".avi")) {
            mimeType = "video/mp4";  // Use appropriate MIME type for video
        } else {
            throw new IllegalArgumentException("Unsupported file type");
        }

        Response response =
                given()
                        .contentType(contentType)
                        .multiPart("child_achievement_id",child_achievement_id)
                        .multiPart("parent_id",parent_id)
                        .multiPart("child_id",child_id)
                        .multiPart("comment",comment)
                        .multiPart("ai_confidence",ai_confidence)
                        .multiPart("ai_approval",ai_approval)
                        .multiPart("created_by",created_by)
                        .multiPart("revision",revision)
                        .multiPart("file1",new File(file_path),mimeType)
                     //   .multiPart("file1", file_path, "image/png")
                        .when()
                        .post(BaseURL.Add_Media_In_Achievement);
        return response;
    }

}
