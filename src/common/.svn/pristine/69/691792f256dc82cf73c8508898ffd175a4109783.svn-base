package common.demo.command;

import common.ICmd2ModelAdapter;
import common.IChatUser;
import common.IChatroom;
import common.IInitUser;
import common.demo.message.chat.AddMe;
import common.message.init.AInvitation2Chatroom;
import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;

/**
 * A command that handles invitation to join chatroom. 
 */
public class Invitation2ChatroomCmd extends ADataPacketAlgoCmd<String, AInvitation2Chatroom, IInitUser> {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -3447433824812691717L;

	/**
	 * Sender of this command. 
	 */
	private IChatUser me;

	/**
	 * Constructs a new command that handles invitation to join chatroom. 
	 * @param me - sender of this command. 
	 */
	public Invitation2ChatroomCmd(IChatUser me) {
		this.me = me;
	}

	@Override
	public String apply(Class<?> index, DataPacket<AInvitation2Chatroom> host, IInitUser... params) {
		AInvitation2Chatroom invitation = host.getData();
		IChatroom room = invitation.getChatroom();
		if (invitation.mustAccept()) {
			room.send(me, new AddMe(me));
		} else {
			room.send(me, new AddMe(me));
		}
		return "Received an invitaion to a chatroom. ";
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
	}

}
