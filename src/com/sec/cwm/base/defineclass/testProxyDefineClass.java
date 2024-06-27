package com.sec.cwm.base.defineclass;

import java.lang.reflect.Method;
import static com.sec.cwm.classTransfer.bytes;
import static com.sec.cwm.classTransfer.className;
public class testProxyDefineClass {
        public static void main(String[] args) throws ClassNotFoundException {
            // 获取系统的类加载器，可以根据具体情况换成一个存在的类加载器
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            Class<?> proxyClass=Class.forName("java.lang.reflect.Proxy");
            try {
                // 反射java.lang.reflect.Proxy类获取其中的defineClass0方法
                Method method = proxyClass.getDeclaredMethod("defineClass0", ClassLoader.class, String.class, byte[].class, int.class, int.class);

                // 修改方法的访问权限
                method.setAccessible(true);

                // 反射调用java.lang.reflect.Proxy.defineClass0()方法，动态向JVM注册
                // com.anbai.sec.classloader.TestHelloWorld类对象
                Class helloWorldClass = (Class) method.invoke(null, new Object[]{
                        classLoader, className, bytes, 0, bytes.length
                });

                // 输出TestHelloWorld类对象
                System.out.println(helloWorldClass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
