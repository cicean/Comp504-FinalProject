package common.demo.message.chat;

import common.message.IChatMessage;
import common.message.chat.AInitUserRequest;
import provided.datapacket.DataPacket;

/**
 * A message asking for a chat user's initial user. 
 */
public class InitUserRequest extends AInitUserRequest {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -5674746615038905389L;

	/**
	 * Constructs a new message to request chat user's initial user. 
	 */
	public InitUserRequest() {
	}

	@Override
	public DataPacket<? extends IChatMessage> getDataPacket() {
		return new DataPacket<AInitUserRequest>(AInitUserRequest.class, this);
	}

}
