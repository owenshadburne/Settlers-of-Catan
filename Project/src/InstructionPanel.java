import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.event.MouseInputListener;
import java.awt.*;
public class InstructionPanel extends JPanel implements MouseListener{
	private ArrayList<BufferedImage> pages;
	private int index;
	private InstructionFrame f;
	private PlayerPanel p;
	public InstructionPanel(InstructionFrame fr, PlayerPanel p) {
		pages = new ArrayList<BufferedImage>();
		populate();
		index = 0;
		f = fr;
		this.p = p;
		addMouseListener(this);
	}
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
		g.drawImage(pages.get(index), 0,0,getWidth(), getHeight()/10 *8,null);
		g.drawRect(getWidth()/10, getHeight(), getWidth()/10, getWidth()/10);
			g.setColor(new Color(183,18,18));
			g.fillRect(0,getHeight()/10 *8,getWidth()/2, getHeight()/10 *2);
			g.setColor(new Color(51,53,7));
			g.drawRect(0,getHeight()/10 *8,getWidth()/2, getHeight()/10 *2);
		
	
			

			g.setColor(new Color(183,18,18));
			g.fillRect(getWidth()/2, (getHeight()/10 * 8),getWidth()/2, getHeight()/10 *2);
			g.setColor(new Color(51,53,7));
			g.drawRect(getWidth()/2, (getHeight()/10 * 8),getWidth()/2, getHeight()/10 *2);
			
			Font myFont = new Font("Georgia", Font.BOLD, getWidth()/20);
	        g.setColor(Color.WHITE);
	        g.setFont(myFont);
	        g.drawString("Next", getWidth()/6 *4, getHeight()/10 *9);
	        g.drawString("Previous", getWidth()/8 *1, getHeight()/10 *9);
	        
	        //CANCEL
	        g.drawRect(getWidth()/7 * 6, 0, getWidth()/7, getHeight()/7);
	        g.drawLine(getWidth()/7 * 6, 0,getWidth(), getHeight()/7);
	        g.drawLine(getWidth(), 0,getWidth()/7 *6, getHeight()/7);
	}
	public void populate() {
		String str = "000";
		for(int i = 1; i <= 16; i++) {
			try {
				System.out.println(i);
				if(i < 10)
					pages.add(ImageIO.read(InstructionPanel.class.getResource("/Images/000" + i +".jpg")));
				else {
					pages.add(ImageIO.read(InstructionPanel.class.getResource("/Images/00" + i +".jpg")));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(index != 0 && x > 0 && x < getWidth()/2 && y > getHeight()/10 *8 && y < getHeight()) {
			System.out.print("preve");
			index--;
		}
		else if(index != 15 && x > getWidth()/2 && x < getWidth() && y > getHeight()/10 *8 && y < getHeight()) {
			System.out.print("next");
			index++;
		} 
		//(getWidth()/7 * 6, 0, getWidth()/7, getHeight()/7
		System.out.println("Clicked " +x+" " +y );
		System.out.println(getWidth()/7 *6+ " " +getHeight()/7);
		if(x > getWidth()/7 *6 && x < getWidth() && y > 0 && y < getHeight()/7) {
			p.setDisplayingIn(false);
			f.dispose();
		}
		System.out.println("Clicked");
		repaint();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
