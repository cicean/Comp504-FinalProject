package common.demo.command;

import common.ICmd2ModelAdapter;
import common.message.chat.ATextMessage;
import common.IChatUser;
import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;

/**
 * A command that handles text message. 
 */
public class TextMessageCmd extends ADataPacketAlgoCmd<String, ATextMessage, IChatUser> {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -2344010504282305244L;

	/**
	 * Constructs a new command that handles text message. 
	 */
	public TextMessageCmd() {
	}

	@Override
	public String apply(Class<?> index, DataPacket<ATextMessage> host, IChatUser... params) {
		ATextMessage text = host.getData();
		System.out.println(text.getText());
		return "Received a text message. ";
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
	}

}
