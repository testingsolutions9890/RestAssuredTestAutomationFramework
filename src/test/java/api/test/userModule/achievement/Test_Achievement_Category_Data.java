package api.test.userModule.achievement;

import api.Utilities.DataProvider;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static api.endPoints.EndPoints.getAchievementCategory;

public class Test_Achievement_Category_Data {

    @Test(priority = 1, dataProvider = "AchievementCategoryData", dataProviderClass = DataProvider.class)
    public void testGetAchievementCategory(String achievementCategoryId, String name)
    {
        Response getAchievementCategoryResponse = getAchievementCategory();
        Assert.assertEquals(getAchievementCategoryResponse.getStatusCode(), 200, "Verifying Status Code");
        System.out.println("Get Achievement Category>>>" + getAchievementCategoryResponse.getBody().asString());

        JsonPath jsonPath = getAchievementCategoryResponse.jsonPath();
        List<Map<String, Object>> data = jsonPath.getList("data");
        for (int i=0; i< data.size(); i++)
        {
            Map<String, Object> element = data.get(i);
            String achievement_category_id =  element.get("achievement_category_id").toString();
            String nameResponce = (String) element.get("name");

            if(achievementCategoryId.equals(achievement_category_id))
            {

                System.out.println("achievement_category_id : " + achievement_category_id);
                System.out.println("name : " + nameResponce);

                Assert.assertEquals(achievementCategoryId,achievementCategoryId);
                Assert.assertEquals(nameResponce,name);
            }
    }
}
}
