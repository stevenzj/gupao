/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test5;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.IOException;
import java.util.Arrays;

/**
 * @Title: CuratorWatcherDemo
 * @Description:
 * @Author zhujing
 * @Date 2019/7/14
 * @Version V1.0
 */
public class CuratorWatcherDemo {
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory
                .builder()
                .connectString("192.168.213.101:2181")
                .sessionTimeoutMs(1000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();

        curatorFramework.start();

        //addListner(curatorFramework);
        addChildListner(curatorFramework);

        System.in.read();
    }

    // 监听主节点变化
    private static void addListner(CuratorFramework curatorFramework) throws Exception {
        NodeCache nodeCache = new NodeCache(curatorFramework, "/watch", false);
        NodeCacheListener listener = () -> {
            System.out.println("节点变化");
            System.out.println(nodeCache.getCurrentData().getPath() + "---->" + new String(nodeCache.getCurrentData().getData()));
        };

        nodeCache.getListenable().addListener(listener);
        nodeCache.start();
    }

    // 监听子节点变化
    private static void addChildListner(CuratorFramework curatorFramework) throws Exception {
        PathChildrenCache cache = new PathChildrenCache(curatorFramework, "/watch", true);
        PathChildrenCacheListener listener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println(pathChildrenCacheEvent.getType() + "---->" + new String(pathChildrenCacheEvent.getData().getData()));
            }
        };
        cache.getListenable().addListener(listener);
        cache.start();
    }
}
