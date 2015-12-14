package common.demo.command;

import common.ICmd2ModelAdapter;
import common.IInitUser;
import common.message.IInitMessage;
import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;

/**
 * A no-op command. 
 */
public class NullCmd extends ADataPacketAlgoCmd<String, IInitMessage, IInitUser> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1652050027284752998L;

	@Override
	public String apply(Class<?> index, DataPacket<IInitMessage> host, IInitUser... params) {
		return "Received an unknown-type message and ignored. ";
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
	}

}
