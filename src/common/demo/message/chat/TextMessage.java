package common.demo.message.chat;

import common.message.IChatMessage;
import common.message.chat.ATextMessage;
import provided.datapacket.DataPacket;

/**
 * A message containing a text string. 
 */
public class TextMessage extends ATextMessage {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -9032536444654285428L;

	/**
	 * The text string. 
	 */
	private String text;

	/**
	 * Constructs a new message containing a text string. 
	 * @param text - a text string. 
	 */
	public TextMessage(String text) {
		this.text = text;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public DataPacket<? extends IChatMessage> getDataPacket() {
		return new DataPacket<ATextMessage>(ATextMessage.class, this);
	}

}
