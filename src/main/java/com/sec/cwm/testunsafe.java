package com.sec.cwm;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class testunsafe {

    public static void main(String args[]) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class unsafeClass=Class.forName("sun.misc.Unsafe");
        Constructor unsafeConstructor= unsafeClass.getDeclaredConstructor();
        unsafeConstructor.setAccessible(true);
        Object unsafeObj=unsafeConstructor.newInstance();
        Method method=unsafeClass.getMethod("allocateInstance",Class.class);
        Method[] methods=unsafeClass.getDeclaredMethods();
        for(Method method1:methods){
            System.out.println(method);
        }

    }
}
