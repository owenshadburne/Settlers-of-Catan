
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;

public class YearOfPlentyPanel extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    private static JButton enter;
    private static JLabel instructions;
    private String resource, secResource;
    private Deck deck;

    public YearOfPlentyPanel(String theT, Deck d) throws IOException {
        super(theT);
        setSize(WIDTH,HEIGHT);
        setLayout(null);
        Container content = getContentPane();
        //makes screen be in middle
        setLocationRelativeTo(null);
        setResizable(false);

        deck = d;
        JPanel monPan = new JPanel(null);

        monPan.setSize(1200, 1200);
        monPan.setLocation(0,0);
        monPan.setVisible(true);
        monPan.setBackground(new Color(128, 0, 0));


        instructions = new JLabel("Select Desired Resources");
        instructions.setBounds(160,110,400,20);
        instructions.setFont(new Font("Georgia", Font.PLAIN, 18));
        instructions.setForeground(Color.white);
        monPan.add(instructions);

        String[] choices = {"WOOD", "WHEAT", "ORE", "CLAY", "WOOL"};
        final JComboBox<String> first = new JComboBox<String>(choices);
        first.setBounds(200,160,100,20);
        first.setBackground(Color.white);
        monPan.add(first);

        String[] secCho = {"WOOD", "WHEAT", "ORE", "CLAY", "WOOL"};
        final JComboBox<String> sec = new JComboBox<String>(secCho);
        sec.setBounds(200,195,100,20);
        sec.setBackground(Color.white);
        monPan.add(sec);


        enter = new JButton("Enter");
        enter.setBounds(200,240,100, 20);
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                resource = (String)first.getSelectedItem();
                secResource = (String) sec.getSelectedItem();

                Hand currentHand = GameFrame.currentPlayer().getHand();
                //Pop up to choose
                Resource choice = determineResource(resource);;
                Resource secondChoice =   determineResource(secResource);
                Resource removed = deck.remove(choice);
                if(removed != null) {
                    currentHand.add(removed);
                }

                Resource removed2 = deck.remove(secondChoice);
                if(removed2 != null) {
                    currentHand.add(removed2);
                }

                dispose();


            }
        });
        monPan.add(enter);

        content.add(monPan);

        setVisible(true);
    }

    public Resource determineResource(String s){

        if(s.equals("WOOL")){
            return Resource.WOOL;
        }
        else if(s.equals("WOOD")){
            return Resource.WOOD;
        }
        else if(s.equals("ORE")){
            return Resource.ORE;
        }
        else if(s.equals("CLAY")){
            return Resource.CLAY;
        }
        else if(s.equals("WHEAT")){
            return Resource.WHEAT;
        }
        else{
            return null;
        }

    }

}
