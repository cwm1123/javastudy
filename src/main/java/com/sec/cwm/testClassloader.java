//classloader调用类
package com.sec.cwm;

import java.lang.reflect.Method;

public class testClassloader extends  ClassLoader{
        public static void  main(String args[]) {
            testJavaClass t=new testJavaClass();
            String s=t.test();
            System.out.println(s);
            testClassloader loader=new testClassloader();
            try{
                Class testclass=loader.loadClass("com.sec.cwm.testJavaClass");
                Object testInstance = testclass.newInstance();
                Method method=testInstance.getClass().getMethod("test");
                String str=(String) method.invoke(testInstance);
                System.out.println(str);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
}
