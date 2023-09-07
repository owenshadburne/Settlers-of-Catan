import java.awt.*;

public enum Colour {
    WHITE,
    BLUE,
    RED,
    ORANGE;

    public static Color getColor(Colour colour) {
    	if(!Runner.yourdidit) {
    		switch(colour) {
	            case RED:
	                return new Color(224,102,102);
	            case BLUE:
	                return new Color(109, 158, 235);
	            case ORANGE:
	                return new Color(246, 178, 107);
	            case WHITE:
	                return new Color(255, 255, 255);
	        }
	        return Color.black;
    	}
    	else {
    		return Color.WHITE;
    	}
    }
}
