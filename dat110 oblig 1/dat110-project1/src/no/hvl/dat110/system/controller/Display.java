package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.*;

public class Display extends RPCStub {

	private byte RPCID = 1;

	public void write(String message) {

		byte[] marshall = RPCUtils.marshallString(RPCID,message);
		rmiclient.call(marshall);
		RPCUtils.unmarshallString(marshall);
	}
}
