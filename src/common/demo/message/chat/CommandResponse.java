package common.demo.message.chat;

import common.IChatUser;
import common.message.IChatMessage;
import common.message.chat.ACommandRequest;
import common.message.chat.ACommandResponse;
import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;

/**
 * A message containing a command for an unknown data type. 
 */
public class CommandResponse extends ACommandResponse {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -5228915825734240633L;

	/**
	 * The class type of unknown data. 
	 */
	private final Class<?> type;

	/**
	 * The command to process the unknown data. 
	 */
	private final ADataPacketAlgoCmd<String, ?, IChatUser> cmd;

	/**
	 * Constructs a new message containing a command for an unknown data type. 
	 * @param request - the corresponding request of this response. 
	 * @param type - class type of unknown data. 
	 * @param cmd - command to process the unknown data. 
	 */
	public CommandResponse(ACommandRequest request, Class<?> type, ADataPacketAlgoCmd<String, ?, IChatUser> cmd) {
		super(request);
		this.type = type;
		this.cmd = cmd;
	}

	@Override
	public Class<?> getUnknownType() {
		return type;
	}

	@Override
	public ADataPacketAlgoCmd<String, ?, IChatUser> getCommand() {
		return cmd;
	}

	@Override
	public DataPacket<? extends IChatMessage> getDataPacket() {
		return new DataPacket<ACommandResponse>(ACommandResponse.class, this);
	}

}
