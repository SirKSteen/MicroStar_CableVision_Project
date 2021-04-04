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

import factories.HibernateConnectorSessionFactory;
import factories.TraditionalDatabaseConnectorFactory;
import models.Account;
import models.User;
import utils.CustomizedException;
import utils.PaymentStatus;
import utils.Role;

public class AccountController {

	// hibernate session varibles
	private SessionFactory sessionFactory;
	private Transaction transaction;
	private Session session;

	// traditional connection varibles
	private Connection connect;
	private Statement statement;
	private String sqlQuery;

	public AccountController() {
		this.sessionFactory = null;
		this.transaction = null;
		this.session = null;
		this.connect = null;
		this.statement = null;
		this.sqlQuery = "";
	}

	/*
	 * Method to add an account using hibernate
	 */

	
	public int createAccount(Account account) throws CustomizedException{
		
		int acct_id = -1; 
		
		try {

			// returns a configured session factory based on hibernate cfg file
			// get a hibernate configured session factory and store it into this instance
			// session factory
			this.sessionFactory = HibernateConnectorSessionFactory.getHibernateSessionFactory();

			// open a session to carry out transactions. a session is needed for every
			// transaction
			this.session = this.sessionFactory.openSession();

			// creation for transaction

			this.transaction = this.session.beginTransaction();

			acct_id = (int) this.session.save(account);

			this.transaction.commit();

			System.out.println("Transaction completed");
		} catch (HibernateException e) {

			if (this.transaction != null) {
				this.transaction.rollback();
				e.printStackTrace();
				System.out.println("transaction incomplete");
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		} finally {

			if (this.session != null) {
				this.session.close();
			}
		}
		return acct_id;

	}
		
	public ArrayList<Account> getAllAccounts() throws CustomizedException{

		ArrayList<Account> accountsList = new ArrayList<Account>();

		try {

			// to get instance for single database connection
			this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();

			// initialize statement that will be used to execute sql query
			this.statement = this.connect.createStatement();

			// create sql query
			this.sqlQuery = "SELECT * FROM Accounts INNER JOIN Users ON Accounts.user_id=Users.user_id";
			// execute sql query on statement and a ResultSet is returned

			ResultSet rs = this.statement.executeQuery(this.sqlQuery);

			// move cursor to beginning of row if it exists
			while (rs.next()) {

				// retrieve by column name
				int id = rs.getInt("acct_id");
				String paymentStatus = rs.getString("payment_status");
				float amountDue = rs.getFloat("amt_due");
				int userId = rs.getInt("user_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String role = rs.getString("user_role");
				String password = rs.getString("password");

				// create user objects using data retrieved from columns

			//create user objects using data retrieved from columns
		
				 User user = new User();
				  
			       user.setUserId(userId);
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
			
			Account account = new Account();
			account.setAcct_id(id);
			account.setAmt_due(amountDue);
			account.setUser(user);
			
			switch (paymentStatus.toLowerCase()) {
			
			case "cancelled":
				account.setPayment_status(PaymentStatus.CANCELLED);
				break;
			case "complete":
				account.setPayment_status(PaymentStatus.COMPLETE);
				break;
			case "pending":
				account.setPayment_status(PaymentStatus.PENDING);
				break;
			case "rejected":
				account.setPayment_status(PaymentStatus.REJECTED);
				break;
			case "success":
				account.setPayment_status(PaymentStatus.SUCCESS);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value:" + paymentStatus);
			}
			
			accountsList.add(account);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accountsList;
	}
	
	//Method to READ one Account. Returns a single acct
	public Account findById(int acct_id) throws CustomizedException{
		
		Account account = null;
		
		try {
			
			this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
			this.statement = this.connect.createStatement();
			
			//create sql query
			this.sqlQuery = "SELECT * FROM `micro_star`.`accounts` INNER JOIN `micro_star`.`users` ON `micro_star`.`accounts`.user_id=`micro_star`.`users`.user_id WHERE Accounts.acct_id="+acct_id;
			ResultSet rs = this.statement.executeQuery(this.sqlQuery);
			
			//read result values and create user objects
			
			if(rs.next()) {
				//will be retrieved by column name
				
				int id = rs.getInt("acct_id");
				String paymentStatus = rs.getString("payment_status");
				float amountDue = rs.getFloat("amt_due");
				int userId = rs.getInt("user_id");
				String firstName= rs.getString("first_name");
				String lastName= rs.getString("last_name");
				String email = rs.getString("email");
				String role = rs.getString("user_role");
				String password = rs.getString("password");
				
				//create user objects using data retrieved from columns
				User user = new User();

				user.setUserId(userId);
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

			    account = new Account();
				account.setAcct_id(id);
				account.setAmt_due(amountDue);
				account.setUser(user);

				switch (paymentStatus.toLowerCase()) {

				case "cancelled":
					account.setPayment_status(PaymentStatus.CANCELLED);
					break;
				case "complete":
					account.setPayment_status(PaymentStatus.COMPLETE);
					break;
				case "pending":
					account.setPayment_status(PaymentStatus.PENDING);
					break;
				case "rejected":
					account.setPayment_status(PaymentStatus.REJECTED);
					break;
				case "success":
					account.setPayment_status(PaymentStatus.SUCCESS);
					break;
				default:
					throw new IllegalArgumentException("Unexpected value:" + paymentStatus);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return account;
	}

					
		//Method to UPDATE a user		
	public Account updateAccount(Account updatedAccount) throws CustomizedException{
			
			Account account = null;
			
			try {
				this.sessionFactory = HibernateConnectorSessionFactory.getHibernateSessionFactory();
				this.session = this.sessionFactory.openSession();
				this.transaction = this.session.beginTransaction();
				
				//gets the Stock object from the database i.e. it tries to retrieve the accounts info
				//with the matching ID and create an object from the values
				
				account = (Account)this.session.get(Account.class, updatedAccount.getAcct_id());
				
				account.setAcct_id(updatedAccount.getAcct_id());
				account.setPayment_status(updatedAccount.getPayment_status());
				account.setAmt_due(updatedAccount.getAmt_due());
				account.setUser(updatedAccount.getUser());
				
				//complete transaction
				this.transaction.commit();
				System.out.println("account updated");
			}catch (HibernateException e) {
				System.out.println(e);
				if(this.transaction != null) {
					this.transaction.rollback();
					System.out.println("rollback completed");
				}
			}
			return account;
		} 
		

		//Method to delete an account
		
		public int deleteAccounts(int acct_id) throws CustomizedException{
			int result = -1;
			
			//delete account using traditional connectivity 
			
			try {
				this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
				this.statement = this.connect.createStatement();
				
				result = this.statement.executeUpdate("DELETE FROM Accounts WHERE acct_id ="+acct_id);
				
				System.out.println(result + " row(s) affected. Delete Successful");
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}		
	
}
