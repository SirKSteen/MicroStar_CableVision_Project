package controllers;

import java.sql.Connection;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import factories.HibernateConnectorSessionFactory;
import models.Response;
import utils.CustomizedException;

public class ResponseController {

	//hibernate session vars
		private SessionFactory sessionFactory;
		private Transaction transaction;
		private Session session;
		
		//traditional connection vars
		private Connection connect;
		private Statement statement;
		private String sqlQuery;
	
	
	
	public ResponseController() {
		this.sessionFactory = null;
		this.transaction = null;
		this.session = null;
		this.connect = null;
		this.statement = null;
		this.sqlQuery = "";
		this.statement = null;
	}

	


	public void addResponse(Response response) throws CustomizedException {
		
	   try{
		   //add user using hibernate
		   this.sessionFactory = HibernateConnectorSessionFactory.getHibernateSessionFactory();
		   //open a session to carry out transactions. a session is needed for every transaction
		   this.session = this.sessionFactory.openSession();
		   //create transaction
		   
		   this.transaction= this.session.beginTransaction();
		 //can save as much objects here
		  this.session.save(response);
		   
		  this.session.getTransaction().commit();
		   System.out.println("transaction complete ");
	    }catch (HibernateException e) {
	       if (transaction!=null) transaction.rollback(); this.session.getTransaction().rollback();
	       e.printStackTrace(); 
	       System.out.println("transaction incomplete ");
	       throw new CustomizedException(e.getMessage());
	      
	    }catch (Exception exception){
	    	System.out.println(exception.getMessage());
	    }finally {
	    	
	    	if(session != null) {
	    		  session.close(); 
	    	}
	     
	    }
	}
	
}
