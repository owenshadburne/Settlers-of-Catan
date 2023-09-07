import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.event.MouseInputListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
public class GameFrame extends JFrame {
	private static final int WIDTH = 1600;
	private static final int HEIGHT = 900;
	private int numOfPlayers;
	private static Deck deck;
	static DeckPanel dPanel;
	static BoardPanel board;
	static DicePanel dice;
	static leftPanel left;
	static PlayerPanel playerHand;
	static BoardSetup boardSetup;
	public static GameFrame instance;
	static Player[] players;
	private static int currentPlayerPos;

	private static ImageIcon icon;
	public static boolean startOfGame = true;
	private static int snakeTurnPos = 0;

	public static boolean roadDevelopmentCard = false;
	public static int roadsLeftToBuild = 2;

	public static boolean hasRolled = false, hasBuilt = false, hasTraded = false, usedDevelopmentCard = false;
	public static boolean lockAllButtons = false;
	public static int numOfDiscardPanels;

	public GameFrame(String title, int players) {
		super(title);
		ImageStruct is = new ImageStruct();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		setFocusable(true);
		this.numOfPlayers = players;
		setResizable(false);
		createGame();
		boardSetup = BoardSetup.instance;
		deck = new Deck();
		makeDeck();
		dPanel = new DeckPanel(deck);
		deck.setDeckPanel(dPanel);
		board = new BoardPanel(this.players);
		instance = this;
		playerHand = new PlayerPanel(deck);
		icon = new ImageIcon(getClass().getResource("Images/CatanIcon.jpg"));
		dice = new DicePanel(this);
		left = new leftPanel(dPanel, dice);
		GridBagLayout gridbag = new GridBagLayout();
		this.setLayout(gridbag);
		GridBagConstraints c = new GridBagConstraints();

		c.weightx = 2.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		add(board,c);

		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 2;
		c.gridy = 0;
		add(playerHand,c);

		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		add(left,c);
		this.setVisible(true);

		startOfGamePlacements();

		//Lets player know why buttons do not work
		String str1 = "Welcome to the Game of Catan!";
		String str2 = "In the start of the game, allow each player to pick the location of their first 2 settlements and roads";
		String str3 = "Please note that the buttons will not work while in this beginning stage";
		//JOptionPane.showMessageDialog(this, str1+ "\n" + str2 + "\n" + str3);
		ImageIcon icon =new ImageIcon(getClass().getResource("Images/CatanIcon.jpg"));

		JLabel label = new JLabel(icon);
		JPanel panel = new JPanel(new GridBagLayout());
		panel.add(label);
		JPanel textPanel = new JPanel(new GridLayout(5, 3));
		textPanel.add(new JLabel(str1));
		textPanel.add(new JLabel(str2 ));
		textPanel.add(new JLabel(str3));

		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add(textPanel);
		panel2.add(panel, BorderLayout.EAST);
		JOptionPane.showMessageDialog(board, panel2, "Welcome!",JOptionPane.DEFAULT_OPTION);
	}
	private void createGame() {
		players = new Player[numOfPlayers];
		ArrayList<Player> list = new ArrayList<>();
		list.add(new Player(Colour.WHITE));
		list.add(new Player(Colour.RED));
		list.add(new Player(Colour.BLUE));
		list.add(new Player(Colour.ORANGE));
		Collections.shuffle(list);
		for(int x = 0; x < players.length; x++) {
			players[x] = list.get(x);
		}

		System.out.println(currentPlayer().toString());
		currentPlayerPos = 0;
	}
	public void makeDeck() {
		for(int i = 0; i < 19; i++) {
			deck.add(Resource.CLAY);
			deck.add(Resource.WOOD);
			deck.add(Resource.WHEAT);
			deck.add(Resource.WOOL);
			deck.add(Resource.ORE);
		}

		ArrayList<Development> temp = new ArrayList<Development>();
		for(int i = 0; i < 14; i++) {
			temp.add(Development.KNIGHT);
		}
		for(int i = 0; i < 2; i++){
			temp.add(Development.MONOPOLY);
			temp.add(Development.PLENTY);
			temp.add(Development.ROAD);
		}
		for(int i = 0; i < 5; i++){
			temp.add(Development.POINT);
		}
		Collections.shuffle(temp);
		for(Development d: temp) {
			deck.add(d);
		}
	}
	// MAKE PLAYERS


	public static Player currentPlayer() {
		return players[currentPlayerPos];
	}
	public static Player[] getPlayers() {
		return players;
	}
	public static void EndTurn()  {
		if(startOfGame) {
			if(snakeTurnPos > players.length - 1) {
				currentPlayerPos = (players.length * 2) - 1 - snakeTurnPos;
			}
			else {
				currentPlayerPos = snakeTurnPos;
			}
		}
		else {
			currentPlayerPos += 1;
			if(currentPlayerPos > players.length - 1) {
				currentPlayerPos = 0;
			}
		}
		
		if(boardSetup == null) {
			boardSetup = boardSetup.instance;
		}
		
		boardSetup.checkForLongestRoad();
		
		repaintAllPanels();

		//Checks to see if they won
		for(int i =0; i < players.length; i++) {
			if(players[i].getTotalVictoryPoints() >= 10) {
				//Lets player know why buttons do not work
				String str1 = "Congrats "+ players[i].getColour().toString()+"! You Won!";
				//JOptionPane.showMessageDialog(this, str1+ "\n" + str2 + "\n" + str3);
			//	JOptionPane.showMessageDialog(instance, str1);
				
				int a = JOptionPane.showConfirmDialog(instance, str1 + " Want to play again?");
				if(a==JOptionPane.YES_OPTION){  
					try {
						IntroductionPanel p = new IntroductionPanel("Welcome to game of Catan");
						instance.dispose();  
						Runner.yourdidit = true;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    
				}  else {
					instance.dispose();  
				}  
			}
		}
	}

	public static void snakeTurn() {
		snakeTurnPos++;
		if(snakeTurnPos > players.length * 2 - 1) {
			startOfGame = false;
			BoardPanel.isBuildingSettlement(false);
			currentPlayerPos = 0;
		}
		else {
			EndTurn();
		}
	}

	private void startOfGamePlacements() {
		BoardPanel.isBuildingSettlement(true);
	}

	public static void repaintAllPanels() {
		dPanel.repaint();
		board.repaint();
		playerHand.repaint();
		dice.repaint();
		left.repaint();
	}

	public static void distribution(int roll) {
		ArrayList<Resource> distributedResources = board.distribution(roll);
		for(Resource r: distributedResources) {
			deck.remove(r);
		}
	}

	public static int getCount(Resource r) {
		return deck.getCount(r);
	}

	public static Deck getDeck() {
		return deck;
	}

	public static void resetTurnOrder() {
		hasRolled = false;
		hasBuilt = false;
		hasTraded = false;
		usedDevelopmentCard = false;
	}

	public static Player getPlayer(Colour c) {
		for(Player player: players) {
			if(player.getColour() == c) {
				return player;
			}
		}
		return null;
	}
	
	public static void discardPanelClosed() {
		numOfDiscardPanels--;
		if(numOfDiscardPanels <= 0 && !BoardPanel.robberActive) {
			lockAllButtons = false;
		}
	}
}
