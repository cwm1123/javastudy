package com.sec.cwm.base.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMITestInterface extends Remote {

    /**
     * RMI测试方法
     *
     * @return 返回测试字符串
     */
    String test() throws RemoteException;

}
