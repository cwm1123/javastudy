package com.sec.cwm;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;

public class simpleReflect {
    public static void main(String args[]) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, NoSuchFieldException {
        // 获取Runtime类对象

        Class runtimeClass1 = Class.forName("java.lang.Runtime");

        // 获取构造方法

        Constructor constructor = runtimeClass1.getDeclaredConstructor();

        constructor.setAccessible(true);

        // 创建Runtime类示例，等价于 Runtime rt = new Runtime();

        Object runtimeInstance = constructor.newInstance();

        Method runtimeMethod = runtimeClass1.getMethod("exec",String.class);

//        Method[] methods=runtimeClass1.getDeclaredMethods();

// 调用exec方法，等价于 rt.exec(cmd);

        Process process = (Process) runtimeMethod.invoke(runtimeInstance, "calc");

// 获取命令执行结果

        InputStream in = process.getInputStream();

// 输出命令执行结果

        System.out.println(IOUtils.toString(in, "UTF-8"));

//获取hackjava类对象

        Class runtimeClass2= Class.forName("com.sec.cwm.hackJava");

// 获取构造方法

        Constructor constructor1 = runtimeClass2.getDeclaredConstructor();

        constructor1.setAccessible(true);

//创建hackjava实例

        Object runtimeInstance1 = constructor1.newInstance();

//获取hackjava的amd变量

        Field fields=runtimeClass2.getDeclaredField("amd");

//取消封装

        fields.setAccessible(true);

//更改变量

        fields.set(runtimeInstance1,"no");

        System.out.println(fields.get(runtimeInstance1));

// 获取构造方法

        Class runtimeClass3=Class.forName("sec.test.cwm.testPrivateClass1");

        Constructor constructor2 = runtimeClass3.getDeclaredConstructor();

        constructor2.setAccessible(true);

//创建实例

        Object runtimeInstance2 = constructor2.newInstance();

//获取hello方法

        Method runtimeMethod1=runtimeClass3.getMethod("hello");//无参不需要传参，不用写参数类型

//输出返回值
        runtimeMethod1.invoke(runtimeInstance2);




//直接运行runtime
//        System.out.println(IOUtils.toString(Runtime.getRuntime().exec("whoami").getInputStream(), "UTF-8"));
    }
}
