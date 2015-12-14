package common.demo.message.chat;

import common.message.IChatMessage;
import common.message.chat.AChatUserInfoRequest;
import provided.datapacket.DataPacket;

/**
 * A message asking for a chat user's information. 
 */
public class ChatUserInfoRequest extends AChatUserInfoRequest {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -7287770081796638827L;

	/**
	 * Constructs a new message to request user's information. 
	 */
	public ChatUserInfoRequest() {
	}

	@Override
	public DataPacket<? extends IChatMessage> getDataPacket() {
		return new DataPacket<AChatUserInfoRequest>(AChatUserInfoRequest.class, this);
	}

}
