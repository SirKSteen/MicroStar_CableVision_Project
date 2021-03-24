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
import models.Complaint;
import models.Response;
import utils.ComplaintCategory;
import utils.ComplaintType;
import utils.CustomizedException;

public class ResponseController {

	// hibernate session config
//    private HibernateConnectorSessionFactory hibernateSessionFactory;
	private SessionFactory sessionFactory;
	private Transaction transaction;
	private Session session;

	// traditional connection config
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

	public Response addResponse(Response response) throws CustomizedException {

		try {
			// add user using hibernate
			this.sessionFactory = HibernateConnectorSessionFactory.getHibernateSessionFactory();
			// open a session to carry out transactions. a session is needed for every
			// transaction
			this.session = this.sessionFactory.openSession();
			// create transaction

			this.transaction = this.session.beginTransaction();
			// can save as much objects here
			this.session.save(response);

			this.session.getTransaction().commit();
			System.out.println("transaction complete ");
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			this.session.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("transaction incomplete ");
			throw new CustomizedException(e.getMessage());

		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		} finally {

			if (session != null) {
				session.close();
			}

			return response;
		}
	}

	public Response updateResponse(Response updateResponse) throws CustomizedException {
		Response response  = null;

		try {
			this.sessionFactory = HibernateConnectorSessionFactory.getHibernateSessionFactory();
			this.session = this.sessionFactory.openSession();
			this.transaction = this.session.beginTransaction();

			// gets the Stock object from the database. i.e it tries to retrieve the
			// complaint
			// with the matching ID and create an object from the values
			response = (Response) this.session.get(Response.class, updateResponse.getResponse());
			
			response.setResponse_id(updateResponse.getResponse_id());
			response.setComplaint_id(updateResponse.getComplaint_id());
			response.setResponse(updateResponse.getResponse());
			response.setResponse_date(updateResponse.getResponse_date());

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
			// TODO: handle exception
			System.out.println(e);
		}

		return response;
	}
	
//	Method to  READ all the responses
	public ArrayList<Response> getAllResponses() throws CustomizedException {
		ArrayList<Response> responsesList = new ArrayList<>();

		try {
			// get instance of single database connection
			this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();

			// initialize statement that will be used to execute sql query
			this.statement = this.connect.createStatement();

			// create sql query
			this.sqlQuery = "SELECT * FROM responses JOIN Complaints ON responses.complaint_id=Complaints.complaint_id";

			// execute sql query on statement and a ResultSet is returned
			ResultSet rs = this.statement.executeQuery(this.sqlQuery);

			// move cursor to beginning of row if it exists
			while (rs.next()) {

				int responseID = rs.getInt("response_id");
				int complaintID = rs.getInt("complaint_id");
				Date responseDate = rs.getDate("response_date");
				String responseInfo = rs.getString("response");
				int custID = rs.getInt("cust_id");
				int empID = rs.getInt("emp_id");
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

				Response response = new Response();
				response.setResponse_id(responseID);
				response.setComplaint_id(complaint);
				response.setResponse_date(responseDate);
				response.setResponse(responseInfo);

				// populate complaintsList to be returned
				responsesList.add(response);

			}
		} catch (SQLException e) {
			// TODO manage and log exceptions
			e.printStackTrace();
		}

		return responsesList;
	}

//	Method to  READ all the responses
	public ArrayList<Response> getResponsesPerComplaint(int complaintId) throws CustomizedException {
		ArrayList<Response> responsesList = new ArrayList<>();

		try {
			// get instance of single database connection
			this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();

			// initialize statement that will be used to execute sql query
			this.statement = this.connect.createStatement();

			// create sql query
			this.sqlQuery = "SELECT * FROM `micro_star`.`responses` INNER JOIN `micro_star`.`complaints` ON `micro_star`.`responses`.complaint_id=`micro_star`.`complaints`.complaint_id WHERE Responses.response_id="
					+ complaintId;

			// execute sql query on statement and a ResultSet is returned
			ResultSet rs = this.statement.executeQuery(this.sqlQuery);

			// move cursor to beginning of row if it exists
			while (rs.next()) {

				int responseID = rs.getInt("response_id");
				int complaintID = rs.getInt("complaint_id");
				Date responseDate = rs.getDate("response_date");
				String responseInfo = rs.getString("response");
				int custID = rs.getInt("cust_id");
				int empID = rs.getInt("emp_id");
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

				Response response = new Response();
				response.setResponse_id(responseID);
				response.setComplaint_id(complaint);
				response.setResponse_date(responseDate);
				response.setResponse(responseInfo);

				// populate complaintsList to be returned
				responsesList.add(response);

			}
		} catch (SQLException e) {
			// TODO manage and log exceptions
			e.printStackTrace();
		}

		return responsesList;
	}

	/* Method to READ one response. Returns a single response. */
	public Response findById(int responseID) throws CustomizedException {

		Response response = null;

		try {

			// retrieve complaints using traditional database connectivity
			this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
			this.statement = this.connect.createStatement();
			// create sql query
			this.sqlQuery = "SELECT * FROM `micro_star`.`responses` INNER JOIN `micro_star`.`complaints` ON `micro_star`.`responses`.complaint_id=`micro_star`.`complaints`.complaint_id WHERE Responses.response_id="
					+ responseID;

			ResultSet rs = this.statement.executeQuery(this.sqlQuery);

			// Read result values and create response objects
			if (rs.next()) {
				// Retrieve by column name
				int responseID1 = rs.getInt("response_id");
				int complaintId = rs.getInt("complaint_id");
				Date responseDate = rs.getDate("response_date");
				String responseInfo = rs.getString("response");
				int custID = rs.getInt("cust_id");
				int empID = rs.getInt("emp_id");
				String complaintCat = rs.getString("complaint_category");
				String complaintInfo = rs.getString("complaint");
				Date complaintDate = rs.getDate("complaint_date");
				String complaintType = rs.getString("complaint_type");

				Complaint complaint = new Complaint();
				complaint.setComplaintID(complaintId);
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

				response = new Response();

				response.setResponse_id(responseID1);
				response.setResponse_date(responseDate);
				response.setResponse(responseInfo);
				response.setComplaint_id(complaint);
			}
		} catch (SQLException e) {
			// TODO manage and log exceptions
			e.printStackTrace();
		}

		return response;
	}

	/* Method to delete a response */
	public int deleteResponse(int responseId) throws CustomizedException {
		int result = -1;
		// delete response using traditional connectivity
		try {
			this.connect = TraditionalDatabaseConnectorFactory.getDatabaseConnection();
			this.statement = this.connect.createStatement();
			result = this.statement.executeUpdate("DELETE FROM responses " + "WHERE response_id =" + responseId);

			System.out.println(result + " row(s) affected. delete successful");

			if (result > 0) {
				throw new CustomizedException("Response deleted.");
			} else if (result == 0) {
				throw new CustomizedException("No repsonse with given ID found");
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
