package mw56_sb55.server.model.roadrunner;

import java.util.List;

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
	List<IPlayer> getMembers();

}
