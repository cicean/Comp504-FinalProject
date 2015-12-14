package common.demo.message.init;

import common.message.IInitMessage;
import common.message.init.AInitUserInfoRequest;
import common.message.init.AInitUserInfoResponse;
import provided.datapacket.DataPacket;

/**
 * A message containing initial user's information. 
 */
public class InitUserInfoResponse extends AInitUserInfoResponse {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -6439203006024872836L;

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
	public InitUserInfoResponse(AInitUserInfoRequest request, String userName, String userIP) {
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
	public DataPacket<? extends IInitMessage> getDataPacket() {
		return new DataPacket<AInitUserInfoResponse>(AInitUserInfoResponse.class, this);
	}

}
