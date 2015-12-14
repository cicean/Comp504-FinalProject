package mw56_sb55.server.model.roadrunner;

import gov.nasa.worldwind.geom.Position;

/**
 * Interface for a city in the Missile World game. A city is owned by one player.
 */
public interface ICity {

	/**
	 * Action for when the city is hit by a missile, for example, increase the hit count.
	 */
	void hitAction();

	/**
	 * @return the number of times hit by a missile.
	 */
	int getHitCount();

	/**
	 * @return the owner of the city
	 */
	IPlayer getOwner();

	/**
	 * @return the position of the city
	 */
	Position getPosition();

}
