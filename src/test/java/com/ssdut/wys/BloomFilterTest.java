package com.ssdut.wys;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.ssdut.wys.function.BKDRFunction;
import com.ssdut.wys.function.MyFunction;
import com.wys.model.APHash;

public class BloomFilterTest {
    private Random rand;
    private BloomFilter boom;
    private List<BFunction> functions;
    private String formatStr="add %s into bloomfilter";
    
    @Before
    public void init(){
        rand=new Random(System.currentTimeMillis());
        functions=new ArrayList<BFunction>();
        functions.add(new BKDRFunction());
        functions.add(new MyFunction());
        boom=new BloomFilter(functions);
    }
    
    @Test
    public void testFilter(){
        ArrayList<BFObject> list=new ArrayList<BFObject>();
        for(int i=0;i<10;i++){
            MyObject bo=new MyObject(getRandomString());
            list.add(bo);
            boom.add(bo);
            System.out.println(String.format(formatStr, bo.getValue()));
        }
        
        System.out.println();
        for(int i=0;i<10;i++){
            MyObject bo=(MyObject)list.get(i);
            isInBloomFilter(boom,bo);
        }
        
        String randomStr=getRandomString();
        MyObject randMo=new MyObject(randomStr);
        isInBloomFilter(boom,randMo);
    }
    
    private void isInBloomFilter(BloomFilter filter,MyObject obj){
        if(filter.isIn(obj)){
            System.out.println("Object "+obj.getValue()+" is in bloomfilter");
        }else{
            System.out.println("Object "+obj.getValue()+" is not in bloomfilter");
        }
    }
    
    private String getRandomString(){
        int len=rand.nextInt(100);
        StringBuffer sb=new StringBuffer();
        
        for(int i=0;i<len;i++){
            int ival=rand.nextInt(26)+96;
            sb.append((char)ival);
        }
        
        return sb.toString();
    }
}
