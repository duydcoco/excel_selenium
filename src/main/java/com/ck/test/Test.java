//package com.ck.test;
//
//import com.ck.utils.Constant;
//import com.ck.utils.ExcelUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by dudycoco on 2017/5/22.
// */
//public class Test {
//
//    public static void main(String[] args)throws Exception{
//        ExcelUtils.setExcelFile(Constant.Path_TestData+Constant.File_TestData,"Deconnexion");
//
//        //get the excel row length
//        int excelRowLength = 2;
//
//        //get the excel col length
//        int excelColLength = 4;
//
//        //get all excel data to a list like this:[[username,password,scaa,results],...]
//        List<List<String>> data = new ArrayList<>();
//        for(int x=0;x<excelRowLength;x++){
//            List<String> list = new ArrayList<>();
//            for(int y =0;y<excelColLength;y++){
//                list.add(ExcelUtils.getCellData(x,y));
//            }
//            data.add(list);
//        }
//        //loop this data list deal with test case and set cell for the excel
//        int x = 0;
//        for (List<String> tmpList:data) {
//            testCase(tmpList,x+1,excelColLength-1);
//            x++;
//        }
//
//        //write data to excel;
//        ExcelUtils.writeToExcel();
//    }
//
//    private static void testCase(List<String> tmpList,int row,int col) throws Exception {
//        String result = "";
//        if(auth(tmpList)){
//            result="Pass";
//            ExcelUtils.setCellData(result,row,col);
//        }else{
//            System.out.println("some step failed");
//        }
//    }
//
//    private static boolean auth(List<String> tmpList) {
//        String username = tmpList.get(0);
//        String password = tmpList.get(1);
//        String param = tmpList.get(2);
//        boolean flag = true;
//        try{
//            System.out.println("do selenium "+username+","+password+","+param);
//            return flag;
//        }catch (Exception e){
//            flag=false;
//            return flag;
//        }
//    }
//}
