package mw56_sb55.server.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import common.IChatroom;
import common.IInitUser;




/**
@author @rice.edu
 * @param <TUser> The generic type for user
 * @param <TChatroom> The generatic type for chat room
 */
public class ServerView<TChatroom, TInitUser> extends JFrame {
    
   

    /**
     * The generate serial version UID
     */
    private static final long serialVersionUID = -1259336758548704871L;
    
    /**
     * The view to model adapter
     */
    private IServerViewToModelAdapter<IChatroom, IInitUser> modelAdapter;
    
    /**
     * The main panel
     */
    private JPanel contentPane;
    
    /**
     * The text area for showing infomation
     */
    private final JTextArea textArea = new JTextArea();
    
    /**
     * The connect button
     */
    private final JButton btnConnect = new JButton("Stat Game");
    
    /**
     * The users label
     */
    private final JLabel lblUsers = new JLabel("Users:");
    
    /**
     * The combo box for selecing user
     */
    private final JComboBox<IInitUser> cbxUsers = new JComboBox<>();
    
    /**
     * The combo box for selecting a chatroom
     */
    private final JComboBox<IChatroom> cbxChatrooms = new JComboBox<>();
    
    
    /**
     * The Assign Team button
     */
    private final JButton btnAssignTeam = new JButton("Assign Team");
    
    /**
     * The scroll panel
     */
    private final JScrollPane scrollPane = new JScrollPane();
    
    /**
     * The button for creating a new chat room
     */
    private final JButton btnNewChatroom = new JButton("New Team");
    
    /**
     * The chat rooms label
     */
    private final JLabel lblChatrooms = new JLabel("Chatrooms:");
    
    
    /**
     * The panel containing panelUP and panelDown
     */
    private final JPanel panel = new JPanel();
    
    /**
     * The upper panel
     */
    private final JPanel panelUp = new JPanel();
    
    /**
     * The lower panel
     */
    private final JPanel panelDown = new JPanel();

    /**
     * Create the frame.
     */
    public ServerView(IServerViewToModelAdapter<IChatroom, IInitUser> modelAdapter) {        
        this.modelAdapter = modelAdapter;
        initGui();
    }
    
    /**
     * Initialize the GUI
     */
    private void initGui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        textArea.setToolTipText("Show information");
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);
        
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(2, 1, 0, 0));
        
        panel.add(panelUp);
        btnConnect.setToolTipText("Start the Game");
        panelUp.add(btnConnect);
        btnNewChatroom.setToolTipText("Create a new chat room");
        panelUp.add(btnNewChatroom);
        btnNewChatroom.addActionListener(e -> {
        	  String teamName = null;
              do {
            	  teamName = JOptionPane.showInputDialog(null, "Please input new chatroom's name:", "Chatroom Name", JOptionPane.INFORMATION_MESSAGE);
                             
              } while (teamName.isEmpty());
              
            if (!teamName.equals("Team")){
            	IChatroom newChatroomModel = modelAdapter.createNewTeam(teamName);
            	
                cbxChatrooms.addItem(newChatroomModel);
            } else {
            	textArea.append("You have to input a different team name.\n");
			}
        });
        
        btnConnect.addActionListener(e -> {
            
            
                modelAdapter.startGame();
            
        });
        
        panel.add(panelDown);
        panelDown.add(lblUsers);
        cbxUsers.setToolTipText("Select a user");
        panelDown.add(cbxUsers);
        panelDown.add(lblChatrooms);
        cbxChatrooms.setToolTipText("Select a chat room");
        panelDown.add(cbxChatrooms);
        btnAssignTeam.setToolTipText("Invite a user");
        panelDown.add(btnAssignTeam);
        btnAssignTeam.addActionListener(e -> {
            if (cbxUsers.getSelectedIndex() == -1 || cbxChatrooms.getSelectedIndex() == -1) {
                return;
            }
            
            Player player = (Player)(cbxUsers.getItemAt(cbxUsers.getSelectedIndex()));

			IInitUser playerstub =  (IInitUser) player.getPlayerStub();
            IChatroom team  = cbxChatrooms.getItemAt(cbxChatrooms.getSelectedIndex());
            /*If personStub and team exist, assign the person to the team.*/
			 if (player != null && team != null) {
				 modelAdapter.setTeamCreated(true);
				 modelAdapter.assignTeam(team, (common.IInitUser) playerstub);//assign the personStub to the team
//				 cbxUsers.removeItem(player);//remove the personStub from the comboBox so that the person
				 									//combo box only display person who has not been assigned a team.
			 }
        });
        
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
//                modelAdapter.quitAllChatrooms();
            }
        });
    }
    

	/**
	 * Append the text onto the server GUI.
	 * @param s The text to be appended.
	 */
	public void append(String s){
		textArea.append(s);
		textArea.setCaretPosition(textArea.getText().length());
	}
	
	/**
	 * Start the server GUI.
	 */
	public void start(){
		setVisible(true);
	}
	
	/**
	 * Add the created team to the team drop list.
	 * @param team The team object of type IChatroom
	 */
	public void addToTeamDropList(IChatroom team){
		
		cbxChatrooms.insertItemAt(team, 0);
	}
	
	/**
	 * Add the newly connected person to the person list.
	 * @param personStub The newly connected person to be added to the person drop list.
	 */
	public void addToPersonDropList(IInitUser user){
		Player clientwrapper = new Player( user);
		cbxUsers.insertItemAt((IInitUser) clientwrapper, 0);
		cbxUsers.setSelectedIndex(0);
	}

	/**
	 * Get the selected team.
	 * @return The selected team.
	 */
	public IChatroom getSelectedChatroom(){
		return cbxChatrooms.getItemAt(cbxChatrooms.getSelectedIndex());
	}
    

}