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


}
