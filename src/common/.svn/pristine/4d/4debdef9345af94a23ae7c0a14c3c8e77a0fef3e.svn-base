package common.message.chat;

import common.message.AResponse;
import common.message.IChatMessage;

/**
 * A message containing a chat user's information. 
 */
public abstract class AChatUserInfoResponse extends AResponse implements IChatMessage {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -5073520089911473553L;

	/**
	 * Constructs a new response with the same ID from its request. 
	 * @param request - the corresponding request of this response. 
	 */
	public AChatUserInfoResponse(AChatUserInfoRequest request) {
		super(request);
	}

	/**
	 * Returns user's name. 
	 * @return user's name. 
	 */
	public abstract String getName();	

	/**
	 * Returns user's IP address. 
	 * @return user's IP address. 
	 */
	public abstract String getIP();	

}
