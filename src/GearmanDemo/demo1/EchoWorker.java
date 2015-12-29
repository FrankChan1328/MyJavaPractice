package GearmanDemo.demo1;

import org.gearman.Gearman;
import org.gearman.GearmanFunction;
import org.gearman.GearmanFunctionCallback;
import org.gearman.GearmanServer;
import org.gearman.GearmanWorker;

public class EchoWorker implements GearmanFunction {

	// function name
	public static final String ECHO_FUNCTION_NAME = "reverse";

	// job server地址
	// Echo host 为安装了Gearman 并开启Gearman 服务的主机地址
	public static final String ECHO_HOST = "192.168.125.131";

	// job server监听的端口  默认的端口
	public static final int ECHO_PORT = 4730;

	public static void main(String... args) {

		// 创建一个Gearman实例
		Gearman gearman = Gearman.createGearman();

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

		// 创建一个Gearman的worker
		GearmanWorker worker = gearman.createGearmanWorker();

		// 告诉工人如何执行工作(主要实现了GearmanFunction接口)
		worker.addFunction(EchoWorker.ECHO_FUNCTION_NAME, new EchoWorker());

		// worker连接服务器
		worker.addServer(server);
	}

	@Override
	public byte[] work(String function, byte[] data,
			GearmanFunctionCallback callback) throws Exception {

		// work方法实现了GearmanFunction接口中的work方法,本实例中进行了字符串的反写
		if (data != null) {
			String str = new String(data);
			StringBuffer sb = new StringBuffer(str);
			return sb.reverse().toString().getBytes();
		} else {
			return "未接收到data".getBytes();
		}

	}

}