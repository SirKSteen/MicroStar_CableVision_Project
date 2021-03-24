package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controllers.*;
import models.*;
import utils.CustomizedException;

import controllers.ResponseController;

public class Server {

	private ServerSocket serverSocket;
	private Socket socket;
	private ObjectOutputStream objectOutStream;
	private static Connection dBConn = null;
	private ObjectInputStream objectInStream;
	private AccountController accountController;
	private AuthController authController;
	private ComplaintController complaintController;
	private ResponseController responseController;
	private UserController userController;
	private Statement statement;
	private ResultSet result = null;
	
	public Server() {
		try {
			this.serverSocket = new ServerSocket(5000);
			accountController = new AccountController();
			try {
				acceptAndProcessRequest();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed server socket "+e.getLocalizedMessage());
		}
	
	}
	
	private void acceptAndProcessRequest() throws ClassNotFoundException {
		String operation = "",endPoint = "";
		while(true) {
			try {
				this.socket = this.serverSocket.accept();
				initDataStreams();
				operation = (String)this.objectInStream.readObject();
				endPoint = (String)this.objectInStream.readObject();
				processRequest(operation, endPoint);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Failed socket "+e.getLocalizedMessage());
				break;
			} catch (CustomizedException ce) {
				
			}
		}
		
	}

	private void initDataStreams() throws IOException {
		try {
			this.objectInStream = new ObjectInputStream(this.socket.getInputStream());
			this.objectOutStream = new ObjectOutputStream(this.socket.getOutputStream());
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	private void processRequest(String operation, String endPoint) throws ClassNotFoundException, IOException, CustomizedException {
	
		switch (endPoint.toLowerCase()) {
		case "account":
			switch (operation.toLowerCase()) {
			case "createaccount": 
				Account account = (Account)this.objectInStream.readObject();
				int newAccountId = accountController.createAccount(account);
				this.objectOutStream.writeObject(newAccountId);
				break;
			case "updateaccount": 
				Account accToUpdate = (Account)this.objectInStream.readObject();
				Account returnAccountUpdate = accountController.updateAccount(accToUpdate);
				this.objectOutStream.writeObject(returnAccountUpdate);
				break;
			case "findbyid": 
				Account findById = (Account)this.objectInStream.readObject();
				Account returnId = accountController.updateAccount(findById);
				this.objectOutStream.writeObject(returnId);
				break;
			case "deleteaccount": 
				int deleteAcctId = (int)this.objectInStream.readObject();
				int deletedId = accountController.deleteAccounts(deleteAcctId);
				this.objectOutStream.writeObject(deletedId);
				break;
			case "getallaccounts": 
				//check
				ArrayList<Account> getAllAccount = (ArrayList<Account>) this.objectInStream.readObject();
				ArrayList<Account> returnAllAcoounts = this.accountController.getAllAccounts();
				this.objectOutStream.writeObject(returnAllAcoounts);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + operation.toLowerCase());
			}
			break;
			
		//AuthenticastionController
		case "auth":
			switch (operation.toLowerCase()) {
			case "login": 
				boolean login = (boolean)this.objectInStream.readObject();
				boolean returnLogin = this.authController.login(0, endPoint, null);
				this.objectOutStream.writeObject(endPoint);
				break;
			case "updatepassword":
				boolean loginPasswordUpate = (boolean)this.objectInStream.readObject();
				boolean returnPasswordUpdate = this.authController.updatePassword(0, operation, endPoint);
				this.objectOutStream.writeObject(returnPasswordUpdate);
				break;
			case "register": 
				int registerId = (int)this.objectInStream.readObject();
				//assess this
				int returnRegisterId = this.authController.register(null);
				this.objectOutStream.writeObject(returnRegisterId);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + operation.toLowerCase());
			}
			break;
		case "complaint":
			switch (operation.toLowerCase()) {
			case "createcomplaint": 
				Complaint complaint = (Complaint)this.objectInStream.readObject();
				int newComplaintId = this.complaintController.addComplaint(complaint);
				this.objectOutStream.writeObject(newComplaintId);
				break;
			case "updatecomplaint": 
				Complaint complaintId = (Complaint)this.objectInStream.readObject();
				Complaint complaintUpdate = this.complaintController.updateComplaints(complaintId);
				this.objectOutStream.writeObject(complaintUpdate);
				break;
			case "findbyid": 
				int findById = (int)this.objectInStream.readObject();
				//assess
				Complaint returnFoundId = this.complaintController.findById(findById);
				this.objectOutStream.writeObject(returnFoundId);
				break;
			case "deletecomplaint": 
				int deleteId = (int)this.objectInStream.readObject();
				int returnDeleteId = this.complaintController.deleteComplaint(deleteId);
				this.objectOutStream.writeObject(returnDeleteId);
				break;
			case "getallcomplaints": 
				//assess
				ArrayList<Complaint> ComplaintAllId = (ArrayList<Complaint>)this.objectInStream.readObject();
				ArrayList<Complaint> returnComplaintId = this.complaintController.getAllComplaints();
				this.objectOutStream.writeObject(returnComplaintId);
				break;
			case "assigntechnician": 
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + operation.toLowerCase());
			}
			break;
			//response 
		case "response":
			switch (operation.toLowerCase()) {
			case "addresponse": 
				Response responseId = (Response)this.objectInStream.readObject();
				//assess class 
				Response returnResponseId = this.responseController.addResponse(responseId);
				this.objectOutStream.writeObject(returnResponseId);
				break;
			case "updateresponse": 
				Response updateId = (Response) this.objectInStream.readObject();
				Response returnUpdateId = this.responseController.updateResponse(updateId);
				this.objectOutStream.writeObject(returnUpdateId);
				break;
			case "findbyid": 
				//assess
				int findByResponseId = (int)this.objectInStream.readObject();
				Response returnFoundResponseId = this.responseController.findById(findByResponseId);
				this.objectOutStream.writeObject(returnFoundResponseId);
				break;
			case "deleteresponse": 
				int deleteResponseId = (int)this.objectInStream.readObject();
				int returnDeleteId = this.responseController.deleteResponse(deleteResponseId);
				this.objectOutStream.writeObject(returnDeleteId);
				break;
			case "getallresponses": //assess
				ArrayList<Response> viewAllId = (ArrayList<Response>)this.objectInStream.readObject();
				ArrayList<Response> returnAllResponse = this.responseController.getAllResponses();
				this.objectOutStream.writeObject(returnAllResponse);
				break;
			case "getresponsespercomplaint": 
				ArrayList<Response> viewPerComplaintId = (ArrayList<Response>) this.objectInStream.readObject();
				ArrayList<Response> returnComplaint = this.responseController.getAllResponses();
				this.objectOutStream.writeObject(returnComplaint);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + operation.toLowerCase());
			}
			break;
		case "user":
			switch (operation.toLowerCase()) {
			case "createuser": 
				//assess
				int userId = (int) this.objectInStream.readObject();
				int returnUserId = this.userController.createUser(null);//here
				this.objectOutStream.writeObject(returnUserId);
				break;
			case "updateuser": 
				User userIdUpdate = (User) this.objectInStream.readObject();
				User returnUserIdUpdate = this.userController.updateUser(userIdUpdate);//here
				this.objectOutStream.writeObject(returnUserIdUpdate);
				break;
			case "findbyid": 
				//assess
				int user = (int)this.objectInStream.readObject();
				User returnFindUser = this.userController.findById(user);
				this.objectOutStream.writeObject(returnFindUser);
				break;
			case "findbyemail": 
				//assess
				String findByEmail = (String)this.objectInStream.readObject();
				boolean returnFindByEmail = this.userController.findByEmail(findByEmail);
				this.objectOutStream.writeObject(returnFindByEmail);
				break;
			case "deleteuser": 
				int deleteUser = (int)this.objectInStream.readObject();
				int returnDeleteUser= this.userController.deleteUser(deleteUser);
				this.objectOutStream.writeObject(returnDeleteUser);
				break;
			case "getallusers": 
				//assess
				ArrayList<User> getAllUser = (ArrayList<User>)this.objectInStream.readObject();
				ArrayList<User> returngetAllUser= this.userController.getAllUsers();//here
				this.objectOutStream.writeObject(returngetAllUser);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + operation.toLowerCase());
			}
			break;
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + endPoint.toLowerCase());
		}
	}
	
	
	private void closeConnection() {
		try {
			this.objectInStream.close();
			this.objectOutStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}


/*
 * package Server;
 * 
 * import java.io.EOFException; import java.io.IOException; import
 * java.io.ObjectInputStream; import java.io.ObjectOutputStream; import
 * java.net.ServerSocket; import java.net.Socket; import java.sql.Connection;
 * import java.sql.DriverManager; import java.sql.ResultSet; import
 * java.sql.SQLException; import java.sql.Statement; import models.*;
 * 
 * import javax.swing.JOptionPane;
 * 
 * public class server {
 * 
 * private ServerSocket serverSocket; private Socket connectionSocket; private
 * ObjectOutputStream objOs; private ObjectInputStream objIs; private static
 * Connection dBConn = null; private Statement statment; private ResultSet
 * result = null;
 * 
 * 
 * public server() {
 * 
 * this.createConnection(); //waitForRequests(); }
 * 
 * private void createConnection() { try { serverSocket = new
 * ServerSocket(8888); } catch (IOException ex) { // TODO Auto-generated catch
 * bloc ex.printStackTrace(); }
 * 
 * }
 * 
 * private void configureStreams() {
 * 
 * try { this.objOs = new
 * ObjectOutputStream(connectionSocket.getOutputStream()); this.objIs = new
 * ObjectInputStream(connectionSocket.getInputStream()); } catch (IOException
 * ex) { // TODO Auto-generated catch block ex.printStackTrace(); }
 * 
 * }
 * 
 * private static Connection MicroStarDatabaseConnection() { if (dBConn == null)
 * { String url = "jdbc:mysql://localhost:3306/microstar_db"; try { dBConn =
 * DriverManager.getConnection(url,"root","");
 * JOptionPane.showMessageDialog(null,
 * "DB Connection Established","CONNECTION STATUS",JOptionPane.
 * INFORMATION_MESSAGE); } catch (SQLException dex) {
 * JOptionPane.showMessageDialog(null,
 * "Could not find a database to connect to. \n" + dex,
 * "Connection Failure",JOptionPane.ERROR_MESSAGE); } } return dBConn;
 * 
 * }
 * 
 * private void closeConnection() { try { this.objIs.close();
 * this.objOs.close(); } catch (IOException e) { // TODO Auto-generated catch
 * block e.printStackTrace(); }
 * 
 * }
 * 
 * 
 * 
 * private void addAccount(models.Account account) { String sql =
 * "INSERT INTO micro_star.`accounts` (acc_id,amt_due,paymnet_status,user_id)" +
 * "VALUES (" + null + ", '" + account.getAcct_id() +"`, `" +
 * account.getAmt_due() + "`, " + "`, `" + account.getPayment_status() + "`, `"
 * + account.getUser().getUserId() +"`);";
 * 
 * try { statment = dBConn.createStatement();
 * 
 * if ((statment.executeUpdate(sql) == 1)) { this.objOs.writeObject(false);
 * this.objOs.writeObject(true);//return true to client if successful }else { }
 * } catch (SQLException | IOException e) { e.printStackTrace(); } }
 * 
 * private void addComplaints(Complaint cmp) { String sql =
 * "INSERT INTO micro_star.`complaints` (complaint,complaint_category,complaint_date,complaint_id,complaint_type,cust_id,emp_id)"
 * + "VALUES (" + null + ", '" + cmp.getComplaint() +"`, `" + cmp.getCategory()
 * + "', "+ cmp.getComplaintID() + "`, " + "`, `" + cmp.getComplaintDate() +
 * "`, `" + cmp.getComplaintType() +"`, '" + cmp.getCustID() +"', '" +
 * cmp.getEmpID()+");";
 * 
 * try { statment = dBConn.createStatement();
 * 
 * if ((statment.executeUpdate(sql) == 1)) { this.objOs.writeObject(false);
 * this.objOs.writeObject(true);//return true to client if successful }else { }
 * } catch (SQLException | IOException e) { e.printStackTrace(); }
 * 
 * }
 * 
 * private void addResponse(models.Response response) { String sql =
 * "INSERT INTO micro_star.`response` (complant_id,response,response_date,response_id)"
 * + "VALUES (" + null + ", '" + response.getComplaint_id() +"`, `" +
 * response.getResponse()+ "`, " + "`, `" + response.getResponse_date() + "`, `"
 * + response.getResponse_id() +"`);";
 * 
 * try { statment = dBConn.createStatement();
 * 
 * if ((statment.executeUpdate(sql) == 1)) { this.objOs.writeObject(false);
 * this.objOs.writeObject(true);//return true to client if successful }else { }
 * } catch (SQLException | IOException e) { e.printStackTrace(); } }
 * 
 * private void addUser(models.User user) { String sql =
 * "INSERT INTO micro_star.`users` (email,first_name,last_name,password,user_id,user_role)"
 * + "VALUES (" + null + ", '" + user.getEmail() +"`, `" + user.getFirstName() +
 * "`, " + "`, `" + user.getLastName() + "`, `" + user.getPassword()+"`, '" +
 * user.getRole() +"');";
 * 
 * try { statment = dBConn.createStatement();
 * 
 * if ((statment.executeUpdate(sql) == 1)) { this.objOs.writeObject(false);
 * this.objOs.writeObject(true);//return true to client if successful }else { }
 * } catch (SQLException | IOException e) { e.printStackTrace(); } }
 * 
 * }
 */
