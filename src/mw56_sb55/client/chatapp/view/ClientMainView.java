package mw56_sb55.client.chatapp.view;

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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import mw56_sb55.client.chatroom.model.ChatUser;
import mw56_sb55.client.chatroom.view.ChatroomLobbyView;
import mw56_sb55.client.chatroom.view.ChatroomTeamView;
import mw56_sb55.client.chatroom.view.IViewToChatroomAdapter;



/**
 * The UI for chat app
 * @author ls53@rice.edu
 * @param <TUser> The generic type for user
 * @param <TChatroom> The generatic type for chat room
 */
public class ClientMainView extends JFrame {
    
	/**
	 * View to model adapter
	 */
	private IViewToModelAdapter viewToModalApt;//client view to model adapter.
    /**
     * The generate serial version UID
     */
    private static final long serialVersionUID = -1259336758548704871L;
    
    /**
     * The main panel
     */
    private JPanel contentPane;
    
    /**
     * The text area for showing infomation
     */
    private final JTextArea textArea = new JTextArea();
    
    /**
     * The text field for inputing IP address
     */
    private final JTextField txtIp = new JTextField();
    
    /**
     * The connect button
     */
    private final JButton btnConnect = new JButton("Connect");
    
    /**
     * The scroll panel
     */
    private final JScrollPane scrollPane = new JScrollPane();
    
    /**
     * The IP label
     */
    private final JLabel lblIp = new JLabel("IP Address:");
    
    /**
     * The upper panel
     */
    private final JPanel panelUp = new JPanel();
    private final JButton btnQuit = new JButton("Quit");
    
    

    /**
	 * Create game MVC
	 */
	private  JButton btnCreateMap;

    /**
     * Create the frame.
     */
    public ClientMainView(IViewToModelAdapter modelAdapter) {        
        this.viewToModalApt = modelAdapter;
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
        contentPane.add(panelUp, BorderLayout.SOUTH);
        panelUp.add(lblIp);
        txtIp.setToolTipText("Input IP address");
        panelUp.add(txtIp);
        txtIp.setColumns(10);
        btnCreateMap = new JButton("Create Map!");
        btnConnect.setToolTipText("Click to connect the server.");
        panelUp.add(btnConnect);
        btnCreateMap = new JButton("Create Map!");
        
        panelUp.add(btnQuit);
        btnConnect.addActionListener(e -> {
            String ip = txtIp.getText().trim();
            if (!ip.isEmpty()) {
            	append("Connecting to remote person...\n");
        		append(viewToModalApt.connectTo(ip) + "\n");
				btnCreateMap.setEnabled(true);
            }
        });
        
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                viewToModalApt.quit();
                System.exit(0);
            }
        });
        
        
        btnQuit.addActionListener(e -> {
          viewToModalApt.quit();
        });
    }
    
    /**
     * Start the UI
     */
    public void start() {
       
        setVisible(true);
        
    }
    
    /**
     * Append a message
     * @param str The message
     */
    public void append(String str) {
        textArea.append(str);
        textArea.setCaretPosition(textArea.getText().length());
    }
    


	
	/**
	 * A user-friendly function to set local IP address
	 * @param ip
	 * 		local IP address
	 */
	public void setRemoteAddress(String ip){
		txtIp.setText(ip);
	}

	public ChatroomTeamView<ChatUser> makeTeamView(
			IViewToChatroomAdapter chatroomModelAdapter) {
		// TODO Auto-generated method stub
		ChatroomTeamView<ChatUser> teamView = new ChatroomTeamView<ChatUser>(chatroomModelAdapter);
		return teamView;
	}
	
	public ChatroomLobbyView makeLobbyView(IViewToChatroomAdapter lobbyView2TeamModelAdpt) {
		ChatroomLobbyView lobbyView = new ChatroomLobbyView(lobbyView2TeamModelAdpt);
		return lobbyView;
	}
	
	
	

}