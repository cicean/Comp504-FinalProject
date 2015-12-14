package mw56_sb55.client.chatapp.model;

import java.rmi.RemoteException;
import java.util.UUID;

import provided.datapacket.DataPacket;
import provided.datapacket.DataPacketAlgo;
import common.IChatroom;
import common.IInitUser;
import common.message.IInitMessage;

public class InitUser implements IInitUser {
	
	/**
	 * This is the person's name.
	 */
	private String name;
	
	/**
	 * This is the ip to identify a person.
	 */
	private String ip ;
	
	private IInitUserToModelAdapt initUserToModelAdapt;
	
	DataPacketAlgo<String, IInitUser> initAlgo;
	
	public  InitUser(IInitUserToModelAdapt initUserToModelAdapt, String name, DataPacketAlgo<String, IInitUser> initAlgo) {
		this.initUserToModelAdapt = initUserToModelAdapt;
		this.name = name;
		this.initAlgo = initAlgo;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This is the person to model adapter.
	 */

	@Override
	public void receive(IInitUser sender, DataPacket<? extends IInitMessage> dp)
			throws RemoteException {
		// TODO Auto-generated method stub
		dp.execute(initAlgo);
		
	}
	
	public String getName() throws RemoteException {
		return name;
	}
	
	public String getInitIp() throws RemoteException {
		return ip;
	}
	
	public String toString() {
		return name;
	}
	
	
	public int connected(IInitUser initMe) throws RemoteException {
		if (initMe != null ) {
			initUserToModelAdapt.addToPersonList(initMe);
			return 0;
		} else {
			return -1;
		}
	}
	
	public int acceptInvitation (IChatroom chatroom) {
		if (chatroom != null) {
			try {
			initUserToModelAdapt.joinTeam(chatroom);
			} catch(Exception exception) {
				exception.printStackTrace();
			}
			return 0;
		}
		else return -1;
	}
	
	
	public int recvRequest(IInitUser requesterStub) throws RemoteException {
		if (requesterStub != null) {
			initUserToModelAdapt.inviteUser(requesterStub);
			return 0;
		}
		else {
			System.out.println("null pointer in recvRequest(..) in class Person.");
			return -1;
		}
	}

	
	

}
