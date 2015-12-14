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
	private HashSet<IChatUser> users = new HashSet<IChatUser>();

	/**
	 * Constructs a new chatroom. 
	 */
	public Chatroom() {
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
		return users;
	}

	@Override
	public boolean addUser(IChatUser user) {
		return users.add(user);
	}

	@Override
	public boolean removeUser(IChatUser user) {
		return users.remove(user);
	}

	@Override
	public void send(IChatUser sender, IChatMessage message) {
		DataPacket<? extends IChatMessage> dp = message.getDataPacket();
		for (IChatUser user: users) {
			try {
				user.receive(sender, dp);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

}
