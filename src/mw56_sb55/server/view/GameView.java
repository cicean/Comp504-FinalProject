package mw56_sb55.server.view;

import javax.swing.JPanel;

/**
 * The panel that each player will see. Each player will have a different instance of this panel.
 */
public class GameView extends JPanel {

	/** Generated serial version UID */
	private static final long serialVersionUID = 111836252546450270L;

	/** The adapter to communicate with the remote GameModel */
	private IGameView2ModelAdapter _adapter;

}
