package common.message;

import provided.datapacket.DataPacket;

/**
 * A message only sent inside chatroom. 
 * 
 * Well-known chat messages are: 
 * TextMessage, 
 * AddMe, RemoveMe, 
 * CommandRequest, CommandResponse, 
 * InitUserRequest, InitUserResponse, 
 * ChatUserInfoRequest, ChatUserInfoResponse. 
 */
public interface IChatMessage extends IMessage {

	@Override
	public DataPacket<? extends IChatMessage> getDataPacket();

}
