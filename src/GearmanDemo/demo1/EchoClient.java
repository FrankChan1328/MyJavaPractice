package GearmanDemo.demo1;

import org.gearman.Gearman;
import org.gearman.GearmanClient;
import org.gearman.GearmanJobEvent;
import org.gearman.GearmanJobReturn;
import org.gearman.GearmanServer;

public class EchoClient {

	public static void main(String... args) throws InterruptedException {

		// 创建一个Gearman实例
		Gearman gearman = Gearman.createGearman();

		// 创建一个Gearman client
		GearmanClient client = gearman.createGearmanClient();

		/*
		 * 创建一个jobserver
		 * 
		 * Parameter 1: job server的IP地址 Parameter 2: job server监听的端口
		 * 
		 * job server收到client的job，并将其分发给注册worker
		 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp
		 * ;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*
		 */
		GearmanServer server = gearman.createGearmanServer(
				EchoWorker.ECHO_HOST, EchoWorker.ECHO_PORT);

		// 告诉客户端，提交工作时它可以连接到该服务器

		client.addServer(server);

		/*
		 * 向job server提交工作
		 * 
		 * Parameter 1: gearman function名字 Parameter 2: 传送给job server和worker的数据
		 * 
		 * GearmanJobReturn返回job发热结果
		 */
		GearmanJobReturn jobReturn = client.submitJob(
				EchoWorker.ECHO_FUNCTION_NAME, ("Hello World!").getBytes());

		// 遍历作业事件，直到我们打到最后文件
		while (!jobReturn.isEOF()) {

			// 下一个作业事件
			GearmanJobEvent event = jobReturn.poll();

			switch (event.getEventType()) {

			case GEARMAN_JOB_SUCCESS: // job执行成功
				System.out.println(new String(event.getData()));
				break;
			case GEARMAN_SUBMIT_FAIL: // job提交失败

			case GEARMAN_JOB_FAIL: // job执行失败
				System.err.println(event.getEventType() + ": "
						+ new String(event.getData()));
			default:
			}

		}

		// 关闭
		gearman.shutdown();
	}
}
