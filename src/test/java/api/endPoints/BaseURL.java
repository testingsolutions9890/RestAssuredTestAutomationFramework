package api.endPoints;

public class BaseURL {

    public static String base_url = "http://pristinetechsolutions.in";
    // User Module
    public  static String create_user = base_url+"/api/user";
    public  static String get_user = base_url+"/api/user/{userId}";
    public  static String update_user = base_url+"/api/user/{userId}";
    public  static String delete_user = base_url+"/api/user/delete/{userId}";


}
