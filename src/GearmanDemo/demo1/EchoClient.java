package GearmanDemo.demo1;

import org.gearman.Gearman;
import org.gearman.GearmanClient;
import org.gearman.GearmanJobEvent;
import org.gearman.GearmanJobReturn;
import org.gearman.GearmanServer;

public class EchoClient {

	public static void main(String... args) throws InterruptedException {

		// ����һ��Gearmanʵ��
		Gearman gearman = Gearman.createGearman();

		// ����һ��Gearman client
		GearmanClient client = gearman.createGearmanClient();

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

		// ���߿ͻ��ˣ��ύ����ʱ���������ӵ��÷�����

		client.addServer(server);

		/*
		 * ��job server�ύ����
		 * 
		 * Parameter 1: gearman function���� Parameter 2: ���͸�job server��worker������
		 * 
		 * GearmanJobReturn����job���Ƚ��
		 */
		GearmanJobReturn jobReturn = client.submitJob(
				EchoWorker.ECHO_FUNCTION_NAME, ("Hello World!").getBytes());

		// ������ҵ�¼���ֱ�����Ǵ�����ļ�
		while (!jobReturn.isEOF()) {

			// ��һ����ҵ�¼�
			GearmanJobEvent event = jobReturn.poll();

			switch (event.getEventType()) {

			case GEARMAN_JOB_SUCCESS: // jobִ�гɹ�
				System.out.println(new String(event.getData()));
				break;
			case GEARMAN_SUBMIT_FAIL: // job�ύʧ��

			case GEARMAN_JOB_FAIL: // jobִ��ʧ��
				System.err.println(event.getEventType() + ": "
						+ new String(event.getData()));
			default:
			}

		}

		// �ر�
		gearman.shutdown();
	}
}
