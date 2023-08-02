package usc.HibernateDemo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.User;
import util.HibernateUtil;

public class Test2 {

	public static void main(String[] args) {
		Session session = HibernateUtil.currentSession();
        Query query = session.getNamedQuery("userQuery");
        query.setInteger("age", 20); // select * from sample where age>20
        List<User> list = query.list();
        for (User u : list) {
            System.out.println(u);
        }

        String sql = "select * from sample where name=:name";
        query = session.createSQLQuery(sql).addEntity(User.class);
        query.setString("name", "HBob");
        User user = (User) query.uniqueResult();
        System.out.print(user);
        HibernateUtil.closeSession();
        
//      String hql = "from User"; // hql
//		Query query = session.createQuery(hql);
//		List<User> list = query.list();
//		for (User u: list) {
//			System.out.println(u);
//		}
	}

}


/* getNameQuery: Obtain an instance of Query for a named query string defined in the mapping file.
session.createSQLQuery: create instane of query for the given sql query string
 
 
*/