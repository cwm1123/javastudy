package com.sec.cwm.base.reflect;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class testCrossUnsafe {
    public static void main(String args[]) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class unsafeClass= Class.forName("sun.misc.Unsafe");
        Constructor unsafeConstructor=unsafeClass.getDeclaredConstructor();
        unsafeConstructor.setAccessible(true);
        Object unsafeObject=unsafeConstructor.newInstance();
        Method unsafeMethod=unsafeClass.getDeclaredMethod("allocateInstance", Class.class);
        sec.test.cwm.testPrivateClass1 tpc1=(sec.test.cwm.testPrivateClass1) unsafeMethod.invoke(unsafeObject,sec.test.cwm.testPrivateClass1.class);
        tpc1.hello();
    }
}
