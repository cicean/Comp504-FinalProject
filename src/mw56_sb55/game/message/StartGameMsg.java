package mw56_sb55.game.message;

import mw56_sb55.game.api.IGameWorld;
import mw56_sb55.game.controller.GameFactory;
import provided.datapacket.DataPacket;
import common.IChatUser;
import common.message.IChatMessage;
import common.message.chat.AAddMe;

public class StartGameMsg extends AAddMe{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7676484531465419638L;

	IChatUser localPlayer;
	
	Class<IGameWorld> class1;
	
	GameFactory gf;


	public StartGameMsg(Class<IGameWorld> gmwClass, IChatUser chatServerStub,
			GameFactory gf2) {
		// TODO Auto-generated constructor stub
		
		this.localPlayer =chatServerStub;
		this.class1 = gmwClass;
		this.gf = gf;
	}

	@Override
	public DataPacket<? extends IChatMessage> getDataPacket() {
		// TODO Auto-generated method stub
		
		return new DataPacket<StartGameMsg>(StartGameMsg.class, this);
	}

	@Override
	public IChatUser getUser() {
		// TODO Auto-generated method stub
		return localPlayer;
	}
	


}
