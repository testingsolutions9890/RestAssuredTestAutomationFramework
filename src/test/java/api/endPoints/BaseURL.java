package api.endPoints;

public class BaseURL {

    public static String base_url = "https://pristinetechsolutions.in";
    // User Module
    public  static String create_user = base_url+"/api/user";
    public  static String get_user = base_url+"/api/user/{userId}";
    public  static String update_user = base_url+"/api/user/{userId}";
    public  static String delete_user = base_url+"/api/user/delete/{userId}";

    // create Activity
    public  static String create_Activity = base_url+"/api/createCustomActivity";
    public  static String getAllCustomActivities = base_url+"/api/getCustomActivities/{userId}";
    public  static String Assign_Custom_Activity_Schedule = base_url+"/api/assign/assignCustomActivitySchedule";

    // Add Achievement
    public  static String Add_Child_Achievement = base_url+"/api/child-achievement";
    public  static String get_Child_Achievement_By_ChildID = base_url+"/api/child-achievement/child/{child_Id}?status=approved";
    public  static String update_Child_Achievement = base_url+"/api/child-achievement/{child_achievement_id}";
    public  static String get_Achievement_Category = base_url+"/api/get-achievement-category";
    public  static String get_Achievement_Proficiency = base_url+"/api/get-achievement-proficiency";
    public  static String get_Achievement_From_All_Category = base_url+"/api/get-achievement";
    public  static String Add_Media_In_Achievement = base_url+"/api/achievement-media";








}
