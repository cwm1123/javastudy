package com.sec.cwm.base.serialize;

import java.io.*;
import java.util.Arrays;

public class testDeserialize implements Serializable {
    private String username;
    private String email;
    public void setUsername(String getusername){
        this.username=getusername;
    }
    public void setEmail(String getemail){
        this.email=getemail;
    }
    public String getUsername(){
        return this.username;
    }
    public String getEmail(){
        return this.email;
    }
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        System.out.println("readObject...");

        // 调用ObjectInputStream默认反序列化方法
        ois.defaultReadObject();

        // 省去调用自定义反序列化逻辑...
    }

    /**
     * 自定义序列化类对象
     *
     * @param oos 序列化输出流对象
     * @throws IOException IO异常
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();

        System.out.println("writeObject...");
        // 省去调用自定义序列化逻辑...
    }

    private void readObjectNoData() {
        System.out.println("readObjectNoData...");
    }

    /**
     * 写入时替换对象
     *
     * @return 替换后的对象
     */
    //当触发writeObject方法时，若有writeReplace() 则会让其返回值为其return值
//    protected Object writeReplace() {
//        System.out.println("writeReplace....");
//
//        return null;
//    }
//      //当触发readObject方法时，若有readResolve 则会让其返回值为其return值
//    protected Object readResolve() {
//        System.out.println("readResolve....");
//
//        return null;
//    }

    public static void main(String args[]){
        ByteArrayOutputStream baos= new ByteArrayOutputStream();
        try{
            testDeserialize td=new testDeserialize();
            td.setUsername("cwm");
            td.setEmail("2770976250@qq.com");
            // 创建Java对象序列化输出流对象
            ObjectOutputStream out=new ObjectOutputStream(baos);
            //序列化类
            out.writeObject(td);
            out.flush();
            out.close();
            System.out.println(Arrays.toString(baos.toByteArray()));
            System.out.println(baos);
            // 利用DeserializationTest类生成的二进制数组创建二进制输入流对象用于反序列化操作
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            // 通过反序列化输入流(bais),创建Java对象输入流(ObjectInputStream)对象
            ObjectInputStream in = new ObjectInputStream(bais);
            // 反序列化输入流数据为DeserializationTest对象
            testDeserialize td1=(testDeserialize) in.readObject();
            System.out.println(td1.getUsername()+td1.getEmail());
            in.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
