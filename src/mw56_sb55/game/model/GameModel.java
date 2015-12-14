package mw56_sb55.game.model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import mw56_sb55.server.model.roadrunner.IPlayer;

/**
 * The main model for the Missile World game.
 */
public class GameModel implements Remote {

	/** List of adapters to remote GameViews*/
	private List<IGameModel2ViewAdapter> _adapterList;

	/**
	 * Sample action that a player can do to the model from their view.
	 * 
	 * @param player the player must be sent
	 * @param i placeholder variable, can be anything
	 * @throws RemoteException
	 */
	public void doSomething(IPlayer player, int i) throws RemoteException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (IGameModel2ViewAdapter adapt : _adapterList) {
					// do something with each adapter
				}
			}
		}).start();
	}

}
