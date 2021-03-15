package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import factories.HibernateConnectorSessionFactory;
import factories.TraditionalDatabaseConnectorFactory;
import models.User;
import utils.CustomizedException;
import utils.Role;

public class UserController {

	//hibernate session vars
	private SessionFactory sessionFactory;
	private Transaction transaction;
	private Session session;
	
	//traditional connection vars
	private Connection connect;
	private Statement statement;
	private String sqlQuery;
	
	
	public UserController() {
		this.sessionFactory = null;
		this.transaction = null;
		this.session = null;
		this.connect = null;
		this.statement = null;
		this.sqlQuery = "";
		this.statement = null;
	}

	

	/* Method to add a user.
	 *add user using hibernate */
	public int createUser(User user) {
		 int userId = -1;
	   try{
		   
		   //returns a configured session factory based on hibernate cfg file
		   //get a hibernate configured session factory and store it into this instance session factory
		   this.sessionFactory = HibernateConnectorSessionFactory.getHibernateSessionFactory();
		   //open a session to carry out transactions. a session is needed for every transaction
		   this.session = this.sessionFactory.openSession();
		  
		   //create transaction
		   
		   this.transaction = this.session.beginTransaction();
		   /*can save as much objects here.
		    * cast the returned valued to int since we are storing userId as int.*/
		   String hashedPassword = this.generatePasswordHash(user.getPassword());
		   user.setPassword(hashedPassword);
		    userId = (int) this.session.save(user);
		  
		  this.transaction.commit();
		   System.out.println("transaction complete ");
	    }catch (HibernateException e) {
	       if (this.transaction!=null) {
	    	   this.transaction.rollback();
		       e.printStackTrace(); 
		       System.out.println("transaction incomplete ");
	       } 
	    }catch (Exception exception){
	    	System.out.println(exception.getMessage());
	    }finally {
	    	
	    	if(this.session != null) {
	    		  this.session.close(); 
	    	}
	     
	    }
	   
	   return userId;
	}
	
	
	
	
	
	/* Method to  READ all the users */
	public ArrayList<User> getAllUsers() {
		ArrayList<User> userList = new ArrayList<User>();
		
	    try {
	    	//get instance of single database connection
	    	this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
	    	
	    	//initialize statement that will be used to execute sql query
			this.statement = this.connect.createStatement();
			
			//create sql query
		    this.sqlQuery = "SELECT user_id, first_name, last_name, email, user_role, password FROM users";
		    //execute sql query on statement and a ResultSet is returned
		    ResultSet rs = this.statement.executeQuery(this.sqlQuery);

		    //move cursor to beginning of row if it exists
		    while(rs.next()){
		       //Retrieve by column name 
		       int id  = rs.getInt("user_id");
		       String firstName = rs.getString("first_name");
		       String lastName = rs.getString("last_name");
		       String email = rs.getString("email");
		       String role = rs.getString("user_role");
		       String password = rs.getString("password");
		       
		       //create user objects using data retrieved from columns.
		       User user = new User();
		  
		       user.setUserId(id);
		       user.setFirstName(firstName);
		       user.setLastName(lastName);
		       user.setEmail(email);
		       user.setPassword(password);
		       
		       switch (role.toLowerCase()) {
			    case "customer": 
			    	user.setRole(Role.CUSTOMER);
				break;
				case "representative":
					user.setRole(Role.REPRESENTATIVE);
					break;
				case "technician":
					user.setRole(Role.TECHNICIAN);
					break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + role);
			}
		       //populate userList to be returned
		       userList.add(user);
		       
		    }
		} catch (SQLException e) {
			// TODO manage and log exceptions
			e.printStackTrace();
		}
	    
	    return userList;
	}
	
	
	
	
	
	
	
	
	/* Method to  READ one user. Returns a single user. */
	public User findById(int userId) {
		
		User user = null;
		
		try {
			
			//retrieve users using traditional database connectivity
			this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
			this.statement = this.connect.createStatement();
			//create sql query
		    this.sqlQuery = "SELECT user_id, first_name, last_name, email, user_role, password FROM users WHERE user_id = "+userId;
		    ResultSet rs = this.statement.executeQuery(this.sqlQuery);
		    
		  //Read result values and create user objects
		   if(rs.next()){
		       //Retrieve by column name 
		       int id  = rs.getInt("user_id");
		       String firstName = rs.getString("first_name");
		       String lastName = rs.getString("last_name");
		       String email = rs.getString("email");
		       String role = rs.getString("user_role");
		       String password = rs.getString("password");
		       
		       //create user objects using data retrieved from columns.
		        user = new User();
		  
		       user.setUserId(id);
		       user.setFirstName(firstName);
		       user.setLastName(lastName);
		       user.setEmail(email);
		       user.setPassword(password);
		       switch (role.toLowerCase()) {
			    case "customer": 
				user.setRole(Role.CUSTOMER);
				break;
				case "representative":
					user.setRole(Role.REPRESENTATIVE);
					break;
				case "technician":
					user.setRole(Role.TECHNICIAN);
					break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + role);
			}
		       
		    }
		} catch (SQLException e) {
			// TODO manage and log exceptions
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	
	
	
	
	
	
	
	/*Method to UPDATE a user*/
	public User updateUser(User updatedUser) {
		User user = null;
	
		try {
			this.sessionFactory = HibernateConnectorSessionFactory.getHibernateSessionFactory();
			this.session = this.sessionFactory.openSession();
			this.transaction = this.session.beginTransaction();
			
			//gets the Stock object from the database. i.e it tries to retrieve the user
			//with the matching ID and create an object from the values
		    user = (User)this.session.get(User.class, updatedUser.getUserId()); 
			user.setFirstName(updatedUser.getFirstName());
			user.setLastName(updatedUser.getLastName());
		    user.setEmail(updatedUser.getEmail());
			user.setPassword(updatedUser.getPassword());
			user.setRole(updatedUser.getRole());
			
			//complete transaction
		     this.transaction.commit();
		    System.out.println("user updated");
		} catch (HibernateException e) {
			// TODO: handle exception
			System.out.println(e);
			if(this.transaction != null) {
				this.transaction.rollback();
				System.out.println("rollback complete");
			}
		}
		  catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return user;
	}
	
	
	
	
	
	
	
	/*Method to delete user*/
	public int deleteUser(int userId) {
		int result = -1;
		//delete user using traditional connectivity
		try {
			this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
			this.statement = this.connect.createStatement();
		  result = this.statement.executeUpdate("DELETE FROM users " +
	                   "WHERE user_id ="+userId);
		
		System.out.println(result + " row(s) affected. delete successful");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	
	
	
	//helper function to generate password hash in database before storing.
	//no raw text passwords will be stored.
	public String generatePasswordHash(String password) {
		
		String encrypted = BCrypt.hashpw(password, BCrypt.gensalt());
		return encrypted;
	}
	

	
	
	
	
	//Method to test if a plain text password matches the hash when converted
	//using BCrypt
	public boolean validatePassword(String testPassword,String encrypted) throws CustomizedException {

		if (BCrypt.checkpw(testPassword, encrypted)) {	
	        return true;
		}
		else {
			throw new CustomizedException("Password invalid.");
		}
		        
	}
	
	

}
