package com.ssdut.wys.function;

import com.ssdut.wys.BFunction;

public class MyFunction implements BFunction{
    public long hash(String key) {
        char[] chars = key.toCharArray();
        int len = chars.length;
        long res = 0;
        for (int i = 0; i < len; i++) {
            res = res * 33 + chars[i];
        }

        return res&0x7FFFFFFF;
    }
}
