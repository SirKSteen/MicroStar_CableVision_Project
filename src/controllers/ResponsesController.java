package controllers;

import java.sql.Connection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import factories.HibernateConnectorSessionFactory;
import models.Responses;
import utils.ControllerException;

public class ResponsesController {

	//hibernate session config
    private HibernateConnectorSessionFactory hibernateSessionFactory;
	private SessionFactory sessionFactory;
	private Transaction transaction;
	private Session session;
	
	//traditional connection config
	private Connection connect;
	
	
	
	public ResponsesController() {
		this.sessionFactory = null;
		this.transaction = null;
		this.session = null;
		this.hibernateSessionFactory = null;
		this.connect = null;
	}

	


	public void addResponse(Responses response) throws ControllerException {
		
	   try{
		   //add user using hibernate
		   this.hibernateSessionFactory = new HibernateConnectorSessionFactory();
		   this.sessionFactory = this.hibernateSessionFactory.getSessionFactory();
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
	    }catch (Exception exception){
	    	System.out.println(exception.getMessage());
	    }finally {
	    	
	    	if(session != null) {
	    		  session.close(); 
	    	}
	     
	    }
	}
	
}
