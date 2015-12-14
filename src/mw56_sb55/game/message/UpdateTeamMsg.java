package mw56_sb55.game.message;

import java.util.UUID;

import provided.datapacket.DataPacket;
import common.message.IChatMessage;
import mw56_sb55.game.api.IUpdateTeam;

public class UpdateTeamMsg implements IUpdateTeam, IChatMessage {
	
	/**
	 * current score.
	 */
	private int curScore;
	
	/**
	 * The latitude.
	 */
	private double latitude;
	
	/**
	 * The longitude
	 */
	private double longtitude;
	/**
	 * 
	 */
	private static final long serialVersionUID = -4638902457990726060L;

	/**
	 * Construct a updateTeamMsg.
	 * @param n The current score.
	 * @param lat The latitude
	 * @param longt The longitude.
	 */
	public UpdateTeamMsg(int n, double lat, double longt){
		this.curScore = n;
		this.latitude = lat;
		this.longtitude = longt;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getLatitude() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getLongtitude() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UUID getID() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DataPacket<? extends IChatMessage> getDataPacket() {
		// TODO Auto-generated method stub
		return null;
	}



}
