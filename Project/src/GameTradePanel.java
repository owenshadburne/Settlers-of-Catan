import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
public class GameTradePanel extends JPanel{
    private JFrame frame;

    private String desiredTrade, desiredResource;
    private JComboBox<String> first, sec;
    public GameTradePanel(JFrame frame, PlayerPanel p) {
        this.frame = frame;
        this.setLayout(null);

        JLabel whichT = new JLabel("Pick Desired Trade Option");
        whichT.setBounds(230, 180, 800, 80);
        whichT.setFont(new Font("Georgia", Font.PLAIN, 20));
        whichT.setForeground(Color.white);
        add(whichT);

        setBackground(new Color(128, 0, 0));

        ArrayList<Vertex> vertices = BoardSetup.getVertices();
        ArrayList<Port> ports = new ArrayList<>();
        for (Vertex vertex : vertices) {

            if (vertex.getSettlement() != null) {
                if (vertex.getSettlement().getColour() == GameFrame.currentPlayer().getColour()) {

                    if (vertex.getPort() != null) {
                        ports.add(vertex.getPort());
                    }

                }
            }

        }


        ArrayList<String> temp = new ArrayList<>();
        temp.add("Select Trade");
        temp.add("Wool 4:1");
        temp.add("Wood 4:1");
        temp.add("Ore 4:1");
        temp.add("Clay 4:1");
        temp.add("Wheat 4:1");

        for(int i = 0; i < ports.size(); i++){

            // WOOL/WOOD/ORE/CLAY/WHEAT/? + " " + firstRatio + "-> ?" + secondRatio
            System.out.println(ports.get(i).toString());
            if(ports.get(i).toString().equals("?" + " " + 3 + "-> ?" + 1)){
                temp.add("? (trade WOOL) " + 3 + "-> " +1);
                temp.add("? (trade WOOD) " + 3 + "-> " +1);
                temp.add("? (trade ORE) " + 3 + "-> " +1);
                temp.add("? (trade CLAY) " + 3 + "-> " +1);
                temp.add("? (trade WHEAT) " + 3 + "-> " +1);

            }
            else {
                temp.add(ports.get(i).toString());
            }
        }

        String[] choices = new String[temp.size()];
        for(int i = 0; i < choices.length; i++){
            choices[i] = temp.get(i);
        }

        first = new JComboBox<String>(choices);
        first.setBounds(240, 300, 200, 40);
        first.setBackground(Color.white);
        add(first);

        String[] want = {"Select Desired Resource","Wool", "Wood", "Ore", "Clay", "Wheat"};
        sec = new JComboBox<String>(want);
        sec.setBounds(240, 360, 200, 40);
        sec.setBackground(Color.white);
        add(sec);


        JButton submit = new JButton("Enter");
        submit.setBounds(300, 430, 100, 30);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p.setTrading(false);
                p.repaint();
                doTrade();
                setVisible(false);
                frame.dispose();
            }
        });
        add(submit);

        repaint();


    }

    public void doTrade(){
        desiredTrade = (String)first.getSelectedItem();
        desiredResource = (String)sec.getSelectedItem();
        if(desiredTrade.equals("Wool 4:1")){

            Player p = GameFrame.currentPlayer();
            if(p.getHand().getCount(Resource.WOOL) >= 4){
                p.getHand().remove(Resource.WOOL);
                p.getHand().remove(Resource.WOOL);
                p.getHand().remove(Resource.WOOL);
                p.getHand().remove(Resource.WOOL);

                if(determineResource(desiredResource) != null) {
                    p.getHand().add(determineResource(desiredResource));
                }
                else{
                    System.out.println("Trade Failed");
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                }


            }
            else{
                System.out.println("Trade Failed");
                JOptionPane.showMessageDialog(frame,
                        "You lack the neccessary cards to complete this trade.",
                        "Slight Error",
                        JOptionPane.PLAIN_MESSAGE);
            }

        }
        else if(desiredTrade.equals("Wood 4:1")){

            Player p = GameFrame.currentPlayer();
            if(p.getHand().getCount(Resource.WOOD) >= 4){
                p.getHand().remove(Resource.WOOD);
                p.getHand().remove(Resource.WOOD);
                p.getHand().remove(Resource.WOOD);
                p.getHand().remove(Resource.WOOD);

                if(determineResource(desiredResource) != null) {
                    p.getHand().add(determineResource(desiredResource));
                }
                else{
                    System.out.println("Trade Failed");
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
            else{
                System.out.println("Trade Failed");
                JOptionPane.showMessageDialog(frame,
                        "You lack the neccessary cards to complete this trade.",
                        "Slight Error",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }
        else if(desiredTrade.equals("Ore 4:1")){

            Player p = GameFrame.currentPlayer();
            if(p.getHand().getCount(Resource.ORE) >= 4){
                p.getHand().remove(Resource.ORE);
                p.getHand().remove(Resource.ORE);
                p.getHand().remove(Resource.ORE);
                p.getHand().remove(Resource.ORE);

                if(determineResource(desiredResource) != null) {
                    p.getHand().add(determineResource(desiredResource));
                }
                else{
                    System.out.println("Trade Failed");
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
            else{
                System.out.println("Trade Failed");
                JOptionPane.showMessageDialog(frame,
                        "You lack the neccessary cards to complete this trade.",
                        "Slight Error",
                        JOptionPane.PLAIN_MESSAGE);
            }

        }
        else if(desiredTrade.equals("Clay 4:1")){
            Player p = GameFrame.currentPlayer();
            if(p.getHand().getCount(Resource.CLAY) >= 4){
                p.getHand().remove(Resource.CLAY);
                p.getHand().remove(Resource.CLAY);
                p.getHand().remove(Resource.CLAY);
                p.getHand().remove(Resource.CLAY);

                if(determineResource(desiredResource) != null) {
                    p.getHand().add(determineResource(desiredResource));
                }
                else{
                    System.out.println("Trade Failed");
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
            else{
                System.out.println("Trade Failed");
                JOptionPane.showMessageDialog(frame,
                        "You lack the neccessary cards to complete this trade.",
                        "Slight Error",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }
        else if(desiredTrade.equals("Wheat 4:1")){
            Player p = GameFrame.currentPlayer();
            if(p.getHand().getCount(Resource.WHEAT) >= 4){
                p.getHand().remove(Resource.WHEAT);
                p.getHand().remove(Resource.WHEAT);
                p.getHand().remove(Resource.WHEAT);
                p.getHand().remove(Resource.WHEAT);

                if(determineResource(desiredResource) != null) {
                    p.getHand().add(determineResource(desiredResource));
                }
                else{
                    System.out.println("Trade Failed");
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }

            else{
                System.out.println("Trade Failed");
                JOptionPane.showMessageDialog(frame,
                        "You lack the neccessary cards to complete this trade.",
                        "Slight Error",
                        JOptionPane.PLAIN_MESSAGE);
            }

        }
        else if(desiredTrade.equals("WOOL " + 2 + "-> ?" + 1 )){

            //WOOL/WOOD/ORE/CLAY/WHEAT/? + " " + firstRatio + "-> ?" + secondRatio;
            //?3:1, wood 2:1, wool 2:1, clay 2:1, wheat 2:1, ore 2:1

            Player p = GameFrame.currentPlayer();
            if(p.getHand().getCount(Resource.WOOL) >= 2){
                p.getHand().remove(Resource.WOOL);
                p.getHand().remove(Resource.WOOL);

                if(determineResource(desiredResource) != null) {
                    p.getHand().add(determineResource(desiredResource));
                }
                else{
                    System.out.println("Trade Failed");
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }

            else{
                System.out.println("Trade Failed");
                JOptionPane.showMessageDialog(frame,
                        "You lack the neccessary cards to complete this trade.",
                        "Slight Error",
                        JOptionPane.PLAIN_MESSAGE);
            }

        }
        else if(desiredTrade.equals("WOOD " + 2 + "-> ?" + 1 )){

            //WOOD/ORE/CLAY/WHEAT/? + " " + firstRatio + "-> ?" + secondRatio;
            //?3:1, wood 2:1, clay 2:1, wheat 2:1, ore 2:1

            Player p = GameFrame.currentPlayer();
            if(p.getHand().getCount(Resource.WOOD) >= 2){
                p.getHand().remove(Resource.WOOD);
                p.getHand().remove(Resource.WOOD);

                if(determineResource(desiredResource) != null) {
                    p.getHand().add(determineResource(desiredResource));
                }
                else{
                    System.out.println("Trade Failed");
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }

            else{
                System.out.println("Trade Failed");
                JOptionPane.showMessageDialog(frame,
                        "You lack the neccessary cards to complete this trade.",
                        "Slight Error",
                        JOptionPane.PLAIN_MESSAGE);
            }

        }
        else if(desiredTrade.equals("ORE " + 2 + "-> ?" + 1 )){

            //CLAY/WHEAT/? + " " + firstRatio + "-> ?" + secondRatio;
            //?3:1, wood 2:1, clay 2:1, wheat 2:1, ore 2:1

            Player p = GameFrame.currentPlayer();
            if(p.getHand().getCount(Resource.ORE) >= 2){
                p.getHand().remove(Resource.ORE);
                p.getHand().remove(Resource.ORE);

                if(determineResource(desiredResource) != null) {
                    p.getHand().add(determineResource(desiredResource));
                }
                else{
                    System.out.println("Trade Failed");
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }

            else{
                System.out.println("Trade Failed");
                JOptionPane.showMessageDialog(frame,
                        "You lack the neccessary cards to complete this trade.",
                        "Slight Error",
                        JOptionPane.PLAIN_MESSAGE);
            }

        }
        else if(desiredTrade.equals("CLAY " + 2 + "-> ?" + 1 )){

            //CLAY/WHEAT/? + " " + firstRatio + "-> ?" + secondRatio;
            //?3:1,  clay 2:1, wheat 2:1,

            Player p = GameFrame.currentPlayer();
            if(p.getHand().getCount(Resource.CLAY) >= 2){
                p.getHand().remove(Resource.CLAY);
                p.getHand().remove(Resource.CLAY);

                if(determineResource(desiredResource) != null) {
                    p.getHand().add(determineResource(desiredResource));
                }
                else{
                    System.out.println("Trade Failed");
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }

            else{
                System.out.println("Trade Failed");
                JOptionPane.showMessageDialog(frame,
                        "You lack the neccessary cards to complete this trade.",
                        "Slight Error",
                        JOptionPane.PLAIN_MESSAGE);
            }

        }
        else if(desiredTrade.equals("WHEAT " + 2 + "-> ?" + 1 )){

            //WHEAT/? + " " + firstRatio + "-> ?" + secondRatio;
            //?3:1, wood 2:1, clay 2:1, wheat 2:1, ore 2:1

            Player p = GameFrame.currentPlayer();
            if(p.getHand().getCount(Resource.WHEAT) >= 2){
                p.getHand().remove(Resource.WHEAT);
                p.getHand().remove(Resource.WHEAT);

                if(determineResource(desiredResource) != null) {
                    p.getHand().add(determineResource(desiredResource));
                }
                else{
                    System.out.println("Trade Failed");
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }

            else{
                JOptionPane.showMessageDialog(frame,
                        "You lack the neccessary cards to complete this trade.",
                        "Slight Error",
                        JOptionPane.PLAIN_MESSAGE);
                System.out.println("Trade Failed");

            }

        }
        else if(desiredTrade.equals("? (trade WOOL) " + 3 + "-> " + 1 )){

            //WOOL/WOOD/ORE/CLAY/WHEAT/? + " " + firstRatio + "-> ?" + secondRatio;
            //?3:1, wood 2:1, wool 2:1, clay 2:1, wheat 2:1, ore 2:1

            Player p = GameFrame.currentPlayer();
            if(p.getHand().getCount(Resource.WOOL) >= 3){
                p.getHand().remove(Resource.WOOL);
                p.getHand().remove(Resource.WOOL);
                p.getHand().remove(Resource.WOOL);

                if(determineResource(desiredResource) != null) {
                    p.getHand().add(determineResource(desiredResource));
                }
                else{
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                    System.out.println("Trade Failed");
                }
            }

            else{
                JOptionPane.showMessageDialog(frame,
                        "You lack the neccessary cards to complete this trade.",
                        "Slight Error",
                        JOptionPane.PLAIN_MESSAGE);
                System.out.println("Trade Failed");
            }

        }
        else if(desiredTrade.equals("? (trade WOOD) " + 3 + "-> " +1)){

            //WOOD/ORE/CLAY/WHEAT/? + " " + firstRatio + "-> ?" + secondRatio;
            //?3:1, wood 2:1, clay 2:1, wheat 2:1, ore 2:1

            Player p = GameFrame.currentPlayer();
            if(p.getHand().getCount(Resource.WOOD) >= 3){
                p.getHand().remove(Resource.WOOD);
                p.getHand().remove(Resource.WOOD);
                p.getHand().remove(Resource.WOOD);

                if(determineResource(desiredResource) != null) {
                    p.getHand().add(determineResource(desiredResource));
                }
                else{
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                    System.out.println("Trade Failed");
                }
            }

            else{
                JOptionPane.showMessageDialog(frame,
                        "You lack the neccessary cards to complete this trade.",
                        "Slight Error",
                        JOptionPane.PLAIN_MESSAGE);
                System.out.println("Trade Failed");
            }

        }
        else if(desiredTrade.equals("? (trade ORE) " + 3 + "-> " + 1 )){

            //CLAY/WHEAT/? + " " + firstRatio + "-> ?" + secondRatio;
            //?3:1, wood 2:1, clay 2:1, wheat 2:1, ore 2:1

            Player p = GameFrame.currentPlayer();
            if(p.getHand().getCount(Resource.ORE) >= 3){
                p.getHand().remove(Resource.ORE);
                p.getHand().remove(Resource.ORE);
                p.getHand().remove(Resource.ORE);

                if(determineResource(desiredResource) != null) {
                    p.getHand().add(determineResource(desiredResource));
                }
                else{
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                    System.out.println("Trade Failed");
                }
            }

            else{
                JOptionPane.showMessageDialog(frame,
                        "You lack the neccessary cards to complete this trade.",
                        "Slight Error",
                        JOptionPane.PLAIN_MESSAGE);
                System.out.println("Trade Failed");
            }

        }
        else if(desiredTrade.equals("? (trade CLAY) " + 3 + "-> " + 1 )){

            //CLAY/WHEAT/? + " " + firstRatio + "-> ?" + secondRatio;
            //?3:1,  clay 2:1, wheat 2:1,

            Player p = GameFrame.currentPlayer();
            if(p.getHand().getCount(Resource.CLAY) >= 3){
                p.getHand().remove(Resource.CLAY);
                p.getHand().remove(Resource.CLAY);
                p.getHand().remove(Resource.CLAY);

                if(determineResource(desiredResource) != null) {
                    p.getHand().add(determineResource(desiredResource));
                }
                else{
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                    System.out.println("Trade Failed");
                }
            }

            else{
                JOptionPane.showMessageDialog(frame,
                        "You lack the neccessary cards to complete this trade.",
                        "Slight Error",
                        JOptionPane.PLAIN_MESSAGE);
                System.out.println("Trade Failed");
            }

        }
        else if(desiredTrade.equals("? (trade WHEAT) " + 3 + "-> " + 1 )){

            //WHEAT/? + " " + firstRatio + "-> ?" + secondRatio;
            //?3:1, wood 2:1, clay 2:1, wheat 2:1, ore 2:1

            Player p = GameFrame.currentPlayer();
            if(p.getHand().getCount(Resource.WHEAT) >= 3){
                p.getHand().remove(Resource.WHEAT);
                p.getHand().remove(Resource.WHEAT);
                p.getHand().remove(Resource.WHEAT);

                if(determineResource(desiredResource) != null) {
                    p.getHand().add(determineResource(desiredResource));
                }
                else{
                    JOptionPane.showMessageDialog(frame,
                            "You lack the neccessary cards to complete this trade.",
                            "Slight Error",
                            JOptionPane.PLAIN_MESSAGE);
                    System.out.println("Trade Failed");
                }
            }

            else{
                JOptionPane.showMessageDialog(frame,
                        "You lack the neccessary cards to complete this trade.",
                        "Slight Error",
                        JOptionPane.PLAIN_MESSAGE);
                System.out.println("Trade Failed");
            }

        }
        else{
            JOptionPane.showMessageDialog(frame,
                    "You did not chose a trade",
                    "Slight Error",
                    JOptionPane.PLAIN_MESSAGE);
            System.out.println("No Trade Chosen");
        }


    }

    public Resource determineResource(String s){

        if(s.equals("Wool")){
            return Resource.WOOL;
        }
        else if(s.equals("Wood")){
            return Resource.WOOD;
        }
        else if(s.equals("Ore")){
            return Resource.ORE;
        }
        else if(s.equals("Clay")){
            return Resource.CLAY;
        }
        else if(s.equals("Wheat")){
            return Resource.WHEAT;
        }
        else{
            return null;
        }

    }


}
