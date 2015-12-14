package mw56_sb55.server.controller;

import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.View;
import common.IChatUser;
import common.IChatroom;
import common.IInitUser;
import mw56_sb55.client.chatapp.model.InitUser;
import mw56_sb55.client.chatroom.model.Team;
import mw56_sb55.server.model.IServerModelToViewAdapter;
import mw56_sb55.server.model.ServerModel;
import mw56_sb55.server.view.IServerViewToModelAdapter;
import mw56_sb55.server.view.ServerView;

public class ServerController {
	private ServerModel serverModel;
	private ServerView<IChatroom, IInitUser>  serverView;
	
	public ServerController(){
		
		serverModel = new ServerModel(new IServerModelToViewAdapter() {
			
			@Override
			public void setRemoteHost(String localAddress) {
				// TODO Auto-generated method stub
				serverView.append("Connected IP address is " + localAddress + "\n");
				
			}
			
			@Override
			public IChatroom getSelectedChatroom() {
				// TODO Auto-generated method stub
				return null;
			}
			
		
			
			@Override
			public void append(String s) {
				// TODO Auto-generated method stub
				serverView.append(s);
			}
			
			@Override
			public void addToTeamDropList(IChatroom team) {
				// TODO Auto-generated method stub
				serverView.addToTeamDropList(team);
			}
			
			@Override
			public void addToPersonDropList(IInitUser personStub) {
				// TODO Auto-generated method stub
				serverView.addToPersonDropList(personStub);
			}
		});
		
		
		serverView = new ServerView<IChatroom, IInitUser>(new IServerViewToModelAdapter<IChatroom, IInitUser>() {
			
			@Override
			public void startGame() {
				// TODO Auto-generated method stub
				serverModel.startGame();
			}
			
			@Override
			public void setTeamCreated(boolean created) {
				// TODO Auto-generated method stub
				serverModel.setTeamCreated(created);
			}
			
			@Override
			public IChatroom createNewTeam(String teamName) {
				// TODO Auto-generated method stub
				return serverModel.createTeam(teamName);
			}
			
			@Override
			public void assignTeam(IChatroom team, IInitUser playerstub) {
				// TODO Auto-generated method stub
				serverModel.assignTeam(team, playerstub);
			}
		});
		
		
		

		
	}
	
	
	/**
	 * The server main function.
	 * @param args
	 */
	public static void main(String[] args){
		ServerController controller = new ServerController();
		controller.start();
	}
		
	public void start() {
		serverView.start();
		serverModel.start();
	}
	
	
	
}
