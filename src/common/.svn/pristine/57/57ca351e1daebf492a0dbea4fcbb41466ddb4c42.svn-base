package common.demo.command;

import common.ICmd2ModelAdapter;
import common.message.chat.AAddMe;
import common.IChatUser;
import common.IChatroom;
import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;

/**
 * A command that handles AddMe message. 
 */
public class AddMeCmd extends ADataPacketAlgoCmd<String, AAddMe, IChatUser> {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -7869203227291283811L;

	/**
	 * Sender of this command. 
	 */
	private IChatroom room;

	/**
	 * Constructs a new command that handles AddMe message. 
	 * @param room - the local chatroom. 
	 */
	public AddMeCmd(IChatroom room) {
		this.room = room;
	}

	@Override
	public String apply(Class<?> index, DataPacket<AAddMe> host, IChatUser... params) {
		AAddMe addme = host.getData();
		IChatUser user2add = addme.getUser();
		room.addUser(user2add);
		return "Received a request to add a user. ";
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
	}

}
