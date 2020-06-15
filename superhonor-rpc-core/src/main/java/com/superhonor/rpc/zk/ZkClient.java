package com.superhonor.rpc.zk;

import com.superhonor.rpc.conf.Consts;
import com.superhonor.rpc.util.RpcException;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.data.Stat;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * 服务消费者的zk客户端
 *
 * @author liuweidong
 */
@Slf4j
public class ZkClient {

    /**
     * zookeeper实例
     */
    private ZooKeeper zk;

    public ZkClient(String zkServeres, int timeout) {
        init(zkServeres, timeout);
    }

    // 连接ZK
    private void init(String zkServeres, int timeout) {
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
            // 查看服务器根节点是否存在
            Stat stat = zk.exists(Consts.ZK_SERVICE_ROOT, false);
            if (stat == null) {
                // 失败
                log.error("RPC服务根节点{}不存在！", Consts.ZK_SERVICE_ROOT);
                throw new RpcException("服务器未启动！");
            }
        } catch (Exception e) {
            throw new RpcException(e);
        }
    }

    /**
     * 获取zk
     * @return
     */
    public ZooKeeper getZk() {
        return zk;
    }

    /**
     * 关闭链接
     *
     * @return
     */
    public void closeZk() {
        try {
            if (Objects.nonNull(zk)) {
                zk.close();
            }
        } catch (InterruptedException e) {
            throw new RpcException(e);
        }
    }

}
