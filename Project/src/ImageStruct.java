import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;

public class ImageStruct {
    public static BufferedImage
            blueCity, redCity, whiteCity, orangeCity,
            blueSettlement, redSettlement, whiteSettlement, orangeSettlement,
            clayCard, oreCard, wheatCard, woodCard, woolCard,
            knightCard, monopolyCard, roadBuildingCard, victoryPointCard, yearOfPlentyCard,
            referenceCard,
            developmentCard;

    public ImageStruct() {
    	
    	if(!Runner.yourdidit) {
    		try {
                blueCity = ImageIO.read(ImageStruct.class.getResource("/Images/blueCity.png"));
                redCity = ImageIO.read(ImageStruct.class.getResource("/Images/redCity.png"));
                whiteCity = ImageIO.read(ImageStruct.class.getResource("/Images/whiteCity.png"));
                orangeCity = ImageIO.read(ImageStruct.class.getResource("/Images/orangeCity.png"));

                blueSettlement = ImageIO.read(ImageStruct.class.getResource("/Images/blueSettlement.png"));
                redSettlement = ImageIO.read(ImageStruct.class.getResource("/Images/redSettlement.png"));
                whiteSettlement = ImageIO.read(ImageStruct.class.getResource("/Images/whiteSettlement.png"));
                orangeSettlement = ImageIO.read(ImageStruct.class.getResource("/Images/orangeSettlement.png"));

                clayCard = ImageIO.read(ImageStruct.class.getResource("/Images/clayCard.png"));
                oreCard = ImageIO.read(ImageStruct.class.getResource("/Images/oreCard.png"));
                wheatCard = ImageIO.read(ImageStruct.class.getResource("/Images/wheatCard.png"));
                woodCard = ImageIO.read(ImageStruct.class.getResource("/Images/woodCard.png"));
                woolCard = ImageIO.read(ImageStruct.class.getResource("/Images/woolCard.png"));

                knightCard = ImageIO.read(ImageStruct.class.getResource("/Images/knightCard.png"));
                monopolyCard = ImageIO.read(ImageStruct.class.getResource("/Images/monopolyCard.png"));
                roadBuildingCard = ImageIO.read(ImageStruct.class.getResource("/Images/roadBuildingCard.png"));
                victoryPointCard = ImageIO.read(ImageStruct.class.getResource("/Images/victoryPointCard.png"));
                yearOfPlentyCard = ImageIO.read(ImageStruct.class.getResource("/Images/yearOfPlentyCard.png"));
                
                referenceCard = ImageIO.read(ImageStruct.class.getResource("/Images/referenceCard.png"));

                developmentCard = ImageIO.read(ImageStruct.class.getResource("/Images/developmentCard.png"));

                System.out.println("All Images Compiled");
            }
            catch(Exception e) {
                System.out.println(e);
            }
    	}
    	else {
    		try {
    			BufferedImage temp = ImageIO.read(ImageStruct.class.getResource("/Images/your did it.png"));
    			
                blueCity = temp;
                redCity = temp;
                whiteCity = temp;
                orangeCity = temp;

                blueSettlement = temp;
                redSettlement = temp;
                whiteSettlement = temp;
                orangeSettlement = temp;

                clayCard = temp;
                oreCard = temp;
                wheatCard = temp;
                woodCard = temp;
                woolCard = temp;

                knightCard = temp;
                monopolyCard = temp;
                roadBuildingCard = temp;
                victoryPointCard = temp;
                yearOfPlentyCard = temp;
                
                referenceCard = temp;

                developmentCard = temp;

                System.out.println("All Images Compiled");
            }
            catch(Exception e) {
                System.out.println(e);
            }
    	}
    }
}
