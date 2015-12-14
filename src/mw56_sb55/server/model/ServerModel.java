package mw56_sb55.server.model;

import gov.nasa.worldwind.View;

import java.awt.Component;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

import javax.swing.JFrame;

import mw56_sb55.client.chatapp.model.IInitUserToModelAdapt;
import mw56_sb55.client.chatapp.model.InitUser;
import mw56_sb55.client.chatroom.model.ChatUser;
import mw56_sb55.client.chatroom.model.Team;
import mw56_sb55.client.chatroom.model.ChatroomModel;
import mw56_sb55.client.chatroom.model.IChatUserToModelAdapt;
import mw56_sb55.game.api.IFailMsg;
import mw56_sb55.game.api.IGameOver;
import mw56_sb55.game.api.IGameWorld;
import mw56_sb55.game.api.ILocalGameOver;
import mw56_sb55.game.api.IMove;
import mw56_sb55.game.api.ISuccessMsg;
import mw56_sb55.game.api.IUpdateTeam;
import mw56_sb55.game.controller.GameFactory;
import mw56_sb55.game.message.LocalGameOverMsg;
import mw56_sb55.game.message.MoveMsg;
import mw56_sb55.game.message.StartGameMsg;
import mw56_sb55.game.message.UpdateTeamMsg;
import mw56_sb55.game.model.GameModel;
import mw56_sb55.util.ThreadPool;
import provided.datapacket.ADataPacket;
import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;
import provided.datapacket.DataPacketAlgo;
import provided.mixedData.MixedDataKey;
import provided.rmiUtils.IRMIUtils;
import provided.rmiUtils.IRMI_Defs;
import provided.rmiUtils.RMIUtils;
import provided.util.IVoidLambda;
import common.IChatUser;
import common.IChatroom;
import common.ICmd2ModelAdapter;
import common.IInitUser;
import common.demo.Bootstrap;
import common.demo.message.chat.ChatUserInfoResponse;
import common.demo.message.chat.CommandRequest;
import common.demo.message.chat.InitUserResponse;
import common.demo.message.init.ChatroomListResponse;
import common.demo.message.init.InitUserInfoResponse;
import common.demo.message.init.Invitation2Chatroom;
import common.message.IChatMessage;
import common.message.chat.AAddMe;
import common.message.chat.AChatUserInfoRequest;
import common.message.chat.AChatUserInfoResponse;
import common.message.chat.ACommandResponse;
import common.message.chat.AInitUserRequest;
import common.message.chat.ARemoveMe;
import common.message.chat.ATextMessage;
import common.message.init.AChatroomListRequest;
import common.message.init.AChatroomListResponse;
import common.message.init.AInitUserInfoRequest;
import common.message.init.AInitUserInfoResponse;
import common.message.init.AInvitation2Chatroom;


public class ServerModel {
	
	/**
	 * Person to model adapter
	 */
	private IInitUserToModelAdapt i2mAdpt;
	/**
	 * Server model to view adapter
	 */
	private IServerModelToViewAdapter sm2vAdpt;
	/**
	 * Member to model adapter
	 */
	private IChatUserToModelAdapt c2mAdpt;
	
	private  String serverIp;
	
	/**
	 * The RMI registry.
	 */
	private Registry registry;
	
	/**
	 * The modelKey of type miexedDataKey.
	 */
	private MixedDataKey<GameModel> modelKey;
	
	/**
	 * TeamManager object, used to manage teams
	 */
	private TeamManage tm;
	
	/**
	 * GameManager object, used to calculate score
	 */
	private GameManage gm;
	
	/**
	 * Indicated if a team has been created.
	 */
	private boolean teamCreated = false;
	
	/**
	 * The name of the game
	 */
	private String gameName = "Road Runner";
	
	/**
     * The user name
     */
    private String username;
    
	
	/**
	 * Stub from local person. This is the person stub, not the member stub.
	 */
	private IInitUser serverInituserStub;
	
	/**
	 * Local member stub
	 */
	private IChatUser localChatuserStub;

	
	/**
	 * output command used to put multiple strings up onto the view.
	 */
	private IVoidLambda<String> outputCmd = new IVoidLambda<String>() {

		@Override
		public void apply(String... params) {
			for (String s : params) {
				sm2vAdpt.append(s + "\n");
			}
		}
	};
	

	

	
	// initialize initAlgo with null as its default command. 
	private DataPacketAlgo<String, IInitUser> initAlgo = new DataPacketAlgo<String, IInitUser>(null);

			// initialize chatAlgo with null as its default command. 
	private DataPacketAlgo<String, IChatUser> chatAlgo = new DataPacketAlgo<String, IChatUser>(null);
	
	private DataPacketAlgo<ADataPacket, IChatUser> algo;
	
	private Map<Class<?>, Object[]> unknownMessages = new HashMap<>();
	
	
	private Set<IInitUser> inituserset;
	
    /**
     * All the connected users
     */
    private Map<String, IInitUser> friends = new HashMap<>();
    
    /**
     * All init users
     */
    private Map<IInitUser, InitUser> initUsers = new HashMap<>();
    
	  /**
     * The chatuser list
     */
    private Map<IChatUser, ChatUser> chatusers = new HashMap<>();
    
    
    /**
     * The connected chatrooms
     */
    private Set<ChatroomModel> chatrooms = new HashSet<>();
	
	
	
//	 /**
//     * All the connected  Inituser Map
//     */
//    private Map<String, IInitUser> initclientuserlist = new HashMap<>();
//    
//    
	
	/**
	 * Factory for the Registry and other uses.
	 */
	private IRMIUtils rmiUtils = new RMIUtils(outputCmd);
	
	
	/**
	 * The update team message.
	 */
	private IUpdateTeam updateScoreMsg;
	
	/**
	 * The uuid of game model.
	 */
	private UUID gameModelUUID;
	
	/**
	 * Game controller factory, used to create a game MVC for client
	 */
	private GameFactory gf;
	
	/**
	 * The number of the players.
	 */
	private int playerNum = 0;
	
	/**
	 * The number of players who have finished the game.
	 */
	private int finishNum = 0;
	
	
	
	
	
	/**
	 * Construct a server model using the server model to server view adapter.
	 * @param adapter The server model to server view adapter.
	 */
	public ServerModel(IServerModelToViewAdapter adapter){
		sm2vAdpt = adapter;
		
//		personList = new ArrayList<IInitUser>();
		
		tm = new TeamManage();
		
		gm = new GameManage();
		
		gameModelUUID = UUID.randomUUID();
		
		modelKey = new MixedDataKey<GameModel>(gameModelUUID, "Game model", GameModel.class);
		

		
		
	}
	
	
	/**
     * The cmd to model adapter
     */
    private transient ICmd2ModelAdapter adapter = new ICmd2ModelAdapter() {

		@Override
		public void addToScrollable(Supplier<Component> componentFac) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateUpdatable(Supplier<Component> componentFac) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void createNewWindow(Supplier<JFrame> frameFac) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public <T> T getMixedDataDictEntry(MixedDataKey<T> key) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> void setMixedDataDictEntry(MixedDataKey<T> key, T value) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void sendToChatroom(IChatMessage message) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void sendMsgTo(IChatMessage msg, IChatUser chatUser) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getUserName() {
			// TODO Auto-generated method stub
			return null;
		}
        
       
    };
	
	/**
	 * Start RMI server
	 */
	public void start(){
		
		
		rmiUtils.startRMI(IRMI_Defs.CLASS_SERVER_PORT_SERVER);
		
		//local person as the rmi client
		try {
			
			String serverIp = System.getProperty("java.rmi.server.hostname");
			
			sm2vAdpt.append("Connected ServerIP address is " + serverIp + "\n");
			
			i2mAdpt = new IInitUserToModelAdapt() {
				
				@Override
				public void joinTeam(IChatroom team) throws Exception {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void inviteUser(IInitUser requestStub) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void addToPersonList(IInitUser initUserStub, InitUser initUser) {
					// TODO Auto-generated method stub
					inituserset.add(initUserStub);
					initUsers.put(initUserStub, initUser);
					sm2vAdpt.addToPersonDropList(initUserStub);
//					serverInituserStub.receive(initUserStub, new InitUserInfoResponse(request, userName, userIP));
					
				}
			}; 
			
			try {
				
				IInitUser serverInituserMe = new InitUser(i2mAdpt, gameName,initAlgo);//this is the remote person object
				
				System.out.println(serverInituserMe);
				
				sm2vAdpt.append("Instantiated new local Person: " + serverInituserMe
						+ "\n");
				// Use this technique rather than the simpler "registry.rebind(name, engine);"
				// because it enables us to specify a port number so we can open that port on the firewall
				serverInituserStub = (IInitUser) UnicastRemoteObject.exportObject(
						serverInituserMe, IInitUser.BOUND_PORT_SERVER);//server object generates its stub
				/*first add person(myself) stub to the personStubList. Then add the stub to the drop list on main view*/

				
				
				sm2vAdpt.append("Remote person as rmi server side bound to "
						+ IInitUser.BOUND_NAME + "\n");
				
			} catch (RemoteException e) {
				e.printStackTrace();
				
			}
			
			
		
			IChatUser serverChatuserStub;
			try{		
				IChatUser serverChatuserMe = new ChatUser(gameName, serverIp, chatAlgo, c2mAdpt);
				serverChatuserStub = (IChatUser) UnicastRemoteObject.exportObject(serverChatuserMe,
						IChatUser.BOUND_PORT_SERVER);
			}catch(Exception e){
				serverChatuserStub = null;
				e.printStackTrace();
			}
			localChatuserStub = serverChatuserStub;
			
			tm.add2Lobby(localChatuserStub);
			
			rmiUtils.getLocalRegistry().rebind(IInitUser.BOUND_NAME, serverInituserStub);	

			sm2vAdpt.append("Found registry: " + registry + "\n");
			
			sm2vAdpt.append("Local person as rmi server side bound to "
					+ IInitUser.BOUND_NAME + "\n");
		} catch (Exception e) {
			System.err.println("InitUser as rmi server exception:" + "\n");
			e.printStackTrace();
			System.exit(-1);
		}
		
		/**
		 * Default command is used to handle unknown message.
		 */
		
		setUnknownChatRoomCmd();
		setChatAllKnownCmd();
		setInitAllKnownCmd();
//		setGameCmd();
	}
	
	
	public void setUnknownChatRoomCmd() {
		
		initAlgo.setDefaultCmd(new ADataPacketAlgoCmd<String, Object, IInitUser>() {
			/**
             * The generated serial version UID
             */
            private static final long serialVersionUID = -2832682237052228492L;

            @Override
            public String apply(Class<?> index, DataPacket<Object> host, IInitUser... params) {
            	sm2vAdpt.append(String.format("Received Unknown data packet from %s.\n",
                                                 params[0].toString()));
//            	sm2vAdpt.warn("The InitUserStub only handles the messages belonging to common.message.init!");
                return null;
            }

            @Override
            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {}
		});
		
		chatAlgo.setDefaultCmd(new ADataPacketAlgoCmd<String, Object, IChatUser>() {

			 /**
             * The generated serial version UID
             */
            private static final long serialVersionUID = 7036336565109666871L;

            @Override
            public String apply(Class<?> index, DataPacket<Object> host, IChatUser... params) {
                try {
                    unknownMessages.put(index, new Object[]{host, params[0]});
                    params[0].receive(localChatuserStub, new CommandRequest(index).getDataPacket());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                
                return null;
            }

            @Override
            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
            	
            } 
			
		});
		
	}
	
	
	public void setInitAllKnownCmd() {
		
		initAlgo.setCmd(AInitUserInfoRequest.class, new ADataPacketAlgoCmd<String, AInitUserInfoRequest, IInitUser>() {

            /**
             * The generated serial version UID
             */
            private static final long serialVersionUID = -1642077281933635963L;

            @Override
            public String apply(Class<?> index, DataPacket<AInitUserInfoRequest> host, IInitUser... params) {
                try {
                    params[0].receive(serverInituserStub, new InitUserInfoResponse(host.getData(), username, serverIp).getDataPacket());
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
            	sm2vAdpt.append("Received a AInitUserInfoResponse\n");
                AInitUserInfoResponse response = host.getData();
                InitUser friend = new InitUser(i2mAdpt,response.getName(),initAlgo);
                initUsers.put(params[0], friend);
//                sm2vAdpt.addUser(friend);
                sm2vAdpt.append(String.format("Successfully connected to the user %s!\n", friend));
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
                sm2vAdpt.append("Received an InviteToChatroom data packet\n");
                
                IChatroom remoteChatroom = host.getData().getChatroom();
                String chatroomName = remoteChatroom.getName();
                if (chatroomName == null) {
                	sm2vAdpt.append("Illegal chatroom name!\n");
                	sm2vAdpt.append("Assign a default chatroom: default chatroom name\n");
                    chatroomName = "default chatroom name";
                }
                
                if (chatrooms.contains(remoteChatroom)) {
                    return null;
                }
                
                if (host.getData().mustAccept() ) {
                	try {
                	i2mAdpt.joinTeam(remoteChatroom);
//                    joinChatroom(remoteChatroom, chatroomName);
                	} catch(Exception e) {
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
                    user.receive(serverInituserStub, new ChatroomListResponse(host.getData(), chatroomList).getDataPacket());
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
                for (IChatroom c: chatroomList) {
                	sm2vAdpt.addToTeamDropList(c);
                }
                return null;
            }

            @Override
            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {}
        });
		
	}
	
	
	public void setChatAllKnownCmd() {
		
		 chatAlgo.setCmd(ACommandResponse.class, new ADataPacketAlgoCmd<String, ACommandResponse, IChatUser>() {

	            /**
	             * The generated serial version UID
	             */
	            private static final long serialVersionUID = 8874613789621670600L;

	            @SuppressWarnings("unchecked")
	            @Override
	            public String apply(Class<?> index, DataPacket<ACommandResponse> host, IChatUser... params) {
	                ACommandResponse response = host.getData();
	                ADataPacketAlgoCmd<String, ?, IChatUser> cmd = response.getCommand();
	                chatAlgo.setCmd(response.getUnknownType(), cmd);
	                cmd.setCmd2ModelAdpt(adapter);
	                Object[] unknown = unknownMessages.get(response.getUnknownType());
	                DataPacket<Object> oldhost = (DataPacket<Object>)unknown[0];
	                IChatUser sender = (IChatUser)unknown[1];
	                unknownMessages.remove(response.getUnknownType());
	                
	                return oldhost.execute(chatAlgo, sender);
	            }

	            @Override
	            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {}
	        });
	        
		 chatAlgo.setCmd(ATextMessage.class, new ADataPacketAlgoCmd<String, ATextMessage, IChatUser>() {

	            /**
	             * The generated serial version UID
	             */
	            private static final long serialVersionUID = 8874613789621670600L;

	            @Override
	            public String apply(Class<?> index, DataPacket<ATextMessage> host, IChatUser... params) {
//	                viewAdapter.append(users.get(params[0]), host.getData().getText());
	                return null;
	            }

	            @Override
	            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {}
	        });
	        
		 chatAlgo.setCmd(AChatUserInfoRequest.class, new ADataPacketAlgoCmd<String, AChatUserInfoRequest, IChatUser>() {

	            /**
	             * The generated serial version UID
	             */
	            private static final long serialVersionUID = 8874613789621670600L;

	            @Override
	            public String apply(Class<?> index, DataPacket<AChatUserInfoRequest> host, IChatUser... params) {
	                try {
	                    params[0].receive(localChatuserStub, new ChatUserInfoResponse(host.getData(), username, serverIp).getDataPacket());
	                } catch (RemoteException e) {
	                    e.printStackTrace();
	                }
	                
	                return null;
	            }

	            @Override
	            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {}
	        });
	        
		 chatAlgo.setCmd(AChatUserInfoResponse.class, new ADataPacketAlgoCmd<String, AChatUserInfoResponse, IChatUser>() {

	            /**
	             * The generated serial version UID
	             */
	            private static final long serialVersionUID = 283333508165590487L;

	            @Override
	            public String apply(Class<?> index, DataPacket<AChatUserInfoResponse> host, IChatUser... params) {
	                AChatUserInfoResponse response = host.getData();
	                chatusers.get(params[0]).setName(response.getName());
	                chatusers.get(params[0]).setIp(response.getIP());
//	                viewAdapter.addUser(users.get(params[0]));
	                return null;
	            }

	            @Override
	            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {}
	        });
	        
		 chatAlgo.setCmd(AInitUserRequest.class, new ADataPacketAlgoCmd<String, AInitUserRequest, IChatUser>() {

	            /**
	             * The generated serial version UID
	             */
	            private static final long serialVersionUID = 5732580926874597021L;

	            @Override
	            public String apply(Class<?> index, DataPacket<AInitUserRequest> host, IChatUser... params) {
	                try {
	                    params[0].receive(localChatuserStub, new InitUserResponse(host.getData(), serverInituserStub).getDataPacket());
	                } catch (RemoteException e) {
	                    e.printStackTrace();
	                }
	                
	                return null;
	            }

	            @Override
	            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {}
	        });
	        
		 chatAlgo.setCmd(AAddMe.class, new ADataPacketAlgoCmd<String, AAddMe, IChatUser>() {

	            /**
	             * The generated serial version UID
	             */
	            private static final long serialVersionUID = 6637848777092124975L;

	            @Override
	            public String apply(Class<?> index, DataPacket<AAddMe> host, IChatUser... params) {
	                c2mAdpt.addUser(host.getData().getUser());
	                return null;
	            }

	            @Override
	            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {}
	        });
	        
		 chatAlgo.setCmd(ARemoveMe.class, new ADataPacketAlgoCmd<String, ARemoveMe, IChatUser>() {

	            /**
	             * The generated serial version UID
	             */
	            private static final long serialVersionUID = 5715553416060420852L;

	            @Override
	            public String apply(Class<?> index, DataPacket<ARemoveMe> host, IChatUser... params) {
//	                viewAdapter.append(users.get(params[0]), "Send a RemoveMe data packet");
//	                removeUser(host.getData().getUser());
	                return null;
	            }

	            @Override
	            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {}
	        });
		
		 
		
		
		
		
		
		
	}
	
	
	
	
	/**
	 * Set all commands that interact with game
	 */
	public void setGameCmd(){
		algo.setCmd(IMove.class, new ADataPacketAlgoCmd<ADataPacket, IMove, IChatUser>(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 5182486767749848282L;

			@Override
			public ADataPacket apply(Class<?> index, DataPacket<IMove> host,
					IChatUser... params) {
				ADataPacket result = null;
				try{
					String c = ((MoveMsg)host.getData()).getCurCity();
					String teamName = "";
					System.out.print("Current city is: " + c);
					IChatroom senderTeam = null;
//					for(IChatroom room: tm.getTeams()){
//						if(((Chatroom) room).isInTeam(params[0])){
//							System.out.println("The sender is in team " + room.getName());
//							teamName = room.getName();
//							senderTeam = room;
//							break;
//						}
//					}
					MoveMsg mMsg = (MoveMsg)host.getData();
					int price = mMsg.getPrice();
					int time = mMsg.getTime();
					
					double lat = mMsg.getLatitude();
					double longt = mMsg.getLongtitude();
					
					
					gm.addScore2Team(teamName, price, time);
					
					IChatUser sender = params[0];
					
					sender.receive(localChatuserStub, new UpdateTeamMsg(gm.getCurScore(teamName), lat, longt).getDataPacket() );
					
					if(senderTeam != null){
						senderTeam.send(sender, new UpdateTeamMsg(gm.getCurScore(teamName), lat, longt));
					}
				
				}catch(Exception e){
					result = new DataPacket<IFailMsg>(IFailMsg.class, null);
				}
				return result;
			}

			@Override
			public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
				
			}
			
		});
		
		algo.setCmd(IGameOver.class, new ADataPacketAlgoCmd<ADataPacket, IGameOver, IChatUser>(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1642633463726399272L;

		

			@Override
			public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
				
			}

			@Override
			public ADataPacket apply(Class<?> index,
					DataPacket<IGameOver> host, IChatUser... params) {
				// TODO Auto-generated method stub
				ADataPacket result;
				try{
					sm2vAdpt.append(gm.getWinningTeam() + " has won the game!");
					System.out.println("Server has received gameover msg!");
					result = new DataPacket<ISuccessMsg>(ISuccessMsg.class, null);
				}catch(Exception e){
					result = new DataPacket<IFailMsg>(IFailMsg.class, null);
				}
				
				return result;
			}});
		
		algo.setCmd(ILocalGameOver.class, new ADataPacketAlgoCmd<ADataPacket, ILocalGameOver, IChatUser>(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1642633463726399272L;

		

			@Override
			public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
				
			}

			@Override
			public ADataPacket apply(Class<?> index,
					DataPacket<ILocalGameOver> host, IChatUser... params) {
				// TODO Auto-generated method stub
				ADataPacket result;
				try{
					finishNum ++;
					if(playerNum == 2 * finishNum){
						result = new DataPacket<IGameOver>(IGameOver.class,  null);
						tm.getLobby().send(params[0], new LocalGameOverMsg());
					}else{
						result = new DataPacket<ISuccessMsg>(ISuccessMsg.class,  null);
					}
				}catch(Exception e){
					result = new DataPacket<IFailMsg>(IFailMsg.class, null);
				}
				return result;
			}});
		
		algo.setCmd(IUpdateTeam.class, new ADataPacketAlgoCmd<ADataPacket, IUpdateTeam, IChatUser>(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 3164671300651974586L;



			@Override
			public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
				
			}

			@Override
			public ADataPacket apply(Class<?> index,
					DataPacket<IUpdateTeam> host, IChatUser... params) {
				// TODO Auto-generated method stub
				ADataPacket result;
				try{
					System.out.println("Server received update message! ");
					result = new DataPacket<ISuccessMsg>(ISuccessMsg.class, null);
				}catch(Exception e){
					result = new DataPacket<IFailMsg>(IFailMsg.class,  null);
				}
				return result;
			}
			
		});
	}
	
	/**
	 * Get the game factory.
	 * @return The game factory.
	 */
	public GameFactory getGameFac(){
		return gf;
	}
	
	/**
	 * Given a team name from the server GUI, create a team.
	 * @param teamName The name of the team.
	 */
	public IChatroom createTeam(String teamName) {
		gm.addTeam(teamName);
		return tm.createTeam(teamName, localChatuserStub);
	}
	
	/**
	 * Given a team and a person, assign this person to this team.
	 * @param team The team.
	 * @param personStub The person.
	 */
	public void assignTeam(IChatroom team, IInitUser personStub) {
		ThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					
					personStub.receive(serverInituserStub, new Invitation2Chatroom(team, true ).getDataPacket());
					
//					personStub.acceptInvitation(team);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Check if the person is already in the personStub list. If true, don't accept that person.
	 * @param personStub The personStub that the server just gets.
	 */
//	public boolean personExist(IInitUser personStub) {
//		Iterator<IInitUser> it = personList.iterator();
//		while (it.hasNext()) {
//			try {
//				if(personStub.getUUID().compareTo(it.next().getUUID()) == 0) 
//					return true;
//			} catch (RemoteException e) {
//				e.printStackTrace();
//			}
//		}
//		return false;
//	}
	
	/**
	 * Notify all IMember in the lobby to start the game, send them all the game.
	 */
	public void startGame(){
		for(IChatUser m: tm.getLobby().getUsers()){
			gf = new GameFactory(localChatuserStub);
			ThreadPool.execute(new Runnable(){
				@Override
				public void run() {
					try {	
						
						
						m.receive(localChatuserStub, new StartGameMsg(IGameWorld.class,localChatuserStub, gf).getDataPacket());

					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}			
			});
		}
	}
	
	/**
	 * Set created=true if the team has been created.
	 * @param created
	 */
	public void setTeamCreated(boolean created){
		teamCreated = created;
	}
	


}
