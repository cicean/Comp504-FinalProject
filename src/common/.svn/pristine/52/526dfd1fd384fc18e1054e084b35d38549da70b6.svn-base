package common.message;

import java.util.UUID;

/**
 * A request message that automatically generates a random UUID. 
 */
public abstract class ARequest implements IMessage {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -5302549414153209157L;

	/**
	 * The ID of this message. 
	 */
	private UUID msgID = UUID.randomUUID();

	/**
	 * Constructs a request with a random ID. 
	 */
	public ARequest() {
	}

	@Override
	public UUID getID() {
		return msgID;
	}

}
