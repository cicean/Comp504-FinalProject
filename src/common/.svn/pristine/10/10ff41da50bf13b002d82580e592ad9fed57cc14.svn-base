package common.message;

import java.util.UUID;

/**
 * A response message that automatically matches its request's UUID. 
 */
public abstract class AResponse implements IMessage {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = 7396496250106434801L;

	/**
	 * The ID of this message. 
	 */
	private UUID msgID;

	/**
	 * Constructs a response with its request's ID. 
	 * @param request - corresponding request message. 
	 */
	public AResponse(ARequest request) {
		msgID = request.getID();
	}

	@Override
	public UUID getID() {
		return msgID;
	}

}
