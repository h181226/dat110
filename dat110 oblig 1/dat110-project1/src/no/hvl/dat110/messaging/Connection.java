package no.hvl.dat110.messaging;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {

	private DataOutputStream outStream; // for writing bytes to the TCP connection
	private DataInputStream inStream; // for reading bytes from the TCP connection
	private Socket socket; // socket for the underlying TCP connection

	public Connection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream(socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) {
		
		byte[] mes = message.encapsulate();
		
		try {
			outStream.write(mes, 0, MessageConfig.SEGMENTSIZE);
		} catch (IOException e) {
			System.out.println("Send: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public Message receive() {

		Message message;
		byte[] recvbuf = new byte[MessageConfig.SEGMENTSIZE];
		
		try {
			inStream.read(recvbuf, 0, MessageConfig.SEGMENTSIZE);
		} catch (IOException e) {
			System.out.println("Receive: " + e.getMessage());
			e.printStackTrace();
		}
		
		message = new Message();
		
		message.decapsulate(recvbuf);
		
		return message;
		
	}

	// close the connection by closing streams and the underlying socket
	public void close() {

		try {

			outStream.close();
			inStream.close();

			socket.close();
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}