package mw56_sb55.game.api;

import java.io.Serializable;

import mw56_sb55.game.controller.GameFactory;

public interface IGameWorld extends Serializable {
	/**
	 * Make a new game.
	 * @return
	 */
	public GameFactory makeGame();
}
