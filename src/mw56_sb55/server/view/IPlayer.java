package mw56_sb55.server.view;

import java.rmi.RemoteException;

import provided.datapacket.DataPacket;
import common.IChatUser;
import common.message.IChatMessage;

public class IPlayer {
	
	private IChatUser playerStub;
	
	public IPlayer(IChatUser chatUserStub) {
		this.playerStub = chatUserStub;
	}
	
	public IChatUser getPlayerStub() {
		return playerStub;
	}
	
	

	
	
	

}
