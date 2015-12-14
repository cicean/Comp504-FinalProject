package common.message.init;

import common.IChatroom;
import common.message.ARequest;
import common.message.IInitMessage;

/**
 * A message asking initial user to join a specified chatroom. 
 */
public abstract class AInvitation2Chatroom extends ARequest implements IInitMessage {

	/**
	 * Auto-generated UID.
	 */
	private static final long serialVersionUID = 2589941742788942976L;

	/**
	 * Returns the chatroom to join. 
	 * @return the chatroom to join. 
	 */
	public abstract IChatroom getChatroom() ;

	/**
	 * Returns true if receiver must join the chatroom. 
	 * @return true if receiver must join the chatroom. 
	 */
	public abstract boolean mustAccept();

}
