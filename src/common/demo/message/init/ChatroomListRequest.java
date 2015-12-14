package common.demo.message.init;

import common.message.IInitMessage;
import common.message.init.AChatroomListRequest;
import provided.datapacket.DataPacket;

/**
 * A message asking for initial user's chatroom list. 
 */
public class ChatroomListRequest extends AChatroomListRequest {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -8543337463182982639L;

	/**
	 * Constructs a new message to request user's chatroom list. 
	 */
	public ChatroomListRequest() {
	}

	@Override
	public DataPacket<? extends IInitMessage> getDataPacket() {
		return new DataPacket<AChatroomListRequest>(AChatroomListRequest.class, this);
	}

}
