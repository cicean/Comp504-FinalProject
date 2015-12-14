package common.demo.message.init;

import common.IChatroom;
import common.message.IInitMessage;
import common.message.init.AInvitation2Chatroom;
import provided.datapacket.DataPacket;

/**
 * A message asking initial user to join a specified chatroom. 
 */
public class Invitation2Chatroom extends AInvitation2Chatroom {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = 2971192829361574318L;

	/**
	 * Chatroom to join. 
	 */
	private final IChatroom chatroom;

	/**
	 * True if receiver must join the chatroom. 
	 */
	private final boolean mustAccept;

	/**
	 * Constructs a new message containing the specified chatroom to join. 
	 * @param chatroom - chatroom to join. 
	 * @param mustAccept - true if receiver must join the chatroom. 
	 */
	public Invitation2Chatroom(IChatroom chatroom, boolean mustAccept){
		this.chatroom = chatroom;
		this.mustAccept = mustAccept;
	}

	@Override
	public IChatroom getChatroom() {
		return chatroom;
	}

	@Override
	public boolean mustAccept() {
		return mustAccept;
	}

	@Override
	public DataPacket<? extends IInitMessage> getDataPacket() {
		return new DataPacket<AInvitation2Chatroom>(AInvitation2Chatroom.class, this);
	}

}
