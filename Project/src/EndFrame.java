import java.awt.*;
import javax.swing.*;

public class EndFrame extends JFrame{
	private static final int WIDTH = 900;
	private static final int HEIGHT = 1500;
	public EndFrame(String title) {
		super(title);
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new EndPanel(this, "Red"));
		setVisible(true);
	}
}
