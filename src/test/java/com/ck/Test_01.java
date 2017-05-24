package com.ck;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by dudycoco on 2017/5/25.
 */
public class Test_01 {

    public static void main(String[] args){
        HashMap map = new HashMap();//表示创建一个对象:动物->狗,猫
        map.put("key",20);//{"key1":"value","key":20}
        map.put("key1","value");
        System.out.println(map.get("key"));
    }
}
