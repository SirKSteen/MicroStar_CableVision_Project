package factories;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// this class will store the single connection for hibernate session factory
public class HibernateConnectorSessionFactory {

	
	private SessionFactory sessionFactory;
	
	public HibernateConnectorSessionFactory() {
		this.sessionFactory = null;
	}




	public SessionFactory getSessionFactory() {
		
		//Session factory stores data in the hibernate application
		//only one session factory is needed per application
		this.sessionFactory = new Configuration().configure().buildSessionFactory();
		return sessionFactory;
	}
}


