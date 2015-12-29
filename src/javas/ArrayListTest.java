package javas;
import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {

	public static void main(String[] args) {
		List<TObject> list = new ArrayList<TObject>();
	
		int i = 0;
		try {
			System.out.println("i =" + i);
			for (i=0;; i++) {
				TObject ob = new TObject();
				ob.setGroup("test");
				ob.setNum(11L);
				ob.setInm(i);
				list.add(ob);
			}
		} catch (Throwable  e) {
			System.out.println("i =" + i);
		}
	}
}

class TObject{
	private String test;
	
	private Long num;
	
	private String group;
	
	private int inm;

	public int getInm() {
		return inm;
	}

	public void setInm(int inm) {
		this.inm = inm;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
}
