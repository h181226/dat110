package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class RPCUtils {

	public static byte[] marshallString(byte rpcid, String str) {

		byte[] encoded = new byte[str.length() + 1];
		
		encoded[0] = rpcid;
		
		byte[] strbyte = str.getBytes();
		
		for(int i = 0; i < strbyte.length; i++) {
			encoded[i+1] = strbyte[i];
		}
		
		return encoded;
	}

	public static String unmarshallString(byte[] data) {
		
		byte[] str = new byte[data.length - 1];
		
		for(int i = 0; i < str.length; i++) {
			str[i] = data[i+1];
		}
		
		String decoded = new String(str);
		
		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded = new byte[1];
		
		encoded[0] = rpcid;


		return encoded;

	}
	
	// Usikker på hva som skal gjøres her. 
	// Metoden skal ikke returnere noe så gir ikke mening å hente ut IDen fra tabellen
	public static void unmarshallVoid(byte[] data) {

		
	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {

		return new byte[] {
				rpcid,
				(byte) (x >> 24),
				(byte) (x >> 16),
				(byte) (x >> 8),
				(byte) (x >> 0)
		};
	}

	public static int unmarshallInteger(byte[] data) {

		return ByteBuffer.wrap(Arrays.copyOfRange(data, 1, data.length)).getInt();
	}
}
