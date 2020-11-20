package org.HashConsistencyAlgorithm.LoadBalancingService;

public interface ILoadBalancingService {

    void addNode(String nodeName) throws Exception;

    void removeNode(String nodeName) throws Exception;

    void writeData(Object key, Object value)throws Exception;

    Object readData(Object key)throws Exception;
}
