//classloader字节码转换成类
package com.sec.cwm.base.classloaders;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static com.sec.cwm.classTransfer.bytes;
public class testCrossClassloader {
    public static String testClassName ="com.sec.cwm.base.testclass.testPrivateClass";
    public static byte[] testClassByte =bytes;
    public static class ClassloaderA extends ClassLoader{
        public ClassloaderA(ClassLoader parent){
            super(parent);
        }
        {
            defineClass(testClassName, testClassByte,0, testClassByte.length);
        }
    }
    public static class ClassloaderB extends ClassLoader{
        public ClassloaderB(ClassLoader parent){
            super(parent);
        }
        {
            defineClass(testClassName, testClassByte,0, testClassByte.length);
        }
    }
    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ClassLoader parentClassLoader=ClassLoader.getSystemClassLoader();
        ClassloaderA aClassloader=new ClassloaderA(parentClassLoader);
        ClassloaderB bClassloader=new ClassloaderB(parentClassLoader);
        Class<?> aClass  = Class.forName(testClassName, true, aClassloader);
        Class<?> aaClass = Class.forName(testClassName, true, aClassloader);
        Class<?> bClass  = Class.forName(testClassName, true, bClassloader);
        System.out.println("aClass == aaClass：" + (aClass == aaClass));
        System.out.println("aClass == bClass：" + (aClass == bClass));

        System.out.println("\n" + aClass.getName() + "方法清单：");

        // 获取该类所有方法
        Method[] methods = aClass.getDeclaredMethods();

        for (Method method : methods) {
            System.out.println(method);
        }

        // 创建类实例
        Object instanceA = aClass.newInstance();

        // 获取hello方法
        Method helloMethod = aClass.getMethod("hello");

        // 调用hello方法
        String result = (String) helloMethod.invoke(instanceA);

        System.out.println("\n反射调用：" + testClassName + "类" + helloMethod.getName() + "方法，返回结果：" + result);

    }
}