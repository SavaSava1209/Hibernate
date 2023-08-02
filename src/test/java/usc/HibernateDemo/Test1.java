package usc.HibernateDemo;



import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.User;
import util.HibernateUtil;

public class Test1 {

	public static void main(String[] args) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		User user = new User("Jenny", 33); // transient
//		User user1 = new User("Jason", 32);
		session.save(user); // persistent
//		session.save(user1);
		tx.commit();
		String hql = "from User"; // hql
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		for (User u: list) {
			System.out.println(u);
		}
		HibernateUtil.closeSession();
	}
}


/*
save is saving to the database
*/
