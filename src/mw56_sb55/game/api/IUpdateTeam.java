package mw56_sb55.game.api;

import java.io.Serializable;

public interface IUpdateTeam extends Serializable {
	/**
	 * Get the score.
	 * @return
	 */
	public int getScore();
	
	/**
	 * Get the latitude.
	 * @return The latitude.
	 */
	public double getLatitude();
	
	/**
	 * Get the longitude.
	 * @return The longitude.
	 */
	public double getLongtitude();
}
