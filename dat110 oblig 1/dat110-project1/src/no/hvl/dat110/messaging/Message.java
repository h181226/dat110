package no.hvl.dat110.messaging;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		if(payload.length > 128) {
			throw new RuntimeException("Payload too large");
		}
		
		this.payload = payload;
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
		
		byte[] encoded = new byte[MessageConfig.SEGMENTSIZE];
		
		encoded[0] = (byte)payload.length;
		
		for(int i = 1; i <= payload.length; i++) {
			encoded[i] = payload[i-1];
		}
		
		
		return encoded;
	}

	public void decapsulate(byte[] received) {
		
		int length = received[0];
		byte[] payload = new byte[length];
		
		for(int i = 1; i <= length; i++) {
			payload[i - 1] = received[i];
		}
		
		this.payload = payload;
		
	}
}
