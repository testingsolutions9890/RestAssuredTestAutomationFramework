package api.Utilities;

public class DataProvider {

    @org.testng.annotations.DataProvider(name="UserData")
    public Object[][] getAllData(){
        String path = System.getProperty("user.dir")+"//src//test//resources//global//UserData.xlsx";
        ExcelUtility excelUtility = new ExcelUtility(path);

        int rownum = excelUtility.getRowCount("Sheet1");
        int colCount = excelUtility.getColumnCount("Sheet1",1);

        String apidata[][] = new String[rownum-1][colCount];

        for(int i=1;i<rownum;i++){
            for(int j=0;j<colCount;j++){
                apidata[i-1][j] = excelUtility.getCellData("Sheet1",j,i+1);
            }
        }
        return apidata;
    }

    @org.testng.annotations.DataProvider(name="AchievementCategoryData")
    public Object[][] getAllAchievementCategoryData(){
        String path = System.getProperty("user.dir")+"//src//test//resources//global//UserData.xlsx";
        ExcelUtility excelUtility = new ExcelUtility(path);

        int rownum = excelUtility.getRowCount("Sheet2");
        int colCount = excelUtility.getColumnCount("Sheet2",1);

        String apidata[][] = new String[rownum-1][colCount];

        for(int i=1;i<rownum;i++){
            for(int j=0;j<colCount;j++){
                apidata[i-1][j] = excelUtility.getCellData("Sheet2",j,i+1);
            }
        }
        return apidata;
    }

    @org.testng.annotations.DataProvider(name="AchievementFromAllCategory")
    public Object[][] getAchievementFromAllCategory(){
        String path = System.getProperty("user.dir")+"//src//test//resources//global//UserData.xlsx";
        ExcelUtility excelUtility = new ExcelUtility(path);

        int rownum = excelUtility.getRowCount("Sheet3");
        int colCount = excelUtility.getColumnCount("Sheet3",1);

        String apidata[][] = new String[rownum-1][colCount];

        for(int i=1;i<rownum;i++){
            for(int j=0;j<colCount;j++){
                apidata[i-1][j] = excelUtility.getCellData("Sheet3",j,i+1);
            }
        }
        return apidata;
    }


    @org.testng.annotations.DataProvider(name="AchievementProficiency")
    public Object[][] getAchievementProficiency(){
        String path = System.getProperty("user.dir")+"//src//test//resources//global//UserData.xlsx";
        ExcelUtility excelUtility = new ExcelUtility(path);

        int rownum = excelUtility.getRowCount("Sheet4");
        int colCount = excelUtility.getColumnCount("Sheet4",1);

        String apidata[][] = new String[rownum-1][colCount];

        for(int i=1;i<rownum;i++){
            for(int j=0;j<colCount;j++){
                apidata[i-1][j] = excelUtility.getCellData("Sheet4",j,i+1);
            }
        }
        return apidata;
    }

    /*@org.testng.annotations.DataProvider(name="UserNames")
    public String[] getUserNames(){
        String path = System.getProperty("user.dir")+"//resources//global//UserData.xlsx";
        ExcelUtility excelUtility = new ExcelUtility(path);

        int rownum = excelUtility.getRowCount("Sheet1");
        String apiData[] = new String[rownum];

        for(int i=1;i<=rownum;i++){
            apiData[i-1] = excelUtility.getCellData("Sheet1",i,1);
        }
        return apiData;
    }*/

}
