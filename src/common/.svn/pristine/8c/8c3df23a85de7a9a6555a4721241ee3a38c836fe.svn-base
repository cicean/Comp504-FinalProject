package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.message.IChatMessage;
import provided.datapacket.DataPacket;

/**
 * Was IConnect in HW08. 
 * 
 * A chatroom remote user, its stub is put in RMI registry. 
 * Only receives chat messages from another chatroom remote user. 
 * This remote user is essentially a remote-to-chatroom adapter. 
 * 
 * Well-known chat messages are: 
 * TextMessage, 
 * AddMe, RemoveMe, 
 * CommandRequest, CommandResponse, 
 * InitUserRequest, InitUserResponse, 
 * ChatUserInfoRequest, ChatUserInfoResponse. 
 */
public interface IChatUser extends Remote {

	/**
	 * The port that the client will use to communicate with the ICompute object. 
	 * Note that this port must be opened for incoming traffic on the server machine. 
	 */
	public static final int BOUND_PORT_CLIENT = 2100;

	/**
	 * The port that the server will use to communicate with the ICompute object. 
	 * Note that this port must be opened for incoming traffic on the client machine. 
	 */
	public static final int BOUND_PORT_SERVER = 2101;

	/**
	 * Handles the chat message sent by another remote user. 
	 * @param sender - user who sent this message. 
	 * @param dp - data packet being sent. 
	 * @throws RemoteException - if this remote user could not be found. 
	 */
	public void receive(IChatUser sender, DataPacket<? extends IChatMessage> dp) throws RemoteException;

}
