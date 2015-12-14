package mw56_sb55.game.api;

import java.util.List;

import mw56_sb55.client.chatroom.model.ChatUser;

/**
 * Interface for a team
 */
public interface ITeam {

	/**
	 * @return the name of the nation in which the team will be protecting
	 */
	String getNation();

	/**
	 * @return a list containing all the players on a team
	 */
	List<ChatUser> getMembers();

}
