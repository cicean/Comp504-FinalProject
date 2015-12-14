package common.demo.message.init;

import java.util.Set;

import common.IChatroom;
import common.message.IInitMessage;
import common.message.init.AChatroomListRequest;
import common.message.init.AChatroomListResponse;
import provided.datapacket.DataPacket;

/**
 * A message containing initial user's chatroom list. 
 */
public class ChatroomListResponse extends AChatroomListResponse {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = 6222263246432828497L;

	/**
	 * User's chatroom list. 
	 */
	private final Set<IChatroom> chatrooms;

	/**
	 * Constructs a new message containing user's chatroom list. 
	 * @param request - the corresponding request of this response. 
	 * @param chatrooms - user's chatroom list. 
	 */
	public ChatroomListResponse(AChatroomListRequest request, Set<IChatroom> chatrooms) {
		super(request);
		this.chatrooms = chatrooms;
	}

	@Override
	public Set<IChatroom> getChatrooms() {
		return chatrooms;
	}

	@Override
	public DataPacket<? extends IInitMessage> getDataPacket() {
		return new DataPacket<AChatroomListResponse>(AChatroomListResponse.class, this);
	}

}
