import java.awt.*;
import javax.swing.*;

public class PlayerTradeFrame extends JFrame{
    private static final int WIDTH = 725;
    private static final int HEIGHT = 900;
    public PlayerTradeFrame(String title, PlayerPanel p) {
        super(title);
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        add(new PlayerTradePanel(this, p));
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
