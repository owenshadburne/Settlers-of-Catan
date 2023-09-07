import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DiscardPanel extends JPanel implements MouseListener{
    private JFrame frame;

    private BufferedImage clayCard, oreCard, wheatCard, woodCard, woolCard;
    private static int clayWanted, oreWanted, wheatWanted, woodWanted, woolWanted;
    private static int cl, or, wh, wd, wl;
    private String dis;

    private JTextField clay, ore, wheat, wood, wool;
    private Player player;
    private Hand hand;
    private int amountToDiscard;


    public DiscardPanel(JFrame frame, Player player) {
        this.frame = frame;
        this.setLayout(null);

        this.player = player;
        hand = player.getHand();

        woodCard = ImageStruct.woodCard;
        oreCard = ImageStruct.oreCard;
        wheatCard = ImageStruct.wheatCard;
        clayCard = ImageStruct.clayCard;
        woolCard = ImageStruct.woolCard;
        addMouseListener(this);

    }

    public void paint(Graphics g) {

        g.setColor(new Color(128, 0, 0));
        g.fillRect(0,0, this.getWidth(), this.getHeight());

        amountToDiscard = Math.abs(7 - player.getHand().getSizeOfResources());
        setUp(g, 200, 350, 140, "Enter Resources to Discard. " +
                "You need to discard " + amountToDiscard + " card" + ((amountToDiscard > 1) ? "s." : "."));

        clay = new JTextField("0");
        add(clay);
        clay.setBounds(90, 340, 70, 20);
        clay.setBackground(Color.white);


        ore = new JTextField("0");
        add(ore);
        ore.setBounds(205, 340, 70, 20);
        ore.setBackground(Color.white);



        wheat = new JTextField("0");
        add(wheat);
        wheat.setBounds(320, 340, 70, 20);
        wheat.setBackground(Color.white);



        wool = new JTextField("0");
        add(wool);
        wool.setBounds(435, 340, 70, 20);
        wool.setBackground(Color.white);


        wood = new JTextField("0");
        add(wood);
        wood.setBounds(550, 340, 70, 20);
        wood.setBackground(Color.white);

        g.drawString(player.toString(),20, 50 );
        g.drawRect(100,25, 100,30);
        g.drawString("See Hand", 108, 50);




        JButton enter = new JButton("Enter");
        add(enter);
        enter.setBounds(310, 470, 80, 30);
        enter.addActionListener(e -> {

            clayWanted = Integer.parseInt(clay.getText());

            oreWanted = Integer.parseInt(ore.getText());

            wheatWanted = Integer.parseInt(wheat.getText());

            woolWanted = Integer.parseInt(wool.getText());

            woodWanted = Integer.parseInt(wood.getText());

            int total = clayWanted + oreWanted + wheatWanted + woolWanted + woodWanted;

            or = hand.getCount(Resource.ORE);
            cl = hand.getCount(Resource.CLAY);
            wh = hand.getCount(Resource.WHEAT);
            wd = hand.getCount(Resource.WOOD);
            wl = hand.getCount(Resource.WOOL);

            boolean checkOre = hand.getCount(Resource.ORE) >= oreWanted;
            boolean checkClay = hand.getCount(Resource.CLAY) >= clayWanted;
            boolean checkWheat = hand.getCount(Resource.WHEAT) >= wheatWanted;
            boolean checkWood = hand.getCount(Resource.WOOD) >= woodWanted;
            boolean checkWool = hand.getCount(Resource.WOOL) >= woolWanted;
            boolean checkTotal = total >= amountToDiscard;

            if(checkOre && checkClay && checkWheat && checkWood && checkWool && checkTotal) {
                Deck deck = Deck.instance;
                System.out.println("worked");
                for(int x = 0; x < clayWanted; x++) {
                    deck.add(hand.remove(Resource.CLAY));
                }
                for(int x = 0; x < oreWanted; x++) {
                    deck.add(hand.remove(Resource.ORE));
                }
                for(int x = 0; x < wheatWanted; x++) {
                    deck.add(hand.remove(Resource.WHEAT));
                }
                for(int x = 0; x < woolWanted; x++) {
                    deck.add(hand.remove(Resource.WOOL));
                }
                for(int x = 0; x < woodWanted; x++) {
                    deck.add(hand.remove(Resource.WOOD));
                }
                GameFrame.discardPanelClosed();
                frame.dispose();
            }

            else{

                if(total < amountToDiscard) {
                    int howManyMore = amountToDiscard - total;
                    dis = "You need to discard " + howManyMore + " more cards";
                }
                else{
                    dis = "You have discarded more cards than you have";
                }

                JPanel disPan = new JPanel(new GridLayout(5, 5));
                disPan.add(new JLabel(dis));

                JPanel pan2 = new JPanel(new BorderLayout());
                pan2.add(disPan);
                JOptionPane.showMessageDialog(this, pan2, player.toString(),JOptionPane.DEFAULT_OPTION);

            }


        });
    }

    public void setUp(Graphics g, int heightOfCards, int heightOfTxtBox, int heightOfText, String text){
        g.setColor(Color.white);
        g.setFont(new Font("Georgia", Font.PLAIN, 20));
        g.drawString(text,120, heightOfText);

        int cardWidth = 90;
        int cardHeight = 133;
        g.drawImage(clayCard, 80, heightOfCards, cardWidth, cardHeight, null);
        g.drawImage(oreCard, 195, heightOfCards, cardWidth, cardHeight, null);
        g.drawImage(wheatCard, 310, heightOfCards, cardWidth, cardHeight, null);
        g.drawImage(woolCard, 425, heightOfCards, cardWidth, cardHeight, null);
        g.drawImage(woodCard, 540, heightOfCards, cardWidth, cardHeight, null);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        int x = e.getX();
        int y = e.getY();
        if(x > 100 && x < 200 && y > 25 && y < 55) {
            String str1 = "Clay -" +player.getHand().getResource().get(Resource.CLAY);
            String str2 = "Ore -"+player.getHand().getResource().get(Resource.ORE);
            String str3 = "Wheat -"+player.getHand().getResource().get(Resource.WHEAT);
            String str4 = "Wood -"+player.getHand().getResource().get(Resource.WOOD);
            String str5 = "Wool -"+player.getHand().getResource().get(Resource.WOOL);
            //JOptionPane.showMessageDialog(this, str1+ "\n" + str2 + "\n" + str3);
            ImageIcon icon =new ImageIcon(getClass().getResource("Images/CatanIcon.jpg"));

            JLabel label = new JLabel(icon);
            JPanel panel = new JPanel(new GridBagLayout());
            panel.add(label);
            JPanel textPanel = new JPanel(new GridLayout(5, 5));
            textPanel.add(new JLabel(str1));
            textPanel.add(new JLabel(str2 ));
            textPanel.add(new JLabel(str3));
            textPanel.add(new JLabel(str4 ));
            textPanel.add(new JLabel(str5));

            JPanel panel2 = new JPanel(new BorderLayout());
            panel2.add(textPanel);
            panel2.add(panel, BorderLayout.EAST);
            JOptionPane.showMessageDialog(this, panel2, player.toString(),JOptionPane.DEFAULT_OPTION);

        }
        System.out.println(e.getX() + " " + e.getY());

    }



    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }



    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }



    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }



    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }



}
