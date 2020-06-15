package com.superhonor.rpc.zk;

import com.superhonor.rpc.conf.Consts;
import com.superhonor.rpc.util.RpcException;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * 服务提供者的zk客户端
 *
 * @author liuweidong
 */
@Slf4j
public class ZkServer {

    /**
     * zk服务
     */
    private ZooKeeper zk;

    public ZkServer(String zkServeres, int timeout) {
        initZk(zkServeres, timeout);
    }

    /**
     * 初始化ZK
     * @param zkServeres
     * @param timeout
     */
    private void initZk(String zkServeres, int timeout) {
        try {
            CountDownLatch connectedSignal = new CountDownLatch(1);
            zk = new ZooKeeper(zkServeres, timeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    // 建立连接
                    if (event.getState() == KeeperState.SyncConnected) {
                        connectedSignal.countDown();
                    }
                }
            });
            // 等待连接建立完毕
            connectedSignal.await();
            Stat stat = zk.exists(Consts.ZK_SERVICE_ROOT, false);
            if (Objects.isNull(stat)) {
                // 创建根节点
                zk.create(Consts.ZK_SERVICE_ROOT, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            log.error("初始化ZK服务异常！", e);
            throw new RpcException(e);
        }
    }

    /**
     * 获取ZooKeeper
     * @return
     */
    public ZooKeeper getZk() {
        return zk;
    }
}
