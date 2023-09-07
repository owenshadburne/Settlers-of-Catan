import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
public class Port {
	private BufferedImage image;
	private Resource give;
	private int[] ratio;
	private Vertex v1;
	private Vertex v2;
	private int[] position;
	private int tileWidth;
	private int tileHeight;
	private int width;
	private int height;
	private int[] bottomPosition;
	public Port(Vertex v1, Vertex v2, int w, int h) {
		this.v1 = v1;
		this.v2 = v2;
		tileWidth = w;
		tileHeight = h;
		position = new int[2];
		bottomPosition = new int[2];
		try {
			if(Runner.yourdidit) {
				image = ImageIO.read(BoardPanel.class.getResource("/Images/your did it.png"));
			}
			else {
				image = ImageIO.read(BoardPanel.class.getResource("/Images/port.png"));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		give = Resource.DESERT;
		ratio = new int[2];
		ratio[0] = 3;
		ratio[1] = 1;
	}
	public void setProp(int tw, int th, int w, int h) {
		tileWidth = tw;
		tileHeight = th;
		width =w;
		height = h;
	}
	public void setType(Resource g,int[] ratio) {
		give = g;
		this.ratio = ratio;
	}
	public int[] getPosition() {
		return position;
	}
	public void setPosition() {
		int[] midPoint = new int[2];
		midPoint[0] = (v1.getPosition()[0] + v2.getPosition()[0])/2;
		midPoint[1] = (v1.getPosition()[1] + v2.getPosition()[1])/2;
		if(midPoint[0] <= width/2 && midPoint[1] > height/2) {
			position[0] = midPoint[0] - tileWidth/4 ;
			position[1] = midPoint[1]- tileHeight/25;

			bottomPosition[0] = position[0] + tileWidth/6;
			bottomPosition[1] = position[1] + tileHeight/3;
		} else if(midPoint[0] >= width/2 && midPoint[1] > height/2) {
			position[0] = midPoint[0] + tileWidth/5;
			position[1] = midPoint[1] - tileHeight/25;

			bottomPosition[0] = position[0] + tileWidth/6;
			bottomPosition[1] = position[1] + tileHeight/3;
		} else if(midPoint[0] < width/2 &&  midPoint[1] < height/2) {
			position[0] = midPoint[0] - tileWidth/3 ;
			position[1] = midPoint[1] - tileHeight/3;


			bottomPosition[0] = position[0] + tileWidth/6;
			bottomPosition[1] = position[1] + tileHeight/3;
		} else {
//			position[0] = midPoint[0] + tileWidth/3 ;
//			position[1] = midPoint[1] - tileHeight/3;
			position[0] = midPoint[0]  + tileWidth/6;
			position[1] = midPoint[1] - tileHeight/3;

			bottomPosition[0] = position[0] + tileWidth/6;
			bottomPosition[1] = position[1]+ tileHeight/3;
		}

	}

	public void highlight(Graphics g) {
		setPosition();
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5));
		g2.setColor(new Color(102,51,0));

		g.drawLine(v1.getPosition()[0] + v1.getRadius()/2, v1.getPosition()[1] + v1.getRadius()/2, bottomPosition[0], bottomPosition[1]);
		g.drawLine(v2.getPosition()[0] + v1.getRadius()/2, v2.getPosition()[1] + v2.getRadius()/2, bottomPosition[0], bottomPosition[1]);
		g.drawImage(image, position[0] ,position[1], tileWidth/3,tileHeight/3,null);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospaced", Font.BOLD, tileWidth/10));
		g.drawString(this.toString(), bottomPosition[0] - (this.toString().length()/3) *tileWidth/10, bottomPosition[1] + tileHeight/20);
		g.setColor(Color.black);
	}
	public String toString() {
		String str = "";
		switch(give) {
			case WOOL :
				str += "WOOL";
				break;
			case WOOD :
				str += "WOOD";
				break;
			case ORE :
				str += "ORE";
				break;
			case CLAY :
				str += "CLAY";
				break;
			case WHEAT :
				str += "WHEAT";
				break;
			case DESERT :
				str += "?";
		}
		str += " " +ratio[0] + "-> ?" + ratio[1];
		return str;
	}
}
