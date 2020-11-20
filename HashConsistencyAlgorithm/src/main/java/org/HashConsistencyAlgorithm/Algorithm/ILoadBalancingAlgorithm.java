package org.HashConsistencyAlgorithm.Algorithm;



public interface ILoadBalancingAlgorithm {
    //计算节点 添加或删除节点
    void calculationNode(String nodeName, CalculationNodeType type) throws Exception;

    //分配节点 返回节点名称
    String distributionNode(Object key)throws Exception;
}
