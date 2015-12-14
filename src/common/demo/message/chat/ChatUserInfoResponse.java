package common.demo.message.chat;

import common.message.IChatMessage;
import common.message.chat.AChatUserInfoRequest;
import common.message.chat.AChatUserInfoResponse;
import provided.datapacket.DataPacket;

/**
 * A message containing a chat user's information. 
 */
public class ChatUserInfoResponse extends AChatUserInfoResponse {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -2781673188894508467L;

	/**
	 * User's name. 
	 */
	private final String userName;

	/**
	 * User's IP address. 
	 */
	private final String userIP;

	/**
	 * Constructs a new message containing user's information. 
	 * @param request - the corresponding request of this response. 
	 * @param userName - user's name. 
	 * @param userIP - user's IP address. 
	 */
	public ChatUserInfoResponse(AChatUserInfoRequest request, String userName, String userIP) {
		super(request);
		this.userName = userName;
		this.userIP = userIP;
	}

	@Override
	public String getName() {
		return userName;
	}

	@Override
	public String getIP() {
		return userIP;
	}

	@Override
	public DataPacket<? extends IChatMessage> getDataPacket() {
		return new DataPacket<AChatUserInfoResponse>(AChatUserInfoResponse.class, this);
	}

}
