package mw56_sb55.game.api;

import java.util.List;

import mw56_sb55.client.chatapp.model.LocalPlace;
import mw56_sb55.game.model.roadrunner.City;

/**
 * Interface for a city in the Missile World game. A city is owned by one player.
 */
public interface ICity {

	/**
	 * Get the place of this city.
	 * @return the place
	 */
	public  LocalPlace getPlace() {
		
	}
	/**
	 * Set the place of this city.
	 * @param place the place to set
	 */
	public void setPlace(LocalPlace place) {
		
	}
	
	/**
	 * Get the next available cities.
	 * @return The nextAvailableCities list.
	 */
	public List<ICity> getNextAvailableCities() {
		
	}

	/**
	 * Set the next available cities.
	 * @param nextAvailableCities The nextAvailableCities list to set
	 */
	public void setNextAvailableCities(List<ICity> nextAvailableCities) {

	}

	/**
	 * Add the next available city.
	 * @param city The city to be added.
	 */
	public void addNextCity(ICity city){
		
	}


	

	public String toString(){
		
	}
}
