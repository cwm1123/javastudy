package com.sec.cwm.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class FileToBase64 {
    public static void main(String[] args) {
        // 指定文件路径
        String filePath = "C:\\Users\\cwm\\IdeaProjects\\javastudy\\target\\classes\\com\\sec\\cwm\\base\\memshell\\tomcatshell.class";

        try {
            // 读取文件
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] fileBytes = new byte[(int) file.length()];
            fileInputStream.read(fileBytes);
            fileInputStream.close();

            // 将文件字节数组编码为Base64
            String base64Encoded = Base64.getEncoder().encodeToString(fileBytes);

            // 输出Base64编码
            System.out.println("Base64 Encoded String: " + base64Encoded);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
