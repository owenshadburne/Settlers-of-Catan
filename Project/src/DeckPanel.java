import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DeckPanel extends JPanel{
    private Deck deck;
    public  BufferedImage clayCard, developmentCard, oreCard,wheatCard,woodCard,woolCard, referenceCard;
    private int cardWidth, cardHeight;

    //get the deck from somewhere!!!!!
    public DeckPanel(Deck d){
        ImageStruct s = new ImageStruct();
        deck = d;
        try {

            oreCard =ImageStruct.oreCard;
            wheatCard =ImageStruct.wheatCard;
            woodCard =ImageStruct.woodCard;
            woolCard =ImageStruct.woolCard;
            clayCard =ImageStruct.clayCard;
            referenceCard = ImageStruct.referenceCard;
            
            if(!Runner.yourdidit) {
                developmentCard = ImageIO.read(leftPanel.class.getResource("/Images/developmentCard.png"));
            }
            else {
                developmentCard = ImageIO.read(leftPanel.class.getResource("/Images/your did it.png"));
            }

            System.out.println("all im good");
        }
        catch(Exception e) {
            System.out.println(e);
        }

        cardWidth = getWidth()/4;
        cardHeight = getHeight()/5;
    }

    public void paint(Graphics g){
        cardWidth = getWidth()/4;
        cardHeight = getHeight()/5;


        g.fillRect(0, 0, getWidth(), getHeight());
        //Tile
        Font myFont = new Font("Georgia", Font.BOLD, getWidth()/20);
        g.setColor(Color.WHITE);
        g.setFont(myFont);
        String str = "DECK INFORMATION";
        g.drawString(str,getWidth()/2- (str.length()/3) * myFont.getSize(), getHeight()/20);
        Font font = new Font("Georgia", Font.PLAIN, 15);
        g.setFont(font);

        paintCards(g);
        g.setColor(new Color(64,64,64));
    }

    public void paintCards(Graphics g) {
        g.drawImage(clayCard,getWidth()/18,getHeight()/15, cardWidth, cardHeight, null);
        g.drawString(Integer.toString(deck.getCount(Resource.CLAY)),getWidth()/20 + cardWidth/3,getHeight()/15 +cardHeight + getHeight()/30);


        g.drawImage(oreCard,getWidth()/18 * 7,getHeight()/15, cardWidth, cardHeight, null);
        g.drawString(Integer.toString(deck.getCount(Resource.ORE)),getWidth()/18 * 7  + cardWidth/3,getHeight()/15 +cardHeight + getHeight()/30);

        g.drawImage(woodCard,getWidth()/18 * 13, getHeight()/15, cardWidth, cardHeight, null);
        g.drawString(Integer.toString(deck.getCount(Resource.WOOD)),getWidth()/18 * 13 + cardWidth/3,getHeight()/15 +cardHeight + getHeight()/30);

        g.drawImage(woolCard, getWidth()/18,getHeight()/15 * 5, cardWidth, cardHeight, null);
        g.drawString(Integer.toString(deck.getCount(Resource.WOOL)),getWidth()/20 + cardWidth/3,getHeight()/15 *5+cardHeight + getHeight()/30);

        g.drawImage(wheatCard,getWidth()/18 * 7,getHeight()/15 * 5, cardWidth, cardHeight, null);
        g.drawString(Integer.toString(deck.getCount(Resource.WHEAT)),getWidth()/18 * 7  + cardWidth/3,getHeight()/15 *5 +cardHeight + getHeight()/30);

        g.drawImage(developmentCard,getWidth()/18 * 13,getHeight()/15 *5, cardWidth, cardHeight, null);
        g.drawString(Integer.toString(deck.getDevSize()),getWidth()/18 * 13 + cardWidth/3,getHeight()/15 *5 +cardHeight + getHeight()/30);

        g.drawImage(referenceCard,(getWidth()/20) *5,(getHeight()/25) *15,(int) (cardWidth * (2)),(int) (cardHeight * (2)), null);
    }

    public void onClick() {

    }
}
