package common.demo.command;

import java.rmi.RemoteException;

import common.ICmd2ModelAdapter;
import common.demo.message.chat.CommandRequest;
import common.IChatUser;
import common.message.IChatMessage;
import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;

/**
 * A command that handles unknown messages. 
 */
public class UnknownMessageCmd extends ADataPacketAlgoCmd<String, IChatMessage, IChatUser> {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -2693800186204923931L;

	/**
	 * Receiver of this unknown message.
	 */
	private IChatUser me;

	/**
	 * Constructs a new command that handles unknown messages. 
	 * @param me - receiver of this unknown message. 
	 */
	public UnknownMessageCmd(IChatUser me) {
		this.me = me;
	}

	@Override
	public String apply(Class<?> index, DataPacket<IChatMessage> host, IChatUser... params) {
		IChatMessage msg = host.getData();
		IChatUser sender = params[0];
		CommandRequest request = new CommandRequest(msg.getClass());
		// unknowns.put(request.getID(), msg); // cache this unknown message until its command returns
		try {
			sender.receive(me, request.getDataPacket());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return "Received unknown-type data. ";
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
	}

}
