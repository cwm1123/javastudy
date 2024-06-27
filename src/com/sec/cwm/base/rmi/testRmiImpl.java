package com.sec.cwm.base.rmi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class testRmiImpl extends UnicastRemoteObject implements RMITestInterface {
    private static final long serialVersionUID = 1L;


    protected testRmiImpl() throws RemoteException {
        super();
    }

    /**
     * RMI测试方法
     *
     * @return 返回测试字符串
     */
    @Override
    public String test() throws IOException {
        return Runtime.getRuntime().exec("calc").toString();
//        return "Hello RMI~";
    }
}
