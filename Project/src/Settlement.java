import java.awt.*;
import java.awt.image.BufferedImage;

public class Settlement {
    private BufferedImage image;
    private int settlementSize = 25, citySize = 40;
    private Colour colour;
    private boolean isCity;

    public Settlement(Colour colour, boolean isCity) {
        this.colour = colour;
        this.isCity = isCity;
        GameFrame.currentPlayer().setRegularVictoryPoints(GameFrame.currentPlayer().getRegularVictoryPoints() + 1);
        if(!isCity) {
            switch (colour) {
                case RED:
                    image = ImageStruct.redSettlement;
                    break;
                case BLUE:
                    image = ImageStruct.blueSettlement;
                    break;
                case ORANGE:
                    image = ImageStruct.orangeSettlement;
                    break;
                case WHITE:
                    image = ImageStruct.whiteSettlement;
                    break;
            }
        }
        else {
            switch (colour) {
                case RED:
                    image = ImageStruct.redCity;
                    break;
                case BLUE:
                    image = ImageStruct.blueCity;
                    break;
                case ORANGE:
                    image = ImageStruct.orangeCity;
                    break;
                case WHITE:
                    image = ImageStruct.whiteCity;
                    break;
            }
        }
    }

    public Colour getColour() { return colour; }
    public boolean isCity() { return isCity; }

    public void draw(Graphics g, int[] position) {
        if(!isCity) {
            g.drawImage(image, position[0] - 2, position[1] - settlementSize / 2, settlementSize, settlementSize, null);
        }
        else {
            g.drawImage(image, position[0] - 10, position[1] - citySize / 2, citySize, citySize, null);
        }
    }
}
