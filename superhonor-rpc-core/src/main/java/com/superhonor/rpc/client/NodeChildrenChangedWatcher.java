package com.superhonor.rpc.client;

import com.superhonor.rpc.util.HashMultimap;
import com.superhonor.rpc.util.RpcException;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;

import java.util.HashSet;
import java.util.List;

/**
 * 子节点变化监听器
 * @author liuweidong
 */
@Slf4j
public class NodeChildrenChangedWatcher implements Watcher {

    /**
     * 服务列表
     */
    private HashMultimap<String, String> servicesMap;

    /**
     * zookeeper实例
     */
    private ZooKeeper zk;

    public NodeChildrenChangedWatcher(HashMultimap<String, String> servicesMap, ZooKeeper zk) {
        this.servicesMap = servicesMap;
        this.zk = zk;
    }

    @Override
    public void process(WatchedEvent event) {
        // 如果发生变化的在服务器节点下,更新节点信息
        if (event.getType() == EventType.NodeChildrenChanged) {
            try {
                String path = event.getPath(),
                        serviceInterfaceName = path.substring(path.lastIndexOf("/") + 1);
                List<String> beanInfos = zk.getChildren(path, this);
                servicesMap.put(serviceInterfaceName, new HashSet<>(beanInfos));
                log.info("注册中心节点{}发生变化，触发消费者服务列表更新...", path);
            } catch (Exception e) {
                throw new RpcException(e);
            }
        }
    }

}
