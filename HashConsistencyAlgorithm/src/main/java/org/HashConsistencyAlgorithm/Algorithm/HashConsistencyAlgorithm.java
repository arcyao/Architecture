package org.HashConsistencyAlgorithm.Algorithm;

import java.util.ArrayList;
import java.util.List;

public class HashConsistencyAlgorithm implements ILoadBalancingAlgorithm {
    //每个节点的虚拟节点数
    private Integer virtualNodes4PerNode;

    //真实节点的名字
    //private List<String> nodeNames;

    //哈希环
    private VirtualNodeCycle hashCycle;

    public HashConsistencyAlgorithm(Integer virtualNodes4PerNode) {
        this.virtualNodes4PerNode = virtualNodes4PerNode;
        hashCycle = new VirtualNodeCycle(virtualNodes4PerNode);
    }

    @Override
    public void calculationNode(String nodeName, CalculationNodeType type) throws Exception {
        if(type == CalculationNodeType.ADD){
            hashCycle.addNode(nodeName);
        }
        else if(type == CalculationNodeType.REMOVE){
            hashCycle.removeNode(nodeName);
        }
    }

    @Override
    public String distributionNode(Object key) throws Exception {
        return hashCycle.getNodeName(key);
    }
}
