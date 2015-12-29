package GearmanDemo.demo1;

import org.gearman.Gearman;
import org.gearman.GearmanFunction;
import org.gearman.GearmanFunctionCallback;
import org.gearman.GearmanServer;
import org.gearman.GearmanWorker;

public class EchoWorker implements GearmanFunction {

	// function name
	public static final String ECHO_FUNCTION_NAME = "reverse";

	// job server��ַ
	// Echo host Ϊ��װ��Gearman ������Gearman �����������ַ
	public static final String ECHO_HOST = "192.168.125.131";

	// job server�����Ķ˿�  Ĭ�ϵĶ˿�
	public static final int ECHO_PORT = 4730;

	public static void main(String... args) {

		// ����һ��Gearmanʵ��
		Gearman gearman = Gearman.createGearman();

		/*
		 * ����һ��jobserver
		 * 
		 * Parameter 1: job server��IP��ַ Parameter 2: job server�����Ķ˿�
		 * 
		 * job server�յ�client��job��������ַ���ע��worker
		 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp
		 * ;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*
		 */
		GearmanServer server = gearman.createGearmanServer(
				EchoWorker.ECHO_HOST, EchoWorker.ECHO_PORT);

		// ����һ��Gearman��worker
		GearmanWorker worker = gearman.createGearmanWorker();

		// ���߹������ִ�й���(��Ҫʵ����GearmanFunction�ӿ�)
		worker.addFunction(EchoWorker.ECHO_FUNCTION_NAME, new EchoWorker());

		// worker���ӷ�����
		worker.addServer(server);
	}

	@Override
	public byte[] work(String function, byte[] data,
			GearmanFunctionCallback callback) throws Exception {

		// work����ʵ����GearmanFunction�ӿ��е�work����,��ʵ���н������ַ����ķ�д
		if (data != null) {
			String str = new String(data);
			StringBuffer sb = new StringBuffer(str);
			return sb.reverse().toString().getBytes();
		} else {
			return "δ���յ�data".getBytes();
		}

	}

}