package mw56_sb55.server.view;

import java.rmi.RemoteException;

import provided.datapacket.DataPacket;
import common.IChatUser;
import common.IInitUser;
import common.message.IChatMessage;
import common.message.IInitMessage;

public class Player {
	
	private IInitUser playerStub;
	
	public Player(IInitUser user) {
		this.playerStub = user;
	}
	
	
	public IInitUser getPlayerStub() {
		return playerStub;
	}

	
	
	

}
