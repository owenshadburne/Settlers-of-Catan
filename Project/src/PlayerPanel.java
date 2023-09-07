import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener.*;
import java.util.Locale;

public class PlayerPanel extends JPanel implements MouseListener {
    private boolean isDisplayingResources = true;
    private BufferedImage clayCard, oreCard, wheatCard, woodCard, woolCard,
            knightCard, monopolyCard, roadBuildingCard, victoryPointCard, yearOfPlentyCard;

    private int cardWidth = 90, cardHeight = 133, offset = cardWidth / 2, textOff = 15 + cardHeight;
    private Deck deck;
    private boolean trading;
    private boolean displayingIn;

    private boolean buildingRoad = false, buildingSettlement = false, buildingCity = false;
    public PlayerPanel(Deck deck) {
        this.setLayout(null);
        displayingIn = false;
        ImageStruct s = new ImageStruct();
        woodCard = ImageStruct.woodCard;
        oreCard = ImageStruct.oreCard;
        wheatCard = ImageStruct.wheatCard;
        clayCard = ImageStruct.clayCard;
        woolCard = ImageStruct.woolCard;
        try {
            if(Runner.yourdidit) {
                knightCard = ImageIO.read(PlayerPanel.class.getResource("/Images/your did it.png"));
                monopolyCard = ImageIO.read(PlayerPanel.class.getResource("/Images/your did it.png"));
                roadBuildingCard = ImageIO.read(PlayerPanel.class.getResource("/Images/your did it.png"));
                victoryPointCard = ImageIO.read(PlayerPanel.class.getResource("/Images/your did it.png"));
                yearOfPlentyCard = ImageIO.read(PlayerPanel.class.getResource("/Images/your did it.png"));
            }
            else {
                knightCard = ImageIO.read(PlayerPanel.class.getResource("/Images/knightCard.png"));
                monopolyCard = ImageIO.read(PlayerPanel.class.getResource("/Images/monopolyCard.png"));
                roadBuildingCard = ImageIO.read(PlayerPanel.class.getResource("/Images/roadBuildingCard.png"));
                victoryPointCard = ImageIO.read(PlayerPanel.class.getResource("/Images/victoryPointCard.png"));
                yearOfPlentyCard = ImageIO.read(PlayerPanel.class.getResource("/Images/yearOfPlentyCard.png"));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.deck = deck;
        trading = false;
        addMouseListener(this);
    }

    public void paint(Graphics g) {
        if(trading != true) {
            paintBackGround(g);
            displayCards(g);
            changeDisplayButton(g);
            drawButtons(g);
            playerInfo(g);
        } else {
            g.fillRect(0,0,getWidth(), getHeight());
        }
    }
    public void paintBackGround(Graphics g) {
//        g.setColor(new Color(64,64,64));
        g.setColor(Color.darkGray);
        g.fillRect(0,0,getWidth(), getHeight());
        Font myFont = new Font("Georgia", Font.BOLD, getWidth()/20);
        g.setColor(Color.WHITE);
        g.setFont(myFont);
        //    String str = "PLAYER INFORMATION";
        //   g.drawString(str,getWidth()/2- (str.length()/3) * myFont.getSize(), getHeight()/20);
        Font font = new Font("Georgia", Font.PLAIN, 15);
        g.setFont(font);
        g.setColor(Color.WHITE);
    }

    private void displayCards(Graphics g) {
        Hand hand = GameFrame.currentPlayer().getHand();
        if(isDisplayingResources) {
            g.drawImage(clayCard, 70 - offset, 100, cardWidth, cardHeight, null);
            g.drawImage(oreCard, 190 - offset, 100, cardWidth, cardHeight, null);
            g.drawImage(wheatCard, 310 - offset, 100, cardWidth, cardHeight, null);
            g.drawImage(woolCard, 130 - offset, 250, cardWidth, cardHeight, null);
            g.drawImage(woodCard, 250 - offset, 250, cardWidth, cardHeight, null);

            g.drawString(Integer.toString(hand.getCount(Resource.CLAY)), 70, 100 + textOff);
            g.drawString(Integer.toString(hand.getCount(Resource.ORE)), 190, 100 + textOff);
            g.drawString(Integer.toString(hand.getCount(Resource.WHEAT)), 310, 100 + textOff);
            g.drawString(Integer.toString(hand.getCount(Resource.WOOL)), 130, 250 + textOff);
            g.drawString(Integer.toString(hand.getCount(Resource.WOOD)), 250, 250 + textOff);
        }
        else {
            g.drawImage(knightCard, 70 - offset, 100, cardWidth, cardHeight, null);
            g.drawImage(monopolyCard, 190 - offset, 100, cardWidth, cardHeight, null);
            g.drawImage(roadBuildingCard, 310 - offset, 100, cardWidth, cardHeight, null);
            g.drawImage(victoryPointCard, 130 - offset, 250, cardWidth, cardHeight, null);
            g.drawImage(yearOfPlentyCard, 250 - offset, 250, cardWidth, cardHeight, null);

            g.drawString(Integer.toString(hand.getCount(Development.KNIGHT)), 70, 100 + textOff);
            g.drawString(Integer.toString(hand.getCount(Development.MONOPOLY)), 190, 100 + textOff);
            g.drawString(Integer.toString(hand.getCount(Development.ROAD)), 310, 100 + textOff);
            g.drawString(Integer.toString(hand.getCount(Development.POINT)), 130, 250 + textOff);
            g.drawString(Integer.toString(hand.getCount(Development.PLENTY)), 250, 250 + textOff);

            String disclaimer = "If you wish to use a development card, click on the to use";
            g.drawString(disclaimer, 5,80);
        }
    }
    private void drawButtons(Graphics g) {
        //Road
        g.drawRect(45, 650, 80, 50);
        if(BoardPanel.isBuildingRoad()) {
            g.drawString("Cancel", 65,680);
        }
        else {
            g.drawString("Build", 67, 670);
            g.drawString("Road", 68, 690);
        }
        //Settlement
        g.drawRect(155, 650, 80, 50);
        if(BoardPanel.isBuildingSettlement()) {
            g.drawString("Cancel", 175,680);
        }
        else {
            g.drawString("Build", 177, 670);
            g.drawString("Settlement", 159, 690);
        }
        //City
        g.drawRect(265, 650, 80, 50);
        if(BoardPanel.isBuildingCity()) {
            g.drawString("Cancel", 285,680);
        }
        else {
            g.drawString("Build", 287, 670);
            g.drawString("City", 291, 690);
        }
        //Dev Card
        g.drawRect(105, 720, 80, 50);
        g.drawString("Buy", 132, 740);
        g.drawString("Dev. Card", 112, 760);
        //Trade
        g.drawRect(215, 720, 80, 50);
        g.drawString("Trade", 235, 750);
        
        //Instructions
//        g.drawRect(245, 550,90,50);
//        g.drawString("Instructions", 250,580);
    }

    private void changeDisplayButton(Graphics g) {
        String showing = "Display Resource Cards";
        if(isDisplayingResources) {
            showing = "Display Development Cards";
            g.drawRect(90, 420, 220, 50);
            g.drawString(showing, 115, 450);
        }else {
            g.drawRect(90, 420, 200, 50);
            g.drawString(showing, 115, 450);
        }

    }
    private void playerInfo(Graphics g) {
        Player player = GameFrame.currentPlayer();
        Font myFont = new Font("Georgia", Font.BOLD, getWidth()/10);
        g.setColor(Color.WHITE);
        g.setFont(myFont);
        String str = player.toString() + "'s Turn";
        g.drawString(str,getWidth()/2- (str.length()/3) * myFont.getSize(), getHeight()/20);

        myFont = new Font("Georgia", Font.BOLD, getWidth()/20);
        g.setFont(myFont);
        g.drawString("Roads Left: " + player.getRoadsLeft(), 20, 530);
        g.drawString("Settlements Left: " + player.getSettlementsLeft(), 20, 570);
        g.drawString("Cities Left: " + player.getCitiesLeft(), 20, 610);
    }
    public void setTrading(boolean b) {
        trading = b;
        repaint();
    }public boolean isTrading() {
        return trading;
    }

    private void buttons(int x, int y) {
        Player player = GameFrame.currentPlayer();
        Hand hand = player.getHand();
        System.out.println( x+ " " +y);
        //Display Change
        if(90 <= x && x <= 290 && 420 <= y && y <= 470) {
            isDisplayingResources = !isDisplayingResources;
        }

        if(GameFrame.hasRolled && !GameFrame.lockAllButtons) {
            //Road
            if (BoardPanel.isBuildingRoad()) {
                if (45 <= x && x <= 125 && 650 <= y && y <= 700) {
                    BoardPanel.isBuildingRoad(false);
                }
            } else if (BoardPanel.isBuildingSettlement()) {
                if (155 <= x && x <= 235 && 650 <= y && y <= 700) {
                    BoardPanel.isBuildingSettlement(false);
                }
            } else if (BoardPanel.isBuildingCity()) {
                if (265 <= x && x <= 345 && 650 <= y && y <= 700) {
                    BoardPanel.isBuildingCity(false);
                }
            } else if (45 <= x && x <= 125 && 650 <= y && y <= 700) {
                if (hand.getCount(Resource.WOOD) >= 1 && hand.getCount(Resource.CLAY) >= 1) {
                    if (player.getRoadsLeft() > 0) {
                        BoardPanel.isBuildingRoad(true);
                    } else {
                        errorMessage("You have no Roads Left to Build");
                    }
                } else {
                    errorMessage("Not Enough Resources for a Road");
                }
            }

            //Settlement
            else if (155 <= x && x <= 235 && 650 <= y && y <= 700) {
                if (hand.getCount(Resource.ORE) >= 1 && hand.getCount(Resource.WOOD) >= 1 && hand.getCount(Resource.WHEAT) >= 1 && hand.getCount(Resource.WOOL) >= 1) {
                    if (player.getSettlementsLeft() > 0) {
                        BoardPanel.isBuildingSettlement(true);
                    } else {
                        errorMessage("You have no Settlements Left to Build");
                    }
                } else {
                    errorMessage("Not Enough Resources for a Settlement");
                }
            }
            //City
            else if (265 <= x && x <= 345 && 650 <= y && y <= 700) {
                if (hand.getCount(Resource.WHEAT) >= 2 && hand.getCount(Resource.ORE) >= 3) {
                    if (player.getCitiesLeft() > 0) {
                        BoardPanel.isBuildingCity(true);
                    } else {
                        errorMessage("You have no Cities Left to Build");
                    }
                } else {
                    errorMessage("Not Enough Resources for a City");
                }
            }

            //Dev Card
            if (105 <= x && x <= 185 && 720 <= y && y <= 770) {
                if (hand.getCount(Resource.WOOL) >= 1 && hand.getCount(Resource.WHEAT) >= 1 && hand.getCount(Resource.ORE) >= 1) {
                    if (deck.getDevSize() > 0) {
                        deck.add(hand.remove(Resource.WOOL));
                        deck.add(hand.remove(Resource.WHEAT));
                        deck.add(hand.remove(Resource.ORE));
                        Development dev = deck.removeDevelopmentCard();
                        if(dev == Development.POINT) {
                            player.setSpecialVictoryPoints(player.getSpecialVictoryPoints() + 1);
                        }
                        hand.add(dev);
                        GameFrame.hasBuilt = true;
                    } else {
                        errorMessage("There are no more Development Cards left");
                    }
                } else {
                    errorMessage("Not Enough Resources for a Development Card");
                }
            }

            //trade button
            if (215 <= x && x <= 295 && 720 <= y && y <= 770) {
                if(!GameFrame.hasBuilt) {
                    try {
                        trading = true;
                        GameFrame.hasTraded = true;
                        GameFrame.lockAllButtons = true;
                        MainTradePanel fd = new MainTradePanel("Trade");

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                else {
                    errorMessage("You must Trade Before you Build!\n " +
                            "(Or buy development cards...)");
                }
            }
            // g.drawRect(245, 550,90,50);
            
        }
//        System.out.println( x+ " " +y);
//        if(x > 245&& x< 245 + 90 &&  y > 550 && y < 600 ) {
//        	System.out.println("Helllllo");
//        	if(!displayingIn) {
//        		InstructionFrame f = new InstructionFrame("Catan Instruction", this);
//        		displayingIn= true;
//        	}
//        }
        else if(650 <= y) {
            errorMessage("You are unable to use this button at this time");
        }
    }
    public void setDisplayingIn(boolean b) {
    	displayingIn = b;
    }
    private void useDevelopmentCards(int x, int y) throws IOException {
        if(!isDisplayingResources) {
            Player player = GameFrame.currentPlayer();
            Hand currentHand = player.getHand();

            if(!GameFrame.usedDevelopmentCard) {
                //knight
                if(70 - offset <= x && x <= 70 - offset + cardWidth && 100 <= y && y <= 100 + cardHeight) {
                    if(currentHand.getCount(Development.KNIGHT) >= 1) {
                        if(currentHand.getCount(Development.KNIGHT) > currentHand.knightCooldown) {
                            BoardPanel.instance.robber();
                            currentHand.remove(Development.KNIGHT);
                            GameFrame.usedDevelopmentCard = true;
                            player.knightUsed();
                            updateLargestArmy();
                        }
                        else {
                            errorMessage("You cannot play a card you just drew");
                        }
                    }
                    else {
                        errorMessage("You have no Knight Cards to play");
                    }
                }
                //monopoly
                else if(190 - offset <= x && x <= 190 - offset + cardWidth && 100 <= y && y <= 100 + cardHeight) {
                    //Pop up for
                    if(currentHand.getCount(Development.MONOPOLY) >= 1) {
                        if(currentHand.getCount(Development.MONOPOLY) > currentHand.monopolyCooldown) {
                            MonopolyPanel mp = new MonopolyPanel("Monopoly");
                            currentHand.remove(Development.MONOPOLY);
                            GameFrame.usedDevelopmentCard = true;
                        }
                        else {
                            errorMessage("You cannot play a card you just drew");
                        }
                    }
                    else {
                        errorMessage("You have no Monopoly Cards to play");
                    }

                }
                //road
                else if(310 - offset <= x && x <= 310 - offset + cardWidth && 100 <= y && y <= 100 + cardHeight) {
                    if(currentHand.getCount(Development.ROAD) >= 1) {
                        int roadsLeft = Math.min(player.getRoadsLeft(), 2);
                        if(roadsLeft > 0) {
                            if(currentHand.getCount(Development.ROAD) > currentHand.roadCooldown) {
                                GameFrame.roadDevelopmentCard = true;
                                GameFrame.roadsLeftToBuild = roadsLeft;
                                BoardPanel.isBuildingRoad(true);
                                currentHand.remove(Development.ROAD);
                                GameFrame.usedDevelopmentCard = true;
                            }
                            else {
                                errorMessage("You cannot play a card you just drew");
                            }
                        }
                        else {
                            errorMessage("You have no Roads Left to Build");
                        }
                    }
                    else {
                        errorMessage("You have no Road Building Cards to play");
                    }

                }
                //year of plenty
                else if(250 - offset <= x && x <= 250 - offset + cardWidth && 250 <= y && y <= 250 + cardHeight) {
                    if(currentHand.getCount(Development.PLENTY) >= 1) {
                        if(currentHand.getCount(Development.PLENTY) > currentHand.plentyCooldown) {
                            YearOfPlentyPanel yp = new YearOfPlentyPanel("Year of Plenty", deck);
                            currentHand.remove(Development.PLENTY);
                            GameFrame.usedDevelopmentCard = true;
                        }
                        else {
                            errorMessage("You cannot play a card you just drew");
                        }
                    }
                    else {
                        errorMessage("You have no Year of Plenty Cards to play");
                    }
                }
            }

            //victory point
            if(130 - offset <= x && x <= 130 - offset + cardWidth && 250 <= y && y <= 250 + cardHeight) {
                /*if(currentHand.getCount(Development.POINT) >= 1) {
                    GameFrame.currentPlayer().setSpecialVictoryPoints(GameFrame.currentPlayer().getSpecialVictoryPoints() + 1);
                    currentHand.remove(Development.POINT);
                    //check to see if the player won!!!!
                }
                else {
                    errorMessage("You have no Victory Point Cards to play");
                }*/
                errorMessage("Victory points from victory point cards are automatically added to your total points");
            }

        }
        GameFrame.repaintAllPanels();
    }
    private void errorMessage(String error) {
        error = error.substring(0, 1).toUpperCase() + error.substring(1).toLowerCase();
        System.out.println(error);
        int funny = (int) Math.floor(Math.random()*(99+1)+0);
        if(funny == 69) {
            JOptionPane.showMessageDialog(this,
                    error,
                    "your did it",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    error,
                    "Notice",
                    JOptionPane.PLAIN_MESSAGE);
        }

    }

    private void updateLargestArmy() {
        Player[] players = GameFrame.players;
        int largest = 0;
        for(Player player: players) {
            player.setLargArmy(false);
            int num = player.getKnightsPlayed();
            largest = Math.max(num, largest);
        }
        if(largest >= 3) {
            int playersWithLargest = 0;
            for(Player player: players) {
                if(player.getKnightsPlayed() == largest) {
                    playersWithLargest++;
                }
            }
            if(playersWithLargest == 1) {
                for(Player player: players) {
                    if(player.getKnightsPlayed() == largest) {
                        player.setLargArmy(true);
                    }
                }
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //System.out.println(x + ", " + y);
        if(!GameFrame.startOfGame) {
            buttons(x, y);
            if(GameFrame.hasRolled) {
                try {
                    useDevelopmentCards(x, y);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        repaint();
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
