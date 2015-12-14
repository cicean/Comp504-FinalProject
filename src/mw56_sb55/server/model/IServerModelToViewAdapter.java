package mw56_sb55.server.model;


import common.IChatroom;
import common.IInitUser;

public interface IServerModelToViewAdapter {

	/**
	 * Append text to the server GUI.
	 * @param s The text to be appended.
	 */
	public void append(String s);

	/**
	 * Set the remote host.
	 * @param localAddress The local address.
	 */
	public void setRemoteHost(String localAddress);
	
	/**
	 * add the created team to the team drop list.
	 * @param team The team object of type IChatroom
	 */
	public void addToTeamDropList(IChatroom team);
	
	/**
	 * add the newly connected person to the person list.
	 * @param personStub The newly connected person to be added to the person drop list.
	 */
	public void addToPersonDropList(IInitUser personStub);

	
	/**
	 * Get the selected chat room
	 * @return The selected chat room
	 */
	public IChatroom getSelectedChatroom();

	
}
