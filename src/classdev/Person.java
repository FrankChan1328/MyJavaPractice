package classdev;

/**
 * 静态内部类 Home：内部类，static 修饰. <br />
 * 说明：只有在静态内部类的情况下才能把static 修饰符放在类前，其它任何时候static 都是不能修饰类的。<br />
 * 静态内部类的优点：加强了内部类的封装性 和 提高了代码的可读性。<br />
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
		// 定义张三这个人
		Person p = new Person("张三");
		// 设置张三的家庭信息
		p.setHome(new Person.Home("上海", "021"));
	}

}
