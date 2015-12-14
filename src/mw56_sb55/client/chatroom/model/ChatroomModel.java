package mw56_sb55.client.chatroom.model;

import java.awt.Component;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

import javax.management.loading.PrivateClassLoader;
import javax.swing.JFrame;

import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;
import provided.datapacket.DataPacketAlgo;
import provided.mixedData.MixedDataKey;
import common.IChatUser;
import common.IChatroom;
import common.ICmd2ModelAdapter;
import common.IInitUser;
import common.demo.message.chat.ChatUserInfoRequest;
import common.demo.message.chat.ChatUserInfoResponse;
import common.demo.message.chat.CommandRequest;
import common.demo.message.chat.InitUserResponse;
import common.demo.message.chat.TextMessage;
import common.message.IChatMessage;
import common.message.IInitMessage;
import common.message.chat.AAddMe;
import common.message.chat.AChatUserInfoRequest;
import common.message.chat.AChatUserInfoResponse;
import common.message.chat.ACommandResponse;
import common.message.chat.AInitUserRequest;
import common.message.chat.ARemoveMe;
import common.message.chat.ATextMessage;



/**
 * The chat room model
 * @author ls53@rice.edu
 */
public class ChatroomModel  implements IChatroom {

	/**
	 * Auto-generated UID
	 */
	private static final long serialVersionUID = 7220930159266341106L;

	 /**
     * The chat room id
     */
    private UUID id;
    
    /**
     * The chat room name
     */
    private String name;
    
    /**
     * The user name
     */
    private String username;
    
    /**
     * The IP address
     */
    private String ip;
    
    /**
	 * the team chatroom object
	 */
	private IChatroom team;
    
	
	 /**
     * this is the local myMemberStub
     */
    private IChatUser  chatmeHolder;
    
    private IInitUser initMeHolder;
    
	/**
	 * My member object. localMemberStub comes from myMember by doing
	 * localMemberStub = (IMember) UnicastRemoteObject.exportObject(myMember, IPerson.CLIENT_BOUND_PORT);
	 */
	private IChatUser myMember;
	
	private Set<ChatUser> userlist;
	
    
    /**
     * The user list
     */
    private Map<IChatUser, ChatUser> users = new HashMap<>();
    
    /**
     * The init user
     */
    private IInitUser initUser;
    
    private Map<Class<?>, Object[]> unknownMessages = new HashMap<>();
	
    
    /**
	 * this is the team mini model to team mini view adapter
	 */
	private IChatroomToViewAdapter _teamModel2TeamViewAdpt;
    
    /**
     * The data packet algorithm
     */
	// initialize initAlgo with null as its default command. 
	DataPacketAlgo<String, IInitUser> initAlgo = new DataPacketAlgo<String, IInitUser>(null);

	 // initialize chatAlgo with null as its default command. 
	DataPacketAlgo<String, IChatUser> chatAlgo = new DataPacketAlgo<String, IChatUser>(null);
	
	/**
	 * the game frame corresponding to addComponent function
	 */
	private JFrame gameFrame;



	
	 /**
     * The model to view adapter
     */
    private transient IChatroomToViewAdapter viewAdapter;
    
    private transient IChatUserToModelAdapt chatUserToModelAdapt;
    
    /**
     * the command to model adapter
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

	private IChatUserToModelAdapt c2mAdpt;    
    

	/**
	 * Constructs a new chatroom. 
	 */
	public ChatroomModel(UUID id, String name, String username, String ip, IInitUser initUser) {

		this.name =  name;
		this.id = id;
        this.username = username;
        this.ip = ip;
        this.initMeHolder = initUser;
        
        IChatUser meChatUser = new ChatUser(username, ip, chatAlgo, chatUserToModelAdapt);
		
		try {
          chatmeHolder = (IChatUser)UnicastRemoteObject.exportObject(meChatUser, IChatUser.BOUND_PORT_CLIENT);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
		
		
	
		
		chatmeHolder = new IChatUser() {
			
			@Override
			public void receive(IChatUser sender, DataPacket<? extends IChatMessage> dp)
					throws RemoteException {
				// TODO Auto-generated method stub
				dp.execute(chatAlgo, sender);
			}
		};
		
		c2mAdpt = new IChatUserToModelAdapt() {

			@Override
			public boolean addUser(IChatUser user) {
				return addUser(user);
			}
			
			
		};
		
		
		
		
		
		
	}
	
	private void chatAllSetCmd() {
		
		chatAlgo.setDefaultCmd(new ADataPacketAlgoCmd<String, Object, IChatUser>() {

            /**
             * The generated serial version UID
             */
            private static final long serialVersionUID = 7036336565109666871L;

            @Override
            public String apply(Class<?> index, DataPacket<Object> host, IChatUser... params) {
                try {
                    unknownMessages.put(index, new Object[]{host, params[0]});
                    params[0].receive(chatmeHolder, new CommandRequest(index).getDataPacket());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                
                return null;
            }

            @Override
            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {} 
        });
        
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
            	
            	try{
            	_teamModel2TeamViewAdpt.append(String.format("%s (%s): %s",
					users.get(params[0]).getName(),
						new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()), host.getData().getText()));
            	} catch (Exception e) {
            		e.printStackTrace();
            	}
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
                    params[0].receive(chatmeHolder, new ChatUserInfoResponse(host.getData(), username, ip).getDataPacket());
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
                users.get(params[0]).setName(response.getName());
                users.get(params[0]).setIp(response.getIP());
//                viewAdapter.addUsertoList(users.get(params[0]));
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
                    params[0].receive(chatmeHolder, new InitUserResponse(host.getData(), initUser).getDataPacket());
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
                addUser(host.getData().getUser());
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
//                viewAdapter.append(users.get(params[0]), "Send a RemoveMe data packet");
                removeUser(host.getData().getUser());
                return null;
            }

            @Override
            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {}
        });
	}
	



	

	public void setViewAdpter(IChatroomToViewAdapter makeChatroomViewAdapter) {
		// TODO Auto-generated method stub
		this.viewAdapter = makeChatroomViewAdapter;
	}

	public void start() {
		// TODO Auto-generated method stub
//		viewAdapter.setTitle(name);
        viewAdapter.start();
	}
	
	 /**
     * Get the meHolder object
     * @return
     */
    public IInitUser getinitMe() {
        return initMeHolder;
    }
    
    public IChatUser getchatMe() {
    	return chatmeHolder;
    }

//	@Override
//	public void send(IChatUser sender, IChatMessage message) {
//		// TODO Auto-generated method stub
//		(new Thread(){
//			@Override
//            public void run() {
//                super.run();
//                users.iterator().forEachRemaining(
//                        (user) ->{ 
//                            try{
//                                user.receive(sender, message.getDataPacket());;
//                            }
//                            catch(RemoteException e)
//                            {
//                                e.printStackTrace();
//                            }
//                        });
//            }
//		}).start();
//	}
	
	
	/**
     * Send a message
     * @param message The message to send
     */
    public void sendMessage(String message) {
//    	chatAlgo.setCmd(ATextMessage.class, new  ADataPacketAlgoCmd<String, ATextMessage, IChatUser>() {
//
//			@Override
//			public String apply(Class<?> index, DataPacket<ATextMessage> host,
//					IChatUser... params) {
//				// TODO Auto-generated method stub
//				ATextMessage text = host.getData();
//				IChatUser me = (IChatUser) params[0];
//				String username = "testing";
//				viewAdapter.displayMessage(String.format("%s (%s): %s",
//						username,
//						new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()), // time
//						text.getText()));
//				System.out.println(text.getText());
//				
//				return "Received a text message. ";
//				
//			}
//
//			@Override
//			public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
//				// TODO Auto-generated method stub
//				
//			}
//    		
//    	});
//    	// send friend a string. 
//    	this.send(chatmeHolder, new TextMessage(message));
    	
    	  send(chatmeHolder, new TextMessage(message));
    }
    
    /**
     * Send message synchronously
     * @param sender The sender
     * @param message The message
     */
    public void sendSync(IChatUser sender, IChatMessage message) {
        for (Entry<IChatUser, ChatUser> entry : users.entrySet()) {
            IChatUser user = entry.getKey();
            try {
                user.receive(sender, message.getDataPacket());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
		
 


@Override
public UUID getID() {
	// TODO Auto-generated method stub
	return id;
}

@Override
public String getName() {
	// TODO Auto-generated method stub
	return name;
}

@Override
public void setName(String name) {
	// TODO Auto-generated method stub
	this.name = name;
}

@Override
public Set<IChatUser> getUsers() {
	// TODO Auto-generated method stub
	return users.keySet();
}

@Override
public boolean addUser(IChatUser user) {
	// TODO Auto-generated method stub
	 if (!users.containsKey(user)) {
         try {
             users.put(user, new ChatUser(null, null, null, null));
             user.receive(chatmeHolder, new ChatUserInfoRequest().getDataPacket());
             
         } catch (RemoteException e) {
             e.printStackTrace();
         }
         
         return true;
     }
     
     return false;
}

@Override
public boolean removeUser(IChatUser user) {
	// TODO Auto-generated method stub
	if (users.containsKey(user)) {
        ChatUser chatUser = users.get(user);
        viewAdapter.removeUserfromList(chatUser);
        users.remove(user);
    }
    
    return false;
}

@Override
public void send(IChatUser sender, IChatMessage message) {
	// TODO Auto-generated method stub
	 (new Thread() {

         @Override
         public void run() {
             super.run();
             for (Entry<IChatUser, ChatUser> entry : users.entrySet()) {
                 IChatUser user = entry.getKey();
                 try {
                     user.receive(sender, message.getDataPacket());
                 } catch (RemoteException e) {
                     e.printStackTrace();
                 }
             }
         }
         
     }).start();
}

public void refreshViewUserList(ChatroomModel teamModel) throws Exception {
	// TODO Auto-generated method stub
	_teamModel2TeamViewAdpt.addUsertoList(teamModel.users.values());
	
	
	
}

public void setTeamModel2ViewAdapter(IChatroomToViewAdapter lobbyViewAdpt) {
	// TODO Auto-generated method stub
	this._teamModel2TeamViewAdpt = lobbyViewAdpt;
}


	
}