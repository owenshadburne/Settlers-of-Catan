
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;

public class MainTradePanel extends JFrame {

    private static final int WIDTH = 725;
    private static final int HEIGHT = 900;
    private JFrame frame;

    public MainTradePanel(String theT) throws IOException {

        super(theT);
        frame = new JFrame();


        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(null);
        frame.setLocation(100, 100);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel firstTrade = new JPanel(null);
        frame.add(firstTrade);

        firstTrade.setSize(WIDTH, HEIGHT);
        firstTrade.setLocation(0,0);
        firstTrade.setVisible(true);
        firstTrade.setBackground(new Color(128, 0, 0));


        JLabel whichT = new JLabel("Pick Desired Trade Option");
        whichT.setBounds(230,180,800,80);
        whichT.setFont(new Font("Georgia", Font.PLAIN, 20));
        whichT.setForeground(Color.white);
        firstTrade.add(whichT);

        JButton tGame = new JButton("Trade With Game/Port");
        tGame.setBounds(80,300,250, 100);
        tGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                firstTrade.setVisible(false);
//
//                try {
//                    TradeWithGamePanel GaPa = new TradeWithGamePanel(frame);
//                    frame.add(GaPa);
                GameTradeFrame g = new GameTradeFrame("Trade With Port/Game");
                GameFrame.lockAllButtons = false;
                frame.dispose();
//
//                } catch (IOException ex) {
//                    System.out.println("failed");
//                }


            }
        });
        firstTrade.add(tGame);

        JButton tPlayer = new JButton("Trade With Player");
        tPlayer.setBounds(400,300,250, 100);
        tPlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstTrade.setVisible(false);

                PlayerTradeFrame n = new PlayerTradeFrame("test", GameFrame.playerHand);
                GameFrame.lockAllButtons = false;
                frame.dispose();
            }
        });
        firstTrade.add(tPlayer);



        repaint();
    }

}
