//classloader调用类
package com.sec.cwm.base.classloaders;

import com.sec.cwm.base.testclass.testJavaClass;

import java.lang.reflect.Method;

public class testClassloader extends  ClassLoader{
        public static void  main(String args[]) {
            //正常调用
//            testJavaClass t=new testJavaClass();
//            String s=t.test();
//            System.out.println(s);
            //classloader
            testClassloader loader=new testClassloader();
            try{
                Class testclass=loader.loadClass("com.sec.cwm.base.testclass.testJavaClass");
                Object testInstance = testclass.newInstance();
                //testJavaClass t=new testJavaClass();
                Method method=testInstance.getClass().getMethod("hello");
                method.invoke(testInstance);
//                String str=(String) method.invoke(testInstance);
                //testJavaClass.class.test()
//                System.out.println(str);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
}
