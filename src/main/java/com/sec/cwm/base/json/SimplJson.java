package com.sec.cwm.base.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class SimplJson {
    public static void main(String args[]){
        User user =new User("11",11,"11");
        String s1= JSON.toJSONString(user);
        System.out.println(s1);
                String s2 = JSON.toJSONString(user, SerializerFeature.WriteClassName);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println("-----------------------------------------------------");
        Object parse = JSON.parse(s2);
        System.out.println(parse);
        System.out.println(parse.getClass().getName());
        System.out.println("-----------------------------------------------------");
        Object parse1 = JSON.parseObject(s2);
        System.out.println(parse1);
        System.out.println(parse1.getClass().getName());
        System.out.println("-----------------------------------------------------");
        Object parse2 = JSON.parseObject(s2,Object.class);
        System.out.println(parse2);
        System.out.println(parse2.getClass().getName());
    }
}
