package org.HashConsistencyAlgorithm.LoadBalancingService;

import org.HashConsistencyAlgorithm.Algorithm.CalculationNodeType;
import org.HashConsistencyAlgorithm.Algorithm.ILoadBalancingAlgorithm;
import org.HashConsistencyAlgorithm.Server.ServerData;

import java.util.HashMap;
import java.util.Map;

public class LoadBalancing implements ILoadBalancingService {
    //负载均衡算法控制器
    private ILoadBalancingAlgorithm algorithmControl;
    //服务器节点群
    private HashMap<String, ServerData> serverNodes;

    public LoadBalancing(ILoadBalancingAlgorithm algorithm){
        this.algorithmControl = algorithm;
        serverNodes = new HashMap<>();
    }

    @Override
    public void addNode(String nodeName) throws Exception {
        algorithmControl.calculationNode(nodeName, CalculationNodeType.ADD);
        serverNodes.put(nodeName, new ServerData());
    }

    @Override
    public void removeNode(String nodeName)throws Exception {
        algorithmControl.calculationNode(nodeName, CalculationNodeType.REMOVE);
        serverNodes.remove(nodeName);
    }

    @Override
    public void writeData(Object key,  Object value)throws Exception{
        String nodeName = algorithmControl.distributionNode(key);
        //System.out.println(String.format("key:{%s},nodename:{%s}", key.toString(), nodeName));
        serverNodes.get(nodeName).write(key,value);
    }

    @Override
    public Object readData(Object key) throws Exception{
        String nodeName = algorithmControl.distributionNode(key);
        return serverNodes.get(nodeName).read(key);
    }

    public void evaluationerformance(int vitualNodesPerServer){
        int serverNodeCount = serverNodes.size();
        int totalDataCount = 0;
        for (Map.Entry<String, ServerData> entry : serverNodes.entrySet()) {
            totalDataCount +=entry.getValue().GetDataCount();
        }

        //计算标准差
        double std = 0;
        double avg =totalDataCount/serverNodeCount;
        for (Map.Entry<String, ServerData> entry : serverNodes.entrySet()) {
            String nodeName = entry.getKey();
            int nodeDataCount = entry.getValue().GetDataCount();
            //System.out.println(String.format("ServerNode:{%s},记录数据个数:{%d}", nodeName,nodeDataCount));
            std += Math.abs(avg - (double) nodeDataCount) * Math.abs(avg - (double) nodeDataCount);
        }
        std = std / serverNodeCount;
        std = Math.sqrt(std);

        System.out.println(String.format("服务器数:{%d},每个服务器对应的虚拟节点数：{%d}，记录数据个数:{%d},标准差为:{%f}",
                serverNodeCount, vitualNodesPerServer, totalDataCount, std));
    }
}
