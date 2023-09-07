
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
public class IntroductionPanel extends JFrame {

    private static final int WIDTH = 720;
    private static final int HEIGHT = 450;
    private static JButton startGame;
    private static JLabel title, instructNum;
    private int numberOfPlayers;
    private BufferedImage bg;
    public IntroductionPanel(String theT) throws IOException {

        super(theT);
        setSize(WIDTH,HEIGHT);
        setLayout(null);
        Container content = getContentPane();
        //makes screen be in middle
        setLocationRelativeTo(null);
        setResizable(false);




        JPanel introPanel = new JPanel(null) {
            ImageIcon image = new ImageIcon(getClass().getResource("Images/background.jpg"));

            public void paint(Graphics g) {
                g.drawImage(image.getImage(), 0, 0, null, null);
                super.paint(g);
            }
        };


        introPanel.setSize(1200, 1200);
        introPanel.setLocation(0,0);
        introPanel.setVisible(true);
        introPanel.setBackground(new Color(0,0,0,65));

        Color yellow = new Color(255,255,102);

        title = new JLabel("CATAN");
        title.setBounds(WIDTH/3-20,HEIGHT-400,800,80);
        title.setFont(new Font("Georgia", Font.PLAIN, 80));
        title.setForeground(yellow);
        introPanel.add(title);


        instructNum = new JLabel("Select Number of Players");
        instructNum.setBounds(265,175,200,20);
        instructNum.setFont(new Font("Georgia", Font.PLAIN, 15));
        instructNum.setForeground(Color.black);
        introPanel.add(instructNum);

        String[] choices = {"4", "3", "2"};
        final JComboBox<String> first = new JComboBox<String>(choices);
        first.setBounds(300,200,100,20);
        first.setBackground(Color.white);
        introPanel.add(first);



        startGame = new JButton("Start Game");
        startGame.setBounds(300,240,100, 20);
        startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = Integer.parseInt((String) first.getSelectedItem());
                introPanel.setVisible(false);
                
                GameFrame game = new GameFrame("Settlers of Catan", numberOfPlayers);
                dispose();


            }
        });
        introPanel.add(startGame);

        content.add(introPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        repaint();
    }
    
}
