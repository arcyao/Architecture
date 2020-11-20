package org.HashConsistencyAlgorithm.Server;

import java.util.HashMap;

/**
 * 服务器数据
 */
public class ServerData {

    private HashMap<Object, Object> data = new HashMap<>();
    public void write(Object key, Object value){
        data.put(key,value);
    }

    public Object read(Object key){
        return data.get(key);
    }

    public Integer GetDataCount(){
        return data.size();
    }
}
