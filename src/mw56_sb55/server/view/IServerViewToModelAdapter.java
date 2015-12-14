package mw56_sb55.server.view;

import common.IChatroom;
import common.IInitUser;

public interface IServerViewToModelAdapter<IChatroom, InitUser> {

	/**
	 * create a new team to be passed to the client to join.
	 * @param teamName The name of the new team.
	 */
	public IChatroom createNewTeam(String teamName);
	
	/**
	 * assign a team from the drop list to the selected person.
	 * @param team The team to be assigned
	 * @param playerstub The person to be assigned.
	 */
	public void assignTeam(IChatroom team, IInitUser playerstub);
	
	/**
	 * Start the game, call model.startGame();
	 */
	public void startGame();
	
	/**
	 * set team created=true if the team has been created.
	 * @param created
	 */
	public void setTeamCreated(boolean created);

}
