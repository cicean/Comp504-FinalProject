package mw56_sb55.client.chatapp.model;

import java.io.Serializable;

import gov.nasa.worldwind.geom.Position;

public class LocalPlace implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 928355161442906176L;
	
	/**
	 * The name of this place.
	 */
	private String   _name;
	
	/**
	 * The position of this place.
	 */
	private Position _pos;
	
	/**
	 * Construct a Place object using the name and position.
	 * @param name The name of the place.
	 * @param pos The position of the place.
	 */
	public LocalPlace(String name, Position pos) {
		_name = name;
		_pos = pos;
	}
	
	/**
	 * Get the position of this place
	 * @return The position of this place.
	 */
	public Position getPosition() {
		return _pos;
	}
	
	/**
	 * toString method to display the name of this place.
	 * @return The name of this place.
	 */
	public String toString() {
		return _name;
	}

	

}
