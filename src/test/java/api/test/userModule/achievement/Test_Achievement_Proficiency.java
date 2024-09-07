package api.test.userModule.achievement;

import api.Utilities.DataProvider;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static api.endPoints.EndPoints.getAchievementFromAllCategory;
import static api.endPoints.EndPoints.getAchievementProficiency;

public class Test_Achievement_Proficiency {

    @Test(priority = 1, dataProvider = "AchievementProficiency", dataProviderClass = DataProvider.class)
    public void testGetAchievementProficiency(String achievement_proficiency_Id,String name,String points)
    {
        Response getAchievementProficiencyResponse = getAchievementProficiency();
        Assert.assertEquals(getAchievementProficiencyResponse.getStatusCode(), 200, "Verifying Status Code");
        System.out.println("Get Achievement Proficiency>>>" + getAchievementProficiencyResponse.getBody().asString());

        JsonPath jsonPath = getAchievementProficiencyResponse.jsonPath();
        List<Map<String, Object>> data = jsonPath.getList("data");
        for (int i=0; i< data.size(); i++) {
            Map<String, Object> element = data.get(i);
            String achievement_proficiency_id = element.get("achievement_proficiency_id").toString();
            String nameResponce = (String) element.get("name");
            String pointsResponce = (String) element.get("points");
            if (achievement_proficiency_id.equals(achievement_proficiency_Id)) {

                System.out.println("achievement_proficiency_id : " + achievement_proficiency_id);
                System.out.println("name : " + nameResponce);
                System.out.println("Points : " + pointsResponce);

                Assert.assertEquals(achievement_proficiency_id, achievement_proficiency_Id);
                Assert.assertEquals(nameResponce, name);
                Assert.assertEquals(points, pointsResponce);
                break;
            }
        }
    }
}
