package common.message.chat;

import common.IInitUser;
import common.message.AResponse;
import common.message.IChatMessage;

/**
 * A message containing a chat user's initial user. 
 */
public abstract class AInitUserResponse extends AResponse implements IChatMessage {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -658890054191338447L;

	/**
	 * Constructs a new response with the same ID from its request. 
	 * @param request - the corresponding request of this response. 
	 */
	public AInitUserResponse(AInitUserRequest request) {
		super(request);
	}

	/**
	 * Returns the initial user of this chat user.
	 * @return the initial user of this chat user. 
	 */
	public abstract IInitUser getUser();	

}
