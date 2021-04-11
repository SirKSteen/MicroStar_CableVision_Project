package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {

	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private int clientCount;
	private ArrayList<ClientHandler> clientList;

	private static final Logger LOGGER = LogManager.getLogger(Server.class.getName());
	public Server() {
		try {
			this.clientCount = 1;
			this.serverSocket = new ServerSocket(5000);
			this.clientList = new ArrayList<>();
			System.out.println("Server started on port 5000\t"+LocalDateTime.now());
			while(true) {
				try {
					this.connectionSocket = this.serverSocket.accept();

					System.out.println("Starting a new thread for client "+this.clientCount +"\t"+LocalDateTime.now()+"\t");
					ClientHandler clientHandler = new ClientHandler(connectionSocket,clientList,"client "+this.clientCount);
					Thread clientTread = new Thread(clientHandler);
					clientList.add(clientHandler);
					clientTread.start();

				} catch (IOException e) {
					LOGGER.fatal(e.getMessage());
				}
			}
		} catch (IOException e) {
			LOGGER.fatal(e.getMessage());
		}
	
	}

}