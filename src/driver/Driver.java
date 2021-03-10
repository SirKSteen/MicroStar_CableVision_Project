package driver;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import models.User;
import utils.Role;

public class Driver {

	public static void main(String[] args) {
		
		User user = new User("P","Forbes","patrickFORBES@email.com","1236544",Role.REPRESENTATIVE);
		
		//Session factory stores data in the hibernate application
		//only one session factory is needed per application
	 SessionFactory sf = new Configuration().configure().buildSessionFactory();
     
   
  
   
	 Transaction tx= null;
	 Session session = null;
   try{
	   
	  session = sf.openSession();
	   //create transaction
	   
	   tx= session.beginTransaction();
	 //can save as much objects here
	   session.save(user);
	   
	   session.getTransaction().commit();
	   System.out.println("transaction complete ");
    }catch (HibernateException e) {
       if (tx!=null) tx.rollback();
       e.printStackTrace(); 
    }finally {
       session.close(); 
    }
   
	}

}
