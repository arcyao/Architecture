package org.HashConsistencyAlgorithm.Algorithm;

import javax.swing.text.html.HTMLDocument;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 虚拟节点环
 */
public class VirtualNodeCycle {
    private Integer vitaualNodeCountPerServer;
    private TreeMap<Integer,String> vitualNodeMap;

    public VirtualNodeCycle(int nodeCountPerServer){
        this.vitaualNodeCountPerServer = nodeCountPerServer;
        vitualNodeMap = new TreeMap<>();
    }

    public void addNode(String nodeName)  {
        if(vitualNodeMap.containsValue(nodeName)){
            throw new IllegalArgumentException(String.format("{%s}节点已经存在",nodeName));
        }
        for(Integer i = 0; i< vitaualNodeCountPerServer; i++){
            Integer randomNum =  1 + (int)(Math.random() * Integer.MAX_VALUE);
            while (vitualNodeMap.containsKey(randomNum)){
                randomNum =  1 + (int)(Math.random() * Integer.MAX_VALUE);
            }
            vitualNodeMap.put(randomNum, nodeName);
        }
    }

    public void removeNode(String nodeName)  {
        if(!vitualNodeMap.containsValue(nodeName)){
            throw new IllegalArgumentException(String.format("{%s}节点不存在",nodeName));
        }
        Integer deleteCount = 0;
        Iterator<Map.Entry<Integer, String>>it = vitualNodeMap.entrySet().iterator();
        while(it.hasNext()){
            if (deleteCount>=vitaualNodeCountPerServer){
                break;
            }
            Map.Entry<Integer, String> entry = it.next();
            if(entry.getValue().equals(nodeName)){
                it.remove();
                deleteCount++;
            }
        }
    }

    public String getNodeName(Object Key)  throws NoSuchAlgorithmException{
        //Integer hashcode = Math.abs(Key.hashCode());
        Integer hashcode = Math.abs(encodeByMd5(Key.toString()).hashCode());
        Integer positionKey;
        if (vitualNodeMap.ceilingKey(hashcode) == null) {
            positionKey = vitualNodeMap.firstKey();
        }else
            positionKey  = vitualNodeMap.ceilingKey(hashcode);
        return  vitualNodeMap.get(positionKey);
    }

    /**利用MD5进行加密
     * @param str  待加密的字符串
     * @return  加密后的字符串
     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
     */
    private String encodeByMd5(String str) throws NoSuchAlgorithmException{
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        //加密后的字符串
        byte[] md5Str= md5.digest(str.getBytes());
        String newstr = Base64.getEncoder().encodeToString(md5Str);
        return newstr;
    }
}
