import javax.swing.JFrame;

public class InstructionFrame extends JFrame{
	private static int WIDTH = 900;
	private static int HEIGHT = 1100;
	public InstructionFrame(String t, PlayerPanel p) {
		super(t);
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		add(new InstructionPanel(this,p));
		setVisible(true);
	}
}
