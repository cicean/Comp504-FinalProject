package common.demo.message.chat;

import common.IInitUser;
import common.message.IChatMessage;
import common.message.chat.AInitUserRequest;
import common.message.chat.AInitUserResponse;
import provided.datapacket.DataPacket;

/**
 * A message containing a chat user's initial user. 
 */
public class InitUserResponse extends AInitUserResponse {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = 6657217558010931971L;

	/**
	 * The initial user of this chat user. 
	 */
	private final IInitUser user;

	/**
	 * Constructs a new message containing chat user's initial user. 
	 * @param request - the corresponding request of this response. 
	 * @param user - the initial user of this chat user. 
	 */
	public InitUserResponse(AInitUserRequest request, IInitUser user) {
		super(request);
		this.user = user;
	}

	@Override
	public IInitUser getUser() {
		return user;
	}

	@Override
	public DataPacket<? extends IChatMessage> getDataPacket() {
		return new DataPacket<AInitUserResponse>(AInitUserResponse.class, this);
	}

}
