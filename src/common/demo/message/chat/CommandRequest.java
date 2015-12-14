package common.demo.message.chat;

import common.message.IChatMessage;
import common.message.chat.ACommandRequest;
import provided.datapacket.DataPacket;

/**
 * A message asking for a command for an unknown data type. 
 */
public class CommandRequest extends ACommandRequest {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -5554873966239814836L;

	/**
	 * The class type of unknown data. 
	 */
	private final Class<?> type;

	/**
	 * Constructs a new message to request a command for an unknown data type. 
	 * @param type - class type of unknown data. 
	 */
	public CommandRequest(Class<?> type) {
		this.type = type;
	}

	@Override
	public Class<?> getUnknownType() {
		return type;
	}

	@Override
	public DataPacket<? extends IChatMessage> getDataPacket() {
		return new DataPacket<ACommandRequest>(ACommandRequest.class, this);
	}

}
