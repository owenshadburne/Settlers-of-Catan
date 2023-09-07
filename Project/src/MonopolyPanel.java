import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class MonopolyPanel extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    private static JButton enter;
    private static JLabel instructions;
    private String resource;
    private BufferedImage bg;
    public MonopolyPanel(String theT) throws IOException {

        super(theT);
        setSize(WIDTH,HEIGHT);
        setLayout(null);
        Container content = getContentPane();
        //makes screen be in middle
        setLocationRelativeTo(null);
        setResizable(false);


        JPanel monPan = new JPanel(null);

        monPan.setSize(500, 400);
        monPan.setLocation(0,0);
        monPan.setVisible(true);
        monPan.setBackground(new Color(128, 0, 0));


        instructions = new JLabel("Select Desired Resource");
        instructions.setBounds(160,125,400,20);
        instructions.setFont(new Font("Georgia", Font.PLAIN, 18));
        instructions.setForeground(Color.white);
        monPan.add(instructions);

        String[] choices = {"WOOD", "WHEAT", "ORE", "CLAY", "WOOL"};
        final JComboBox<String> first = new JComboBox<String>(choices);
        first.setBounds(200,180,100,20);
        first.setBackground(Color.white);
        monPan.add(first);


        enter = new JButton("Enter");
        enter.setBounds(200,240,100, 20);
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                resource = (String)first.getSelectedItem();
                Resource choice = determineResource(resource); //this will give you the Resource the player picked
                Player currentPlayer = GameFrame.currentPlayer();
                Hand currentHand = currentPlayer.getHand();
                ArrayList<Resource> stolen = new ArrayList<>();

                for(Player player: GameFrame.players) {
                    if(player != currentPlayer) {
                        Hand hand = player.getHand();
                        int total = hand.getCount(choice);
                        for(int i = 0; i < total; i++) {
                            stolen.add(hand.remove(choice));
                        }
                    }
                }
                for(Resource r: stolen) {
                    currentHand.add(r);
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
