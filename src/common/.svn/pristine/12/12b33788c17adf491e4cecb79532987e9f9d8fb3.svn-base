package common.demo;

import java.rmi.RemoteException;

import common.IChatUser;
import common.IInitUser;
import common.demo.Bootstrap;
import common.demo.command.AddMeCmd;
import common.demo.command.UnknownMessageCmd;
import common.demo.command.Invitation2ChatroomCmd;
import common.demo.command.NullCmd;
import common.demo.command.TextMessageCmd;
import common.demo.message.chat.TextMessage;
import common.demo.message.init.Invitation2Chatroom;
import common.message.IChatMessage;
import common.message.IInitMessage;
import common.message.chat.AAddMe;
import common.message.chat.ATextMessage;
import common.message.init.AInvitation2Chatroom;
import provided.datapacket.DataPacket;
import provided.datapacket.DataPacketAlgo;

/**
 * A console chat app only prints string in console. 
 */
public class ConsoleChatApp {

	/**
	 * Run the demo. 
	 * @param args - console arguments. 
	 */
	public static void main(String[] args) {

		// initialize bootstrap. 
		Bootstrap bootstrap = new Bootstrap();

		// initialize initAlgo with null as its default command. 
		DataPacketAlgo<String, IInitUser> initAlgo = new DataPacketAlgo<String, IInitUser>(null);

		// initialize chatAlgo with null as its default command. 
		DataPacketAlgo<String, IChatUser> chatAlgo = new DataPacketAlgo<String, IChatUser>(null);

		// create a remote to local adapter.  
		IInitUser initMe = new IInitUser() {
			@Override
			public void receive(IInitUser sender, DataPacket<? extends IInitMessage> dp) throws RemoteException {
				dp.execute(initAlgo, sender);
			}
		};

		// create a remote to chatroom adapter. 
		IChatUser chatMe = new IChatUser() {
			@Override
			public void receive(IChatUser sender, DataPacket<? extends IChatMessage> dp) throws RemoteException {
				dp.execute(chatAlgo, sender);
			}
		};

		// get the stub of initial adapter. 
		IInitUser initMeStub = bootstrap.register(initMe);

		// get the stub of chatroom adapter. 
		IChatUser chatMeStub = bootstrap.register(chatMe);

		// create a new chatroom. 
		Chatroom room = new Chatroom();
		room.addUser(chatMeStub);

		// add commands to initAlgo. 
		initAlgo.setDefaultCmd(new NullCmd());
		initAlgo.setCmd(AInvitation2Chatroom.class, new Invitation2ChatroomCmd(chatMeStub));

		// add commands to chatAlgo. 
		chatAlgo.setDefaultCmd(new UnknownMessageCmd(chatMeStub)); 
		chatAlgo.setCmd(AAddMe.class, new AddMeCmd(room));
		chatAlgo.setCmd(ATextMessage.class, new TextMessageCmd());

		// get the initial stub from friend's IP address. 
		IInitUser friend = bootstrap.connectToUser("127.0.0.1");

		// ask friend to join your chatroom. 
		try {
			friend.receive(initMeStub, new Invitation2Chatroom(room, true).getDataPacket());
		} catch (RemoteException e) {
			System.out.println("Can't find friend. ");
			e.printStackTrace();
		}

		// send friend a string. 
		room.send(chatMeStub, new TextMessage("Hello world!"));

	}

}
