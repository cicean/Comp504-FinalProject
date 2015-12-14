package mw56_sb55.game.api;



import common.message.IChatMessage;

import provided.datapacket.ADataPacket;
import provided.datapacket.ADataPacketAlgoCmd;

public interface IGameOver extends IChatMessage {

	/**
	 * Show game over.
	 */
	public void ShowGameOver();

	/**
	 * Get the command
	 * @return The command.
	 */
	public ADataPacketAlgoCmd<ADataPacket, ?, Void> getCmd();
	
}
