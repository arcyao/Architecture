package org.HashConsistencyAlgorithm;

import org.HashConsistencyAlgorithm.Algorithm.HashConsistencyAlgorithm;
import org.HashConsistencyAlgorithm.LoadBalancingService.LoadBalancing;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            int vitualNodesPerServer = 150;
            int totalRecordNum = 1000000;
            int serverCount = 10;
            LoadBalancing loadBalancing = new LoadBalancing(new HashConsistencyAlgorithm(vitualNodesPerServer));
            //添加服务器节点
            for (int i = 1; i<=serverCount;i++){
                loadBalancing.addNode(String.format("Server%d",i));
            }

            //写入100w数据
            long startTime=System.currentTimeMillis();
            for(int i = 1; i <= totalRecordNum; i++){
                loadBalancing.writeData(i, String.format("TestData%s",i));
            }
            long endTime=System.currentTimeMillis();
            System.out.println(String.format("服务器数:{%d},每个服务器对应的虚拟节点数：{%d},写入%d数据耗时:{%d}ms",
                    serverCount, vitualNodesPerServer, totalRecordNum,endTime - startTime ));
            //读出数据
            if (totalRecordNum<=500) {
                for (int i = 1; i <= totalRecordNum; i++) {
                    Object o = loadBalancing.readData(i);
                    System.out.println(String.format("key:{%d},value:{%s}", i, o.toString()));
                }
            }

            //性能评价
            loadBalancing.evaluationerformance(vitualNodesPerServer);

            //删除服务器节点
            for (int i = 1; i<=serverCount;i++){
                loadBalancing.removeNode(String.format("Server%d",i));
            }
        }
        catch (Exception e){
            System.out.println( "Test Fail!" );
        }

        System.out.println( "Test End!" );
    }
}
