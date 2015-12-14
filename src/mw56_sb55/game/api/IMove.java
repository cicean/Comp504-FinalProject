package mw56_sb55.game.api;

import java.io.Serializable;

public interface IMove extends Serializable {

	/**
	 * Get the current city.
	 * @return
	 */
	public String getCurCity();
	
	/**
	 * Get the time spent using the selected transporation.
	 * @return Time spent.
	 */
	public int getTime();
	
	/**
	 * Get the price of the transportation.
	 * @return Price of the transportation
	 */
	public int getPrice();
	
}
