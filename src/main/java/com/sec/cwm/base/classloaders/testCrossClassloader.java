//classloader字节码转换成类
package com.sec.cwm.base.classloaders;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static com.sec.cwm.classTransfer.bytes;
public class testCrossClassloader {
    public static String testClassName ="com.sec.cwm.base.testclass.testPrivateClass";
    public static byte[] testClassByte =new byte[]{-54, -2, -70, -66, 0, 0, 0, 52, 0, 33, 10, 0, 7, 0, 18, 9, 0, 19, 0, 20, 8, 0, 21, 10, 0, 22, 0, 23, 8, 0, 24, 7, 0, 25, 7, 0, 26, 1, 0, 6, 60, 105, 110, 105, 116, 62, 1, 0, 3, 40, 41, 86, 1, 0, 4, 67, 111, 100, 101, 1, 0, 15, 76, 105, 110, 101, 78, 117, 109, 98, 101, 114, 84, 97, 98, 108, 101, 1, 0, 18, 76, 111, 99, 97, 108, 86, 97, 114, 105, 97, 98, 108, 101, 84, 97, 98, 108, 101, 1, 0, 4, 116, 104, 105, 115, 1, 0, 45, 76, 99, 111, 109, 47, 115, 101, 99, 47, 99, 119, 109, 47, 98, 97, 115, 101, 47, 116, 101, 115, 116, 99, 108, 97, 115, 115, 47, 116, 101, 115, 116, 80, 114, 105, 118, 97, 116, 101, 67, 108, 97, 115, 115, 59, 1, 0, 5, 104, 101, 108, 108, 111, 1, 0, 10, 83, 111, 117, 114, 99, 101, 70, 105, 108, 101, 1, 0, 21, 116, 101, 115, 116, 80, 114, 105, 118, 97, 116, 101, 67, 108, 97, 115, 115, 46, 106, 97, 118, 97, 12, 0, 8, 0, 9, 7, 0, 27, 12, 0, 28, 0, 29, 1, 0, 17, 85, 110, 115, 97, 102, 101, 32, 105, 115, 32, 115, 117, 99, 99, 101, 115, 115, 7, 0, 30, 12, 0, 31, 0, 32, 1, 0, 12, 104, 101, 108, 108, 111, 32, 117, 110, 115, 97, 102, 101, 1, 0, 43, 99, 111, 109, 47, 115, 101, 99, 47, 99, 119, 109, 47, 98, 97, 115, 101, 47, 116, 101, 115, 116, 99, 108, 97, 115, 115, 47, 116, 101, 115, 116, 80, 114, 105, 118, 97, 116, 101, 67, 108, 97, 115, 115, 1, 0, 16, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 1, 0, 16, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 121, 115, 116, 101, 109, 1, 0, 3, 111, 117, 116, 1, 0, 21, 76, 106, 97, 118, 97, 47, 105, 111, 47, 80, 114, 105, 110, 116, 83, 116, 114, 101, 97, 109, 59, 1, 0, 19, 106, 97, 118, 97, 47, 105, 111, 47, 80, 114, 105, 110, 116, 83, 116, 114, 101, 97, 109, 1, 0, 7, 112, 114, 105, 110, 116, 108, 110, 1, 0, 21, 40, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 41, 86, 0, 33, 0, 6, 0, 7, 0, 0, 0, 0, 0, 2, 0, 2, 0, 8, 0, 9, 0, 1, 0, 10, 0, 0, 0, 63, 0, 2, 0, 1, 0, 0, 0, 13, 42, -73, 0, 1, -78, 0, 2, 18, 3, -74, 0, 4, -79, 0, 0, 0, 2, 0, 11, 0, 0, 0, 14, 0, 3, 0, 0, 0, 4, 0, 4, 0, 5, 0, 12, 0, 6, 0, 12, 0, 0, 0, 12, 0, 1, 0, 0, 0, 13, 0, 13, 0, 14, 0, 0, 0, 1, 0, 15, 0, 9, 0, 1, 0, 10, 0, 0, 0, 55, 0, 2, 0, 1, 0, 0, 0, 9, -78, 0, 2, 18, 5, -74, 0, 4, -79, 0, 0, 0, 2, 0, 11, 0, 0, 0, 10, 0, 2, 0, 0, 0, 8, 0, 8, 0, 9, 0, 12, 0, 0, 0, 12, 0, 1, 0, 0, 0, 9, 0, 13, 0, 14, 0, 0, 0, 1, 0, 16, 0, 0, 0, 2, 0, 17};
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
