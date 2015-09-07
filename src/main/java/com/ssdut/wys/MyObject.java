package com.ssdut.wys;

public class MyObject extends BFObject{
    private String value;
    
    public MyObject(){}
    
    public MyObject(String val){
        this.value=val;
    }
    
    public String getValue(){
        return value;
    }
    public void setValue(String val){
        value=val;
    }
    private String md5(){
        try{
            return Base64Decrypt.encryptMD5(value.getBytes());
        }catch(Exception e){
            e.printStackTrace();
            return value;
        }
    }
    @Override
    public String getKey() {
        return md5();
    }
}
