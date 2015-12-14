package mw56_sb55.game.api;

import common.IChatUser;
import common.IChatroom;



/**
 * Interface for a player in the Missile World game. A player is on a team and owns a city.
 */
public interface IPlayer {

	/**
	 * @return the name player's name
	 */
	String getName();

	/**
	 * @return the User object defined by the ChatApp API, containing the IP and name
	 */
	IChatUser getUser();

	/**
	 * @return the team in which the player is playing on
	 */
	IChatroom getTeam();

	/**
	 * @param team the team the player has been assigned to
	 */
	void setTeam(ITeam team);

	/**
	 * @return the name of the city that the player is in
	 */
	ICity getOwnedCity();

	/**
	 * @param city the city for the player to own
	 */
	void setOwnedCity(ICity city);

}
