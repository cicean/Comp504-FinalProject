package common;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import common.message.IChatMessage;

/**
 * Chatroom is essentially a collection of remote users. 
 */
public interface IChatroom extends Serializable {

	/**
	 * Returns the ID of this chatroom. 
	 * @return the ID of this chatroom.
	 */
	public UUID getID();

	/**
	 * Returns the name of this chatroom. 
	 * @return the name of this chatroom. 
	 */
	public String getName();

	/**
	 * Replaces the name of this chatroom. 
	 * @param name - name to use. 
	 */
	public void setName(String name);

	/**
	 * Returns a set of all users in this chatroom. 
	 * @return a set of all users in this chatroom. 
	 */
	public Set<IChatUser> getUsers();

	/**
	 * Adds the specified user to this chatroom. 
	 * @param user - user to be added. 
	 * @return true if this chatroom did not already contain the specified user. 
	 */
	public boolean addUser(IChatUser user);

	/**
	 * Removes the specified user from this chatroom. 
	 * @param user - user to be removed. 
	 * @return true if this chatroom contained the specified user. 
	 */
	public boolean removeUser(IChatUser user);

	/**
	 * Sends the specified message to every user in this chatroom. 
	 * @param sender - user who sent this message. 
	 * @param message - message being sent. 
	 */
	public void send(IChatUser sender, IChatMessage message);

}
