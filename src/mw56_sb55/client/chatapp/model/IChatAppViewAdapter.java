package mw56_sb55.client.chatapp.model;

import mw56_sb55.client.chatroom.model.ChatroomModel;
import mw56_sb55.client.chatroom.model.IChatroomViewAdapter;



/**
 * The chat app model to view adapter
 * @author mw56
 */
public interface IChatAppViewAdapter {


	/**
	 * Set the UI's title
	 * @param title The title
	 */
	public void setIPAddress(String title);

	public String getNewChatroomName();

	
	public void addChatroomlist(String chatroomName);

	 /**
     * Make a chat room model to view adapter
     * @param model The chat room model
     * @return The chat room model to view adapter
     */
	public IChatroomViewAdapter makeChatroomViewAdapter(ChatroomModel chatroomModel);

	public void append(String format);

	
}
