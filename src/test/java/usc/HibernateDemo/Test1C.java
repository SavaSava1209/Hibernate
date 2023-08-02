package usc.HibernateDemo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.User;
import util.HibernateUtil;

public class Test1C {

	public static void main(String[] args) {
		Session session = HibernateUtil.currentSession();
        String hql = "from User";
        Query query = session.createQuery(hql);

        query.setCacheable(true);
        List<User> list = query.list();
        for (User u : list) {
            System.out.println(u);
        }
        User user2 = (User) session.load(User.class, "HBob");
        System.out.println(user2);
        
        
        System.out.println("*******************************");
        List<User> list2 = query.list();
        for (User u : list2) {
            System.out.println(u);
        }
       
        HibernateUtil.closeSession();
        
      
	}

}

// set query result be cacheable
// the list2 doesn't need to access database 