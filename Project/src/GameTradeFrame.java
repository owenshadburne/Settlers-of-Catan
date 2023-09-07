import java.awt.*;
import javax.swing.*;

public class GameTradeFrame extends JFrame{
    private static final int WIDTH = 725;
    private static final int HEIGHT = 900;
    public GameTradeFrame(String title) {
        super("GameTradeFrame");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        add(new GameTradePanel(this, GameFrame.playerHand));
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
