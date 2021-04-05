package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {

	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private int clientCount;

	private static final Logger LOG = LogManager.getLogger(Server.class.getName());
	public Server() {
		try {
			this.clientCount = 1;
			this.serverSocket = new ServerSocket(5000);
			System.out.println("Server started on port 5000\t"+LocalDateTime.now());
			while(true) {
				try {
					this.connectionSocket = this.serverSocket.accept();
					System.out.println("Starting a new thread for client "+this.clientCount++ +"\t"+LocalDateTime.now()+"\t");
					ClientHandler clientHandler = new ClientHandler(connectionSocket);
					new Thread(clientHandler).start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.out.println("Failed server socket "+e.getLocalizedMessage());
		}
	
	}

}