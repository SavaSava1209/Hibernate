package usc.HibernateDemo;


import org.hibernate.Session;

import beans.User;
import util.HibernateUtil;

public class Test2B {

	public static void main(String[] args) {
		// load and get
		Session session = HibernateUtil.currentSession();
		
		// load
		// user1 is a proxy object. but it is not null
		// throw runtime exception if not exists in DB.
		User user1 = (User) session.load(User.class, "HBob");		
		System.out.println(user1.getClass().getName());
		System.out.println(user1 == null);
		System.out.println("User1 "+ user1);
		System.out.println(user1.getClass().getName());
		
		System.out.println("************************");
		
		// get
		// return null if not exists in DB
		User user2 = (User) session.get(User.class, "HAlice");
		System.out.println(user2.getClass().getName());
		System.out.println(user2 == null);
		System.out.println("User2 "+ user2); // toString trigger hql
		System.out.println(user2.getClass().getName());

	}

}
/*
 load => lazy-loading 真正需要的時候才會跟database connect
 get 會一開始就去database找資料了 
 but no idea why this hibernate shows interaction at load
 proxy object => like an empty, fake object
 get() loads the data as soon as it’s called 
 whereas load() returns a proxy object and loads data only when it’s actually required, 
 so load() is better because it support lazy loading.
 Since load() throws exception when data is not found, we should use it only when 
 we know data exists.
 */