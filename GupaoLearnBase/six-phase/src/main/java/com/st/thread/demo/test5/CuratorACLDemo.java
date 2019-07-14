/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test5;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: CuratorACLDemo
 * @Description:
 * @Author zhujing
 * @Date 2019/7/14
 * @Version V1.0
 */
public class CuratorACLDemo {

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory
                .builder()
                .connectString("192.168.213.101:2181")
                .sessionTimeoutMs(1000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();

        curatorFramework.start();

        List<ACL> aclList = new ArrayList<>();
        // scheme包括: ip/digest/world/super
        //ACL acl = new ACL(ZooDefs.Perms.READ, new Id("digest", "admin:admin"));
        ACL acl = new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.WRITE, new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin")));
        aclList.add(acl);
        curatorFramework.create().withMode(CreateMode.PERSISTENT).withACL(aclList).forPath("/auth");
    }
}
