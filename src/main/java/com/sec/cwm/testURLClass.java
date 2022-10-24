package com.sec.cwm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import sun.net.www.protocol.http.HttpURLConnection;

public class testURLClass {
    public static void main(String[] args) throws IOException {
        URL u = new URL("http://172.20.153.162" );
        URLConnection urlConnection = u.openConnection();
        URLConnection httpUrl = urlConnection;
        BufferedReader in = new BufferedReader(new InputStreamReader(httpUrl.getInputStream()));
    }
////        URL url = new URL("file:///C:\\Users\\CWM\\IdeaProjects\\test\\src\\main\\java\\com\\sec\\cwm\\flag");
//        URL url=new URL("http://172.18.8.132" );
//
//        // 打开和url之间的连接
//        URLConnection connection = url.openConnection();
//        HttpURLConnection httpUrl=(HttpURLConnection)connection;
//
//        // 设置请求参数
////        connection.setRequestProperty("user-agent", "javasec");
////        connection.setConnectTimeout(1000);
////        connection.setReadTimeout(1000);
//        // 建立实际连接
////        connection.connect();
//
//        // 获取响应头字段信息列表
////        connection.getHeaderFields();
//
//        // 获取URL响应
////        connection.getInputStream();
//
//        StringBuilder response = new StringBuilder();
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(httpUrl.getInputStream()));
//        String line;
//
//        while ((line = in.readLine()) != null) {
//            response.append("/n").append(line);
//        }
//
//        System.out.print(response.toString());
//    }

}
