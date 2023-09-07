import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
public class EndPanel extends JPanel{
	private JFrame frame;
	private String player;
	public EndPanel(JFrame frame, String str) {
		this.frame = frame;
		player = str;
	}
	public void paint(Graphics g) {

		BufferedImage star = null;
		try {
			star = ImageIO.read(EndPanel.class.getResource("/Images/your did it.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(star,0,0,getWidth(), getHeight(), null);
		JButton begin = new JButton("Play Again!");
		JButton end = new JButton("Quit?");

		g.drawString("Congrats " + player + ", you won the game!",getWidth()/2, getHeight()/2);
		add(begin);
		add(end);
		
		begin.setBounds(2*(getWidth()/8), 3*(getHeight()/4), getWidth()/9, getHeight()/20);
		end.setBounds(4*(getWidth()/8), 3*(getHeight()/4), getWidth()/9, getHeight()/20);
		
		begin.addActionListener(e -> {
			StartFrame f = new StartFrame("Start");
			frame.dispose();
		});
		
		end.addActionListener(e -> {
			frame.dispose();
		});
	}
}
