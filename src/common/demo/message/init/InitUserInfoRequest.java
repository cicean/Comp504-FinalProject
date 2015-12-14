package common.demo.message.init;

import common.message.IInitMessage;
import common.message.init.AInitUserInfoRequest;
import provided.datapacket.DataPacket;

/**
 * A message asking for initial user's information. 
 */
public class InitUserInfoRequest extends AInitUserInfoRequest {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -8352597776239044691L;

	/**
	 * Constructs a new message to request user's information. 
	 */
	public InitUserInfoRequest() {
	}

	@Override
	public DataPacket<? extends IInitMessage> getDataPacket() {
		return new DataPacket<AInitUserInfoRequest>(AInitUserInfoRequest.class, this);
	}

}
