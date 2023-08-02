package usc.HibernateDemo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import beans.User;
import util.HibernateUtil;

public class Test2A {

	public static void main(String[] args) {
		Session session = HibernateUtil.currentSession();
        Query query = session.getNamedQuery("userSP");
        List<User> list = query.list();
        for (User u:list) {
            System.out.println(u);
        }

        System.out.println("************************");
        //Connection conn = session.connection();
        session.doWork(new Work() {
            public void execute(Connection conn) throws SQLException {
             
                String sp = "{call queryuser()}";
                CallableStatement cs = conn.prepareCall(sp);
//                cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.execute();
//                ResultSet rs = (ResultSet)cs.getObject(1);
//                while (rs.next()) {
//                    System.out.println(rs.getString("name") + "\t" + rs.getInt("age"));
//                }
//                rs.close();
            }
        });
        HibernateUtil.closeSession();

	}

}

/*
You can create an object of the CallableStatement (interface) using the prepareCall() 
method of the Connection interface. This method accepts a string 
variable representing a query to call the stored procedure and
returns a CallableStatement object.
*/

