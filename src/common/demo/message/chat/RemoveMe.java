package common.demo.message.chat;

import common.IChatUser;
import common.message.IChatMessage;
import common.message.chat.ARemoveMe;
import provided.datapacket.DataPacket;

/**
 * A message asking a chat user to remove a specified user. 
 */
public class RemoveMe extends ARemoveMe {

	/**
	 * Auto-generated UID. 
	 */
	private static final long serialVersionUID = -43476278183651200L;

	/**
	 * User to be removed. 
	 */
	private final IChatUser user;

	/**
	 * Constructs a new message containing the specified user to be removed. 
	 * @param user - user to be removed. 
	 */
	public RemoveMe(IChatUser user) {
		this.user = user;
	}

	@Override
	public IChatUser getUser() {
		return user;
	}

	@Override
	public DataPacket<? extends IChatMessage> getDataPacket() {
		return new DataPacket<ARemoveMe>(ARemoveMe.class, this);
	}

}
