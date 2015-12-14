package common.message;

import provided.datapacket.DataPacket;

/**
 * A message only sent outside chatroom. 
 * 
 * Well-known "initial" messages are: 
 * Invitation2Chatroom, 
 * ChatroomListRequest, ChatroomListResponse, 
 * InitUserInfoRequest, InitUserInfoResponse. 
 */
public interface IInitMessage extends IMessage {
	
	@Override
	public DataPacket<? extends IInitMessage> getDataPacket();

}
