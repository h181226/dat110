package no.hvl.dat110.messaging;

import java.net.Socket;

public class MessagingClient {

	private String server;
	private int port;

	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
	}

	// connect to messaging server
	public Connection connect() {

		Socket clientSocket = null;
		Connection connection = null;
		
		try {
			clientSocket = new Socket(server, port);
		} catch (Exception e) {
			System.out.println("Connect: " + e.getMessage());
		}
		
		connection = new Connection(clientSocket);
		
		return connection;
	}
}
