package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import factories.HibernateConnectorSessionFactory;
import factories.TraditionalDatabaseConnectorFactory;
import models.Complaint;
import utils.ComplaintCategory;
import utils.ComplaintType;
import utils.CustomizedException;
import utils.Role;

public class ComplaintController {

	//hibernate session config
	private SessionFactory sessionFactory;
	private Transaction transaction;
	private Session session;
	
	//traditional connection vars
	private Connection connect;
	private Statement statement;
	private String sqlQuery;
	
	
	public ComplaintController() {
		this.sessionFactory = null;
		this.transaction = null;
		this.session = null;
		this.connect = null;
		this.statement = null;
		this.sqlQuery = "";
		this.statement = null;
	}

//	Method to ADD complaints
	public int addComplaint(Complaint complaint) throws CustomizedException {
		
		 int complaintId = -1;
		   try{
			   
			   //returns a configured session factory based on hibernate cfg file
			   //get a hibernate configured session factory and store it into this instance session factory
			   this.sessionFactory = HibernateConnectorSessionFactory.getHibernateSessionFactory();
			   //open a session to carry out transactions. a session is needed for every transaction
			   this.session = this.sessionFactory.openSession();
			  
			   //create transaction
			   
			   this.transaction = this.session.beginTransaction();
			    complaintId = (int) this.session.save(complaint);
			  
			  this.transaction.commit();
			   System.out.println("\nTransaction successful!");
		    }catch (HibernateException e) {
		       if (this.transaction!=null) {
		    	   this.transaction.rollback();
			       e.printStackTrace(); 
			       System.out.println("\nTransaction unsuccessful! ");
		       } 
		    }catch (Exception exception){
		    	System.out.println(exception.getMessage());
		    }finally {
		    	
		    	if(this.session != null) {
		    		  this.session.close(); 
		    	}
		     
		    }
		   
		   return complaintId;
}
	
	


	
//	Method to  READ all the complaints 
	public ArrayList<Complaint> getAllComplaints() throws CustomizedException {
		ArrayList<Complaint> complaintsList= new ArrayList<>();
		
	    try {
	    	//get instance of single database connection
	    	this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
	    	
	    	//initialize statement that will be used to execute sql query
			this.statement = this.connect.createStatement();
			
			//create sql query
			this.sqlQuery = "SELECT complaint_id,cust_id, emp_id, complaint_category, complaint,"
					+ "complaint_date, complaint_type FROM complaints";
			
		    //execute sql query on statement and a ResultSet is returned
		    ResultSet rs = this.statement.executeQuery(this.sqlQuery);

		    //move cursor to beginning of row if it exists
		    while(rs.next()){
		    	
		    	int complaintID = rs.getInt("complaint_id");
		    	int custID = rs.getInt("cust_id");
		    	int empID= rs.getInt("emp_id");
		    	String complaintCat = rs.getString("complaint_category"); 
		    	String complaintInfo = rs.getString("complaint");
		    	Date complaintDate = rs.getDate("complaint_date");
		    	String complaintType = rs.getString("complaint_type");
		    	
		    	Complaint complaint = new Complaint();
		    	complaint.setComplaintID(complaintID);
		    	complaint.setCustID(custID);
		    	complaint.setEmpID(empID);
		    	
		       switch (complaintCat.toLowerCase()) {
			    case "mild": 
			    	complaint.setCategory(ComplaintCategory.MILD);
			    	break;
			    case "moderate": 
			    	complaint.setCategory(ComplaintCategory.MODERATE);
			    	break;
			    case "severe": 
			    	complaint.setCategory(ComplaintCategory.SEVERE);
			    	break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + complaintCat);
			}
		       
		       complaint.setComplaint(complaintInfo);
		       complaint.setComplaintDate(complaintDate);
		       
		       switch (complaintType.toLowerCase()) {
			    case "broadband": 
			    	complaint.setComplaintType(ComplaintType.BROADBAND);
				break;
				case "cable":
					complaint.setComplaintType(ComplaintType.CABLE);
					break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + complaintType);
			}
		       
		       //populate complaintsList to be returned
		       complaintsList.add(complaint);
		       
		    }
		} catch (SQLException e) {
			// TODO manage and log exceptions
			e.printStackTrace();
		}
	    
	    return complaintsList;
	}
	
	
	
	

	/* Method to  READ one complaint. Returns a single complaint. */
	public Complaint findById(int complaintID) throws CustomizedException {
		
		Complaint complaint = null;
		
		try {
			
			//retrieve complaints using traditional database connectivity
			this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
			this.statement = this.connect.createStatement();
			//create sql query
			this.sqlQuery = "SELECT complaint_id,cust_id, emp_id, complaint_category, complaint,"
					+ "complaint_date, complaint_type FROM complaints";		    
		    ResultSet rs = this.statement.executeQuery(this.sqlQuery);
		    
		  //Read result values and create complaints objects
		   if(rs.next()){
		       //Retrieve by column name 
			    int complaintID1 = rs.getInt("complaint_id");
		    	int custID = rs.getInt("cust_id");
		    	int empID= rs.getInt("emp_id");
		    	String complaintCat = rs.getString("complaint_category"); 
		    	String complaintInfo = rs.getString("complaint");
		    	Date complaintDate = rs.getDate("complaint_date");
		    	String complaintType = rs.getString("complaint_type");
		    	
		       
		       //create complaints objects using data retrieved from columns.
		    	complaint = new Complaint();
		    	
		    	complaint.setComplaintID(complaintID1);
		    	complaint.setCustID(custID);
		    	complaint.setEmpID(empID);
		    	
		       switch (complaintCat.toLowerCase()) {
			    case "mild": 
			    	complaint.setCategory(ComplaintCategory.MILD);
			    	break;
			    case "moderate": 
			    	complaint.setCategory(ComplaintCategory.MODERATE);
			    	break;
			    case "severe": 
			    	complaint.setCategory(ComplaintCategory.SEVERE);
			    	break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + complaintCat);
			}
		       
		       complaint.setComplaint(complaintInfo);
		       complaint.setComplaintDate(complaintDate);
		       
		       switch (complaintType.toLowerCase()) {
			    case "broadband": 
			    	complaint.setComplaintType(ComplaintType.BROADBAND);
				break;
				case "cable":
					complaint.setComplaintType(ComplaintType.CABLE);
					break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + complaintType);
			}
		           
		    }
		} catch (SQLException e) {
			// TODO manage and log exceptions
			e.printStackTrace();
		}
	    
	    return complaint;
	}
	
	
	

	
	/*Method to UPDATE a complaint*/
	public Complaint updateComplaints(Complaint updatedComplaint) throws CustomizedException {
		Complaint complaint = null;
	
		try {
			this.sessionFactory = HibernateConnectorSessionFactory.getHibernateSessionFactory();
			this.session = this.sessionFactory.openSession();
			this.transaction = this.session.beginTransaction();
			
			//gets the Stock object from the database. i.e it tries to retrieve the complaint
			//with the matching ID and create an object from the values
			complaint = (Complaint)this.session.get(Complaint.class, 
									updatedComplaint.getComplaintID()); 
			complaint.setCustID(updatedComplaint.getCustID());
			complaint.setEmpID(updatedComplaint.getEmpID());
			complaint.setCategory(updatedComplaint.getCategory());
			complaint.setComplaint(updatedComplaint.getComplaint());
			complaint.setComplaintDate(updatedComplaint.getComplaintDate());
			complaint.setComplaintType(updatedComplaint.getComplaintType());
			
			//complete transaction
		     this.transaction.commit();
		    System.out.println("Complaint successfully updated");
		} catch (HibernateException e) {
			// TODO: handle exception
			System.out.println(e);
			if(this.transaction != null) {
				this.transaction.rollback();
				System.out.println("Rollback complete!");
			}
			throw new CustomizedException(e.getMessage());
		}
		  catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return complaint;
	}
	
	
	
	
	/*Method to delete a complaint*/
	public int deleteComplaint(int complaintId) throws CustomizedException {
		int result = -1;
		//delete complaint using traditional connectivity
		try {
			this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
			this.statement = this.connect.createStatement();
		  result = this.statement.executeUpdate("DELETE FROM complaints " +
	                   "WHERE complaint_id ="+complaintId);
		  
		System.out.println(result + " row(s) affected. delete successful");
		
			if(result > 0) {
				throw new CustomizedException("Complaint deleted.");
			}else if(result == 0) {
				throw new CustomizedException("No complaint with given ID found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CustomizedException e) {
			throw new CustomizedException(e.getMessage());
		}
		
		return result;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}