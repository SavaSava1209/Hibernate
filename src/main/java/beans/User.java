package beans;
// OR mapping bean -> DB table connect
public class User {
	private String name;
	
	private int age;
	
	public User() {
		super();
	}
	
	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
}

/*what is pojo?
POJO (Plain Old Java Object)
POJO就是一個Java物件只包含自己的屬性(private)和提取或儲存這些屬性的method(get、set)
Bean is a special type of POJO
Hibernate
using object-oriented programming to interact with a database
hibernate is ORM 將object 映射到 database 上
*/