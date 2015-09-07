package com.ssdut.wys;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class BloomFilter {
    public static final int INIT_LENGTH = 256;
    private List<BFunction> functions;
    private BitSet bits = null;
    
    public BloomFilter(int length,List<BFunction> funcs){
        init(length,funcs);
    }
    
    public BloomFilter(List<BFunction> funcs){
        init(INIT_LENGTH,funcs);
    }

    private void init(int length, List<BFunction> funcs) {
        bits=new BitSet(length);
        functions = funcs;
    }

    public void add(BFObject obj) {
        String key = obj.getKey();
        for (int i = 0; i < functions.size(); i++) {
            long hashVal=functions.get(i).hash(key);
            bits.set(getBucketByHash(hashVal));
        }
    }
    
    public boolean isIn(BFObject obj){
        String key=obj.getKey();
        for(int i=0;i<functions.size();i++){
            long hashVal=functions.get(i).hash(key);
            if(!bits.get(getBucketByHash(hashVal))){
                return false;
            }
        }
        return true;
    }

    public long hash(String data) {
        char[] chars = data.toCharArray();
        int len = chars.length;
        long res = 0;
        for (int i = 0; i < len; i++) {
            res = res * 33 + chars[i];
        }

        return res;
    }

    public int getBucketByHash(long hashVal) {
        return (int) hashVal % INIT_LENGTH;
    }
}
