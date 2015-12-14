package common.message.init;

import java.util.Set;

import common.IChatroom;
import common.message.AResponse;
import common.message.IInitMessage;

/**
 * A message containing initial user's chatroom list. 
 */
public abstract class AChatroomListResponse extends AResponse implements IInitMessage {

	/**
	 * Auto-generated UID.
	 */
	private static final long serialVersionUID = 4793933884613226865L;

	/**
	 * Constructs a new response with the same ID from its request. 
	 * @param request - the corresponding request of this response. 
	 */
	public AChatroomListResponse(AChatroomListRequest request) {
		super(request);
	}

	/**
	 * Returns user's chatroom list. 
	 * @return user's chatroom list. 
	 */
	public abstract Set<IChatroom> getChatrooms();

}
