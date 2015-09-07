package com.ssdut.wys.function;

import com.ssdut.wys.BFunction;
import com.wys.model.BKDRHash;

public class BKDRFunction implements BFunction{

    public long hash(String key) {
        BKDRHash hash=new BKDRHash();
        return hash.hash(key);
    }

}
