package com.sec.cwm.base.serialize;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class  testExternalize implements Externalizable {
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(username);
        out.writeObject(email);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.username = (String) in.readObject();
        this.email = (String) in.readObject();
    }
}
