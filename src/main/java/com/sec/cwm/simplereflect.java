package com.sec.cwm;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;

public class simplereflect {
    public static void main(String argsp[]) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, NoSuchFieldException {
        // 获取Runtime类对象
        Class runtimeClass1 = Class.forName("java.lang.Runtime");
        Class runtimeClass2= Class.forName("com.sec.cwm.hackjava");

// 获取构造方法
        Constructor constructor = runtimeClass1.getDeclaredConstructor();
        Constructor constructor1 = runtimeClass2.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor1.setAccessible(true);

// 创建Runtime类示例，等价于 Runtime rt = new Runtime();
        Object runtimeInstance = constructor.newInstance();
        Object runtimeInstance1 = constructor1.newInstance();

// 获取Runtime的exec(String cmd)方法
        Method runtimeMethod = runtimeClass1.getMethod("exec", String.class);
        Method[] methods=runtimeClass1.getDeclaredMethods();

//获取hackjava的amd变量
        Field fields=runtimeClass2.getDeclaredField("amd");
//取消封装
        fields.setAccessible(true);
//更改变量
        fields.set(runtimeInstance1,"no");
        System.out.println(fields.get(runtimeInstance1));

// 调用exec方法，等价于 rt.exec(cmd);
        Process process = (Process) runtimeMethod.invoke(runtimeInstance, "calc");

// 获取命令执行结果
        InputStream in = process.getInputStream();

// 输出命令执行结果
        System.out.println(IOUtils.toString(in, "UTF-8"));
//        System.out.println(methods);
        System.out.println(IOUtils.toString(Runtime.getRuntime().exec("whoami").getInputStream(), "UTF-8"));
    }
}
