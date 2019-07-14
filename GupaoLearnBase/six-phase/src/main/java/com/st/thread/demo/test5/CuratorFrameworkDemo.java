/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test5;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * @Title: CuratorFrameworkDemo
 * @Description:
 * @Author zhujing
 * @Date 2019/7/13
 * @Version V1.0
 */
public class CuratorFrameworkDemo {

    public static void main(String[] args) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory
                .builder()
                .connectString("192.168.213.101:2181")
                .sessionTimeoutMs(1000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();

        curatorFramework.start();

        //create(curatorFramework);

        //update(curatorFramework);

        delete(curatorFramework);
    }

    private static void create(CuratorFramework curatorFramework){
        try {
            curatorFramework
                    .create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath("/data/data_1", "test111".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void update(CuratorFramework curatorFramework){
        try {
            curatorFramework.setData().forPath("/data/data_1", "test222".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void delete(CuratorFramework curatorFramework) {

        try {
            // 只会删除data_1节点, data节点不会被删除
            //curatorFramework.delete().forPath("/data/data_1");

            // 递归删除data及data节点下的所有子节点
            //curatorFramework.delete().deletingChildrenIfNeeded().forPath("/data");

            // 根据dataVersion进行删除, dataVersion不对删除不了, data节点下有子节点删除不了
            //curatorFramework.delete().withVersion(0).forPath("/data");

            // 先获取Stat, 再根据Stat中的version进行删除, 先获取再删除
            Stat stat = new Stat();
            curatorFramework.getData().storingStatIn(stat);
            curatorFramework.delete().withVersion(stat.getVersion()).forPath("/data/data_1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
