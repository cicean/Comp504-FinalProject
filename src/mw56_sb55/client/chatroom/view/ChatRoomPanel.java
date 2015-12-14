package mw56_sb55.client.chatroom.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;




import java.awt.List;
import java.awt.Button;
import java.util.Set;

public class ChatRoomPanel extends JPanel {

	
	
	/** Serial ID */
	  private static final long serialVersionUID = -4520690904050470438L;

	  /**
	     * The view to model adapter
	     */
	  private IViewToChatroomAdapter modelAdapter;

	  private final Box chatBox = Box.createVerticalBox();
	  private final JPanel panelChatroomActions = new JPanel();
	  private final JTextField textFieldMessage = new JTextField();
	  private final JButton btnSend = new JButton("Send");
	  private final JButton btnLeave = new JButton("Leave");
	  private final JScrollPane scrollPane = new JScrollPane(chatBox, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	  private final JButton btnUnknown = new JButton("Unknown");
	  private final JPanel userListPanel = new JPanel();
	  private final JList<String> userList = new JList<String>();
	  private final Button addUserbutton = new Button("Add User");
	  private DefaultListModel<String> userlistModel = new DefaultListModel<String>();

	  /**
	   * Create the panel.
	   * 
	   * @param chatroom the adapter to the chatroom model
	   */
	  public ChatRoomPanel(IViewToChatroomAdapter modelAdapter) {
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

	    setLayout(new BorderLayout(0, 0));

	    add(panelChatroomActions, BorderLayout.SOUTH);
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
	    	  
	      }
	    });
	    
	    panelChatroomActions.add(btnUnknown);
	    btnLeave.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	  
	      }
	    });

	    add(scrollPane, BorderLayout.CENTER);
	    
	  	
	  	add(userListPanel, BorderLayout.WEST);
	  	
	  	userListPanel.setLayout(new BorderLayout(0, 0)); 
	  	
	  	userListPanel.add(userList,BorderLayout.CENTER);
	  	
	  	userListPanel.add(addUserbutton,BorderLayout.SOUTH);
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

	public void updateUserList(Set<String> users) {
		// TODO Auto-generated method stub
		userlistModel = new DefaultListModel<String>();
		for (String user : users) {
			userlistModel.addElement(user);
		}
		userList.setModel(userlistModel);
		
		
	}

	public void start() {
		// TODO Auto-generated method stub
		 setVisible(true);
	}

	
}
