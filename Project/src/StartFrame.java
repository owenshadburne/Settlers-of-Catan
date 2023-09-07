import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class StartFrame extends JFrame {
	private static final int WIDTH = 900;
	private static final int HEIGHT = 900;
	public StartFrame(String str) {
		super(str);
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
