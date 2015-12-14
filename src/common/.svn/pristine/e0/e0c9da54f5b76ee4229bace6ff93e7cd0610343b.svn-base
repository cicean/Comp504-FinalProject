package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.message.IInitMessage;
import provided.datapacket.DataPacket;

/**
 * Was IConnect in HW08. 
 * 
 * An initial remote user, its stub is put in RMI registry. 
 * Only receives "initial" messages from another initial remote user. 
 * This remote user is essentially a remote-to-local adapter. 
 * 
 * Well-known "initial" messages are: 
 * Invitation2Chatroom, 
 * ChatroomListRequest, ChatroomListResponse, 
 * InitUserInfoRequest, InitUserInfoResponse. 
 */
public interface IInitUser extends Remote {

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
	 * The name the ICompute object will be bound to in the RMI Registry. 
	 */
	public static final String BOUND_NAME = "InitUser";

	/**
	 * Handles the "initial" message sent by another remote user. 
	 * @param sender - user who sent this message. 
	 * @param dp - data packet being sent. 
	 * @throws RemoteException - if this remote user could not be found. 
	 */
	public void receive(IInitUser sender, DataPacket<? extends IInitMessage> dp) throws RemoteException;

}
