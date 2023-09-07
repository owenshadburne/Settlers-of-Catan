import java.awt.*;
import javax.swing.*;

public class DiscardFrame extends JFrame{
    private static final int WIDTH = 725;
    private static final int HEIGHT = 600;
    public DiscardFrame(String title, Player player) {
        super(title);
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        add(new DiscardPanel(this, player));
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
