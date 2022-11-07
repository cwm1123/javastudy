//Not effective in Java8+
package com.sec.cwm.base.reflect;



import com.sec.cwm.base.testclass.testPrivateClass;
import sun.misc.Unsafe;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class testUnsafe {

    public static void main(String args[]) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class unsafeClass=Class.forName("sun.misc.Unsafe");
        //反射获取类
        Constructor unsafeConstructor= unsafeClass.getDeclaredConstructor();
        //构造类
        unsafeConstructor.setAccessible(true);
        //更改为可访问
        Object unsafeObj=unsafeConstructor.newInstance();
        Unsafe unsafe1=(Unsafe) unsafeConstructor.newInstance();
        //Unsafe unsafe=new Unsafe();
        Method unsafemethod=unsafeClass.getMethod("allocateInstance",Class.class);
        Field unsafeField=unsafeClass.getDeclaredField("theUnsafe");
        unsafeField.setAccessible(true);
        //获取allocateInstance方法
        Method[] unsafemethods=unsafeClass.getDeclaredMethods();
        Field[] unsafeFields=unsafeClass.getDeclaredFields();
//        for(Method method:unsafemethods) {
//            System.out.println(method);
//        }
//        for(Field field:unsafeFields) {
//            System.out.println(field);
//        }
//        Process process=(Process) unsafemethod.invoke(unsafeObj,"testUnsafe");
//        testPrivateClass tpc=(testPrivateClass) unsafe1.allocateInstance(testPrivateClass.class);
        testPrivateClass tpc=(testPrivateClass) unsafemethod.invoke(unsafeObj,testPrivateClass.class);
        tpc.hello();
    }
}
