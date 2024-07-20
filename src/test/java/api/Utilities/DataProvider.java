package api.Utilities;

public class DataProvider {

    @org.testng.annotations.DataProvider(name="Data")
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
