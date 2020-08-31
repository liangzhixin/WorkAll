package com.lzx.work.map;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @desc TreeMap实现一致性哈希(带虚拟节点)
 * @author LIANGZHIXIN059
 */
public class ConsistentHashLoadBalanceWithVirtualNode {

    /**
     * 虚拟节点(key为hashcode,value为虚拟节点)
     */
    private TreeMap<Integer, String> virtualNodes = new TreeMap<>();

    /**
     * key为虚拟节点,value为真实节点
     */
    private Map<String, String> relationMap = new HashMap<>();

    /**
     * 每个真实节点对应虚拟节点的数量
     */
    private int replicCnt;

    /**
     * @desc 初始化虚拟节点
     */
    public ConsistentHashLoadBalanceWithVirtualNode(String[] nodes, int replicCnt) {
        if (ArrayUtils.isEmpty(nodes)) {
            throw new IllegalArgumentException("nodes不能为空");
        } else if (replicCnt <= 0) {
            throw new IllegalArgumentException("replicCnt必须大于0");
        }
        this.replicCnt = replicCnt;
        Arrays.stream(nodes).forEach(node -> {
            for (int i = 1; i <= replicCnt; i++) {
                String virtualNode = node + "&&" + i;
                //把虚拟节点放入virtualNodes
                virtualNodes.put(hashCode(virtualNode), virtualNode);
                //把虚拟节点以及对应的真实节点放入relationMap
                relationMap.put(virtualNode, node);
            }
        });
    }

    private int hashCode(Object key) {
        int h;
        return (key == null) ? 0 : ((h = key.hashCode()) ^ (h >>> 16)) & Integer.MAX_VALUE;
    }

    /**
     * @desc 找到key的下一个节点
     */
    public String selectNode(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key不能为空");
        }
        Map.Entry<Integer, String> entry = virtualNodes.ceilingEntry(hashCode(key));
        //找到下一个虚拟节点
        String virtualNode = entry != null ? entry.getValue() : virtualNodes.firstEntry().getValue();
        //根据虚拟节点找到对应的真实节点
        return relationMap.get(virtualNode);
    }

    public static void main(String[] args) {
        String[] nodes = new String[]{"192.168.2.1:8080", "192.168.2.2:8080", "192.168.2.3:8080", "192.168.2.4:8080"};
        ConsistentHashLoadBalanceWithVirtualNode consistentHash = new ConsistentHashLoadBalanceWithVirtualNode(nodes, 2);
        consistentHash.virtualNodes.forEach((k, v) -> System.out.println(k + "," + v));
        System.out.println("==================");
        String key = "192.168.2.4:8080&&3";
        System.out.println(((key.hashCode() ^ (key.hashCode()  >>> 16)) & Integer.MAX_VALUE) + "," + consistentHash.selectNode(key));
    }
}
