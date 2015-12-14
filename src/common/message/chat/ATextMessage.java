package common.message.chat;

import common.message.ARequest;
import common.message.IChatMessage;

/**
 * A message containing a text string. 
 */
public abstract class ATextMessage extends ARequest implements IChatMessage {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -5258832909858658308L;

	/**
	 * Returns the text string. 
	 * @return the text string. 
	 */
	public abstract String getText();	

}
