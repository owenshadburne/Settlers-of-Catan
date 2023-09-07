import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.MouseInputListener;
import javax.swing.text.AttributeSet.FontAttribute;
import javax.imageio.ImageIO;
import javax.swing.*;
public class BoardPanel extends JPanel implements MouseListener{
    public static BoardPanel instance;
    private BoardSetup board;
    private int tileWidth;
    private int tileHeight;
    public Graphics graphics;
    private ArrayList<Edge> paint;
    private boolean debugMode = false;
    private static boolean buildingRoad = false, buildingSettlement = false, buildingCity = false;
    public static boolean robberActive = false;
    private Player[] players;
    public BoardPanel(Player[] players) {
    	instance = this;
        tileWidth = (int)(getWidth()/6);
        tileHeight =(int)(getHeight()/4.4);
        board = new BoardSetup(getWidth(), getHeight(), tileWidth, tileHeight);
        graphics = null;
        paint = new ArrayList<Edge>();
        addMouseListener(this);
        this.players = players;
    }

    public void paint(Graphics g) {
        graphics = g;
        tileWidth = (int)(getWidth()/6);
        tileHeight =(int)(getHeight()/4.4);
        board.setProp(getWidth(), getHeight(), tileWidth, tileHeight);
        board.setVerticeLocations();
        paintBackground(g);
        paintTiles(g);
        darken(g, 40);
        paintRobber(g);
        paintVertices(g);
        paintPorts(g);

        //Items are repeated, this is for graphical purposes
        //Go ahead and remove these extra calls if performance is low
        paintEdges(g);
        board.paintRoads(g);
        paintVertices(g);
        if(robberActive) {
        	GameFrame.lockAllButtons = true;
            board.robber(g);
        }

        paintNumbers(g);
        paintPlayerInfo(g);
        board.paintForBuildingModes(g);

        //	repaint();
    }
    public void paintBackground(Graphics g) {
        //Ocean
        g.setColor(new Color(90, 133, 255));
        //	g.setColor(Color.CYAN);
        g.fillRect(0,0, getWidth(),getHeight());
        //Reseting to black
        darken(g, 100);
        g.setColor(Color.black);
    }
    private void darken(Graphics g, int alpha) {
        g.setColor(new Color(0, 0, 0, alpha));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);
    }
    //Testing the location of tiles 4-5-2022
    //center will be getWidth()/12, getHeight()/6
    public void paintTiles(Graphics g) {
        board.setTiles(getWidth(), getHeight());
        for(int i = 0; i < board.getTiles().size(); i++) {
            Tile t = board.getTiles().get(i);
            //t.highlight(g);
            g.drawImage(t.getImage(),(t.getPosition()[0] -tileWidth/2), t.getPosition()[1] -tileHeight/2, tileWidth,tileHeight, null);
        }
    }
    public void paintRobber(Graphics g) {
		BufferedImage robber = null;
		try {
			robber = ImageIO.read(BoardPanel.class.getResource("/Images/robber.png"));
		} catch (IOException e) {
			System.out.println("Can't draw robber");
		}
		ArrayList<Tile> tiles= new ArrayList<Tile>(board.getTiles());
		Tile current = null;
		for(int i = 0; i < tiles.size(); i++) {
			current = tiles.get(i);
			//	System.out.println(current.getPosition()[0] + " " + current.getPosition()[1]);
			if(current.hasThief()) {
				int[] position = current.getPosition();
				g.drawImage(robber, position[0]- tileWidth/3, position[1], tileWidth/3,tileHeight/3, null);
				return;
			}
		}

	}

    public void paintEdges(Graphics g) {
//		for(int i = 0; i < board.getTiles().size(); i++) {
//			ArrayList<Edge> edges = board.getEdges();
//			for(int j = 0; j < edges.size(); j++) {
//				edges.get(j).highlight(g);
//			}
//		}
        for(int i =0; i < paint.size(); i++) {
            paint.get(i).highlight(g);
        }
    }

  public void paintNumbers(Graphics g) {
		g.setColor(Color.WHITE);
		Font f = new Font("serif", Font.BOLD, tileHeight/3);
		g.setFont(f);
		for(int i = 0; i < board.getTiles().size(); i++) {
			if(board.getTiles().get(i).getRollValue() >0) {
				if(board.getTiles().get(i).getRollValue() == 8 || board.getTiles().get(i).getRollValue() == 6) {
					g.setColor(new Color(255,102,102));
				}
				g.drawString(board.getTiles().get(i).getRollValue() +"",board.getTiles().get(i).getPosition()[0]-tileWidth/5, board.getTiles().get(i).getPosition()[1]+ tileHeight/10  );
				g.setColor(Color.WHITE);
			}
		}

		g.setColor(Color.BLACK);
	}
    /*      1
     *
     *    2   3
     *
     *    4   5
     *      6
     * */
    public void paintVertices(Graphics g) {
        board.setVerticeLocations();
        Player player = GameFrame.currentPlayer();
        for(int j = 0; j < board.getVertices().size(); j++) {
            //board.getVertices().get(j).highlight(g);
            Vertex v = board.getVertices().get(j);
            if(isBuildingCity()) {
                if(v.getSettlement() != null && !v.getSettlement().isCity() && v.getSettlement().getColour() == player.getColour()) {
                    v.highlight(g);
                }
                else {
                    v.draw(g);
                }
            }
            else {
                v.draw(g);
            }
        }
    }
    public void paintPorts(Graphics g) {

        for(int j = 0; j < board.getPorts().size(); j++) {
            board.getPorts().get(j).highlight(g);
        }
    }
    public ArrayList<Resource> distribution(int roll) {
        return board.distribution(roll);
    }

    public static boolean isBuildingRoad() {
        return buildingRoad;
    }
    public static void isBuildingRoad(boolean b) {
        buildingRoad = b;
    }
    public static boolean isBuildingSettlement() {
        return buildingSettlement;
    }
    public static void isBuildingSettlement(boolean b) {
        buildingSettlement = b;
    }
    public static boolean isBuildingCity() {
        return buildingCity;
    }
    public static void isBuildingCity(boolean b) {
        buildingCity = b;
    }

    public void paintPlayerInfo(Graphics g) {
		Player currentPlayer = GameFrame.currentPlayer();
		BufferedImage army = null;
		BufferedImage road = null;
		if(!Runner.yourdidit) {
			try {
				army =ImageIO.read(BoardPanel.class.getResource("/Images/largestArmy.png"));
				road =ImageIO.read(BoardPanel.class.getResource("/Images/longestRoad.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				army =ImageIO.read(BoardPanel.class.getResource("/Images/your did it.png"));
				road =ImageIO.read(BoardPanel.class.getResource("/Images/your did it.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String title = players[0].getColour().toString() + ":";
		String r = "Resources: " + players[0].getSizeOfResources();
		String vp = "VP: " + (players[0].getRegularVictoryPoints() +
				(players[0].isLongRoad() ? 2 : 0) +
				(players[0].isLargArmy() ? 2 : 0)) +
				(players[0] == currentPlayer ? " + " + players[0].getSpecialVictoryPoints() : "");


		g.setColor(Color.WHITE);
		Font myFont = new Font("Georgia", Font.BOLD, getWidth()/30);
		g.setFont(myFont);
		g.drawString("1 -" +title, getWidth()/40, getHeight()/20);

		Font nomral = new Font("Georgia", Font.PLAIN, getWidth()/50);
		g.setFont(nomral);
		g.drawString(r, getWidth()/40, getHeight()/20 * 3);
		g.drawString(vp, getWidth()/40, getHeight()/20 *4);
		if(players[0].isLargArmy()) {
			g.drawImage(army, getWidth()/40 * 1, getHeight()/ 15, getHeight()/20, getHeight()/20,null);
		}
		if(players[0].isLongRoad()) {
			g.drawImage(road, getWidth()/40 * 5, getHeight()/ 15, getHeight()/20, getHeight()/20,null);
		}


		if(players.length > 1) {
			title = players[1].getColour().toString() + ":";
			r = "Resources: " + players[1].getSizeOfResources();
			vp = "VP: " + (players[1].getRegularVictoryPoints() +
					(players[1].isLongRoad() ? 2 : 0) +
					(players[1].isLargArmy() ? 2 : 0)) +
					(players[1] == currentPlayer ? " + " + players[1].getSpecialVictoryPoints() : "");

			g.setColor(Color.WHITE);
			g.setFont(myFont);
			g.drawString("2 -"+title, getWidth()/40 *32, getHeight()/20);

			nomral = new Font("Georgia", Font.PLAIN, getWidth()/50);
			g.setFont(nomral);
			g.drawString(r, getWidth()/40 * 32, getHeight()/20 * 3);
			g.drawString(vp, getWidth()/40 *32, getHeight()/20 * 4);
		
			if(players[1].isLargArmy()) {
				g.drawImage(army, getWidth()/40 * 34, getHeight()/ 15, getHeight()/20, getHeight()/20,null);
			}
			if(players[1].isLongRoad()) {
				g.drawImage(road, getWidth()/40 * 37, getHeight()/ 15, getHeight()/20, getHeight()/20,null);
			}
		}

		if(players.length > 2) {
			title = players[2].getColour().toString() + ":";
			r = "Resources: " + players[2].getSizeOfResources();
			vp = "VP: " + (players[2].getRegularVictoryPoints() +
					(players[2].isLongRoad() ? 2 : 0) +
					(players[2].isLargArmy() ? 2 : 0)) +
					(players[2] == currentPlayer ? " + " + players[2].getSpecialVictoryPoints() : "");

			g.setColor(Color.WHITE);
			g.setFont(myFont);
			g.drawString("3 -" +title, getWidth()/40 *32, getHeight()/20 *17);

			nomral = new Font("Georgia", Font.PLAIN, getWidth()/50);
			g.setFont(nomral);
			g.drawString(r, getWidth()/40 * 32, getHeight()/20 * 18);
			g.drawString(vp, getWidth()/40 * 32, getHeight()/20 * 19);
			
			if(players[2].isLargArmy()) {
				g.drawImage(army, getWidth()/40 * 39, getHeight()/30 * 26, getHeight()/20, getHeight()/20,null);
			}

			if(players[2].isLongRoad()) {
				g.drawImage(road, getWidth()/40 * 39, getHeight()/30 * 28, getHeight()/20, getHeight()/20,null);
			}
		}

		if(players.length > 3) {
			title = players[3].getColour().toString() + ":";
			r = "Resources: " + players[3].getSizeOfResources();
			vp = "VP: " + (players[3].getRegularVictoryPoints() +
					(players[3].isLongRoad() ? 2 : 0) +
					(players[3].isLargArmy() ? 2 : 0)) +
					(players[3] == currentPlayer ? " + " + players[3].getSpecialVictoryPoints() : "");

			g.setColor(Color.WHITE);
			g.setFont(myFont);
			g.drawString("4 -"+title, getWidth()/40, getHeight()/20 *17);

			nomral = new Font("Georgia", Font.PLAIN, getWidth()/50);
			g.setFont(nomral);
			g.drawString(r, getWidth()/40, getHeight()/20 * 18);
			g.drawString(vp, getWidth()/40, getHeight()/20 * 19);
			if(players[3].isLargArmy()) {
				g.drawImage(army, getWidth()/40 * 7, getHeight()/30 * 26, getHeight()/20, getHeight()/20,null);
			}
			if(players[3].isLongRoad()) {
				g.drawImage(road, getWidth()/40 * 7, getHeight()/30 * 28, getHeight()/20, getHeight()/20,null);
			}
		}
	}

    public void robber() {
        robberActive = !robberActive;
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        boolean vertex = false;
        board.checkClicks(x, y);
        ArrayList<Vertex> vertices = board.getVertices();
        for(int i =0; i < board.getVertices().size(); i++) {
            if(vertices.get(i).checkIfClicked(x, y)){
                //	System.out.println(vertices.get(i).toString() + "Amount of edges " + vertices.get(i).getEdges().size());
                for(int l = 0; l < vertices.get(i).getEdges().size(); l++) {
                    //System.out.println("Edge " + l + ": " +vertices.get(i).getEdges().get(l).toString() );
                }
                break;
            }
        }
        for(int j = 0; j < board.getTiles().size(); j++) {
            if(board.getTiles().get(j).checkIfClicked(x, y)){
                //System.out.println(board.getTiles().get(j).toString());
                break;
            }
        }
        for(int d = 0; d < board.getEdges().size(); d++) {
            if(board.getEdges().get(d).checkIfClicked(x, y)) {
                //System.out.println(board.getEdges().get(d).toString());
                break;

            }

        }
    }
    private void debugDisplay(int x, int y) {
        boolean vertex = false;
        ArrayList<Vertex> vertices = board.getVertices();
        for(int i =0; i < board.getVertices().size(); i++) {
            if(vertices.get(i).checkIfClicked(x, y)){
                //	System.out.println(vertices.get(i).toString() + "Amount of edges " + vertices.get(i).getEdges().size());
                for(int l = 0; l < vertices.get(i).getEdges().size(); l++) {
                    //System.out.println("Edge " + l + ": " +vertices.get(i).getEdges().get(l).toString() );
                    paint.add(vertices.get(i).getEdges().get(l));
                    repaint();
                }
                break;
            }
        }
        for(int j = 0; j < board.getTiles().size(); j++) {
            if(board.getTiles().get(j).checkIfClicked(x, y)){
                //System.out.println(board.getTiles().get(j).toString());
                break;
            }
        }
        for(int d = 0; d < board.getEdges().size(); d++) {
            if(board.getEdges().get(d).checkIfClicked(x, y)) {
                //System.out.println(board.getEdges().get(d).toString());
                break;

            }

        }
    }

    public void mousePressed(MouseEvent e) {	}
    public void mouseReleased(MouseEvent e) {	}
    public void mouseEntered(MouseEvent e) {	}
    public void mouseExited(MouseEvent e) {	}

}
