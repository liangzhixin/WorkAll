package com.lzx.work.classUse;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class ImmutableMap_Use {

    /**
     * ImmutableMap:
     *      1.key和value不能为null
     *      2.不可修改
     */
    public static void main(String[] args) {
        //初始化方式一,键值对<=5个
        Map<String, Object> map = ImmutableMap.of(
                "k1", "v1",
                "k2", "v2",
                "k3", "v3",
                "k4", "v4",
                "k5", "v5");

        //初始化方式二：超过5个
        map = ImmutableMap.<String, Object>builder()
                .put("k1", "k1")
                .put("k2", "k2")
                .put("k3", "k3")
                .put("k4", "k4")
                .put("k5", "k5")
                .put("k6", "k6")
                .build();

        map.forEach((k, v) -> System.out.println(k + "," + v));
    }
}
