package com.sec.cwm.base.serialize;

import com.sec.cwm.base.testclass.testJavaClass;
import sun.reflect.ReflectionFactory;
import java.lang.reflect.Constructor;

public class testReflectFactory {
    public static void main(String[] args) {
        try {
            // 获取sun.reflect.ReflectionFactory对象
            ReflectionFactory factory = ReflectionFactory.getReflectionFactory();

            // 使用反序列化方式获取testJavaClass类的构造方法
            Constructor<?> constructor = factory.newConstructorForSerialization(
                    testJavaClass.class, Object.class.getConstructor()
            );

            // 实例化testJavaClass对象
            testJavaClass tjc=(testJavaClass) constructor.newInstance();
            tjc.hello();
            System.out.println(tjc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

