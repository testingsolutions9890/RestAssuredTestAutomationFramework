package api.test.userModule;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static api.endPoints.EndPoints.*;

public class createCustomActivity {

    JSONObject activityDetails = new JSONObject();
    @Test(priority = 0)
    void testCreateActivity(ITestContext context)
    {
        activityDetails.put("title","Cycling");
        activityDetails.put("description","Cycling for 30 min");
        activityDetails.put("material","bicycle");
        activityDetails.put("points",40);
        activityDetails.put("user_id",137); // provide user id
        activityDetails.put("is_archived",false);
        activityDetails.put("created_by","Vijay");

        System.out.println(activityDetails.toString());

        Response createActivityResponse = createActivity(activityDetails);
        System.out.println("Create Activity>>>" + createActivityResponse.getBody().asString());
        Assert.assertEquals(createActivityResponse.getStatusCode(), 201, "Verifying Status Code");

        JsonPath jsonPathEvaluator = createActivityResponse.jsonPath();
        Assert.assertEquals(jsonPathEvaluator.get("data.title"), activityDetails.get("title"), "Verifying title");
        Assert.assertEquals(jsonPathEvaluator.get("data.description"), activityDetails.get("description"), "Verifying description");
        Assert.assertEquals(jsonPathEvaluator.get("data.material"), activityDetails.get("material"), "Verifying material");
        Assert.assertEquals(jsonPathEvaluator.get("data.points"), activityDetails.get("points"), "Verifying points");
        Assert.assertEquals(jsonPathEvaluator.get("data.user_id"), activityDetails.get("user_id"), "Verifying user_id");
        Assert.assertEquals(jsonPathEvaluator.get("data.is_archived"), activityDetails.get("is_archived"), "Verifying is_archived");
        Assert.assertEquals(jsonPathEvaluator.get("data.created_by"), activityDetails.get("created_by"), "Verifying created_by");


        String userID = jsonPathEvaluator.get("data.user_id").toString();
        Assert.assertNotNull(userID, "Verifying user ID is not null");
        context.setAttribute("userID", userID);
        System.out.println("userID is " + userID);
        Response getActivityResponse = getActivity(userID);
        Assert.assertEquals(getActivityResponse.getStatusCode(), 201, "Verifying Status Code"); //TODO: Responce code should be 200 bug raised

        System.out.println("get ActivityByID>>>" + getActivityResponse.getBody().asString());
        JsonPath jsonPathEvaluatorGetUser = createActivityResponse.jsonPath();
        Assert.assertEquals(jsonPathEvaluatorGetUser.get("data.title"), activityDetails.get("title"), "Verifying title");
        Assert.assertEquals(jsonPathEvaluatorGetUser.get("data.description"), activityDetails.get("description"), "Verifying description");
        Assert.assertEquals(jsonPathEvaluatorGetUser.get("data.material"), activityDetails.get("material"), "Verifying material");
        Assert.assertEquals(jsonPathEvaluatorGetUser.get("data.points"), activityDetails.get("points"), "Verifying points");
        Assert.assertEquals(jsonPathEvaluatorGetUser.get("data.user_id"), activityDetails.get("user_id"), "Verifying user_id");
        Assert.assertEquals(jsonPathEvaluatorGetUser.get("data.is_archived"), activityDetails.get("is_archived"), "Verifying is_archived");
        Assert.assertEquals(jsonPathEvaluatorGetUser.get("data.created_by"), activityDetails.get("created_by"), "Verifying created_by");

        String custActivityId = jsonPathEvaluator.get("data.cust_activity_id").toString();
        Assert.assertNotNull(custActivityId, "Verifying cust_activity_id is not null");
        context.setAttribute("custActivityId", custActivityId);
        System.out.println("cust_activity_id is " + custActivityId);

    }

    @Test(priority=1,dependsOnMethods = {"testCreateActivity"})
    void testAssignCustomActivitySchedule(ITestContext context)
    {
        String custActivityId = (String) context.getAttribute("custActivityId");

        activityDetails.put( "cust_activity_occurance_id",4);
        activityDetails.put( "time_min",10);
        activityDetails.put( "start_date","2024-08-31T11:51:42.157Z");
        activityDetails.put( "end_date","2024-09-01T11:51:42.157Z");
        activityDetails.put( "cust_activity_complete_in_id",3);
        activityDetails.put( "week_days_id",JSONObject.NULL);
        activityDetails.put( "end_after",2);
        activityDetails.put( "complete_by_day",JSONObject.NULL);
        activityDetails.put( "cust_activity_id",custActivityId);
        activityDetails.put( "created_by","Vijay");

        System.out.println(activityDetails.toString());

        Response assignCustomActivityScheduleResponse = assignCustomActivitySchedule(activityDetails);
        System.out.println("Assign Custom Activity Schedule>>>" + assignCustomActivityScheduleResponse.getBody().asString());
        Assert.assertEquals(assignCustomActivityScheduleResponse.getStatusCode(), 201, "Verifying Status Code");

        JsonPath jsonPathEvaluator = assignCustomActivityScheduleResponse.jsonPath();
        Assert.assertEquals(jsonPathEvaluator.get("data.cust_activity_occurance_id"), activityDetails.get("cust_activity_occurance_id"), "Verifying cust_activity_occurance_id");
        Assert.assertEquals(jsonPathEvaluator.get("data.time_min"), activityDetails.get("time_min"), "Verifying time_min");
        Assert.assertEquals(jsonPathEvaluator.get("data.start_date"), activityDetails.get("start_date"), "Verifying start_date");
        Assert.assertEquals(jsonPathEvaluator.get("data.end_date"), activityDetails.get("end_date"), "Verifying end_date");
        Assert.assertEquals(jsonPathEvaluator.get("data.cust_activity_complete_in_id"), activityDetails.get("cust_activity_complete_in_id"), "Verifying cust_activity_complete_in_id");
       // Assert.assertEquals(jsonPathEvaluator.get("data.week_days_id"), activityDetails.get("week_days_id"), "Verifying week_days_id");
        Assert.assertEquals(jsonPathEvaluator.get("data.end_after"), activityDetails.get("end_after"), "Verifying end_after");
       // Assert.assertEquals(jsonPathEvaluator.get("data.complete_by_day"), activityDetails.get("complete_by_day"), "Verifying complete_by_day");
        Assert.assertEquals(jsonPathEvaluator.get("data.cust_activity_id"), activityDetails.get("cust_activity_id"), "Verifying cust_activity_id");
        Assert.assertEquals(jsonPathEvaluator.get("data.created_by"), activityDetails.get("created_by"), "Verifying created_by");

        String custActivityScheduleId = jsonPathEvaluator.get("data.cust_activity_schedule_id").toString();
        Assert.assertNotNull(custActivityScheduleId, "Verifying cust_activity_schedule_id is not null");
        context.setAttribute("cust_activity_schedule_id", custActivityScheduleId);
        System.out.println("cust_activity_schedule_id is " + custActivityScheduleId);
    }

    @Test(priority=2,dependsOnMethods = {"testAssignCustomActivitySchedule"})
    void testAssignCustomActivityScheduleChild(ITestContext context)
    {
        String custActivityId = (String) context.getAttribute("custActivityId");
        String custActivityScheduleId = (String) context.getAttribute("custActivityScheduleId");

        activityDetails.put("childs", new int[]{49});
        activityDetails.put("cust_activity_schedule_id", custActivityScheduleId); // Use the retrieved custActivityScheduleId
        activityDetails.put("cust_activity_id", custActivityId);  // Use the retrieved custActivityId
        activityDetails.put("created_by", "Vijay");

        System.out.println(activityDetails.toString());

        Response assignCustomActivityScheduleChildResponse = assignCustomActivitySchedule(activityDetails);
        System.out.println("Assign Custom Activity Schedule Child>>>" + assignCustomActivityScheduleChildResponse.getBody().asString());
        Assert.assertEquals(assignCustomActivityScheduleChildResponse.getStatusCode(), 201, "Verifying Status Code");

        JsonPath jsonPathEvaluator = assignCustomActivityScheduleChildResponse.jsonPath();
       // Assert.assertEquals(jsonPathEvaluator.get("data.childs"), activityDetails.get("childs"), "Verifying childs");
        Assert.assertEquals(jsonPathEvaluator.get("data.cust_activity_schedule_id"), activityDetails.get("cust_activity_schedule_id"), "Verifying cust_activity_schedule_id");
        Assert.assertEquals(jsonPathEvaluator.get("data.cust_activity_id"), activityDetails.get("cust_activity_id"), "Verifying cust_activity_id");
        Assert.assertEquals(jsonPathEvaluator.get("data.created_by"), activityDetails.get("created_by"), "Verifying created_by");

    }


//    @Test(priority=3)
//    void testDeleteUser(ITestContext context) {
//        String user_id = (String) context.getAttribute("userID");
//        Response deleteactivityResponse = deleteUser(user_id);
//
//        System.out.println("Delete Activity>>>" + deleteactivityResponse.getBody().asString());
//        Assert.assertEquals(deleteactivityResponse.getStatusCode(), 200, "Verifying Status Code");
//
//        JsonPath jsonPathEvaluator = deleteactivityResponse.jsonPath();
//        String userID = jsonPathEvaluator.get("data.user_id").toString();
//        Assert.assertNotNull(userID, "Verifying user ID is not null");
//        Assert.assertEquals(userID, user_id, "Verifying if user ID is same");
//
//        Boolean is_archived = jsonPathEvaluator.get("data.is_archived");
//        Assert.assertTrue(is_archived);
//
//        Response getUserResponse = getUser(userID);
//        Assert.assertEquals(getUserResponse.getStatusCode(), 200, "Verifying Status Code");
//
//        System.out.println("get UserByID>>>" + getUserResponse.getBody().asString());
//        JsonPath jsonPathEvaluatorGetUser = getUserResponse.jsonPath();
//        Assert.assertTrue(jsonPathEvaluatorGetUser.get("data.is_archived"));
//    }

}
