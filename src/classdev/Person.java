package classdev;

/**
 * ��̬�ڲ��� Home���ڲ��࣬static ����. <br />
 * ˵����ֻ���ھ�̬�ڲ��������²��ܰ�static ���η�������ǰ�������κ�ʱ��static ���ǲ���������ġ�<br />
 * ��̬�ڲ�����ŵ㣺��ǿ���ڲ���ķ�װ�� �� ����˴���Ŀɶ��ԡ�<br />
 *
 */
public class Person {
	private String name;
	private Home home;

	public Person(String _name) {
		name = _name;
	}

	public void setHome(Home _home) {
		home = _home;
	}

	public static class Home {
		private String address;
		private String tel;

		public Home(String _address, String _tel) {
			address = _address;
			tel = _tel;
		}
	}

	public static void main(String[] args) {
		// �������������
		Person p = new Person("����");
		// ���������ļ�ͥ��Ϣ
		p.setHome(new Person.Home("�Ϻ�", "021"));
	}

}
