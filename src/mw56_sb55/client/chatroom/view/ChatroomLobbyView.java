package mw56_sb55.client.chatroom.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Set;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import mw56_sb55.client.chatroom.model.ChatUser;

public class ChatroomLobbyView extends JFrame {

	
	/** Serial ID */
	  private static final long serialVersionUID = -4520690904050470438L;

	  /**
	     * The view to model adapter
	     */
	  private IViewToChatroomAdapter modelAdapter;

	  
	  private final JPanel panelChatroomActions = new JPanel();
	  private final JTextField textFieldMessage = new JTextField();
	  private final JButton btnSend = new JButton("Send");
	  private final JButton btnLeave = new JButton("Leave");
	  private final JScrollPane scrollPane = new JScrollPane();
	  private final JButton btnUnknown = new JButton("Unknown");
	  private final JPanel userListPanel = new JPanel();
	  private final JList<String> userList = new JList<String>();
	  private final Button addUserbutton = new Button("Add User");
	  private DefaultListModel<String> userlistModel = new DefaultListModel<String>();
	  private final JPanel titlePanel = new JPanel();
	  private final JLabel lblNewLabel = new JLabel("Lobby");
	  private final JTextArea chatBox = new JTextArea();

	  /**
	   * Create the panel.
	   * 
	   * @param chatroom the adapter to the chatroom model
	   */
	  public ChatroomLobbyView(IViewToChatroomAdapter modelAdapter) {
	    this.modelAdapter = modelAdapter;
	    initGUI();
	  }

//	  /**
//	   * Installs the specified adapter to communicate with the model.
//	   * @param chatroom the adapter to install
//	   */
//	  public void installAdapter(IViewToChatroomAdapter chatroom) {
//	    this.chatroom = chatroom;
//	  }

	  /**
	   * Appends a text string to the end of the main text frame.
	   * @param message the message to append
	   */
	  public void appendTextToFrame(String message) {
	    addComponent(new JLabel(message));
	  }
	  
	  /**
	   * Appends a generic component to the end of the main text frame.
	   * @param comp the component to add
	   */
	  public void addComponent(Component comp) {
	    chatBox.add(comp);
	    scrollPane.paintAll(getGraphics());
	    scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	    repaint();  
	  }

	  /**
	   * Sends the message in the text field by calling the model's send method.
	   */
	  private void sendTextInBox() {
		modelAdapter.send(textFieldMessage.getText());
	    textFieldMessage.setText("");
	  }
	  


	  /**
	   * Initializes the GUI.
	   */
	  public void initGUI() {

	    getContentPane().setLayout(new BorderLayout(0, 0));

	    getContentPane().add(panelChatroomActions, BorderLayout.SOUTH);
	    textFieldMessage.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        sendTextInBox();
	      }
	    });
	    textFieldMessage.setToolTipText("Type a message to send to chatroom.");

	    textFieldMessage.setColumns(20);
	    panelChatroomActions.add(textFieldMessage);
	    btnSend.setToolTipText("Send message to chatroom.");
	    btnSend.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        sendTextInBox();
	      }
	    });

	    panelChatroomActions.add(btnSend);
	    btnLeave.setToolTipText("Leave the chatroom.");

	    panelChatroomActions.add(btnLeave);
	    btnUnknown.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	  modelAdapter.leave();
	      }
	    });
	    
	    panelChatroomActions.add(btnUnknown);
	    btnLeave.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	  
	      }
	    });
	    
	    chatBox.setEditable(false);
		scrollPane.setViewportView(chatBox);

	    getContentPane().add(scrollPane, BorderLayout.CENTER);
	    
	  	
	  	getContentPane().add(userListPanel, BorderLayout.WEST);
	  	
	  	userListPanel.setLayout(new BorderLayout(0, 0)); 
	  	
	  	userListPanel.add(userList,BorderLayout.CENTER);
	  	
	  	userListPanel.add(addUserbutton,BorderLayout.SOUTH);
	  	
	  	getContentPane().add(titlePanel, BorderLayout.NORTH);
	  	
	  	titlePanel.add(lblNewLabel);
	  }
	  
	  public void setChatroomName(String text){
		  System.out.println("setChatRoomName: "+text);
		  repaint();
	  }
	  
	  public void displayMessage() {
		  	validate();
		    scrollPane.paintAll(getGraphics());
		    scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
		    repaint();  
	  }

	public void updateUserList(Collection<ChatUser> users) throws RemoteException {
		// TODO Auto-generated method stub
		userlistModel = new DefaultListModel<String>();
		for (ChatUser user : users) {
			userlistModel.addElement(user.getName());
		}
		userList.setModel(userlistModel);
		
		
	}

	public void start() {
		// TODO Auto-generated method stub
		 setVisible(true);
	}

	public void append(String text) {
		// TODO Auto-generated method stub
		chatBox.append(text);
	}

}
