package mw56_sb55.client.chatapp.model;

import common.IChatUser;
import common.IChatroom;
import common.IInitUser;

public interface IInitUserToModelAdapt {
	/**
	 * add the person stub to the personStubList in the server Model.
	 * @param personStub
	 */
	public void addToPersonList(IInitUser initUserStub, InitUser initUser);
	
	/**
	 * join a team miniMVC
	 * @param chatroom the given chatroom.
	 * @throws Exception 
	 */
	public void joinTeam(IChatroom team) throws Exception;
	
	
	/**
	 * Invite a person
	 * @param stub the given person stub.
	 */
	public void inviteUser(IInitUser requestStub);
}
