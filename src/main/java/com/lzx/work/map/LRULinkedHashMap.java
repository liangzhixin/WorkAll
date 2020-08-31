package com.lzx.work.map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @desc LinkedHashMap实现LRU算法
 * @author LIANGZHIXIN059
 */
public class LRULinkedHashMap<K,V> extends LinkedHashMap<K,V> {

    /**
     * 最大容量
     */
    private static final int LRU_MAX_CAPACITY = 10;

    /**
     * 容量
     */
    private int capacity;

    public LRULinkedHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor, true);
        this.capacity = LRU_MAX_CAPACITY;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LRULinkedHashMap<>(16, 0.75f);
        //a
        map.put("a", "a");
        //a b
        map.put("b", "b");
        //a b c
        map.put("c", "c");
        //b c a
        map.put("a", "a");
        //b c a d
        map.put("d", "d");
        //b c d a
        map.put("a", "a");
        //c d a b
        map.put("b", "b");
        //c d a b f
        map.put("f", "f");
        //c d a b f g
        map.put("g", "g");
        //c a b f g d
        map.get("d");
        map.forEach((k,v) -> System.out.print(v + ","));
        System.out.println();

        //c b f g d a
        map.get("a");
        map.forEach((k,v) -> System.out.print(v + ","));
        System.out.println();

        //b f g d a c
        map.get("c");
        map.forEach((k,v) -> System.out.print(v + ","));
        System.out.println();

        //f g d a c b
        map.get("b");
        map.forEach((k,v) -> System.out.print(v + ","));
        System.out.println();

        //f g d a c b h
        map.put("h", "h");
        map.forEach((k,v) -> System.out.print(v + ","));
        System.out.println();

        //f,g,d,a,c,b,h,j,k,l
        map.put("j", "j");
        map.put("k", "k");
        map.put("l", "l");
        map.forEach((k,v) -> System.out.print(v + ","));
        System.out.println();

        //g,d,a,c,b,h,j,k,l,m
        map.put("m", "m");
        map.forEach((k,v) -> System.out.print(v + ","));
        System.out.println();
    }
}
