import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
public class leftPanel extends JPanel{

    public leftPanel(DeckPanel deck, DicePanel dice) {
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(grid);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 2.0;
        c.gridx = 0;
        c.gridy = 0;
        add(deck,c);

        c.weighty = .5;
        c.gridx = 0;
        c.gridy = 1;
        add(dice, c);
    }

}
