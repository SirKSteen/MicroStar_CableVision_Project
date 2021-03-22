package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import controllers.AccountController;
import models.Account;
import utils.CustomizedException;

public class Server {

	private ServerSocket serverSocket;
	private Socket socket;
	private ObjectOutputStream objectOutStream;
	private ObjectInputStream objectInStream;
	private AccountController accountController;
	
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
		this.objectInStream = new ObjectInputStream(this.socket.getInputStream());
		this.objectOutStream = new ObjectOutputStream(this.socket.getOutputStream());
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
				Account updatedAccount = (Account)this.objectInStream.readObject();
				break;
			case "findbyid": 
				int accountId = (int)this.objectInStream.readObject();
				break;
			case "deleteaccount": 
				int deleteAcctId = (int)this.objectInStream.readObject();
				break;
			case "getallaccounts": 
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + operation.toLowerCase());
			}
			break;
		case "auth":
			switch (operation.toLowerCase()) {
			case "login": 
				break;
			case "updatepassword": 
				break;
			case "register": 
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + operation.toLowerCase());
			}
			break;
		case "complaint":
			switch (operation.toLowerCase()) {
			case "createcomplaint": 
				break;
			case "updatecomplaint": 
				break;
			case "findbyid": 
				break;
			case "deletecomplaint": 
				break;
			case "getallcomplaints": 
				break;
			case "assigntechnician": 
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + operation.toLowerCase());
			}
			break;
		case "response":
			switch (operation.toLowerCase()) {
			case "addresponse": 
				break;
			case "updateresponse": 
				break;
			case "findbyid": 
				break;
			case "deleteresponse": 
				break;
			case "getallresponses": 
				break;
			case "getresponsespercomplaint": 
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + operation.toLowerCase());
			}
			break;
		case "user":
			switch (operation.toLowerCase()) {
			case "createuser": 
				break;
			case "updateuser": 
				break;
			case "findbyid": 
				break;
			case "findbyemail": 
				break;
			case "deleteuser": 
				break;
			case "getallusers": 
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + operation.toLowerCase());
			}
			break;
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + endPoint.toLowerCase());
		}
	}
}
