package com.lzx.work.map;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * @desc TreeMap实现一致性哈希(不带虚拟节点)
 * @author LIANGZHIXIN059
 */
public class ConsistentHashLoadBalanceNoVirtualNode {

    /**
     * 真实节点
     */
    private TreeMap<Integer, String> nodes = new TreeMap<>();

    public ConsistentHashLoadBalanceNoVirtualNode(String[] nodes) {
        if(ArrayUtils.isEmpty(nodes)){
            throw new IllegalArgumentException("nodes不能为空");
        }
        Arrays.stream(nodes).forEach(node -> this.nodes.put(hashCode(node), node));
    }

    /**
     * @desc 找到key的下一个节点
     */
    public String selectNode(String key){
        if(key == null){
            throw new IllegalArgumentException("key不能为空");
        }
        int hashCode = hashCode(key);
        //ceilingEntry()的作用是得到比hashCode大的第一个Entry
        Map.Entry<Integer, String> entry = nodes.ceilingEntry(hashCode);
        return entry != null ? entry.getValue() : nodes.firstEntry().getValue();
    }

    private int hashCode(Object object){
        return object == null ? 0 : object.hashCode() & Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        String[] nodes = new String[]{"192.168.2.1:8080", "192.168.2.2:8080", "192.168.2.3:8080", "192.168.2.4:8080"};
        ConsistentHashLoadBalanceNoVirtualNode consistentHash = new ConsistentHashLoadBalanceNoVirtualNode(nodes);
        consistentHash.nodes.forEach((k,v) -> System.out.println(k + "," + v));
        System.out.println("==================");
        String key = "192.168.2.4:8079";
        System.out.println(key.hashCode() + "," + consistentHash.selectNode(key));
    }
}
