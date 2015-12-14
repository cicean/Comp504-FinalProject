package mw56_sb55.client.chatroom.model;

import java.rmi.RemoteException;
import java.util.UUID;

import mw56_sb55.client.chatapp.model.IInitUserToModelAdapt;
import provided.datapacket.DataPacket;
import provided.datapacket.DataPacketAlgo;
import common.IChatUser;
import common.IChatroom;
import common.IInitUser;
import common.message.IChatMessage;
import common.message.IInitMessage;

public class ChatUser implements IChatUser {
	
	
	/**
	 * This is the chatuser's name.
	 */
	private String chatusername;
	
	/**
	 * This is the randomized uuid to identify a person.
	 */
	private String ip;
	
	private IChatUserToModelAdapt chatUserToModelAdapt;
	
	private IChatroom ownChatRoom;
	
	DataPacketAlgo<String, IChatUser> chatAlgo;
	
	
	public  ChatUser(String username,  String ip,DataPacketAlgo<String, IChatUser> chatAlgo,IChatUserToModelAdapt chatUserToModelAdapt) {
		this.chatUserToModelAdapt = chatUserToModelAdapt;
		this.chatusername = username;
		this.ip = ip;
		this.chatAlgo = chatAlgo;
		
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Get the team that this member belongs to.
	 * @return The team that this member belongs to.
	 */
	public IChatroom getOwnChatRoom() {
		return ownChatRoom;
	}

	/**
	 * Set the member to team model adapter.
	 * @param adapter The member to team model adapter.
	 */
	public void setMember2TeamModelAdpt(IChatUserToModelAdapt adapter){
		this.chatUserToModelAdapt = adapter;
	}
	
	public String getName() throws RemoteException {
		return chatusername;
	}
	
	public String getUserIp() throws RemoteException {
		return ip;
	}
	
	public String toString() {
		return chatusername;
	}

	@Override
	public void receive(IChatUser sender, DataPacket<? extends IChatMessage> dp)
			throws RemoteException {
		// TODO Auto-generated method stub
		dp.execute(chatAlgo);
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		this.chatusername = name;
		
	}

	public void setIp(String ip2) {
		// TODO Auto-generated method stub
		this.ip = ip2;
		
	}

}
