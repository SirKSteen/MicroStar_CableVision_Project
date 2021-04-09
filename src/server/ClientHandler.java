 package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.LogManager;

import org.jboss.logging.Logger;

import com.mysql.cj.log.Log;

import controllers.AccountController;
import controllers.AuthController;
import controllers.ComplaintController;
import controllers.ResponseController;
import controllers.UserController;
import models.Account;
import models.Complaint;
import models.Response;
import models.User;
import utils.CustomizedException;
import utils.Role;

public class ClientHandler implements Runnable {
	private Socket socket;
	private ObjectOutputStream objectOutStream;
	private ObjectInputStream objectInStream;
	private AccountController accountController;
	private AuthController authController;
	private ComplaintController complaintController;
	private ResponseController responseController;
	private UserController userController;
	
	private static final Logger LOGGER = LogManager.getLogger(Server.class.getName());
	
	public ClientHandler(Socket socket) {
		this.socket = socket;
		this.authController = new AuthController();
		this.accountController = new AccountController();
		this.complaintController = new ComplaintController();
		this.responseController = new ResponseController();
		this.userController = new UserController();
		try {
			initDataStreams();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
	
	private void initDataStreams() throws IOException {
		try {
			this.objectOutStream = new ObjectOutputStream(this.socket.getOutputStream());
			this.objectInStream = new ObjectInputStream(this.socket.getInputStream());

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private void acceptAndProcessRequest() throws CustomizedException {
		String operation = "",endPoint = "";
			try {
				operation = (String)this.objectInStream.readObject();
				endPoint = (String)this.objectInStream.readObject();
				processRequest(operation, endPoint);
			}catch (IOException e) {
				throw new CustomizedException(e.getMessage());
				LOGGER.error(e.getMessage());
			}
			catch(ClassNotFoundException cnf) {
				throw new CustomizedException(cnf.getMessage());
				LOGGER.error(cnf.getMessage());
			}catch (Exception exc){
				throw new CustomizedException(exc.getMessage());
				LOGGER.error(exc.getMessage());
			}
		
	}
	
	private void processRequest(String operation, String endPoint) throws CustomizedException {
		
		switch (endPoint.toLowerCase()) {
		case "account":
			switch (operation.toLowerCase()) {
			case "createaccount": 
				try {
					Account acctToCreate = (Account)this.objectInStream.readObject();
					int newAccountId = accountController.createAccount(acctToCreate);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(newAccountId);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			case "updateaccount": 
				try {
					Account accToUpdate = (Account)this.objectInStream.readObject();
					Account updatedAccount = accountController.updateAccount(accToUpdate);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(updatedAccount);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			case "findbyid": 
				try {
					int acctIdToFind = (int)this.objectInStream.readObject();
					Account acctFound = accountController.findById(acctIdToFind);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(acctFound);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			case "deleteaccount": 
				try {
					int deleteAcctId = (int)this.objectInStream.readObject();
					int deletedId = accountController.deleteAccounts(deleteAcctId);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(deletedId);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			case "getallaccounts": 
				try {
					ArrayList<Account> accountList = this.accountController.getAllAccounts();
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(accountList);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break; 
			default:
				throw new IllegalArgumentException("Unexpected value: " + operation.toLowerCase());
				LOGGER.debug(operation.toLowerCase());
			}
			break;
		//AuthenticationController
		case "auth":
			switch (operation.toLowerCase()) {
			case "login": 
				try {
					int loginUserId = (int)this.objectInStream.readObject();
					String password = (String)this.objectInStream.readObject();
					Role role = (Role)this.objectInStream.readObject();
					boolean loginSuccess = this.authController.login(loginUserId, password, role);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(loginSuccess);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			case "updatepassword":
				try {
					int updatePasswordUserId = (int)this.objectInStream.readObject();
					String oldPassword = (String)this.objectInStream.readObject();
					String newPassword = (String)this.objectInStream.readObject();
					boolean passwordUpdated = this.authController.updatePassword(updatePasswordUserId, oldPassword, newPassword);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(passwordUpdated);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + operation.toLowerCase());
				LOGGER.debug(operation.toLowerCase());
			}
			break;
		case "complaint":
			switch (operation.toLowerCase()) {
			case "createcomplaint": 
				try {
					Complaint complaint = (Complaint)this.objectInStream.readObject();
					int newComplaintId = this.complaintController.addComplaint(complaint);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(newComplaintId);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			case "updatecomplaint": 
				try {
					Complaint complaintId = (Complaint)this.objectInStream.readObject();
					Complaint complaintUpdate = this.complaintController.updateComplaints(complaintId);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(complaintUpdate);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			case "findbyid": 
				try {
					int comIdToFind = (int)this.objectInStream.readObject();
					Complaint returnFoundComplaint = this.complaintController.findById(comIdToFind);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(returnFoundComplaint);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				
				break;
			case "deletecomplaint": 
				try {
					int deleteId = (int)this.objectInStream.readObject();
					int returnDeleteId = this.complaintController.deleteComplaint(deleteId);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(returnDeleteId);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			case "getallcomplaints": 
				try {
					ArrayList<Complaint> returnComplaintList = this.complaintController.getAllComplaints();
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(returnComplaintList);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			case "assigntechnician": 
				try {
					Complaint techComplaint = (Complaint)this.objectInStream.readObject();
					Complaint c = this.complaintController.assignTechnician(techComplaint);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(c);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + operation.toLowerCase());
				LOGGER.debug(operation.toLowerCase());
			}
			break;
			//response 
		case "response":
			switch (operation.toLowerCase()) {
			case "addresponse": 
				try {
					Response responseToAdd = (Response)this.objectInStream.readObject(); 
					int addedRespId = this.responseController.addResponse(responseToAdd);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(addedRespId);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			case "updateresponse": 
				try {
					Response updateId = (Response) this.objectInStream.readObject();
					Response returnUpdateId = this.responseController.updateResponse(updateId);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(returnUpdateId);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			case "findbyid": 
				try {
					int findResponseId = (int)this.objectInStream.readObject();
					Response returnFoundResponseId = this.responseController.findById(findResponseId);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(returnFoundResponseId);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			case "deleteresponse":
				try {
					int deleteResponseId = (int)this.objectInStream.readObject();
					int returnDeleteId = this.responseController.deleteResponse(deleteResponseId);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(returnDeleteId);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			case "getallresponses": 
				try {
					ArrayList<Response> returnAllResponse = this.responseController.getAllResponses();
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(returnAllResponse);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			case "getresponsespercomplaint": 
				try {
					int complaintId = (int)this.objectInStream.readObject();
					ArrayList<Response> returnComplaint = this.responseController.getResponsesPerComplaint(complaintId);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(returnComplaint);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + operation.toLowerCase());
				LOGGER.debug(operation.toLowerCase());
			}
			break;
		case "user":
			switch (operation.toLowerCase()) {
			case "createuser": 
				try {
					User user = (User)this.objectInStream.readObject();
					int returnUserId = this.userController.createUser(user);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(returnUserId);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			case "updateuser":
				try {
					User userIdUpdate = (User) this.objectInStream.readObject();
					User returnUserIdUpdate = this.userController.updateUser(userIdUpdate);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(returnUserIdUpdate);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			case "findbyid": 
				try {
					int userId = (int)this.objectInStream.readObject();
					User returnFindUser = this.userController.findById(userId);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(returnFindUser);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			case "findbyemail": 
				try {
					String findByEmail = (String)this.objectInStream.readObject();
					boolean returnFindByEmail = this.userController.findByEmail(findByEmail);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(returnFindByEmail);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			case "deleteuser": 
				try {
					int deleteUser = (int)this.objectInStream.readObject();
					int returnDeleteUser= this.userController.deleteUser(deleteUser);
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(returnDeleteUser);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			case "getallusers": 
				try {
					ArrayList<User> userList = this.userController.getAllUsers();
					this.objectOutStream.writeObject("success");
					this.objectOutStream.writeObject(userList);
				} catch (Exception e) {
					throw new CustomizedException(e.getMessage());
					LOGGER.error(e.getMessage());
				}
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + operation.toLowerCase());
				LOGGER.debug(operation.toLowerCase());
			}
			break;
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + endPoint.toLowerCase());
			LOGGER.debug(endPoint.toLowerCase());
		}
	}
	
	

	@Override
	public void run() {
		
		try {
			acceptAndProcessRequest();
		} catch (CustomizedException e) {
			try {
				this.objectOutStream.writeObject("error");
				this.objectOutStream.writeObject(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (Exception exc) {
			CustomizedException e = new CustomizedException(exc.getMessage());
			try {
				this.objectOutStream.writeObject("error");
				this.objectOutStream.writeObject(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
