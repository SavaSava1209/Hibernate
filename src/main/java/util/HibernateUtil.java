package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 

public class HibernateUtil {
	private static final SessionFactory FACTORY;

    static {
        try {
            FACTORY = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
//  Thread local created another thread
//  在session factor 裡面再創造一個節點 session
    private static final ThreadLocal<Session> SESSION = new ThreadLocal<Session>() {
        @Override
        protected Session initialValue() {
            return FACTORY.openSession();
        }
    };

    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }

    public static Session currentSession() {
        Session s = SESSION.get();
        if (s == null) {
            s = FACTORY.openSession();
            SESSION.set(s);
        }
        return s;
    }

    public static void closeSession() {
        Session s = SESSION.get();
        SESSION.set(null);
        if (s != null) {
            s.close();
        }
    }
}

/*
SessionFacotry is a singleton
ThreadLocal is creating another thread
通常情况下，我们创建的变量是可以被任何一个线程访问并修改的。
而使用ThreadLocal创建的变量只能被当前线程访问，其他线程则无法访问和修改。
*/
