package mw56_sb55.client.chatroom.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * The UI to show image
 * @author ls53@rice.edu
 */
public class ImageView extends JFrame {

	/**
	 * The generated serial version UID
	 */
	private static final long serialVersionUID = -6087318810943096208L;

	/**
	 * The main panel
	 */
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ImageView() {
		initGui();
	}

	/**
	 * Initialize GUI
	 */
	private void initGui() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	/**
	 * Start the view
	 */
	public void start() {
		setVisible(true);
	}
}
