package org.iti.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.tomcat.util.threads.CounterLatch;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event;
import org.iti.rmi.service.Constant;

public class WatcherTest {

	public CountDownLatch latch = new CountDownLatch(1);
	
	public static void main(String[] args) throws KeeperException,
			InterruptedException {

		ZooKeeper zk = connectServer();

		// 创建一个目录节点
		zk.create("/testRootPath", "testRootPath".getBytes(),
				Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		// 创建一个子目录节点
		zk.create("/testRootPath/testChildPathOne",
				"testChildPathOne".getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT);

		// 取出子目录节点列表
		System.out.println(zk.getChildren("/testRootPath", true));
		// 修改子目录节点数据
		zk.setData("/testRootPath/testChildPathOne", "modifyChildDataOne".getBytes(),-1);
		System.out.println("目录节点状态：["+zk.exists("/testRootPath",true)+"]"); 
		
		// 创建另外一个子目录节点
		 zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(), 
		   Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
		 System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo",true,null))); 
		 // 删除子目录节点
		 zk.delete("/testRootPath/testChildPathTwo",-1); 
		 zk.delete("/testRootPath/testChildPathOne",-1); 
		 // 删除父目录节点
		 zk.delete("/testRootPath",-1); 
		 // 关闭连接
		 zk.close();
	}
	
	public static ZooKeeper connectServer() {

		ZooKeeper zk = null;
		try {
			zk = new ZooKeeper(Constant.ZK_CONNECT_STRING,
					Constant.ZK_SESSION_TIMEOUT, new Watcher() {

						@Override
						public void process(WatchedEvent event) {

							System.out.println("已经触发了"+event.getType()+"事件");
							if (event.getState() == Event.KeeperState.SyncConnected) {
								// 唤醒当前线程
								//latch.countDown();
							}
						}
					});
			// 使当前线程处于等待状态
			//latch.await();
		} catch (Exception e) {
			System.out.println(e);
		}
		return zk;
	}
}
