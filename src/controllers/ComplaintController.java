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
import factories.HibernateConnectorSessionFactory;
import factories.TraditionalDatabaseConnectorFactory;
import models.Complaint;
import models.Response;
import models.User;
import utils.ComplaintCategory;
import utils.ComplaintStatus;
import utils.ComplaintType;
import utils.CustomizedException;

public class ComplaintController {

		// hibernate session config
		private SessionFactory sessionFactory;
		private Transaction transaction;
		private Session session;
		private UserController userController;
		// traditional connection vars
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
		this.userController = null;
		}
		
		// Method to ADD complaints
		public int addComplaint(Complaint complaint) throws CustomizedException {
		
		int complaintId = -1;
		try {

			// returns a configured session factory based on hibernate cfg file
			// get a hibernate configured session factory and store it into this instance
			// session factory
			this.sessionFactory = HibernateConnectorSessionFactory.getHibernateSessionFactory();
			// open a session to carry out transactions. a session is needed for every
			// transaction
			this.session = this.sessionFactory.openSession();

			// create transaction

			this.transaction = this.session.beginTransaction();
			complaintId = (int) this.session.save(complaint);

			this.transaction.commit();
			System.out.println("\nTransaction successful!");
		} catch (HibernateException e) {
			if (this.transaction != null) {
				this.transaction.rollback();
				e.printStackTrace();
				System.out.println("\nTransaction unsuccessful! ");
			}
		} catch (Exception exception) {
			throw new CustomizedException(exception.getMessage());
		} finally {

			if (this.session != null) {
				this.session.close();
			}

		}

		return complaintId;
	}
		
		
//		*************************************************************************	
		
		
		
		// Method to  READ all the complaints
		public ArrayList<Complaint> getAllComplaints() throws CustomizedException {
		ArrayList<Complaint> complaintsList = new ArrayList<>();
		
		try {
		// get instance of single database connection
		this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
		
		// initialize statement that will be used to execute sql query
		this.statement = this.connect.createStatement();
		
		// create sql query
		this.sqlQuery = "SELECT * FROM micro_star.complaints";
		
		// execute sql query on statement and a ResultSet is returned
		ResultSet rs = this.statement.executeQuery(this.sqlQuery);
		userController = new UserController();
		
		   //move cursor to beginning of row if it exists
		   while(rs.next()){
		   
		    int complaintID = rs.getInt("complaint_id");
		    int custID = rs.getInt("cust_id");
		    int empID= rs.getInt("emp_id");
		    String complaintCat = rs.getString("complaint_category");
		    String complaintInfo = rs.getString("complaint");
		    Date complaintDate = rs.getDate("complaint_date");
		    String complaintType = rs.getString("complaint_type");
		    String complaintStatus = rs.getString("complaint_status");
		   
		   
		    Complaint complaint = new Complaint();
		    User customer = userController.findById(custID);
		    User employee = userController.findById(empID);
		   
		    complaint.setComplaintID(complaintID);
		    complaint.setCustID(customer);
		    complaint.setEmpID(employee);
		   
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
		      
		      switch (complaintStatus.toLowerCase()) {
			   case "resolved":
			    complaint.setComplaintStatus(ComplaintStatus.RESOLVED);
			break;
			case "outstanding":
				complaint.setComplaintStatus(ComplaintStatus.OUTSTANDING);
			break;
			default:
			throw new IllegalArgumentException("Unexpected value: " + complaintStatus);
			}
		      
		// populate complaintsList to be returned
		complaintsList.add(complaint);
		
		}
		} catch (SQLException e) {
			throw new CustomizedException(e.getMessage());
		}
		return complaintsList;
	}
		
		
//		*************************************************************************	
		
		
		// Method to  READ all MILD complaints 
		public ArrayList<Complaint> getAllMildComplaints() throws CustomizedException {
				ArrayList<Complaint> complaintsList = new ArrayList<>();
				
				try {
				// get instance of single database connection
				this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
				
				// initialize statement that will be used to execute sql query
				this.statement = this.connect.createStatement();
				
				// create sql query
				this.sqlQuery = "SELECT * FROM micro_star.complaints "
						+ "WHERE complaint_category="+"'" + ComplaintCategory.MILD + "'";
				
				// execute sql query on statement and a ResultSet is returned
				ResultSet rs = this.statement.executeQuery(this.sqlQuery);
				userController = new UserController();
				
				   //move cursor to beginning of row if it exists
				   while(rs.next()){
				   
				    int complaintID = rs.getInt("complaint_id");
				    int custID = rs.getInt("cust_id");
				    int empID= rs.getInt("emp_id");
				    String complaintCat = rs.getString("complaint_category");
				    String complaintInfo = rs.getString("complaint");
				    Date complaintDate = rs.getDate("complaint_date");
				    String complaintType = rs.getString("complaint_type");
				    String complaintStatus = rs.getString("complaint_status");
				   
				    Complaint complaint = new Complaint();
				    User customer = userController.findById(custID);
				    User employee = userController.findById(empID);
				   
				    complaint.setComplaintID(complaintID);
				    complaint.setCustID(customer);
				    complaint.setEmpID(employee);
				   
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
				      
				      switch (complaintStatus.toLowerCase()) {
					   case "resolved":
					    complaint.setComplaintStatus(ComplaintStatus.RESOLVED);
					break;
					case "outstanding":
						complaint.setComplaintStatus(ComplaintStatus.OUTSTANDING);
					break;
					default:
					throw new IllegalArgumentException("Unexpected value: " + complaintStatus);
					}
				
				// populate complaintsList to be returned
				complaintsList.add(complaint);
				
				}
				} catch (SQLException e) {
				// TODO manage and log exceptions
				e.printStackTrace();
				}
				return complaintsList;
				}
				
		
//		*************************************************************************	
		
		
		// Method to  READ all MODERATE complaints 
				public ArrayList<Complaint> getAllModerateComplaints() throws CustomizedException {
						ArrayList<Complaint> complaintsList = new ArrayList<>();
						
						try {
						// get instance of single database connection
						this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
						
						// initialize statement that will be used to execute sql query
						this.statement = this.connect.createStatement();
						
						// create sql query
						this.sqlQuery = "SELECT * FROM micro_star.complaints "
								+ "WHERE complaint_category="+"'" + ComplaintCategory.MODERATE + "'";
						
						// execute sql query on statement and a ResultSet is returned
						ResultSet rs = this.statement.executeQuery(this.sqlQuery);
						userController = new UserController();
						
						   //move cursor to beginning of row if it exists
						   while(rs.next()){
						   
						    int complaintID = rs.getInt("complaint_id");
						    int custID = rs.getInt("cust_id");
						    int empID= rs.getInt("emp_id");
						    String complaintCat = rs.getString("complaint_category");
						    String complaintInfo = rs.getString("complaint");
						    Date complaintDate = rs.getDate("complaint_date");
						    String complaintType = rs.getString("complaint_type");
						    String complaintStatus = rs.getString("complaint_status");
						   
						    Complaint complaint = new Complaint();
						    User customer = userController.findById(custID);
						    User employee = userController.findById(empID);
						   
						    complaint.setComplaintID(complaintID);
						    complaint.setCustID(customer);
						    complaint.setEmpID(employee);
						   
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
						      
						      switch (complaintStatus.toLowerCase()) {
							   case "resolved":
							    complaint.setComplaintStatus(ComplaintStatus.RESOLVED);
							break;
							case "outstanding":
								complaint.setComplaintStatus(ComplaintStatus.OUTSTANDING);
							break;
							default:
							throw new IllegalArgumentException("Unexpected value: " + complaintStatus);
							}
						
						// populate complaintsList to be returned
						complaintsList.add(complaint);
						
						}
						} catch (SQLException e) {
						// TODO manage and log exceptions
						e.printStackTrace();
						}
						return complaintsList;
						}
						
	
//				*************************************************************************	
				
				
				
				// Method to  READ all SEVERE complaints 
				public ArrayList<Complaint> getAllSevereComplaints() throws CustomizedException {
						ArrayList<Complaint> complaintsList = new ArrayList<>();
						
						try {
						// get instance of single database connection
						this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
						
						// initialize statement that will be used to execute sql query
						this.statement = this.connect.createStatement();
						
						// create sql query
						this.sqlQuery = "SELECT * FROM micro_star.complaints "
								+ "WHERE complaint_category="+"'" + ComplaintCategory.SEVERE + "'";
						
						// execute sql query on statement and a ResultSet is returned
						ResultSet rs = this.statement.executeQuery(this.sqlQuery);
						userController = new UserController();
						
						   //move cursor to beginning of row if it exists
						   while(rs.next()){
						   
						    int complaintID = rs.getInt("complaint_id");
						    int custID = rs.getInt("cust_id");
						    int empID= rs.getInt("emp_id");
						    String complaintCat = rs.getString("complaint_category");
						    String complaintInfo = rs.getString("complaint");
						    Date complaintDate = rs.getDate("complaint_date");
						    String complaintType = rs.getString("complaint_type");
						    String complaintStatus = rs.getString("complaint_status");
						    
						   
						    Complaint complaint = new Complaint();
						    User customer = userController.findById(custID);
						    User employee = userController.findById(empID);
						   
						    complaint.setComplaintID(complaintID);
						    complaint.setCustID(customer);
						    complaint.setEmpID(employee);
						   
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
						
						      switch (complaintStatus.toLowerCase()) {
							   case "resolved":
							    complaint.setComplaintStatus(ComplaintStatus.RESOLVED);
							break;
							case "outstanding":
								complaint.setComplaintStatus(ComplaintStatus.OUTSTANDING);
							break;
							default:
							throw new IllegalArgumentException("Unexpected value: " + complaintStatus);
							}
						      
						// populate complaintsList to be returned
						complaintsList.add(complaint);
						
						}
						} catch (SQLException e) {
						// TODO manage and log exceptions
						e.printStackTrace();
						}
						return complaintsList;
						}
						
	
//				*************************************************************************	
				
				
				// Method to  READ all Resolved Cable complaints 
						public ArrayList<Complaint> getAllResolvedComplaints() throws CustomizedException {
								ArrayList<Complaint> complaintsList = new ArrayList<>();
								
								try {
								// get instance of single database connection
								this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
								
								// initialize statement that will be used to execute sql query
								this.statement = this.connect.createStatement();
								
								// create sql query
								this.sqlQuery = "SELECT * FROM micro_star.complaints "
										+ "WHERE complaint_status="+"'" + ComplaintStatus.RESOLVED + "'"
										+ "AND complaint_type"+"'" + ComplaintType.CABLE + "'";
								
								// execute sql query on statement and a ResultSet is returned
								ResultSet rs = this.statement.executeQuery(this.sqlQuery);
								userController = new UserController();
								
								   //move cursor to beginning of row if it exists
								   while(rs.next()){
								   
								    int complaintID = rs.getInt("complaint_id");
								    int custID = rs.getInt("cust_id");
								    int empID= rs.getInt("emp_id");
								    String complaintCat = rs.getString("complaint_category");
								    String complaintInfo = rs.getString("complaint");
								    Date complaintDate = rs.getDate("complaint_date");
								    String complaintType = rs.getString("complaint_type");
								    String complaintStatus = rs.getString("complaint_status");
								   
								    Complaint complaint = new Complaint();
								    User customer = userController.findById(custID);
								    User employee = userController.findById(empID);
								   
								    complaint.setComplaintID(complaintID);
								    complaint.setCustID(customer);
								    complaint.setEmpID(employee);
								   
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
								      
								      switch (complaintStatus.toLowerCase()) {
									   case "resolved":
									    complaint.setComplaintStatus(ComplaintStatus.RESOLVED);
									break;
									case "outstanding":
										complaint.setComplaintStatus(ComplaintStatus.OUTSTANDING);
									break;
									default:
									throw new IllegalArgumentException("Unexpected value: " + complaintStatus);
									}
								
								// populate complaintsList to be returned
								complaintsList.add(complaint);
								
								}
								} catch (SQLException e) {
								// TODO manage and log exceptions
								e.printStackTrace();
								}
								return complaintsList;
								}				
				
				
//		*************************************************************************	
			
						
						// Method to  READ all Resolved Broadband complaints 				
						
						public ArrayList<Complaint> getAllResolvedBroadbandComplaints() throws CustomizedException {
							ArrayList<Complaint> complaintsList = new ArrayList<>();
							
							try {
							// get instance of single database connection
							this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
							
							// initialize statement that will be used to execute sql query
							this.statement = this.connect.createStatement();
							
							// create sql query
							this.sqlQuery = "SELECT * FROM micro_star.complaints "
									+ "WHERE complaint_status="+"'" + ComplaintStatus.RESOLVED + "'"
									+ "AND complaint_type"+"'" + ComplaintType.BROADBAND + "'";
							
							// execute sql query on statement and a ResultSet is returned
							ResultSet rs = this.statement.executeQuery(this.sqlQuery);
							userController = new UserController();
							
							   //move cursor to beginning of row if it exists
							   while(rs.next()){
							   
							    int complaintID = rs.getInt("complaint_id");
							    int custID = rs.getInt("cust_id");
							    int empID= rs.getInt("emp_id");
							    String complaintCat = rs.getString("complaint_category");
							    String complaintInfo = rs.getString("complaint");
							    Date complaintDate = rs.getDate("complaint_date");
							    String complaintType = rs.getString("complaint_type");
							    String complaintStatus = rs.getString("complaint_status");
							   
							    Complaint complaint = new Complaint();
							    User customer = userController.findById(custID);
							    User employee = userController.findById(empID);
							   
							    complaint.setComplaintID(complaintID);
							    complaint.setCustID(customer);
							    complaint.setEmpID(employee);
							   
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
							      
							      switch (complaintStatus.toLowerCase()) {
								   case "resolved":
								    complaint.setComplaintStatus(ComplaintStatus.RESOLVED);
								break;
								case "outstanding":
									complaint.setComplaintStatus(ComplaintStatus.OUTSTANDING);
								break;
								default:
								throw new IllegalArgumentException("Unexpected value: " + complaintStatus);
								}
							
							// populate complaintsList to be returned
							complaintsList.add(complaint);
							
							}
							} catch (SQLException e) {
							// TODO manage and log exceptions
							e.printStackTrace();
							}
							return complaintsList;
							}				
						
						
						
						
						
						
//						*************************************************************************							
						
						// Method to  READ all Outstanding Cable complaints 
						
						public ArrayList<Complaint> getAllOutstandingCableComplaints() throws CustomizedException {
							ArrayList<Complaint> complaintsList = new ArrayList<>();
							
							try {
							// get instance of single database connection
							this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
							
							// initialize statement that will be used to execute sql query
							this.statement = this.connect.createStatement();
							
							// create sql query
							this.sqlQuery = "SELECT * FROM micro_star.complaints "
									+ "WHERE complaint_status="+"'" + ComplaintStatus.OUTSTANDING + "'"
									+ "AND complaint_type"+"'" + ComplaintType.CABLE + "'";
							
							// execute sql query on statement and a ResultSet is returned
							ResultSet rs = this.statement.executeQuery(this.sqlQuery);
							userController = new UserController();
							
							   //move cursor to beginning of row if it exists
							   while(rs.next()){
							   
							    int complaintID = rs.getInt("complaint_id");
							    int custID = rs.getInt("cust_id");
							    int empID= rs.getInt("emp_id");
							    String complaintCat = rs.getString("complaint_category");
							    String complaintInfo = rs.getString("complaint");
							    Date complaintDate = rs.getDate("complaint_date");
							    String complaintType = rs.getString("complaint_type");
							    String complaintStatus = rs.getString("complaint_status");
							   
							    Complaint complaint = new Complaint();
							    User customer = userController.findById(custID);
							    User employee = userController.findById(empID);
							   
							    complaint.setComplaintID(complaintID);
							    complaint.setCustID(customer);
							    complaint.setEmpID(employee);
							   
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
							      
							      switch (complaintStatus.toLowerCase()) {
								   case "resolved":
								    complaint.setComplaintStatus(ComplaintStatus.RESOLVED);
								break;
								case "outstanding":
									complaint.setComplaintStatus(ComplaintStatus.OUTSTANDING);
								break;
								default:
								throw new IllegalArgumentException("Unexpected value: " + complaintStatus);
								}
							
							// populate complaintsList to be returned
							complaintsList.add(complaint);
							
							}
							} catch (SQLException e) {
							// TODO manage and log exceptions
							e.printStackTrace();
							}
							return complaintsList;
							}		
						
						
						
						
				
						
						
						
//						*************************************************************************	
						
						// Method to  READ all Outstanding Broadband complaints 
						
						
						public ArrayList<Complaint> getAllOutstandingBroadbandComplaints() throws CustomizedException {
							ArrayList<Complaint> complaintsList = new ArrayList<>();
							
							try {
							// get instance of single database connection
							this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
							
							// initialize statement that will be used to execute sql query
							this.statement = this.connect.createStatement();
							
							// create sql query
							this.sqlQuery = "SELECT * FROM micro_star.complaints "
									+ "WHERE complaint_status="+"'" + ComplaintStatus.OUTSTANDING + "'"
									+ "AND complaint_type"+"'" + ComplaintType.BROADBAND + "'";
							
							// execute sql query on statement and a ResultSet is returned
							ResultSet rs = this.statement.executeQuery(this.sqlQuery);
							userController = new UserController();
							
							   //move cursor to beginning of row if it exists
							   while(rs.next()){
							   
							    int complaintID = rs.getInt("complaint_id");
							    int custID = rs.getInt("cust_id");
							    int empID= rs.getInt("emp_id");
							    String complaintCat = rs.getString("complaint_category");
							    String complaintInfo = rs.getString("complaint");
							    Date complaintDate = rs.getDate("complaint_date");
							    String complaintType = rs.getString("complaint_type");
							    String complaintStatus = rs.getString("complaint_status");
							   
							    Complaint complaint = new Complaint();
							    User customer = userController.findById(custID);
							    User employee = userController.findById(empID);
							   
							    complaint.setComplaintID(complaintID);
							    complaint.setCustID(customer);
							    complaint.setEmpID(employee);
							   
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
							      
							      switch (complaintStatus.toLowerCase()) {
								   case "resolved":
								    complaint.setComplaintStatus(ComplaintStatus.RESOLVED);
								break;
								case "outstanding":
									complaint.setComplaintStatus(ComplaintStatus.OUTSTANDING);
								break;
								default:
								throw new IllegalArgumentException("Unexpected value: " + complaintStatus);
								}
							
							// populate complaintsList to be returned
							complaintsList.add(complaint);
							
							}
							} catch (SQLException e) {
							// TODO manage and log exceptions
							e.printStackTrace();
							}
							return complaintsList;
							}		
						
						
						
						
						
						
						
						
		
//		*************************************************************************	
				
				
				
		/* Method to READ one complaint. Returns a single complaint. */
		public Complaint findById(int complaintID) throws CustomizedException {
		
		Complaint complaint = null;
		
		try {
		
		// retrieve complaints using traditional database connectivity
		this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
		this.statement = this.connect.createStatement();
		//create sql query
		this.sqlQuery = "SELECT * FROM micro_star.complaints WHERE complaint_id="+complaintID;    
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
		    String complaintStatus = rs.getString("complaint_status");
		    
		        userController = new UserController();
		   
		    complaint = new Complaint();
		   
		    //get users from database
		    User customer = userController.findById(custID);
		    User employee = userController.findById(empID);
		   
		    complaint.setCustID(customer);
		    complaint.setEmpID(employee);
		   
		    complaint.setComplaintID(complaintID1);
		 
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
		
		      switch (complaintStatus.toLowerCase()) {
			   case "resolved":
			    complaint.setComplaintStatus(ComplaintStatus.RESOLVED);
			break;
			case "outstanding":
				complaint.setComplaintStatus(ComplaintStatus.OUTSTANDING);
			break;
			default:
			throw new IllegalArgumentException("Unexpected value: " + complaintStatus);
			}
		      
		}
		} catch (SQLException e) {

			throw new CustomizedException(e.getMessage());
		}
		   return complaint;
		}
		
		
	
		
//		*************************************************************************	
		
		
		
		/* Method to READ one complaint. Returns a single complaint. */
		public Complaint viewComplaintAndResponses(int complaintID) throws CustomizedException {
		
		Complaint complaint = null;
		
		try {
		
		// retrieve complaints using traditional database connectivity
		this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
		this.statement = this.connect.createStatement();
		//create sql query
		this.sqlQuery = "SELECT * FROM micro_star.complaints WHERE complaint_id="+complaintID;    
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
		    String complaintStatus = rs.getString("complaint_status");
		        userController = new UserController();
		   
		    complaint = new Complaint();
		   
		    //get users from database
		    User customer = userController.findById(custID);
		    User employee = userController.findById(empID);
		   
		    complaint.setCustID(customer);
		    complaint.setEmpID(employee);
		   
		    complaint.setComplaintID(complaintID1);
		 
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
		      
		      switch (complaintStatus.toLowerCase()) {
			   case "resolved":
			    complaint.setComplaintStatus(ComplaintStatus.RESOLVED);
			break;
			case "outstanding":
				complaint.setComplaintStatus(ComplaintStatus.OUTSTANDING);
			break;
			default:
			throw new IllegalArgumentException("Unexpected value: " + complaintStatus);
			}
		      
		      
		      ArrayList <Response> responseList = new ArrayList <> ();
		      ResponseController rc = new ResponseController();
		      responseList = rc.getResponsesPerComplaint(complaintID);
		      System.out.println(responseList);
		}
		} catch (SQLException e) {
		// TODO manage and log exceptions
		e.printStackTrace();
		}
		   return complaint;
		}
		
		

		
//		*************************************************************************
		
		
		
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
		// 	complaint.setCustID(updatedComplaint.getCustID());
		// 	complaint.setEmpID(updatedComplaint.getEmpID());
		//	complaint.setCategory(updatedComplaint.getCategory());
		//	complaint.setComplaint(updatedComplaint.getComplaint());
		// 	complaint.setComplaintDate(updatedComplaint.getComplaintDate());
		//	complaint.setComplaintType(updatedComplaint.getComplaintType());
		complaint.setComplaintStatus(updatedComplaint.getComplaintStatus());
		
		// complete transaction
		this.transaction.commit();
		System.out.println("Complaint successfully updated");
		} catch (HibernateException e) {
		// TODO: handle exception
		System.out.println(e);
		if (this.transaction != null) {
		this.transaction.rollback();
		System.out.println("Rollback complete!");
		}
		throw new CustomizedException(e.getMessage());
		} catch (Exception e) {
			throw new CustomizedException(e.getMessage());
		}
		
		return complaint;
		}
		
//		*************************************************************************	
		
		
		public Complaint assignTechnician(Complaint assignComplaint) throws CustomizedException {
		Complaint complaint = null;
		try {
		this.sessionFactory = HibernateConnectorSessionFactory.getHibernateSessionFactory();
		this.session = this.sessionFactory.openSession();
		this.transaction = this.session.beginTransaction();
		//gets the Stock object from the database. i.e it tries to retrieve the complaint
		//with the matching ID and create an object from the values
		complaint = (Complaint)this.session.get(Complaint.class,
		assignComplaint.getComplaintID());
		// complaint.setCustID(assignComplaint.getCustID());
		complaint.setEmpID(assignComplaint.getEmpID());
		// complaint.setCategory(assignComplaint.getCategory());
		// complaint.setComplaint(assignComplaint.getComplaint());
		// complaint.setComplaintDate(assignComplaint.getComplaintDate());
		// complaint.setComplaintType(assignComplaint.getComplaintType());
		//complete transaction
		    this.transaction.commit();
		   System.out.println("Technician successfully added");
		} catch (HibernateException e) {
		// TODO: handle exception
		System.out.println(e);
		if(this.transaction != null) {
		this.transaction.rollback();
		System.out.println("Rollback complete!");
		}
		}catch (Exception e) {
			  throw new CustomizedException(e.getMessage());

		}
		return complaint;
		}
		
		
//		*************************************************************************	
		
		
		// Method to accept a user id and returns an ArrayList of complaints
		// that are tied to that specific User
		public ArrayList <Complaint> getComplaintsPerUser(int userID) throws CustomizedException {
		ArrayList<Complaint> userComplaintsList = new ArrayList <>();
		Complaint complaint = null;
		User customer = null;
		User employee = null;
		try {
		    //get instance of single database connection
		    this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
		   
		    //initialize statement that will be used to execute sql query
		this.statement = this.connect.createStatement();
		//create sql query
		this.sqlQuery = "SELECT * FROM complaints WHERE cust_id="+userID;
		   
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
		    String complaintStatus = rs.getString("complaint_status");
		    
		    userController = new UserController();
		   
		    complaint = new Complaint();
		   
		    //get users from database
		    customer = userController.findById(custID);
		    employee = userController.findById(empID);
		    
		    complaint.setComplaintID(complaintID);
		    complaint.setCustID(customer);
		    complaint.setEmpID(employee);
		   
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
		     
		      switch (complaintStatus.toLowerCase()) {
			   case "resolved":
			    complaint.setComplaintStatus(ComplaintStatus.RESOLVED);
			break;
			case "outstanding":
				complaint.setComplaintStatus(ComplaintStatus.OUTSTANDING);
			break;
			default:
			throw new IllegalArgumentException("Unexpected value: " + complaintStatus);
			}
		      
		      
		      //populate complaintsList to be returned
		      userComplaintsList.add(complaint);
		     
		   }
		} catch (SQLException e) {
			throw new CustomizedException(e.getMessage());
		}
		return userComplaintsList;
		}
		
		
//		*************************************************************************	
		
		
		/*Method to delete a complaint*/
		public int deleteComplaint(int complaintId) throws CustomizedException {
		int result = -1;
		// delete complaint using traditional connectivity
		try {
		this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
		this.statement = this.connect.createStatement();
		result = this.statement.executeUpdate("DELETE FROM complaints " + "WHERE complaint_id =" + complaintId);
		
		System.out.println(result + " row(s) affected. delete successful");
		
		if (result > 0) {
		throw new CustomizedException("Complaint deleted.");
		} else if (result == 0) {
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