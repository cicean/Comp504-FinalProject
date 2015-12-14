package mw56_sb55.game.message;

import java.io.Serializable;
import java.util.UUID;

import provided.datapacket.DataPacket;
import common.IChatUser;
import common.message.IChatMessage;

public class LocalGameOverMsg implements IChatMessage {
	
	IChatUser clientuser;
	
	public IChatUser getSender() {
		return clientuser;
	}

	@Override
	public UUID getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataPacket<? extends IChatMessage> getDataPacket() {
		// TODO Auto-generated method stub
		return null;
	}

}
