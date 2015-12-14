package mw56_sb55.game.api;

import common.IChatUser;
import common.message.IChatMessage;

public interface ILocalGameOver extends IChatMessage {
	
	/**
	 * Get the sender.
	 * @return sender.
	 */
	public IChatUser getSender();

}
