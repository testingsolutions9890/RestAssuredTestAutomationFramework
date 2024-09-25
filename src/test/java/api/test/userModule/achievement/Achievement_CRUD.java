package api.test.userModule.achievement;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static api.endPoints.EndPoints.*;

public class Achievement_CRUD {


    JSONObject achievementDetails = new JSONObject();

    @Test(priority = 0)
    public void testAddChildAchievement(ITestContext context)
    {
        achievementDetails.put("child_id",49);
        achievementDetails.put("achievement_category_id",1);
        achievementDetails.put("achievement_id",1);
        achievementDetails.put("achievement_proficiency_id",1);
        achievementDetails.put("description","Testing Media Upload");
        achievementDetails.put("date","2024-09-13"); // today
        achievementDetails.put("points",50);
        achievementDetails.put("created_by","Vijay");
        achievementDetails.put("updated_by","Vijay");
        achievementDetails.put("is_parent_approved",true);

        System.out.println(achievementDetails.toString());

        Response addChildAchievementResponse = addChildAchievement(achievementDetails);
        System.out.println("Add Child Achievement>>>" + addChildAchievementResponse.getBody().asString());
        Assert.assertEquals(addChildAchievementResponse.getStatusCode(), 201, "Verifying Status Code");

        JsonPath jsonPathEvaluator = addChildAchievementResponse.jsonPath();
        Assert.assertEquals(jsonPathEvaluator.get("data.child_id"), achievementDetails.get("child_id"), "Verifying child_id");
        Assert.assertEquals(jsonPathEvaluator.get("data.achievement_category_id"), achievementDetails.get("achievement_category_id"), "Verifying achievement_category_id");
        Assert.assertEquals(jsonPathEvaluator.get("data.achievement_id"), achievementDetails.get("achievement_id"), "Verifying achievement_id");
        Assert.assertEquals(jsonPathEvaluator.get("data.achievement_proficiency_id"), achievementDetails.get("achievement_proficiency_id"), "Verifying achievement_proficiency_id");
        Assert.assertEquals(jsonPathEvaluator.get("data.description"), achievementDetails.get("description"), "Verifying description");
        Assert.assertEquals(jsonPathEvaluator.get("data.date"), achievementDetails.get("date"), "Verifying date");
        Assert.assertEquals(jsonPathEvaluator.get("data.points"), achievementDetails.get("points"), "Verifying points");
        Assert.assertEquals(jsonPathEvaluator.get("data.created_by"), achievementDetails.get("created_by"), "Verifying created_by");
        Assert.assertEquals(jsonPathEvaluator.get("data.updated_by"), achievementDetails.get("updated_by"), "Verifying updated_by");
        Assert.assertEquals(jsonPathEvaluator.get("data.is_parent_approved"), achievementDetails.get("is_parent_approved"), "Verifying is_parent_approved");

        String achievement_Id = jsonPathEvaluator.get("data.achievement_id").toString();
        Assert.assertNotNull(achievement_Id, "Verifying achievement_Id is not null");
        context.setAttribute("Achievement Id", achievement_Id);
        System.out.println("Achievement Id is " + achievement_Id);

        String child_Achievement_Id = jsonPathEvaluator.get("data.child_achievement_id").toString();
        Assert.assertNotNull(child_Achievement_Id, "Verifying child_achievement_id is not null");
        context.setAttribute("child_achievement_id", child_Achievement_Id);
        System.out.println("child_achievement_id is " + child_Achievement_Id);

        String achievement_Category_Id = jsonPathEvaluator.get("data.achievement_category_id").toString();
        Assert.assertNotNull(achievement_Category_Id, "Verifying achievement_category_id is not null");
        context.setAttribute("achievement_category_id", achievement_Category_Id);
        System.out.println("achievement_category_id is " + achievement_Category_Id);

        String child_id = jsonPathEvaluator.get("data.child_id").toString();
        Assert.assertNotNull(child_id, "Verifying child_id is not null");
        context.setAttribute("child_id", child_id);
        System.out.println("child_id is " + child_id);

        String Date = jsonPathEvaluator.get("data.date").toString();
        Assert.assertNotNull(child_id, "Verifying date is not null");
        context.setAttribute("Date", Date);
        System.out.println("date is " + Date);

        String created_Date = jsonPathEvaluator.get("data.created_date").toString();
        Assert.assertNotNull(child_id, "Verifying created_Date is not null");
        context.setAttribute("created_Date", created_Date);
        System.out.println("created_Date is " + created_Date);

        Response getChildAchievementByChildIDResponse = getChildAchievementByChildID(child_id);
        Assert.assertEquals(getChildAchievementByChildIDResponse.getStatusCode(), 200, "Verifying Status Code");
        System.out.println("get Achievement by child_id>>>" + getChildAchievementByChildIDResponse.getBody().asString());

        JsonPath jsonPath = getChildAchievementByChildIDResponse.jsonPath();
        List<Map<String, Object>> data = jsonPath.getList("data");
        for (int i=0; i< data.size(); i++)
        {
            Map<String, Object> element = data.get(i);
            String childAchievementId =  element.get("child_achievement_id").toString();
            if(childAchievementId.equals(child_Achievement_Id))
            {
                String description = (String) element.get("description");
                String child_Id = (String) element.get("child_id");
                String achievement_category_id = (String) element.get("achievement_category_id");
                String achievement_id = (String) element.get("achievement_id");
                String points = element.get("points").toString();
                String achievement_proficiency_id = (String) element.get("achievement_proficiency_id");
                String date = (String) element.get("date");
                String created_by = (String) element.get("created_by");
                String created_date = (String) element.get("created_date");

                context.setAttribute("achievement_proficiency_id",achievement_proficiency_id);
                context.setAttribute("achievement_id",achievement_id);

                System.out.println("Child Achievement ID: " + childAchievementId);
                System.out.println("Description: " + description);
                System.out.println("Child_Id: " + child_Id);
                System.out.println("Achievement Category Id: " + achievement_category_id);
                System.out.println("Achievement Id: " + achievement_id);
                System.out.println("Points: " + points);
                System.out.println("Achievement Proficiency Id: " + achievement_proficiency_id);
                System.out.println("Date: " + date);
                System.out.println("Created By: " + created_by);
                System.out.println("created_date: " + created_date);

                Assert.assertEquals(childAchievementId,child_Achievement_Id);
                Assert.assertEquals(description,achievementDetails.get("description"));
                Assert.assertEquals(child_Id,child_id);
                Assert.assertEquals(achievement_category_id,achievement_Category_Id);
                Assert.assertEquals(achievement_id,achievement_Id);
                Assert.assertEquals(achievement_proficiency_id,achievementDetails.get("achievement_proficiency_id").toString());
                Assert.assertEquals(date.substring(0, 10),Date);
                Assert.assertEquals(created_by,achievementDetails.get("created_by"));
                Assert.assertEquals(created_date,created_Date);
            }
        }

    }

    @Test(priority = 1, dependsOnMethods = {"testAddChildAchievement"})
    public void testUpdateMediaInAchievement(ITestContext context)
    {
    //    String file_path = "src/test/resources/testData_Media/success-quote-on-progress.png";
          String file_path = "src/test/resources/testData_Media/wallpaperflare.com_wallpaper (1).jpg";
    //    String file_path = "src/test/resources/testData_Media/Video Test.mp4";
        ContentType contentType = ContentType.MULTIPART;
        String parent_id = "47"; // fix
        String comment = "Testing Media Upload"; // you can change
        String ai_confidence= "0.95"; // fix
        String ai_approval= "false";
        String created_by= "Vijay"; // you can change
        String revision ="0";
        String child_achievement_id = (String) context.getAttribute("child_achievement_id");
        String child_id = (String) context.getAttribute("child_id");

        Response addMediaInAchievementResponse = addMediaInAchievement(contentType,file_path,parent_id,comment,ai_confidence,ai_approval,created_by,revision,child_achievement_id,child_id);
        System.out.println("Add Media In Achievement Response>>>" + addMediaInAchievementResponse.getBody().asString());
        Assert.assertEquals(addMediaInAchievementResponse.getStatusCode(), 201, "Verifying Status Code");

    }


    @Test(priority = 2, dependsOnMethods ={"testUpdateMediaInAchievement"} )
    public void testUpdateChildAchievement(ITestContext context)
    {
        String achievementCategoryId = (String) context.getAttribute("achievement_category_id");
        String achievementProficiencyId = (String) context.getAttribute("achievement_proficiency_id");
        String achievement_Id = (String) context.getAttribute("achievement_id");
        String child_achievement_id = (String) context.getAttribute("child_achievement_id");
        String child_id = (String) context.getAttribute("child_id");

        achievementDetails.put("is_parent_approved",true);
        achievementDetails.put("achievement_category_id",Integer.valueOf(achievementCategoryId));
        achievementDetails.put("achievement_id",Integer.valueOf(achievement_Id));
        achievementDetails.put("achievement_proficiency_id",Integer.valueOf(achievementProficiencyId));
        achievementDetails.put("date","2024-09-02");
        achievementDetails.put("points",75);
        achievementDetails.put("description","like to play Cricket");
        achievementDetails.put("is_archived",false);
        achievementDetails.put("updated_by","Vijay");

        System.out.println(achievementDetails.toString());

        Response updateChildAchievementResponse = updateChildAchievement(achievementDetails,child_achievement_id);
        System.out.println("Update Child Achievement Response>>>" + updateChildAchievementResponse.getBody().asString());
        Assert.assertEquals(updateChildAchievementResponse.getStatusCode(), 200, "Verifying Status Code"); //TODO: Responce code should be 201 bug raised

        Response getChildAchievementByChildIDResponse = getChildAchievementByChildID(child_id);
        Assert.assertEquals(getChildAchievementByChildIDResponse.getStatusCode(), 200, "Verifying Status Code");
        System.out.println("get Achievement by child_id>>>" + getChildAchievementByChildIDResponse.getBody().asString());

        JsonPath jsonPath = getChildAchievementByChildIDResponse.jsonPath();
        List<Map<String, Object>> data = jsonPath.getList("data");
        for (int i=0; i< data.size(); i++)
        {
            Map<String, Object> element = data.get(i);
            String childAchievementId =  element.get("child_achievement_id").toString();
            if(childAchievementId.equals(child_achievement_id))
            {
                String description = (String) element.get("description");
                String achievement_category_id = (String) element.get("achievement_category_id");
                String achievement_id = (String) element.get("achievement_id");
                String points = element.get("points").toString();
                String achievement_proficiency_id = (String) element.get("achievement_proficiency_id");
                String date = (String) element.get("date");
                String updated_by = (String) element.get("updated_by");

                System.out.println("Description: " + description);
                System.out.println("Achievement Category Id: " + achievement_category_id);
                System.out.println("Achievement Id: " + achievement_id);
                System.out.println("Points: " + points);
                System.out.println("Achievement Proficiency Id: " + achievement_proficiency_id);
                System.out.println("Date: " + date);
                System.out.println("updated_by : " + updated_by);

                Assert.assertEquals(description,achievementDetails.get("description"));
                Assert.assertEquals(achievement_category_id,achievementDetails.get("achievement_category_id").toString());
                Assert.assertEquals(achievement_id,achievementDetails.get("achievement_id").toString());
                Assert.assertEquals(points,achievementDetails.get("points").toString());
                Assert.assertEquals(achievement_proficiency_id,achievementDetails.get("achievement_proficiency_id").toString());
                Assert.assertEquals(date.substring(0, 10),achievementDetails.get("date"));
                Assert.assertEquals(updated_by,achievementDetails.get("updated_by"));
            }
        }

    }
}