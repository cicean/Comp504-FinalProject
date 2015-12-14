package common.demo.command;

import java.rmi.RemoteException;

import mw56_sb55.client.chatroom.model.ChatroomModel;
import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;
import common.IChatUser;
import common.ICmd2ModelAdapter;
import common.IInitUser;
import common.demo.message.init.InitUserInfoRequest;
import common.message.chat.AAddMe;
import common.message.chat.AChatUserInfoRequest;

public class InitUserInfoRequestCmd extends ADataPacketAlgoCmd<String, AChatUserInfoRequest, IInitUser>{

	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private IInitUser me;
	
	/**
	 * Constructs a new command that handles invitation to join chatroom. 
	 * @param me - sender of this command. 
	 */
	public InitUserInfoRequestCmd(IInitUser me) {
		this.me = me;
	}


	@Override
	public String apply(Class<?> index, DataPacket<AChatUserInfoRequest> host,
			IInitUser... params) {
		// TODO Auto-generated method stub
		
		try {
			params[0].receive(me, new InitUserInfoRequest().getDataPacket());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		// TODO Auto-generated method stub
		
	}

	

}
