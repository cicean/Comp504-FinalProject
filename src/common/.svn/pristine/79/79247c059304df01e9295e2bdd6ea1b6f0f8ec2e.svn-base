package common.message.chat;

import common.IChatUser;
import common.message.AResponse;
import common.message.IChatMessage;
import provided.datapacket.ADataPacketAlgoCmd;

/**
 * A message containing a command for an unknown data type. 
 */
public abstract class ACommandResponse extends AResponse implements IChatMessage {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -1591864713700810101L;

	/**
	 * Constructs a new response with the same ID from its request. 
	 * @param request - the corresponding request of this response. 
	 */
	public ACommandResponse(ACommandRequest request) {
		super(request);
	}

	/**
	 * Returns the class type of unknown data. 
	 * @return the class type of unknown data. 
	 */
	public abstract Class<?> getUnknownType();

	/**
	 * Returns the command to process the unknown data. 
	 * @return the command to process the unknown data. 
	 */
	public abstract ADataPacketAlgoCmd<String, ?, IChatUser> getCommand();	

}
