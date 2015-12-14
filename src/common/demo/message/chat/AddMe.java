package common.demo.message.chat;

import common.IChatUser;
import common.message.IChatMessage;
import common.message.chat.AAddMe;
import provided.datapacket.DataPacket;

/**
 * A message asking a chat user to add a specified user. 
 */
public class AddMe extends AAddMe {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = 6698734680048475690L;

	/**
	 * User to be added. 
	 */
	private final IChatUser user;

	/**
	 * Constructs a new message containing the specified user to be added. 
	 * @param user - user to be added. 
	 */
	public AddMe(IChatUser user) {
		this.user = user;
	}

	@Override
	public IChatUser getUser() {
		return user;
	}

	@Override
	public DataPacket<? extends IChatMessage> getDataPacket() {
		return new DataPacket<AAddMe>(AAddMe.class, this);
	}

}
