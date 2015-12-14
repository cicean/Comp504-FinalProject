package common.message.init;

import common.message.AResponse;
import common.message.IInitMessage;

/**
 * A message containing user name and IP address.
 */
public abstract class AInitUserInfoResponse extends AResponse implements IInitMessage {

	/**
	 * Auto-generated UID.
	 */
	private static final long serialVersionUID = 6285999014533425762L;

	/**
	 * Constructs a new response with the same ID from its request. 
	 * @param request - the corresponding request of this response. 
	 */
	public AInitUserInfoResponse(AInitUserInfoRequest request) {
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
