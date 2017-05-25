package com.ck;

import com.ck.utils.Constant;
import com.ck.utils.ExcelUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dudycoco on 2017/5/23.
 */
public class TestCase {


    private WebDriver driver;

    private int row=0;

    @BeforeSuite
    public void initDriver(){
        System.setProperty("webdriver.chrome.driver", "F:\\javaDemo\\excel_selenium\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @DataProvider(name = "testData")
    public Object[][] data() throws Exception {
        return ExcelUtils.testData(Constant.Path_TestData + Constant.File_TestData, "Deconnexion");
    }

    @Test(dataProvider = "testData")
    public void testCase(HashMap<String, String> data)throws Exception{
        String userName = data.get("UserName").trim();
        String passWord = data.get("PassWord").trim();
        String resultValue = testCase(userName,passWord);
        data.put("Results",resultValue);
        row++;
    }

    @AfterMethod
    public void afterTest() throws Exception {
        //after testCase method execute this method will be excuted
        HashMap<String,String>[][] maps = ExcelUtils.getMap();
        Map<String,Integer> header = ExcelUtils.getHeader();
        HashMap<String,String>[] tmpMaps=maps[row-1];
        HashMap<String,String> tmp = tmpMaps[0];
        String result = tmp.get("Results");
        Integer colIndex = header.get("Results");
        ExcelUtils.setCellData(result,row,colIndex);
        ExcelUtils.writeToExcel();
    }

    private String testCase(String username, String param) throws Exception {
        String resultValue = "";
        if(login(username,param)){
            resultValue="Pass";
        }else{
            resultValue="Fail";
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
        String url = "https://www.zhihu.com/#signin";
        boolean flag = true;
        try {
            driver.get(url);
            driver.findElement(By.xpath("//*[@name=\"account\"]")).sendKeys(username);
            driver.findElement(By.xpath("//*[@name=\"password\"]")).sendKeys(param);
            driver.findElement(By.xpath("//*[@class=\"sign-button submit\"]")).click();
            for(String winHandle : driver.getWindowHandles()){
                driver.switchTo().window(winHandle);
            }
            (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    WebElement webElement = d.findElement(By.xpath("//*[@id=\"top-nav-profile-dropdown\"]"));
                    return webElement!=null;
                }
            });
            driver.quit();
            return flag;
        }catch (Exception e){
            e.printStackTrace();
            flag = false;
            driver.quit();
            return flag;
        }
    }
}
