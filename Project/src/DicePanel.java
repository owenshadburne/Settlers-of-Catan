import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.text.AttributeSet.ColorAttribute;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener.*;
public class DicePanel extends JPanel implements MouseListener{
	private DiceObject dices;
	private boolean hasRolled;
	private BufferedImage[] images;
	private GameFrame frame;
	public DicePanel(GameFrame f) {
		dices = new DiceObject();
		hasRolled = false;
		images = new BufferedImage[6];
		frame = f;

		if(!Runner.yourdidit) {
			try {
				images[0] = ImageIO.read(DicePanel.class.getResource("/Images/dice1.png"));
				images[1] = ImageIO.read(DicePanel.class.getResource("/Images/dice2.png"));
				images[2] = ImageIO.read(DicePanel.class.getResource("/Images/dice3.png"));
				images[3] = ImageIO.read(DicePanel.class.getResource("/Images/dice4.png"));
				images[4] = ImageIO.read(DicePanel.class.getResource("/Images/dice5.png"));
				images[5] = ImageIO.read(DicePanel.class.getResource("/Images/dice6.png"));


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				images[0] = ImageIO.read(DicePanel.class.getResource("/Images/your did it.png"));
				images[1] = ImageIO.read(DicePanel.class.getResource("/Images/your did it.png"));
				images[2] = ImageIO.read(DicePanel.class.getResource("/Images/your did it.png"));
				images[3] = ImageIO.read(DicePanel.class.getResource("/Images/your did it.png"));
				images[4] = ImageIO.read(DicePanel.class.getResource("/Images/your did it.png"));
				images[5] = ImageIO.read(DicePanel.class.getResource("/Images/your did it.png"));


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		addMouseListener(this);
		setVisible(true);
		//setFocusable(true);
		repaint();
	}
	public void paint(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0,0,getWidth(),getHeight());
		g.setColor(Color.WHITE);

		int dice1 = dices.getDice1();
		int dice2 = dices.getDice2();

		g.drawImage(images[dice1-1], getWidth()/6, getHeight()/5, getHeight()/2, getHeight()/2, null);
		g.drawRect(getWidth()/6, getHeight()/5, getHeight()/2, getHeight()/2);
		g.drawImage(images[dice2-1], getWidth()/2, getHeight()/5, getHeight()/2, getHeight()/2, null);
		g.drawRect(getWidth()/2, getHeight()/5, getHeight()/2, getHeight()/2);

		g.setColor(Color.BLACK);
		g.fillRect(getWidth()/5, 6*(getHeight()/8), getWidth()/2, getHeight()/4);
		g.setColor(Color.WHITE);
		g.drawString("Pass Dice (End Turn)", getWidth()/4, 7*(getHeight()/8));
		if(hasRolled == false) {
			g.drawString("(Need to roll)",getWidth()/15, getHeight()/12);
			g.drawString("Click to Roll Dice", getWidth()/3, getHeight()/8);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		//Pass the dice

		if(BoardPanel.isBuildingCity() ||BoardPanel.isBuildingSettlement() || BoardPanel.isBuildingCity() || GameFrame.startOfGame || GameFrame.lockAllButtons){

		}
		else if(GameFrame.hasRolled && e.getX() >= getWidth()/5 && e.getX() <= getWidth()/5 + getWidth()/2 && e.getY() >= 6*(getHeight()/8) && e.getY() <=6*(getHeight()/8) + getHeight()/4 && hasRolled == true) {
			GameFrame.resetTurnOrder();
			GameFrame.EndTurn();
			hasRolled = false;
		}
		else if(hasRolled == false){
			dices.rollDice();
			GameFrame.distribution(dices.getTotal());
			if(dices.getTotal() == 7) {
				for(Player player: GameFrame.players) {
					if(player.getHand().getSizeOfResources() > 7) {
						GameFrame.numOfDiscardPanels++;
						DiscardFrame df = new DiscardFrame("A 7 was Rolled, Discard!", player);
					}
				}
				BoardPanel.robberActive = true;
			}
			hasRolled = true;
			GameFrame.hasRolled = true;
		}
		repaint();
		GameFrame.repaintAllPanels();
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
