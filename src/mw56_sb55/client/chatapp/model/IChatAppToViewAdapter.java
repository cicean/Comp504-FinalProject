package mw56_sb55.client.chatapp.model;


import java.util.Set;

import common.IChatroom;
import mw56_sb55.client.chatroom.model.ChatroomModel;
import mw56_sb55.client.chatroom.model.IChatroomToViewAdapter;



/**
 * The chat app model to view adapter
 * @author mw56
 */
public interface IChatAppToViewAdapter {


	/**
	 * Set the UI's title
	 * @param title The title
	 */
	public void setIPAddress(String title);

;



	public void append(String format);
	
	
	 /**
     * Make a chat room model to view adapter
     * @param model The chat room model
     * @return The chat room model to view adapter
     */
    public IChatroomToViewAdapter makeChatroomViewAdapter(ChatroomModel teammodel);

    public IChatroomToViewAdapter makeTeamModel2LobbyViewAdapter(
			ChatroomModel teamlobbyModel);

	public void chooseChatroom(InitUser initUser, Set<IChatroom> chatroomList);


    

	
}
