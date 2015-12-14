package mw56_sb55.game.view;


import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwindx.examples.util.DirectedPath;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;


//import map.IRightClickAction;
import map.MapLayer;
import map.MapPanel;
import mw56_sb55.game.model.City;


/**
 * The panel that each player will see. Each player will have a different instance of this panel.
 */
public class GameView extends JFrame {



	/** The adapter to communicate with the remote GameModel */
	private IGameView2ModelAdapter modelAdapter;
	
	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = 2619622707248300113L;
	/**
	 * Content panel
	 */
	private JPanel contentPane;

	/**
	 * Map panel, used to display map
	 */
	private MapPanel mapPanel;
	/**
	 * A list of Place stored in JComboBox
	 */
	private JComboBox<City> cities;
	
	private List<Position> pathPositions;
	
	private Path path;
	
	private AllCities ac;
	
	/**
	 * Right click action
	 */
//	private IRightClickAction  rightClick;
	
	private RenderableLayer layer;
	
	private PointPlacemark pp;
	
	private ShapeAttributes attrs;
	
	private String gameName = "Road Runner";
	
	private MenuBar mb;
	
	private Menu mf;
	
	private MenuItem itemSelect, itemQuit;
	
	private final JPanel pnlNorth = new JPanel();
	
	private final JLabel lblTimeSpent = new JLabel("Step Spent:");
	
	private final JTextArea timeSpentTxt = new JTextArea();
	
	private final JLabel lblCity = new JLabel("Current City:");
	
	private final JTextArea cityTxt = new JTextArea();
	private final JPanel pnlSouth = new JPanel();
	private final JButton btnStay = new JButton("Stay");
	private final JButton btnMove = new JButton("Let's move!");
	private final JLabel lblNextDestination = new JLabel("Next Move:");
	
	private final JLabel lblDays = new JLabel("moves");
	private final JLabel yourScore = new JLabel("Your Score:");
	private final JTextField scoreField = new JTextField();
	private final JLabel teamScore = new JLabel("Team Score:");
	private final JTextField teamScoreField = new JTextField();
	private final JButton btnHelp = new JButton("How to play?");
	private final JPanel pnlScore = new JPanel();
	private final JPanel pnlStatues = new JPanel();

	/**
	 * Start the view
	 */
	public void start(){
		setLocationRelativeTo(null);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
			    setVisible(true);
			}	
		});
	}
	/**
	 * Create the frame.
	 */
	public GameView(IGameView2ModelAdapter adpter) {
		
		this.modelAdapter = adpter;
		this.setTitle(gameName);
		ac = new AllCities();
		
		initGameElement();
		initGUI();
	}
	
	/**
	 * Initialize the game element.
	 */
	private void initGameElement(){
		layer = new RenderableLayer();
		pathPositions = new ArrayList<Position>();
		attrs = new BasicShapeAttributes();
		attrs.setOutlineMaterial(Material.YELLOW);
		attrs.setOutlineWidth(2d);
	}
	
	private void initGUI() {
		try{
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 1083, 761);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			
			contentPane.add(pnlNorth, BorderLayout.NORTH);
			
			mb = new MenuBar();
			mf = new Menu("File");
			
			itemSelect = new MenuItem("Select");
			itemQuit = new MenuItem("Quit");
			
			initMenu();
			
			mf.add(itemSelect);
			mf.addSeparator();
			mf.add(itemQuit);
			
			mb.add(mf);
			
			setMenuBar(mb);
			pnlNorth.setLayout(new BorderLayout(0,0));
			
			pnlNorth.add(pnlStatues, BorderLayout.NORTH);
			
			pnlStatues.add(lblCity);
			pnlStatues.add(cityTxt);
			cityTxt.setToolTipText("current place");
			cityTxt.setEnabled(false);
			cityTxt.setEditable(false);
			cityTxt.setColumns(10);
			pnlStatues.add(lblTimeSpent);
			pnlStatues.add(timeSpentTxt);
			timeSpentTxt.setToolTipText("Total time spent");
			timeSpentTxt.setText("0");
			timeSpentTxt.setEnabled(false);
			timeSpentTxt.setEditable(false);
			timeSpentTxt.setColumns(5);
			pnlStatues.add(lblDays);
			pnlNorth.add(pnlScore, BorderLayout.CENTER);
			pnlScore.add(yourScore);
			pnlScore.add(scoreField);
			scoreField.setToolTipText("This is your score.");
			
			scoreField.setText("0");
			scoreField.setEnabled(false);
			scoreField.setEditable(false);
			scoreField.setColumns(10);
			pnlScore.add(teamScore);
			pnlScore.add(teamScoreField);
			teamScoreField.setToolTipText("This is your team's score.");
			teamScoreField.setText("0");
			teamScoreField.setEnabled(false);
			teamScoreField.setEditable(false);
			teamScoreField.setColumns(10);
	
			pnlScore.add(btnHelp);
			btnHelp.setToolTipText("Click to show the instruction of playing this game.");
			btnHelp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String helpInfo = 
							"<Game: Around World in 80 Days> \n"
						  + "Welcome to the game! First you need to choose a city as your \n"
						  + "first city. What you need to is just travel around the world \n"
						  + "and goes back to the first city. You will have up to 4 kinds \n"
						  + "of transportation you can choose to travel across cities. And \n"
						  + "each choice will leads to different scores your can gain.\n \n"
						  + "<Game Operation> \n"
						  + "Chose a city, chose one transportation, then click Let's Move! \n"
						  + "That is a basic way the move to next city. You may be caught \n"
						  + "in the situtaion that you do not have enough money for travel, \n"
						  + "don't worry, just click Stay&Work, it will cost you one day \n"
						  + "to gain $500. Remember, always try to get back to your original \n"
						  + "city! \n\n"
						  + "<Winning Critieria> \n"
						  + "When all members finished their own games, the game is over. \n"
						  + "All steps will leads to different kinds of score gainning. \n"
						  + "Shorter day and lower cost will help to higher score. \n \n"
						  + ""
						  + "Then, have fun! You can enjoy the whole travel!";
					JOptionPane.showMessageDialog(null, helpInfo);
				}
			});
			
			contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
			mapPanel = new MapPanel();
			mapPanel.start();
			contentPane.add(mapPanel, BorderLayout.CENTER);
			mapPanel.setPreferredSize(new java.awt.Dimension(600, 400));
			
			cities = new JComboBox<City>();
			cities.setToolTipText("Choose the next destination ");
			initCities();
			
			pnlSouth.add(lblNextDestination);
			pnlSouth.add(cities);
			btnMove.setToolTipText("Click to move the next selected city.");
			btnMove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Transportation trans = transportations.getItemAt(transportations.getSelectedIndex());
					if (Integer.parseInt(moneyTxt.getText()) - trans.getPrice() >= 0){
						gv2mAdpt.goPlace(cities.getItemAt(cities.getSelectedIndex()),
								transportations.getItemAt(transportations.getSelectedIndex()));
					}else {
						JOptionPane pane = new JOptionPane();
					    pane.setMessage("Have no enough money.\n"+"You may work in the current city and earn some money by clicking the Stay&Work button");
					    JDialog d = pane.createDialog(null, "Hint");
					    d.setVisible(true);
					}
				}
			});
			
			
			pnlSouth.add(btnMove);
			
			btnStay.setToolTipText("Make money for further travel cost...");
			btnStay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int curMoney = Integer.parseInt(moneyTxt.getText());
					int curTime = Integer.parseInt(timeSpentTxt.getText());
					
					curMoney += 500;
					curTime += 1;
					
					moneyTxt.setText("" + curMoney);
					timeSpentTxt.setText("" + curTime);
					
				}
			});
			
			
			pnlSouth.add(btnStay);
			
			
			
			initTransp();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * Initialize cities for JComboBox
	 */
	private void initCities(){
		List<City> cities = ac.getCities();
		for(City c: cities){
			addCity(c);
		}
		
	}
	
	/**
	 * Initialize the available transportation.
	 */
	private void initTransp(){
		List<Transportation> trans = cities.getItemAt(cities.getSelectedIndex()).getAvailableTransp(); 
		for(Transportation t: trans){
			transportations.addItem(t);
		}
	}
	
	/**
	 * Initialize the menu.
	 */
	private void initMenu(){
		itemQuit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
	}
	
	/**
	 * Set the position.
	 * @param pos The position
	 */
	public void setPosition(Position pos)
	{
		mapPanel.setPosition(pos, true);
		
		if(layer != null){
			mapPanel.removeLayer(layer);
		}
		pp = new PointPlacemark(pos);
		pathPositions.add(pos);
		
		path = new DirectedPath(pathPositions);
		path.setAttributes(attrs);
		path.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
        path.setPathType(AVKey.GREAT_CIRCLE);
        
		layer.addRenderable(pp);
//		layer.addRenderable(path);
		
		
		mapPanel.addLayer(layer);
		
	}

	/**
	 * Add a map layer.
	 * @param layer Map layer.
	 */
	public void addMapLayer(MapLayer layer)
	{
		mapPanel.addLayer(layer);
	}

	/**
	 * Remove a map layer
	 * @param layer Map layer.
	 */
	public void removeMapLayer(MapLayer layer)
	{
		mapPanel.removeLayer(layer);
	}

	/**
	 * Add city to the JComboBox
	 * @param p The city.
	 */
	public void addCity(City p) {
		cities.insertItemAt(p, 0);
		cities.setSelectedIndex(0);
	}
	
	/**
	 * Each time player tries to move on, the travel information is updated, including
	 * the city and transportation
	 * @param city The city
	 */
	public void updateTravelInfo(City city){
		List<City> citiesInfo = city.getNextAvailableCities();
		List<Transportation> transInfo = city.getAvailableTransp();

		cities.removeAllItems();
		for(City c: citiesInfo){
			addCity(c);
		}
		cities.repaint();
		
		transportations.removeAllItems();
		for(Transportation t: transInfo){
			transportations.addItem(t);
		}
		transportations.repaint();
	}
	
	
	@Override 
	public void repaint(){
		super.repaint();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mapPanel.repaint();
			    mapPanel.setVisible(true);
			}	
		});
	}
	
	/**
	 * Set the current city that player is in
	 * @param city
	 * 		The city object
	 */
	public void setCurrentCity(City city){
		cityTxt.setText(city.toString());
	}
	
	/**
	 * Update the city, money information.
	 * @param t
	 * 		The transportation type, with which we can update the price and time information.
	 */
	public void updateCurrentInfo(Transportation t){
		int curMoney = Integer.parseInt(moneyTxt.getText());
		int curTime = Integer.parseInt(timeSpentTxt.getText());
		
		curMoney -= t.price;
		curTime += t.time;
		
		int timeScore = (10 - t.time) * 10;
		int priceScore = 1700 - t.price;
		
		int addedScore = timeScore + priceScore;
		
		int curScore = Integer.parseInt(scoreField.getText());
		
		curScore += addedScore;
		curScore -= 100;
		
		scoreField.setText(String.valueOf(curScore));
		
		moneyTxt.setText("" + curMoney);
		timeSpentTxt.setText("" + curTime);
		
	}
	
	/**
	 * All game over.
	 * @param winningTeam The winning team.
	 * @param winningScore The winning score.
	 */
	public void gameOver(String winningTeam, int winningScore){
		
		btnMove.setEnabled(false);
		btnStay.setEnabled(false);
		cities.removeAllItems();
		transportations.removeAllItems();
		
		JOptionPane.showMessageDialog(null, "Game over, " + winningTeam + " has won the game with the score "
		+ String.valueOf(winningScore)+" !");
		
		
	}
	
	/**
	 * A local person get back to its original city.
	 */
	public void localGameOver(){
		btnMove.setEnabled(false);
		JOptionPane.showMessageDialog(null, "You have finished the game! Please wait for others.");

	}
	
	/**
	 * Update the team score.
	 * @param n The score of the team.
	 */
	public void upadateTeamScore(int n){
		teamScoreField.setText(String.valueOf(n));
	}
	
	/**
	 * Update the team mates' position.
	 * @param lat The latitude
	 * @param longt The longitude
	 */
	public void updateTeammatesPlace(double lat, double longt){
		Position p = Position.fromDegrees(lat, longt);
		pp = new PointPlacemark(p);
		pp.setLabelText("Teammates arrived here!");
	
		
		PointPlacemarkAttributes newAttr = new PointPlacemarkAttributes();
		newAttr.setImageColor(Color.cyan);
		
		pp.setAttributes(newAttr);
		
		layer.addRenderable(pp);
		
		mapPanel.repaint();
		
	}

}
