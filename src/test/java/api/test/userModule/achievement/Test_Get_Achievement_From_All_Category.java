package api.test.userModule.achievement;

import api.Utilities.DataProvider;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static api.endPoints.EndPoints.getAchievementFromAllCategory;

public class Test_Get_Achievement_From_All_Category {

    @Test(priority = 1, dataProvider = "AchievementFromAllCategory", dataProviderClass = DataProvider.class)
    public void testGetAchievementFromAllCategory(String achievement_Id, String name, String achievement_category_Id)
    {
        Response getAchievementFromAllCategoryResponse = getAchievementFromAllCategory();
        Assert.assertEquals(getAchievementFromAllCategoryResponse.getStatusCode(), 200, "Verifying Status Code");
        System.out.println("Get Achievement From All Category>>>" + getAchievementFromAllCategoryResponse.getBody().asString());

        JsonPath jsonPath = getAchievementFromAllCategoryResponse.jsonPath();
        List<Map<String, Object>> data = jsonPath.getList("data");
        for (int i=0; i< data.size(); i++) {
            Map<String, Object> element = data.get(i);
            String achievement_id = element.get("achievement_id").toString();
            String nameResponce = (String) element.get("name");
            String achievement_category_id = (String) element.get("achievement_category_id");
            if (achievement_Id.equals(achievement_id)) {

                System.out.println("achievement_id : " + achievement_id);
                System.out.println("name : " + nameResponce);
                System.out.println("achievement_category_id : " + achievement_category_id);

                Assert.assertEquals(achievement_id, achievement_Id);
                Assert.assertEquals(nameResponce, name);
                Assert.assertEquals(achievement_category_id, achievement_category_Id);
                break;
            }
        }
    }
}
