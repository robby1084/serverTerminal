package org.iti.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.iti.rmi.service.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceProvider.class);
	
	// 用于等待synConnected事件触发后继续执行当前进程
	private CountDownLatch latch = new CountDownLatch(1);
	
	// 发布RMI服务并注册RMI地址到Zookeeper中
	public void publish(Remote remote,String host,int port){
		// 发布RMI服务并返回RMI地址
		String url = publishService(remote, host, port);
		if (url != null) {
			// 连接Zookeeper服务器并获取Zookeeper对象
			ZooKeeper zk = connectServer();
			if (zk != null) {
				// 创建ZNode并将RMI地址放到ZNode上
				createNode(zk, url);
			}
		}
	}
	
	// 发布RMI服务
	private String publishService(Remote remote,String host,int port){
		String url = null;
		try {
			url = String.format("rmi://%s:%d/%s", host,port,remote.getClass().getName());
			LocateRegistry.createRegistry(port);
			Naming.rebind(url, remote);
			LOGGER.debug("publish rmi service (url:{})",url);
		} catch (RemoteException e) {
			LOGGER.error("",e);
		} catch (MalformedURLException e) {
			LOGGER.error("",e);
		}
		return url;
	}
	
	// 连接Zookeeper服务器
	private ZooKeeper connectServer() {

		ZooKeeper zk = null;
		try {
			zk = new ZooKeeper(Constant.ZK_CONNECT_STRING,
					Constant.ZK_SESSION_TIMEOUT, new Watcher() {

						@Override
						public void process(WatchedEvent event) {

							if (event.getState() == Event.KeeperState.SyncConnected) {
								// 唤醒当前线程
								latch.countDown();
							}
						}
					});
			// 使当前线程处于等待状态
			latch.await();
		} catch (Exception e) {
			LOGGER.error("",e);
		}
		return zk;
	}
	
	// 创建ZNode
	private void createNode(ZooKeeper zk, String url) {
		try {
			byte[] data = url.getBytes();
			// 创建一个临时且有序的ZNode
			String path = zk.create(Constant.ZK_PROVIDER_PATH, data,
					ZooDefs.Ids.OPEN_ACL_UNSAFE,
					CreateMode.EPHEMERAL_SEQUENTIAL);
			LOGGER.debug("create zookeeper node ({} => {})", path, url);
		} catch (Exception e) {
			LOGGER.error("", e);
		}
	}
	
}
