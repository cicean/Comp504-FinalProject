package mw56_sb55.client.chatroom.model;

import java.awt.Container;
import java.awt.Image;
import java.util.Collection;



/**
 * The chat room model to view adatper
 * @author 
 *
 */
public interface IChatroomToViewAdapter {

	
	void setTitle(String name);

	
	/**
     * Start the chat room view
     */
	void start();

	
	/**
	 * Displays a message on the chatroom's view.
	 * 
	 * @param message the message to display
	 */
	void displayMessage(String message);


	void append(String text);


	void addUsertoList(Collection<ChatUser> collection) throws Exception;


	void removeUserfromList(ChatUser chatUser);


	void addUsertoList(ChatUser chatUser);


	
	/**
	 * make the team-mini-model-to-lobby-mini-view adapter. We use the same ITeamModel2ViewAdapter interface
	 * and the same mini model for the lobby MVC.
	 * @param teamMiniModel Lobby mini model.(Lobby and team are both the same as the chatroom)
	 * @return team The lobby mini model.
	 */
	public IChatroomToViewAdapter makeTeamModel2LobbyViewAdapter(ChatroomModel teamMiniModel);


	
}
