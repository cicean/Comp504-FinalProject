package mw56_sb55.game.api;

import common.message.IChatMessage;

public interface IReject extends IChatMessage {

	/**
	 * Get the reason for rejecting the message
	 * 
	 * @return The text that explains the reason for rejection.
	 */
	public String getReasonText();
}
