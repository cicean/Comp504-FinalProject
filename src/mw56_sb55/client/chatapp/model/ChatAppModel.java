package mw56_sb55.client.chatapp.model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import mw56_sb55.client.chatroom.model.ChatroomModel;
import mw56_sb55.util.ThreadPool;
import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;
import provided.datapacket.DataPacketAlgo;
import provided.rmiUtils.IRMIUtils;
import provided.rmiUtils.IRMI_Defs;
import provided.rmiUtils.RMIUtils;
import provided.util.IVoidLambda;
import common.IChatUser;
import common.IChatroom;
import common.ICmd2ModelAdapter;
import common.IInitUser;
import common.demo.Bootstrap;
import common.demo.message.chat.AddMe;
import common.demo.message.chat.RemoveMe;
import common.demo.message.init.ChatroomListResponse;
import common.demo.message.init.InitUserInfoRequest;
import common.demo.message.init.InitUserInfoResponse;
import common.demo.message.init.Invitation2Chatroom;
import common.message.init.AChatroomListRequest;
import common.message.init.AChatroomListResponse;
import common.message.init.AInitUserInfoRequest;
import common.message.init.AInitUserInfoResponse;
import common.message.init.AInvitation2Chatroom;
import mw56_sb55.client.chatroom.model.IChatroomToViewAdapter;



/**
 * The chat app model
 * 
 * @author mw56
 */

public class ChatAppModel {
	
	  /**
     * The host's IP
     */
    private String ip;
	
	 /**
     * The user name
     */
    private String username;
    
    /**
     * client model to view adapter
     */
    
	private IChatAppToViewAdapter viewAdapter;

	/**
	 * The bootstrap for starting RMI
	 */
	private Bootstrap bootstrap = new Bootstrap();

	// private IInitUser initUser of client host;
	private IInitUser initClientStub;

	
	private IInitUser initServerStub;
//	
    
    /**
     * All init users
     */
    private Map<IInitUser, InitUser> initUsers = new HashMap<>();
    

    
	/**
	 * team's chatroom list
	 */
	private Set<ChatroomModel> chatrooms = new HashSet<>(); 
	
	/**
	 * 
	 */
	private IChatroom team;
	
	/**
	 * output command used to put multiple strings up onto the view.
	 */
	private IVoidLambda<String> outputCmd = new IVoidLambda<String>() {

		@Override
		public void apply(String... params) {
			for (String s : params) {
				viewAdapter.append(s + "\n");
			}
		}
	};
	
	
//	private Map<String, IInitUser> users = new HashMap<>();
	
	
	/**
	 * Factory for the Registry and other uses.
	 */
	IRMIUtils rmiUtils = new RMIUtils(outputCmd);
	
	/**
	 * 
	 */
	
	private IInitUserToModelAdapt initUserToModelAdapt = new IInitUserToModelAdapt() {

		@Override
		public void addToPersonList(IInitUser initUserStub, InitUser initUser) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void inviteUser(IInitUser requestStub) {
			
		}

		

		@Override
		public void joinTeam(IChatroom team) throws Exception {
			// TODO Auto-generated method stub
			ChatroomModel teamchatroom = createNewTeam(team);
			if (team.getName().equals("Lobby")) {
				createLobbyMVC(teamchatroom);
			}else{
				createLobbyMVC(teamchatroom);
				team = teamchatroom;
				viewAdapter.append("You got the team: "+team.getName());
			}
			//notify everybody
			joinChatRoom(team,teamchatroom);
		}

		

		


	
		
		
	
	};
	
	
	// initialize initAlgo with null as its default command. 
	DataPacketAlgo<String, IInitUser> initAlgo = new DataPacketAlgo<String, IInitUser>(null);
	


	

	public ChatAppModel(IChatAppToViewAdapter iChatAppViewAdapter) {
		// TODO Auto-generated constructor stub
		this.viewAdapter = iChatAppViewAdapter;

		initDataPacketAlgo();
		
	}
	
	 /**
     * Initialize data packet algorithm
     */
    public void initDataPacketAlgo() {
    	initAlgo.setDefaultCmd(new ADataPacketAlgoCmd<String, Object, IInitUser>() {

            /**
             * The generated serial version UID
             */
            private static final long serialVersionUID = -2832682237052228492L;

            @Override
            public String apply(Class<?> index, DataPacket<Object> host, IInitUser... params) {
                viewAdapter.append(String.format("Received Unknown data packet from %s.\n",
                                                 params[0].toString()));
//                viewAdapter.warn("The InitUserStub only handles the messages belonging to common.message.init!");
                return null;
            }

            @Override
            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {}
        });
        
    	initAlgo.setCmd(AInitUserInfoRequest.class, new ADataPacketAlgoCmd<String, AInitUserInfoRequest, IInitUser>() {

            /**
             * The generated serial version UID
             */
            private static final long serialVersionUID = -1642077281933635963L;

            @Override
            public String apply(Class<?> index, DataPacket<AInitUserInfoRequest> host, IInitUser... params) {
                try {
                	viewAdapter.append("Receiced AInitUserInfoRequest from Server \n");
                    params[0].receive(initClientStub, new InitUserInfoResponse(host.getData(), username, ip).getDataPacket());
//                    initUserToModelAdapt.addToPersonList(initClientStub);
                    viewAdapter.append("Send InitUserInfoResponse to remote server \n");
                    
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                
                return null;
            }

            @Override
            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {}
        });
        
    	initAlgo.setCmd(AInitUserInfoResponse.class, new ADataPacketAlgoCmd<String, AInitUserInfoResponse, IInitUser>() {

            /**
             * The generated serial version UID
             */
            private static final long serialVersionUID = 6842281532097303458L;

            @Override
            public String apply(Class<?> index, DataPacket<AInitUserInfoResponse> host, IInitUser... params) {
//                viewAdapter.append("Received a AInitUserInfoResponse\n");
                AInitUserInfoResponse response = host.getData();
                InitUser friend = new InitUser(initUserToModelAdapt, response.getIP(),initAlgo);
                initUsers.put(params[0], friend);
                initUserToModelAdapt.addToPersonList(initClientStub, friend);
//                viewAdapter.addUser(friend);
                viewAdapter.append(String.format("Successfully connected to the remote server %s!\n", friend));
                return null;
            }

            @Override
            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {}
        });
        
    	initAlgo.setCmd(AInvitation2Chatroom.class, new ADataPacketAlgoCmd<String, AInvitation2Chatroom, IInitUser>() {
            
            /**
             * The generated serial version UID
             */
            private static final long serialVersionUID = -2750033857606213270L;

            @Override
            public String apply(Class<?> index, DataPacket<AInvitation2Chatroom> host, IInitUser... params) {
                IInitUser user = params[0];
                viewAdapter.append("Received an InviteToChatroom data packet\n");
                
                IChatroom remoteChatroom = host.getData().getChatroom();
                String chatroomName = remoteChatroom.getName();
                if (chatroomName == null) {
                    viewAdapter.append("Illegal chatroom name!\n");
                    viewAdapter.append("Assign a default chatroom: default chatroom name\n");
                    chatroomName = "lobby";
                }
                
                if (chatrooms.contains(remoteChatroom)) {
                    return null;
                }
                
                if (host.getData().mustAccept() ) {
                   try {
                	   joinTeam(remoteChatroom);
					initUserToModelAdapt.joinTeam(remoteChatroom);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
                }
                
                return null;
            }

            @Override
            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {}
        });

    	initAlgo.setCmd(AChatroomListRequest.class, new ADataPacketAlgoCmd<String, AChatroomListRequest, IInitUser>() {

            /**
             * The generated serial version UID
             */
            private static final long serialVersionUID = -1051843247525083187L;

            @Override
            public String apply(Class<?> index, DataPacket<AChatroomListRequest> host, IInitUser... params) {
                IInitUser user = params[0];
                Set<IChatroom> chatroomList = new HashSet<>();
                for (IChatroom chatroom : chatrooms) {
                    chatroomList.add(chatroom);
                }
                
                try {
                    user.receive(initClientStub, new ChatroomListResponse(host.getData(), chatroomList).getDataPacket());
                    viewAdapter.chooseChatroom(initUsers.get(user), chatroomList );
                    
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                
                return null;
            }

            @Override
            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {}
        });
        
        initAlgo.setCmd(AChatroomListResponse.class, new ADataPacketAlgoCmd<String, AChatroomListResponse, IInitUser>() {

            /**
             * The generated serial version UID
             */
            private static final long serialVersionUID = 4103819982135023367L;

            @Override
            public String apply(Class<?> index, DataPacket<AChatroomListResponse> host, IInitUser... params) {
                Set<IChatroom> chatroomList = host.getData().getChatrooms();
//              viewAdapter.chooseChatroom(initUsers.get(params[0]), chatroomList);
                return null;
            }

            @Override
            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {}
        });
    }
	
	

	/**
	 * Start chat app model
	 */
	public void start(String username) {
    	

		/**
		 * get the stub of initail adapter
		 */
		
		rmiUtils.startRMI(IRMI_Defs.CLASS_SERVER_PORT_CLIENT);
		
		try {
			
			IInitUser initclientMe = new InitUser(initUserToModelAdapt, username, initAlgo);
			
			initClientStub = (IInitUser) UnicastRemoteObject.exportObject(initclientMe, IInitUser.BOUND_PORT_CLIENT);
			   
			System.out.println(initclientMe);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
	 	viewAdapter.append("Creat remote me");
		
    	viewAdapter.setIPAddress(bootstrap.getIP());
    	
    	
    	
    	
    }

	/**
	 * Connects to the given remote host and retrieves the stub to the ICompute object bound 
	 * to the ICompute.BOUND_NAME name in the remote Registry on port 
	 * IRMI_Defs.REGISTRY_PORT.  
	 * 
	 * @param remoteHost The IP address or host name of the remote server.
	 * @return  A status string on the connection.
	 */
	public String connectTo(String ipAddress) {
		// TODO Auto-generated method stub
		try{
			ThreadPool.execute(new Runnable(){

				@Override
				public void run() {
					
					viewAdapter.append("Locating registry at " + ipAddress + "...\n");
					
					
					initServerStub = bootstrap.connectToUser(ipAddress);
					
					
					viewAdapter.append("Locating registry at " + ipAddress + "...\n");
				
					viewAdapter.append("Found remote Server object: " + initServerStub + "\n");
					
					try {
						 initClientStub.receive(initServerStub, new InitUserInfoRequest().getDataPacket());
						
						 initClientStub.receive(initServerStub, new Invitation2Chatroom(team, true).getDataPacket());
						 
											
					} catch (RemoteException e) {
						e.printStackTrace();
					}
							
							
				}	
			});
			}catch(Exception e){
				viewAdapter.append("Exception connecting to " + ipAddress + ": " + e
						+ "\n");
				e.printStackTrace();
				return "No connection established!";
			
			}
			return "Connection to " + ipAddress + " established!";
	
		
		
	}
	
	
	/**
	 * Stops the client by shutting down the class server.
	 */
	public void stop() {
		System.out.println("ClientModel.stop(): client is quitting.");
		
		//send the ILeaveRoomMsg to all the people in the team/lobby
		for (ChatroomModel c: chatrooms) {
			 c.removeUser(c.getchatMe());
	         c.sendSync(c.getchatMe(), new RemoveMe(c.getchatMe()));
	       
		}
		
		try {
//			rmiUtils.getLocalRegistry().unbind(IInitUser.BOUND_NAME);
			rmiUtils.stopRMI();
		} catch (Exception e) {
			System.err
					.println("ClientModel.stop(): Error stopping class server: "
							+ e);
		}
		System.exit(0);
	}
	
    /**
     * Quit a chat room
     * @param chatroom The chat room
     */
    public void quitChatroom(ChatroomModel chatroom) {
        chatroom.removeUser(chatroom.getchatMe());
        chatroom.send(chatroom.getchatMe(), new RemoveMe(chatroom.getchatMe()));
//        viewAdapter.removeChatroom(chatroom);
        chatrooms.remove(chatroom);
    }
	


	private void createLobbyMVC(ChatroomModel teamchatroom) {
		// TODO Auto-generated method stub
		IChatroomToViewAdapter LobbyViewAdpt = viewAdapter
				.makeChatroomViewAdapter(teamchatroom);
		teamchatroom.setTeamModel2ViewAdapter(LobbyViewAdpt);//equivalent to miniModel.miniModel2ViewAdpt = miniModel2ViewAdpt;
		teamchatroom.start();//start the mini lobby view
		chatrooms.add(teamchatroom);//add the miniModel to the miniModelList
	}
	
	public void joinTeam(IChatroom team) throws Exception {
		// TODO Auto-generated method stub
		ChatroomModel teamchatroom = createNewTeam(team);
		if (team.getName().equals("Lobby")) {
			createLobbyMVC(teamchatroom);
		}else{
			createLobbyMVC(teamchatroom);
			team = teamchatroom;
			viewAdapter.append("You got the team: "+team.getName());
		}
		//notify everybody
		joinChatRoom(team,teamchatroom);
	}
	
	private void joinChatRoom(IChatroom team, ChatroomModel teamModel) throws Exception {
		// TODO Auto-generated method stub
		for(IChatUser otherUser : team.getUsers()) {
			teamModel.addUser(otherUser);
		}
		
		teamModel.send(teamModel.getchatMe(), new AddMe(teamModel.getchatMe()));
		teamModel.addUser(teamModel.getchatMe());
		teamModel.refreshViewUserList(teamModel);
		
	}
	

	private ChatroomModel createNewTeam(IChatroom team) {
		// TODO Auto-generated method stub
		UUID chatroomId = team.getID();
		String name = team.getName();
		
		ChatroomModel chatroomModel = new ChatroomModel(chatroomId,name, username, ip, initClientStub );
//        chatrooms.add(chatroomModel);
//        chatroomModel.setViewAdapter(viewAdapter.makeChatroomViewAdapter(chatroomModel));
//        viewAdapter.addChatroom(chatroomModel);
//        chatroomModel.start();
        
        return chatroomModel;
	}
	
	



}