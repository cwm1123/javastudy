//将class转换为字节
package com.sec.cwm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class classTransfer extends ClassLoader{
    private final String clName;
    // 构造函数1：不指定父类加载器，仅指定自定义类加载器的名称
    public classTransfer(String clName) {
        super();
        this.clName = clName;
    }
    // 构造函数2：指定父类记载器和类加载器的名称。
    public classTransfer(ClassLoader parent, String clName) {
        super(parent);
        this.clName = clName;
    }
    /**
     * loadClassData,将class文件读取为byte[]数组。
     * @param target 目标class文件路径
     * @return
     */
    /**
     * 转换为字节码
     * @param target
     * @return
     */
    private byte[] loadClassData(String target) {
        InputStream is = null;
        byte[] bytes = null;
        ByteArrayOutputStream os = null;
        int len;
        try {
            is = new FileInputStream(new File(target));
            os = new ByteArrayOutputStream();
            while(-1 != (len = is.read())) {
                while(len>128){
                    len=len-256;
                }
//                System.out.print(len);
//                System.out.print(", ");
                os.write(len);
            }
            bytes = os.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }
    /**
     * 通过调用 defineClass()方法，将byte[] 字节数组转化为类对象
     * @param target
     * @return
     */
    public Class<?> findClass(String target, String className) {
        byte[] bytes = loadClassData(target);
        // clName 应该是 类的binary name
        return defineClass(className, bytes, 0, bytes.length);
    }
    // 以下代码会调用自定义的类加载器加载TestCode15
    public static String className = "com.sec.cwm.base.testclass.testJavaClass";
    public static classTransfer loader1=new classTransfer("loader1");
    public static String target="C:\\Users\\CWM\\IdeaProjects\\test\\target\\classes\\com\\sec\\cwm\\base\\testclass\\testJavaClass.class";
//    public static String target="C:\\Users\\CWM\\IdeaProjects\\test\\target\\classes\\com\\sec\\cwm\\testPrivateClass.class";
    public static byte[] bytes=loader1.loadClassData(target);
    public static void main(String[] args) throws Exception {
//        classTransfer loader1 = new classTransfer("loader1");
//        String target = "C:\\Users\\CWM\\IdeaProjects\\test\\target\\classes\\com\\sec\\cwm\\testjavaclass.class";

        Class clazz = loader1.findClass(target, className);
        byte[] bytes=loader1.loadClassData(target);
        System.out.println(clazz.getClassLoader()); // cn.com.ccxi.jvm.test.TestCode16@773de2bd
    }
}
