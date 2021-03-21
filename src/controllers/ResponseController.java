package controllers;

import java.sql.Connection;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import factories.HibernateConnectorSessionFactory;
import factories.TraditionalDatabaseConnectorFactory;
import models.Response;

public class ResponseController {

	//hibernate session config
//    private HibernateConnectorSessionFactory hibernateSessionFactory;
	private SessionFactory sessionFactory;
	private Transaction transaction;
	private Session session;
	
	//traditional connection config
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

	
	
//	Method to  READ all the responses
	public ArrayList<Response> getAllResponses() throws CustomizedException {
		ArrayList<Response> responsesList= new ArrayList<>();
		
	    try {
	    	//get instance of single database connection
	    	this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
	    	
	    	//initialize statement that will be used to execute sql query
			this.statement = this.connect.createStatement();
			
			//create sql query
			this.sqlQuery = "SELECT response_id, complaint_id, "
					+ "response_date, response FROM responses";
			
		    //execute sql query on statement and a ResultSet is returned
		    ResultSet rs = this.statement.executeQuery(this.sqlQuery);

		    //move cursor to beginning of row if it exists
		    while(rs.next()){
		    	
		    	int responseID = rs.getInt("response_id");
		    	int complaintID = rs.getInt("complaint_id");
		    	Date responseDate = rs.getDate("response_date");
		    	String responseInfo = rs.getString("response");	
		    	
		    	Response response = new Response();
		    	response.setResponse_id(responseID);
		    	response.setComplaint_id(complaintID);
		    	response.setResponse_date(responseDate);
		    	response.setResponse(responseInfo);
		    	
		       //populate complaintsList to be returned
		       responsesList.add(response);
		       
		    }
		} catch (SQLException e) {
			// TODO manage and log exceptions
			e.printStackTrace();
		}
	    
	    return responsesList;
	}
	


	/* Method to  READ one response. Returns a single response. */
	public Response findById(int responseID) throws CustomizedException {
		
		Response response = null;
		
		try {
			
			//retrieve complaints using traditional database connectivity
			this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
			this.statement = this.connect.createStatement();
			//create sql query
			this.sqlQuery = "SELECT * FROM `micro_star`.`responses` WHERE Responses.response_id="+responseID;   
			
//			this.sqlQuery = "SELECT * FROM `micro_star`.`accounts` INNER JOIN "
//					+ "`micro_star`.`users` ON `micro_star`.`accounts`.user_id=`micro_star`.`users`.user_id "
//					+ "WHERE Accounts.acct_id="+acct_id;
			
		    ResultSet rs = this.statement.executeQuery(this.sqlQuery);
		    
		  //Read result values and create response objects
		   if(rs.next()){
		       //Retrieve by column name 
			   	int responseID1 = rs.getInt("response_id");
		    	int complaintId = rs.getInt("complaint_id");
		    	Date responseDate = rs.getDate("response_date");
		    	String responseInfo = rs.getString("response");	
		    	
		    	response = new Response();
		    	
		    	response.setResponse_id(responseID1);
		    	response.setComplaint_id(complaintId);
		    	response.setResponse_date(responseDate);
		    	response.setResponse(responseInfo);
		       
			    }
			} catch (SQLException e) {
				// TODO manage and log exceptions
				e.printStackTrace();
			}
		    
	    return response;
	}	
	
	
	
	/*Method to delete a response*/
	public int deleteResponse(int responseId) throws CustomizedException {
		int result = -1;
		//delete response using traditional connectivity
		try {
			this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
			this.statement = this.connect.createStatement();
		  result = this.statement.executeUpdate("DELETE FROM responses " +
	                   "WHERE response_id ="+responseId);
		  
		System.out.println(result + " row(s) affected. delete successful");
		
			if(result > 0) {
				throw new ControllerException("Response deleted.");
			}else if(result == 0) {
				throw new ControllerException("No repsonse with given ID found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ControllerException e) {
			throw new ControllerException(e.getMessage());
		}
		
		return result;
	}
	
	
	
	
	

	
}
