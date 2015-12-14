package mw56_sb55.client.chatapp.controller;

import java.awt.EventQueue;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import common.IChatUser;
import common.IChatroom;
import mw56_sb55.client.chatapp.model.ChatAppModel;
import mw56_sb55.client.chatapp.model.IChatAppToViewAdapter;
import mw56_sb55.client.chatapp.model.InitUser;
import mw56_sb55.client.chatapp.view.ClientMainView;
import mw56_sb55.client.chatapp.view.IViewToModelAdapter;
import mw56_sb55.client.chatroom.model.ChatUser;
import mw56_sb55.client.chatroom.model.ChatroomModel;
import mw56_sb55.client.chatroom.model.IChatroomToViewAdapter;
import mw56_sb55.client.chatroom.view.ChatroomLobbyView;
import mw56_sb55.client.chatroom.view.ChatroomTeamView;
import mw56_sb55.client.chatroom.view.IViewToChatroomAdapter;






/**
 * The controller for chat app
 * @author mw56@rice.edu
 */
public class ChatAppController {

	/**
	 * The chat app model
	 */
	private ChatAppModel model;

	/**
	 * The chat app view
	 */
	private ClientMainView view;

	/**
	 * The constructor for chat app
	 */
	public ChatAppController() {
		
		view = new ClientMainView(new IViewToModelAdapter() {

			@Override
			public String connectTo(String text) {
				// TODO Auto-generated method stub
				return model.connectTo(text);
			}

			@Override
			public void quit() {
				// TODO Auto-generated method stub
				model.stop();
			}
			
		
		});
		
		
	
		model = new ChatAppModel(new IChatAppToViewAdapter() {

			@Override
			public void setIPAddress(String title) {
				// TODO Auto-generated method stub
				view.setRemoteAddress(title);
			}

			@Override
			public void append(String format) {
				// TODO Auto-generated method stub
				view.append(format);
			}

			@Override
			public IChatroomToViewAdapter makeChatroomViewAdapter(
					ChatroomModel model) {
				// TODO Auto-generated method stub
				
				 IViewToChatroomAdapter chatroomModelAdapter = new IViewToChatroomAdapter() {

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
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public void leave() {
						// TODO Auto-generated method stub
						
					}
	                    
	             
	                };
	                
	              ChatroomTeamView<ChatUser> chatroomGui = view.makeTeamView(chatroomModelAdapter);
	              
	              IChatroomToViewAdapter chatroomviewAdt = new IChatroomToViewAdapter() {
					
					@Override
					public void start() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void setTitle(String name) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void removeUserfromList(ChatUser chatUser) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void displayMessage(String message) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void append(String text) {
						// TODO Auto-generated method stub
						chatroomGui.append(text);
					}
					
					@Override
					public void addUsertoList(ChatUser chatUser) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void addUsertoList(Collection<ChatUser> collection)
							throws Exception {
						// TODO Auto-generated method stub
						
					}

					@Override
					public IChatroomToViewAdapter makeTeamModel2LobbyViewAdapter(
							ChatroomModel teamMiniModel) {
						// TODO Auto-generated method stub
						return null;
					}
					
					
					
				};
				
				return chatroomviewAdt;
				
			}
				
				/**
				 * make Team Model 2 Lobby View Adapter
				 */
				@Override
				public IChatroomToViewAdapter makeTeamModel2LobbyViewAdapter(
						ChatroomModel teamModel) {
					IViewToChatroomAdapter _lobbyView2ModelAdpt = new IViewToChatroomAdapter() {

						@Override
						public void send(String str) {
							teamModel.sendMessage(str);
						}

						@Override
						public void leave() {
							model.stop();
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
							// TODO Auto-generated method stub
							return null;
						}
					};
				
				ChatroomLobbyView lobbyView = view.makeLobbyView(_lobbyView2ModelAdpt);
				
				IChatroomToViewAdapter lobbyviewApt = new IChatroomToViewAdapter() {
					
					@Override
					public void start() {
						// TODO Auto-generated method stub
						lobbyView.start();
					}
					
					@Override
					public void setTitle(String name) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void removeUserfromList(ChatUser chatUser) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void displayMessage(String message) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void append(String text) {
						// TODO Auto-generated method stub
						lobbyView.append(text);
					}
				

					@Override
					public void addUsertoList(Collection<ChatUser> collection) throws Exception {
						// TODO Auto-generated method stub
						lobbyView.updateUserList(collection);
					}

					@Override
					public void addUsertoList(ChatUser chatUser) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public IChatroomToViewAdapter makeTeamModel2LobbyViewAdapter(
							ChatroomModel teamMiniModel) {
						// TODO Auto-generated method stub
						
						return null;
					}
				};
				
				
				
				return lobbyviewApt;	
			}

				@Override
				public void chooseChatroom(InitUser initUser,
						Set<IChatroom> chatroomList) {
					// TODO Auto-generated method stub
					
					
				}
			
			
			

			

	

	
		});
		
		
		
		
	}

	/**
	 * Start the controller
	 */
	public void start() {
		 String username = JOptionPane.showInputDialog(null, "Please input your username:", "Login", JOptionPane.INFORMATION_MESSAGE);
	        if (username == null) {
	            System.exit(0);
	        } else if (username.trim().isEmpty()) {
	            username = "COMP 504 user";
	        }
	        
		view.start();
		model.start(username);
	}

	/**
	 * Launch the application.
	 * @param args no use
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(() -> {
			try {
				ChatAppController controller = new ChatAppController();
				controller.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}