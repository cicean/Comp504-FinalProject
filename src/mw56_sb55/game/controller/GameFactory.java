package mw56_sb55.game.controller;

import common.IChatUser;
import mw56_sb55.game.api.IGameWorld;

public class GameFactory implements IGameWorld {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 871080065403457808L;

	/**
	 * The game controller.
	 */
	private GameController gc;
	
	/**
	 * The server member stub.
	 */
	private IChatUser clientPlayer;
	
	/**
	 * Construct a game factory.
	 * @param serverMember
	 */
	public GameFactory(IChatUser clientPlayer){
		this.clientPlayer = clientPlayer;
	}
	
	/**
	 * create a new game controller and start it.
	 */
	public void start(){
		gc = new GameController(clientPlayer);
		gc.start();
	}
	
	/**
	 * Get the game controller
	 * @return The game controller.
	 */
	public GameController getController(){
		return gc;
	}

	@Override
	public GameFactory makeGame() {
		// TODO Auto-generated method stub
		start();
		return this;
	}

}
