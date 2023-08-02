package usc.HibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import beans.User;

public class Test1B {

	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure();
        SessionFactory factory = cfg.buildSessionFactory();
        // First session
        Session session1 = factory.openSession();
        User user1 = (User) session1.load(User.class, "Tommy");
        System.out.println("1 " + user1);
        session1.close();

        // Second session
        Session session2 = factory.openSession();
        // The query is running again because of no caching after 6 seconds
        User user2 = (User) session2.load(User.class, "HBob");
        System.out.println("2 " + user2);
        session2.close();
        
        // Third session
        try {
            Thread.sleep(1000); // Delay 6 seconds to make cache expire
        } catch (Exception e) {
            e.printStackTrace();
        }
        Session session3 = factory.openSession();
        // The query is not running because of caching on SessionFactory
        User user3 = (User) session3.load(User.class, "HBob");
        User user5 = (User) session3.load(User.class, "HBob");
        System.out.println("3 " + user3);
        System.out.println("user2==user3? " + (user2 == user3)); // false
        System.out.println("user5==user3? " + (user5 == user3)); // true
        // there is no new String so all reference to string pool name
        System.out.println(user2.getName() == user3.getName());
        
        session3.close();

        Session session4 = factory.openSession();
        User user4 = (User) session4.load(User.class, "HBob");
        System.out.println("user3==user4? " + (user3 == user4));
        session4.close();
	}
}

/*
 timeToIdleSeconds - The maximum number of seconds an element can exist in the 
 cache without being accessed
 1st level cache -- session cache (is enable by default)
 
 
 
 https://so.csdn.net/so/search?spm=1001.2101.3001.4498&q=Hibernate%E7%BC%93%E5%AD%98&t=&u=
 */
