package mw56_sb55.game.api;

import gov.nasa.worldwind.geom.Position;
import map.IRightClickAction;

/**
 * Interface for a missile in the Missile World game. The missile will move, hit cities, and detect mouse clicks.
 */
public interface IMissile extends IRightClickAction {

	/**
	 * Action when the missile is clicked.
	 */
	void clickAction();

	/**
	 * Action when it hits a city.
	 * 
	 * @param city the city that was hit
	 */
	void hitCity(ICity city);

	/**
	 * @return the position of the missile.
	 */
	Position getPosition();

	/**
	 * Update the the missile with every tick of the game clock.
	 */
	void update();

}
