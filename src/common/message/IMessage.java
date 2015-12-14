package common.message;

import java.io.Serializable;
import java.util.UUID;

import provided.datapacket.DataPacket;

/**
 * A message sent between remote users, which is 
 * essentially a wrapper class of DataPacket. 
 */
public interface IMessage extends Serializable {

	/**
	 * Returns the ID of this message. The ID of a response 
	 * should be same as the ID of its corresponding request. 
	 * @return the ID of this message. 
	 */
	public UUID getID();

	/**
	 * Returns the data-packet of this message. 
	 * @return the data-packet of this message
	 */
	public DataPacket<? extends IMessage> getDataPacket();

}
