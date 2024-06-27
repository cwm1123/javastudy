package com.sec.cwm.base.defineclass;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.security.cert.Certificate;

import static com.sec.cwm.classTransfer.bytes;

public class testUnsafeDefineClass {
    public static String testClassName ="com.sec.cwm.base.testclass.testJavaClass";
    public static byte[] testClassByte =bytes;
    public static void main(String args[]) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class unsafeClass=Class.forName("sun.misc.Unsafe");
        Constructor unsafeConstructor=unsafeClass.getDeclaredConstructor();
        unsafeConstructor.setAccessible(true);
        Object unsafeObj=unsafeConstructor.newInstance();
        Unsafe unsafe1=(Unsafe) unsafeConstructor.newInstance();
        Method unsafeDefineClassMethod=unsafeClass.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class, ClassLoader.class, ProtectionDomain.class);
        /**
         * in jdk8
         * String.class:类名
         * byte[]:字节码
         * int。class1:开始
         * int.class2:结尾
         * Classloader:类加载器
         * ProtectionDomain:开启防护
         */
        ClassLoader cl=ClassLoader.getSystemClassLoader();
        ProtectionDomain pd=new ProtectionDomain(new CodeSource(null, (Certificate[]) null),null,cl,null);
//        testJavaClass tjc=(testJavaClass) unsafeDefineClassMethod.invoke(unsafeObj,testClassName,testClassByte,0,testClassByte.length,cl,pd);
        Class tjc=unsafe1.defineClass(testClassName,testClassByte,0,testClassByte.length,cl,pd);
        Method tjcMethod=tjc.getDeclaredMethod("hello");
        Object tjcObj=tjc.newInstance();
        tjcMethod.invoke(tjcObj);
    }
}
