package no.hvl.dat110.rpc;

import no.hvl.dat110.messaging.*;

public class RPCClient {

	private MessagingClient msgclient;
	private Connection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void register(RPCStub remote) {
		remote.register(this);
	}
	
	public void connect() {
		
		connection = msgclient.connect();
			
	}
	
	public void disconnect() {
		
		if(connection != null) {
		connection.close();
		}
		
	}
	
	public byte[] call(byte[] rpcrequest) {
		
		byte[] rpcreply;
		
		if(connection == null) {
			connect();
		}
		
		Message send = new Message(rpcrequest);
		connection.send(send);
		
		Message received = connection.receive();
		rpcreply = received.getData();
		
		return rpcreply;
		
	}

}
