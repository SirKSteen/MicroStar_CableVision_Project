package factories;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// this class will store the single connection for hibernate session factory
public class HibernateConnectorSessionFactory {

	private static SessionFactory sessionFactory = null;
	
	public static SessionFactory getHibernateSessionFactory() {
		
		//Session factory stores data in the hibernate application
		//only one session factory is needed per application
		
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			return sessionFactory;
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	}
}


