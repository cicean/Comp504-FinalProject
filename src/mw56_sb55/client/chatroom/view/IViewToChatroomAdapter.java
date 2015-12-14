package mw56_sb55.client.chatroom.view;

import java.util.Set;

import common.IChatUser;


/**
 * Adapter for the chatroom view to communicate with its model.
 * 
 * @author cicean
 *
 */
public interface IViewToChatroomAdapter {

 
	public void send(String text);
	public void removeUser();
	public void invitetoChatRoom(String text);
	public Set<IChatUser> getUsers();
	public String getChatroomName();
	
	static public IViewToChatroomAdapter NULL = new IViewToChatroomAdapter(){

		@Override
		public void send(String text) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeUser() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void invitetoChatRoom(String text) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Set<IChatUser> getUsers() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getChatroomName() {
			return null;
		}

		@Override
		public void leave() {
			// TODO Auto-generated method stub
			
		}
		
	};

	public void leave();
}
