package com.sec.cwm.base.jni;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.ClassLoader;

public class runJni {
    public static void main(String args[]) throws Exception {
//        File jniObj=new File("/home/cwm/IdeaProjects/javastudy/src/main/java/com/sec/cwm/base/jni/libcmd.so");
//        Class jniclass=Class.forName("java.lang.ClassLoader");
//        Constructor jniConstructor=jniclass.getDeclaredConstructor();
//        jniConstructor.setAccessible(true);
//        Method jniLoadMethod=jniclass.getMethod("load");
//        jniLoadMethod.invoke(null,jniObj);
        System.load("/home/cwm/IdeaProjects/javastudy/src/main/java/com/sec/cwm/base/jni/libcmd.so");
        newJni newJni=new newJni();
        String cmd=newJni.exec("ls");
        System.out.println(cmd);
    }
}
