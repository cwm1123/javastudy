//classloader调用类
package com.sec.cwm;

import java.lang.reflect.Method;

public class testclassloader extends  ClassLoader{
        public static void  main(String args[]) {
            testjavaclass t=new testjavaclass();
            String s=t.test();
            System.out.println(s);
            testclassloader loader=new testclassloader();
            try{
                Class testclass=loader.loadClass("com.sec.cwm.testjavaclass");
                Object testInstance = testclass.newInstance();
                Method method=testInstance.getClass().getMethod("test");
                String str=(String) method.invoke(testInstance);
                System.out.println(str);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
}
