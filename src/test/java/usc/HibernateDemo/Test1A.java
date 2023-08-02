package usc.HibernateDemo;

import org.hibernate.Session;
import org.hibernate.Transaction;


import beans.User;
import util.HibernateUtil;

public class Test1A {

	public static void main(String[] args) {
		Session session = HibernateUtil.currentSession();
		// user: Persistent
		User user = (User)session.load(User.class, "HBob");
		System.out.println("load " + user); //User [name=HBob, age=34]
		HibernateUtil.closeSession();
		// when session is closed, user is Detached. but the object can still be modified 
		user.setAge(6);
		
		Session session2 = HibernateUtil.currentSession();
		
		session2.merge(user); // persistent
		System.out.println("merge " + user);
		user = (User) session2.load(User.class, "HBob");
		System.out.println(user);
		Transaction tx = session2.beginTransaction();
		tx.commit(); // sync session(HBob, 18) and database(Hbob, 48). rollback
		HibernateUtil.closeSession();
	
	}

}

/*
load and get are getting data from database

load return a persistent instance and it assumes instance exists in the session

merge: copy the state of given object to persistent object with the same identifier
會將在detached status object turned to persistent object and then copy the object to the session
https://www.baeldung.com/hibernate-save-persist-update-merge-saveorupdate
*/
