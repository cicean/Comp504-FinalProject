package mw56_sb55.game.model.roadrunner;

import java.util.ArrayList;
import java.util.List;

import gov.nasa.worldwind.geom.Position;
import mw56_sb55.client.chatapp.model.LocalPlace;
import mw56_sb55.game.api.ICity;
import mw56_sb55.server.view.Player;

public class City implements ICity {
	
	/**
	 * The place of the city.
	 */
	private LocalPlace place;
	
	/**
	 * The next available cities.
	 */
	private List<City> nextAvailableCities;
	

	
	/**
	 * Construct this city.
	 * @param place The place of this city.
	 */
	
	/**
	 * Construct this city.
	 * @param place The place of this city.
	 */
	public City(LocalPlace place){
		this.place = place;
		nextAvailableCities = new ArrayList<City>();
		
	}



	@Override
	public LocalPlace getPlace() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void setPlace(LocalPlace place) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<ICity> getNextAvailableCities() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void setNextAvailableCities(List<ICity> nextAvailableCities) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void addNextCity(ICity city) {
		// TODO Auto-generated method stub
		
	}








}
