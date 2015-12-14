package common.demo;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import common.IChatUser;
import common.IChatroom;
import common.message.IChatMessage;
import provided.datapacket.DataPacket;

/**
 * A chatroom. 
 */
public class Chatroom implements IChatroom, Serializable {

	/**
	 * Auto-generated UID
	 */
	private static final long serialVersionUID = 7220930159266341106L;

	/**
	 * ID of this chatroom
	 */
	private UUID roomID = UUID.randomUUID();

	/**
	 * Name of this chatroom
	 */
	private String name = "Room";

	/**
	 * Remote users in this chatroom
	 */
	private HashSet<IChatUser> chatUserStubList;

	/**
	 * Constructs a new chatroom. 
	 */
	public Chatroom(String name) {
		this.name = name;
		chatUserStubList = new HashSet<IChatUser>();
	}

	@Override
	public UUID getID() {
		return roomID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Set<IChatUser> getUsers() {
		return chatUserStubList;
	}

	@Override
	public boolean addUser(IChatUser user) {
		return chatUserStubList.add(user);
	}

	@Override
	public boolean removeUser(IChatUser user) {
		return chatUserStubList.remove(user);
	}

	@Override
	public void send(IChatUser sender, IChatMessage message) {
		DataPacket<? extends IChatMessage> dp = message.getDataPacket();
		for (IChatUser user: chatUserStubList) {
			try {
				user.receive(sender, dp);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Check if a member is in the team.
	 * @param host The member stub.
	 * @return True if the given memberstub is already in the team.
	 */
//	public boolean isInTeam(Object host){
//		
//		
//		for(IChatUser m: chatUserStubList){
//			try {
//				if(m.getchatIp().equals(host.getchatIp())){
//					return true;
//				}
//			} catch (RemoteException e) {
//				e.printStackTrace();
//			}
//		}
//		return false;
//		
//	}

}
