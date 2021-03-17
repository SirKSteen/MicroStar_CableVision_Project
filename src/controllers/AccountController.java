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
import utils.CustomizedException;
import utils.PaymentStatus;

public class AccountController {
	
	//hibernate session varibles
	private SessionFactory sessionFactory;
	private Transaction transaction;
	private Session session;
	
	
	//traditional connection varibles 
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
	
	public int createAccount(Account accounts) {
		
		int acct_id = -1; 
		
		try {
			
		
		//returns a configured session factory based on hibernate cfg file
		   //get a hibernate configured session factory and store it into this instance session factory
		this.sessionFactory = HibernateConnectorSessionFactory.getHibernateSessionFactory();
		
		//open a session to carry out transactions. a session is needed for every transaction
		this.session = this.sessionFactory.openSession();
		
	// creation for transaction
		
		this.transaction = this.session.beginTransaction();
		 
		acct_id = (int) this.session.save(accounts);
		
		this.transaction.commit();
		
		System.out.println("Transaction completed");
		}catch (HibernateException e) {
			
			if (this.transaction != null) {
				this.transaction.rollback();
				e.printStackTrace();
				System.out.println("transaction incomplete");
			}
		}catch (Exception exception) {
			System.out.println(exception.getMessage());
		}finally {
			
			if(this.session != null) {
				this.session.close();
			}
		}
		return acct_id;
		
	}
	
	public ArrayList<Account> getAllAccounts(){
		
		ArrayList<Account> accountsList = new ArrayList<Account>();
		
		try {
			
			//to get instance for single database connection
			this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
			
			//initialize statement that will be used to execute sql query
			this.statement = this.connect.createStatement();
			
			//create sql query
			this.sqlQuery = "SELECT acct_id, payment_status, amt_due, user FROM Accounts";
			
			//execute sql query on statement and a ResultSet is returned
			
			ResultSet rs = this.statement.executeQuery(this.sqlQuery);
			
			//move cursor to beginning of row if it exists 
			while(rs.next()) {
				
				//retrieve by column name
				int id = rs.getInt("acct_id");
				String paymentStatus = rs.getString("payment_status");
				float amountDue = rs.getFloat("amt_due");
				String user = rs.getString("user");
			
			
			//create user objects using data retrieved from columns
			
			Account accounts = new Account();
			
			accounts.setAcct_id(id);
			//accounts.setPayment_status(paymentStatus);
			accounts.setAmt_due(amountDue);
			accounts.setUser(accounts.getUser());
			
			switch (paymentStatus.toLowerCase()) {
			
			case "cancelled":
				accounts.setPayment_status(PaymentStatus.CANCELLED);
				break;
			case "complete":
				accounts.setPayment_status(PaymentStatus.COMPLETE);
				break;
			case "pending":
				accounts.setPayment_status(PaymentStatus.PENDING);
				break;
			case "rejected":
				accounts.setPayment_status(PaymentStatus.REJECTED);
				break;
			case "success":
				accounts.setPayment_status(PaymentStatus.SUCCESS);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value:" + paymentStatus);
			}
			
			accountsList.add(accounts);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accountsList;
	}
	
	//Method to READ one Account. Returns a single acct
	public Account findById(int acct_id) {
		
		Account accounts = null;
		
		try {
			
			this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
			this.statement = this.connect.createStatement();
			
			//create sql query
			this.sqlQuery = "SELECT acct_id, payment_status, amt_due, user FROM Accounts";
			ResultSet rs = this.statement.executeQuery(this.sqlQuery);
			
			//read result values and create user objects
			
			if(rs.next()) {
				//will be retrieved by column name
				
				int id = rs.getInt("acct_id");
				String paymentStatus = rs.getString("payment_status");
				float amountDue = rs.getFloat("amt_due");
				String user = rs.getString("user");
				
				//create user objects using data retrieved from columns
				
				accounts = new Account();
				
				accounts.setAcct_id(id);
				//accounts.setPayment_status(paymentStatus);
				accounts.setAmt_due(amountDue);
				accounts.setUser(accounts.getUser());
				
				switch (paymentStatus.toLowerCase()) {
				
				case "cancelled":
					accounts.setPayment_status(PaymentStatus.CANCELLED);
					break;
				case "complete":
					accounts.setPayment_status(PaymentStatus.COMPLETE);
					break;
				case "pending":
					accounts.setPayment_status(PaymentStatus.PENDING);
					break;
				case "rejected":
					accounts.setPayment_status(PaymentStatus.REJECTED);
					break;
				case "success":
					accounts.setPayment_status(PaymentStatus.SUCCESS);
					break;
				default:
					throw new IllegalArgumentException("Unexpected value:" + paymentStatus);
				}
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			
		}
		return accounts;
	}
				
		//Method to UPDATE a user		
		public Account updateAccounts(Account updatedAccounts) {
			
			Account accounts = null;
			
			try {
				this.sessionFactory = HibernateConnectorSessionFactory.getHibernateSessionFactory();
				this.session = this.sessionFactory.openSession();
				this.transaction = this.session.beginTransaction();
				
				//gets the Stock object from the database i.e. it tries to retrieve the accounts info
				//with the matching ID and create an object from the values
				
				accounts = (Account)this.session.get(Account.class, updatedAccounts.getAcct_id());
				
				accounts.setAcct_id(updatedAccounts.getAcct_id());
				accounts.setPayment_status(updatedAccounts.getPayment_status());
				accounts.setAmt_due(updatedAccounts.getAmt_due());
				accounts.setUser(updatedAccounts.getUser());
				
				//complete transaction
				this.transaction.commit();
				System.out.println("user updated");
			}catch (HibernateException e) {
				System.out.println(e);
				if(this.transaction != null) {
					this.transaction.rollback();
					System.out.println("rollback completed");
				}
			}
			catch (Exception e) {
				System.out.println(e);
		}
		return accounts;	
	}
	
		//Method to delete an account
		
		public int deleteAccounts(int acct_id) {
			int result = -1;
			
			//delete account using traditional connectivity 
			
			try {
				this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
				this.statement = this.connect.createStatement();
				
				result = this.statement.executeUpdate("DELETE FROM Accounts" + "Where acct_id =" +acct_id);
				
				System.out.println(result + "row(s) affected. Delete Successful");
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}		
	

}
