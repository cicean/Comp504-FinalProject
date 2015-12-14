package common.message.chat;

import common.IChatUser;
import common.message.ARequest;
import common.message.IChatMessage;

/**
 * A message asking a chat user to add a specified user. 
 */
public abstract class AAddMe extends ARequest implements IChatMessage {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -5374369223775153634L;

	/**
	 * Returns the user to be added. 
	 * @return the user to be added. 
	 */
	public abstract IChatUser getUser();

}
