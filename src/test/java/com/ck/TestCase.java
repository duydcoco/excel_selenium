package com.ck;

import com.ck.utils.Constant;
import com.ck.utils.ExcelUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dudycoco on 2017/5/23.
 */
public class TestCase {


    @DataProvider(name = "testData")
    public Object[][] data() throws Exception {
        return ExcelUtils.testData(Constant.Path_TestData + Constant.File_TestData, "Deconnexion");
    }

    @Test(dataProvider = "testData")
    public void testCase(HashMap<String, String> data)throws Exception{
        String colName = data.get("Test Case Name");
        String userName = data.get("UserName");
        String paramName = data.get("N°Compte de facturation");
        String resultName = data.get("Results");
//        System.out.println(colName + "," + userName + "," + paramName + "," + resultName);
        String resultValue = testCase(userName,paramName);
        data.put("Results",resultValue);
    }

    @AfterMethod
    public void afterTest() throws Exception {
//        HashMap<String,String>[][] maps = ExcelUtils.getMap();
//        Map<String,Integer> header = ExcelUtils.getHeader();
//        for(int x=0;x<maps.length;x++){
//            HashMap<String,String>[] tmpMaps=maps[x];
//            HashMap<String,String> tmp = tmpMaps[0];
//            String result = tmp.get("Results");
//            Integer colIndex = header.get("Results");
//            ExcelUtils.setCellData(result,x+1,colIndex);
//        }
//        ExcelUtils.writeToExcel();
        System.out.println("执行。。。。");
    }

    private String testCase(String username, String param) throws Exception {
        String resultValue = "";
        if(auth(username,param)){
            resultValue="Pass";
        }else{
            System.out.println("some step failed");
        }
        return resultValue;
    }

    private  boolean auth(String username, String param) {
        boolean flag = true;
        try{
            //System.out.println("do selenium "+username+","+param);
            flag=login(username,param);
            return flag;
        }catch (Exception e){
            flag=false;
            return flag;
        }
    }

    private boolean login(String username, String param) {
        //https://www.zhihu.com/#signin
    }
}
