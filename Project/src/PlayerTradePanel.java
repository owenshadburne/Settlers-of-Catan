import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class PlayerTradePanel extends JPanel implements MouseListener{
    private JFrame frame;

    private BufferedImage clayCard, oreCard, wheatCard, woodCard, woolCard;
    public boolean cover;
    private PlayerPanel p;
    private static int clayWanted, oreWanted, wheatWanted, woodWanted, woolWanted, clayDesired, oreDesired, wheatDesired,
            woolDesired, woodDesired;

    private JTextField clay, ore, wheat, wood, wool, desClay, desOre, desWheat, desWool, desWood;
    private Player player;
    private int numberOfTraders, disposeCount;
    private ArrayList<Player> traders;


    public PlayerTradePanel(JFrame frame, PlayerPanel p) {
        this.frame = frame;
        //player = str;
        this.setLayout(null);
        player = GameFrame.currentPlayer();
        numberOfTraders = (GameFrame.getPlayers().length) - 1;
        traders = new ArrayList<>();
        disposeCount = 0;

        for(int i = 0; i < GameFrame.getPlayers().length; i++){

            if(GameFrame.getPlayers()[i] != player){
                traders.add(GameFrame.getPlayers()[i]);
            }

        }


        cover = false;
        this.p = p;
        woodCard = ImageStruct.woodCard;
        oreCard = ImageStruct.oreCard;
        wheatCard = ImageStruct.wheatCard;
        clayCard = ImageStruct.clayCard;
        woolCard = ImageStruct.woolCard;
        addMouseListener(this);
    }



    public void paint(Graphics g) {
        if(!cover) {
            g.setColor(new Color(128, 0, 0));
            g.fillRect(0,0, this.getWidth(), this.getHeight());

            setUp(g, 145, 285, 100, "Pick Resources to Give");
            setUp(g, 395, 545, 370, "Pick Resources to Gain");


            clay = new JTextField("0");
            add(clay);
            clay.setBounds(90, 285, 70, 20);
            clay.setBackground(Color.white);


            ore = new JTextField("0");
            add(ore);
            ore.setBounds(205, 285, 70, 20);
            ore.setBackground(Color.white);



            wheat = new JTextField("0");
            add(wheat);
            wheat.setBounds(320, 285, 70, 20);
            wheat.setBackground(Color.white);



            wool = new JTextField("0");
            add(wool);
            wool.setBounds(435, 285, 70, 20);
            wool.setBackground(Color.white);


            wood = new JTextField("0");
            add(wood);
            wood.setBounds(550, 285, 70, 20);
            wood.setBackground(Color.white);



            desClay = new JTextField("0");
            add(desClay);
            desClay.setBounds(90, 545, 70, 20);
            desClay.setBackground(Color.white);



            desOre = new JTextField("0");
            add(desOre);
            desOre.setBounds(205, 545, 70, 20);
            desOre.setBackground(Color.white);



            desWheat = new JTextField("0");
            add(desWheat);
            desWheat.setBounds(320, 545, 70, 20);
            desWheat.setBackground(Color.white);


            desWool = new JTextField("0");
            add(desWool);
            desWool.setBounds(435, 545, 70, 20);
            desWool.setBackground(Color.white);



            desWood = new JTextField("0");
            add(desWood);
            desWood.setBounds(550, 545, 70, 20);
            desWood.setBackground(Color.white);


            JButton enter = new JButton("Trade");
            add(enter);
            enter.setBounds(310, 630, 80, 30);
            enter.addActionListener(e -> {

                clayWanted = Integer.parseInt(clay.getText());

                oreWanted = Integer.parseInt(ore.getText());

                wheatWanted = Integer.parseInt(wheat.getText());

                woolWanted = Integer.parseInt(wool.getText());

                woodWanted = Integer.parseInt(wood.getText());

                clayDesired = Integer.parseInt(desClay.getText());

                oreDesired = Integer.parseInt(desOre.getText());

                wheatDesired = Integer.parseInt(desWheat.getText());

                woolDesired = Integer.parseInt(desWool.getText());

                woodDesired = Integer.parseInt(desWood.getText());



                cover = true;
                enter.setVisible(false);
                clay.setVisible(false);
                ore.setVisible(false);
                wheat.setVisible(false);
                wool.setVisible(false);
                wood.setVisible(false);
                desClay.setVisible(false);
                desOre.setVisible(false);
                desWheat.setVisible(false);
                desWood.setVisible(false);
                desWool.setVisible(false);

                repaint();

            });
        }
        else{
            g.setColor(new Color(128, 0, 0));
            g.fillRect(0,0, this.getWidth(), this.getHeight());
            endTrade(g);

        }


    }
    public void secCards(Graphics g, int heightOfCards){
        int cardWidth = 90;
        int cardHeight = 133;
        g.setColor(Color.white);
        g.setFont(new Font("Georgia", Font.PLAIN, 20));

        if(heightOfCards == 180) {
            if(clayWanted == 0) {
                g.drawRect(80, heightOfCards,cardWidth,cardHeight);
            }
            else{
                g.drawImage(clayCard, 80, heightOfCards, cardWidth, cardHeight, null);
                g.drawString(Integer.toString(clayWanted), 120, heightOfCards + 153);
            }

            if(oreWanted==0){
                g.drawRect(195, heightOfCards,cardWidth,cardHeight);
            }
            else {
                g.drawImage(oreCard, 195, heightOfCards, cardWidth, cardHeight, null);
                g.drawString(Integer.toString(oreWanted), 235, heightOfCards + 153);
            }

            if(wheatWanted==0){
                g.drawRect(310, heightOfCards,cardWidth,cardHeight);
            }
            else {
                g.drawImage(wheatCard, 310, heightOfCards, cardWidth, cardHeight, null);
                g.drawString(Integer.toString(wheatWanted), 350, heightOfCards + 153);
            }

            if(woolWanted ==0){
                g.drawRect(425, heightOfCards,cardWidth,cardHeight);
            }
            else {
                g.drawImage(woolCard, 425, heightOfCards, cardWidth, cardHeight, null);
                g.drawString(Integer.toString(woolWanted), 465, heightOfCards + 153);
            }

            if(woodWanted == 0){
                g.drawRect(540, heightOfCards,cardWidth,cardHeight);
            }
            else {
                g.drawImage(woodCard, 540, heightOfCards, cardWidth, cardHeight, null);
                g.drawString(Integer.toString(woodWanted), 580, heightOfCards + 153);
            }
        }
        else{
            if(clayDesired == 0){
                g.drawRect(80, heightOfCards,cardWidth,cardHeight);
            }
            else {
                g.drawImage(clayCard, 80, heightOfCards, cardWidth, cardHeight, null);
                g.drawString(Integer.toString(clayDesired), 120, heightOfCards + 153);
            }

            if(oreDesired == 0){
                g.drawRect(195, heightOfCards,cardWidth,cardHeight);
            }
            else {
                g.drawImage(oreCard, 195, heightOfCards, cardWidth, cardHeight, null);
                g.drawString(Integer.toString(oreDesired), 235, heightOfCards + 153);
            }

            if(wheatDesired == 0){
                g.drawRect(310, heightOfCards,cardWidth,cardHeight);
            }
            else {
                g.drawImage(wheatCard, 310, heightOfCards, cardWidth, cardHeight, null);
                g.drawString(Integer.toString(wheatDesired), 350, heightOfCards + 153);
            }

            if(woolDesired==0){
                g.drawRect(425, heightOfCards,cardWidth,cardHeight);
            }
            else {
                g.drawImage(woolCard, 425, heightOfCards, cardWidth, cardHeight, null);
                g.drawString(Integer.toString(woolDesired), 465, heightOfCards + 153);
            }

            if(woodDesired==0){
                g.drawRect(540, heightOfCards,cardWidth,cardHeight);
            }
            else {
                g.drawImage(woodCard, 540, heightOfCards, cardWidth, cardHeight, null);
                g.drawString(Integer.toString(woodDesired), 580, heightOfCards + 153);
            }

        }
    }
    public void setUp(Graphics g, int heightOfCards, int heightOfTxtBox, int heightOfText, String text){
        g.setColor(Color.white);
        g.setFont(new Font("Georgia", Font.PLAIN, 20));
        g.drawString(text,250, heightOfText );

        int cardWidth = 90;
        int cardHeight = 133;
        g.drawImage(clayCard, 80, heightOfCards, cardWidth, cardHeight, null);
        g.drawImage(oreCard, 195, heightOfCards, cardWidth, cardHeight, null);
        g.drawImage(wheatCard, 310, heightOfCards, cardWidth, cardHeight, null);
        g.drawImage(woolCard, 425, heightOfCards, cardWidth, cardHeight, null);
        g.drawImage(woodCard, 540, heightOfCards, cardWidth, cardHeight, null);

    }

    public void endTrade(Graphics g) {

        g.setColor(new Color(128, 0, 0));
        g.fillRect(0,0, this.getWidth(), this.getHeight());
        makeButtons(g, numberOfTraders);

    }

    public void makeButtons(Graphics g, int num){
        g.setColor(Color.white);
        g.setFont(new Font("Georgia", Font.PLAIN, 20));

        secCards(g,180);
        secCards(g,375);

        if(num == 1){
            g.drawString(traders.get(0).toString(),20, 50 );
            g.drawRect(100,25, 100,30);
            g.drawString("See Hand", 108, 50);
            JButton accept = new JButton("Accept");
            add(accept);
            accept.setBackground(new Color(120, 180, 107, 255));
            accept.setBounds(20, 70, 80,30);
            accept.addActionListener(e -> {

                Player trad = traders.get(0);
                boolean whatDo = commenceTrade(trad, player);

                if(whatDo){
                    p.setTrading(false);
                    p.repaint();
                    GameFrame.lockAllButtons = false;
                    frame.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                    accept.setBackground(new Color(255, 103, 106));
                }

            });

            JButton decline = new JButton("Decline");
            add(decline);
            decline.setBackground(new Color(255, 103, 106));
            decline.setBounds(20, 70+50, 80,30);
            decline.addActionListener(e -> {

                //frame.dispose();
                disposeCount++;
                disposeCheck();
                decline.setEnabled(false);
                accept.setEnabled(false);
                repaint();
            });
        }
        if(num == 2){

            //player 1
            g.drawString(traders.get(0).toString(),20, 50 );
            g.drawString(traders.get(0).toString(),20, 50 );
            g.drawRect(100,25, 100,30);
            g.drawString("See Hand", 108, 50);
            JButton accept = new JButton("Accept");
            add(accept);
            accept.setBackground(new Color(120, 180, 107, 255));
            accept.setBounds(20, 70, 80,30);
            accept.addActionListener(e -> {
                Player trad = traders.get(0);

                boolean whatDo = commenceTrade(trad, player);

                if(whatDo){
                    p.setTrading(false);
                    p.repaint();
                    GameFrame.lockAllButtons = false;
                    frame.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                    accept.setBackground(new Color(255, 103, 106));
                }



            });

            JButton decline = new JButton("Decline");
            add(decline);
            decline.setBackground(new Color(255, 103, 106));
            decline.setBounds(20, 70+50, 80,30);
            decline.addActionListener(e -> {

                //frame.dispose();
                disposeCount++;
                disposeCheck();
                decline.setEnabled(false);
                accept.setEnabled(false);

                repaint();
            });
//for secondPlayer
            g.drawString(traders.get(1).toString(),600, 50 );
            g.drawRect(490,25, 100,30);
            g.drawString("See Hand", 498, 50);
            JButton accept2 = new JButton("Accept");
            add(accept2);
            accept2.setBackground(new Color(120, 180, 107, 255));
            accept2.setBounds(600, 70, 80,30);
            accept2.addActionListener(e -> {
                Player trad = traders.get(1);
                boolean whatDo = commenceTrade(trad, player);

                if(whatDo){
                    p.setTrading(false);
                    p.repaint();
                    GameFrame.lockAllButtons = false;
                    frame.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                    accept2.setBackground(new Color(255, 103, 106));
                }

            });

            JButton decline2 = new JButton("Decline");
            add(decline2);
            decline2.setBackground(new Color(255, 103, 106));
            decline2.setBounds(600, 70+50, 80,30);
            decline2.addActionListener(e -> {

                //frame.dispose();
                disposeCount++;
                disposeCheck();
                decline2.setEnabled(false);
                accept2.setEnabled(false);
                repaint();
            });


        }
        if(num == 3){
            //player 1
            g.drawString(traders.get(0).toString(),20, 50 );
            g.drawRect(100,25, 100,30);
            g.drawString("See Hand", 108, 50);
            JButton accept = new JButton("Accept");
            add(accept);
            accept.setBackground(new Color(120, 180, 107, 255));
            accept.setBounds(20, 70, 80,30);
            accept.addActionListener(e -> {

                Player trad = traders.get(0);
                boolean whatDo = commenceTrade(trad, player);

                if(whatDo){
                    p.setTrading(false);
                    p.repaint();
                    GameFrame.lockAllButtons = false;
                    frame.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                    accept.setBackground(new Color(255, 103, 106));
                }

            });

            JButton decline = new JButton("Decline");
            add(decline);
            decline.setBackground(new Color(255, 103, 106));
            decline.setBounds(20, 70+50, 80,30);
            decline.addActionListener(e -> {

                //frame.dispose();
                disposeCount++;
                disposeCheck();
                decline.setEnabled(false);
                accept.setEnabled(false);
                repaint();
            });
//for secondPlayer
            g.drawString(traders.get(1).toString(),600, 50 );
            g.drawRect(490,25, 100,30);
            g.drawString("See Hand", 498, 50);
            JButton accept2 = new JButton("Accept");
            add(accept2);
            accept2.setBackground(new Color(120, 180, 107, 255));
            accept2.setBounds(600, 70, 80,30);
            accept2.addActionListener(e -> {

                Player trad = traders.get(1);
                boolean whatDo = commenceTrade(trad, player);

                if(whatDo){
                    p.setTrading(false);
                    p.repaint();
                    GameFrame.lockAllButtons = false;
                    frame.dispose();
                }
                else{
                    //If can't do the trade
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                    accept2.setBackground(new Color(255, 103, 106));
                }

            });

            JButton decline2 = new JButton("Decline");
            add(decline2);
            decline2.setBackground(new Color(255, 103, 106));
            decline2.setBounds(600, 70+50, 80,30);
            decline2.addActionListener(e -> {

                //frame.dispose();
                disposeCount++;
                disposeCheck();
                decline2.setEnabled(false);
                accept2.setEnabled(false);
                repaint();
            });
//third player
            g.drawString(traders.get(2).toString(),20, 580 );
            g.drawRect(100,560, 100,30);
            g.drawString("See Hand", 108, 580);
            JButton accept3 = new JButton("Accept");
            add(accept3);
            accept3.setBackground(new Color(120, 180, 107, 255));
            accept3.setBounds(20, 600, 80,30);
            accept3.addActionListener(e -> {

                Player trad = traders.get(2);
                boolean whatDo = commenceTrade(trad, player);

                if(whatDo){
                    p.setTrading(false);
                    p.repaint();
                    GameFrame.lockAllButtons = false;
                    frame.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                    accept3.setBackground(new Color(255, 103, 106));
                }
            });

            JButton decline3 = new JButton("Decline");
            add(decline3);
            decline3.setBackground(new Color(255, 103, 106));
            decline3.setBounds(20, 600+50, 80,30);
            decline3.addActionListener(e -> {

                disposeCount++;
                disposeCheck();
                decline3.setEnabled(false);
                accept3.setEnabled(false);
                repaint();
            });

        }


    }

    public boolean commenceTrade(Player reciever, Player giver){

        boolean tradeWorked = true;

        boolean firstCheck = checkGiver(giver);
        boolean secondCheck = checkReciever(reciever);

        if(firstCheck && secondCheck){

            for(int i = 0; i < clayDesired; i++){
                giver.getHand().add(Resource.CLAY);
                reciever.getHand().remove(Resource.CLAY);
            }
            for(int i = 0; i < oreDesired; i++){
                giver.getHand().add(Resource.ORE);
                reciever.getHand().remove(Resource.ORE);
            }
            for(int i = 0; i < wheatDesired; i++){
                giver.getHand().add(Resource.WHEAT);
                reciever.getHand().remove(Resource.WHEAT);
            }
            for(int i = 0; i < woodDesired; i++){
                giver.getHand().add(Resource.WOOD);
                reciever.getHand().remove(Resource.WOOD);
            }
            for(int i = 0; i < woolDesired; i++){
                giver.getHand().add(Resource.WOOL);
                reciever.getHand().remove(Resource.WOOL);
            }

            for(int i = 0; i < clayWanted; i++){
                reciever.getHand().add(Resource.CLAY);
                giver.getHand().remove(Resource.CLAY);
            }
            for(int i = 0; i < oreWanted; i++){
                reciever.getHand().add(Resource.ORE);
                giver.getHand().remove(Resource.ORE);
            }
            for(int i = 0; i < wheatWanted; i++){
                reciever.getHand().add(Resource.WHEAT);
                giver.getHand().remove(Resource.WHEAT);
            }
            for(int i = 0; i < woodWanted; i++){
                reciever.getHand().add(Resource.WOOD);
                giver.getHand().remove(Resource.WOOD);
            }
            for(int i = 0; i < woolWanted; i++){
                reciever.getHand().add(Resource.WOOL);
                giver.getHand().remove(Resource.WOOL);
            }


        }
        else{
            tradeWorked = false;
            if(firstCheck == false) {
                System.out.println("Giver doesn't have cards");
                JOptionPane.showMessageDialog(frame,
                        "Current Player lacks the neccessary cards to complete this trade.",
                        "Slight Error",
                        JOptionPane.PLAIN_MESSAGE);
            }
            else if(secondCheck == false){
                System.out.println("Receiver doesn't have cards");
            }
            else{
                System.out.println("Receiver and giver don't have cards");
                JOptionPane.showMessageDialog(frame,
                        "Current Player and Yourself lack the neccessary cards to complete this trade.",
                        "Slight Error",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }

        return tradeWorked;
    }

    public boolean checkGiver(Player giver){
        //clayWanted, oreWanted, wheatWanted, woodWanted, woolWanted,
        if(clayWanted != 0){
            if(!(giver.getHand().getCount(Resource.CLAY) >= clayWanted)){
                return false;
            }
        }
        if(oreWanted != 0){
            if(!(giver.getHand().getCount(Resource.ORE) >= oreWanted)){
                return false;
            }
        }
        if(wheatWanted != 0){
            if(!(giver.getHand().getCount(Resource.WHEAT) >= wheatWanted)){
                return false;
            }
        }
        if(woodWanted != 0){
            if(!(giver.getHand().getCount(Resource.WOOD) >= woodWanted)){
                return false;
            }
        }
        if(woolWanted != 0){
            if(!(giver.getHand().getCount(Resource.WOOL) >= woolWanted)){
                return false;
            }
        }

        return true;

    }

    public boolean checkReciever(Player reciever){


        if(clayDesired != 0){
            if(!(reciever.getHand().getCount(Resource.CLAY) >= clayDesired)){
                return false;
            }
        }
        if(oreDesired != 0){
            if(!(reciever.getHand().getCount(Resource.ORE) >= oreDesired)){
                return false;
            }
        }
        if(wheatDesired != 0){
            if(!(reciever.getHand().getCount(Resource.WHEAT) >= wheatDesired)){
                return false;
            }
        }
        if(woodDesired != 0){
            if(!(reciever.getHand().getCount(Resource.WOOD) >= woodDesired)){
                return false;
            }
        }
        if(woolDesired != 0){
            if(!(reciever.getHand().getCount(Resource.WOOL) >= woolDesired)){
                return false;
            }
        }

        return true;


    }

    public void disposeCheck(){
        if(disposeCount == numberOfTraders ){
            p.setTrading(false);
            p.repaint();
            GameFrame.lockAllButtons = false;
            frame.dispose();
        }
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

        //g.drawRect(100,25, 100,30);
// g.drawRect(490,25, 100,30);
        //g.drawRect(100,560, 100,30);
        int x = e.getX();
        int y = e.getY();
        if(x > 100 && x < 200 && y > 25 && y < 55) {
            String str1 = "Clay -" +traders.get(0).getHand().getResource().get(Resource.CLAY);
            String str2 = "Ore -"+traders.get(0).getHand().getResource().get(Resource.ORE);
            String str3 = "Wheat -"+traders.get(0).getHand().getResource().get(Resource.WHEAT);
            String str4 = "Wood -"+traders.get(0).getHand().getResource().get(Resource.WOOD);
            String str5 = "Wool -"+traders.get(0).getHand().getResource().get(Resource.WOOL);
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
            JOptionPane.showMessageDialog(this, panel2, traders.get(0).toString(),JOptionPane.DEFAULT_OPTION);
        } else if(x > 490 && x < 590 && y > 25 && y < 55) {
            String str1 = "Clay -" +traders.get(1).getHand().getResource().get(Resource.CLAY);
            String str2 = "Ore -"+traders.get(1).getHand().getResource().get(Resource.ORE);
            String str3 = "Wheat -"+traders.get(1).getHand().getResource().get(Resource.WHEAT);
            String str4 = "Wood -"+traders.get(1).getHand().getResource().get(Resource.WOOD);
            String str5 = "Wool -"+traders.get(1).getHand().getResource().get(Resource.WOOL);
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
            JOptionPane.showMessageDialog(this, panel2, traders.get(1).toString(),JOptionPane.DEFAULT_OPTION);
        } else if(x > 100 && x < 200 && y > 560 && y < 590) {
            String str1 = "Clay -" +traders.get(2).getHand().getResource().get(Resource.CLAY);
            String str2 = "Ore -"+traders.get(2).getHand().getResource().get(Resource.ORE);
            String str3 = "Wheat -"+traders.get(2).getHand().getResource().get(Resource.WHEAT);
            String str4 = "Wood -"+traders.get(2).getHand().getResource().get(Resource.WOOD);
            String str5 = "Wool -"+traders.get(2).getHand().getResource().get(Resource.WOOL);
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
            JOptionPane.showMessageDialog(this, panel2, traders.get(2).toString(),JOptionPane.DEFAULT_OPTION);
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
