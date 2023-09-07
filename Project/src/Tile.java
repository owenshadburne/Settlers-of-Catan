import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.image.*;
import javax.imageio.ImageIO;
public class Tile {
    private Resource resource;
    private ArrayList<Vertex> vertices;
    private int rollValue;

    private int[] position;
    private BufferedImage image;
    private  int width = 15;
    private  int height = 15;
    private int radiusOfHighlight = 40;

    private boolean hasThief;
    private boolean isDesert;

    public Tile() {
        rollValue = -1;
        hasThief = true;
        isDesert = true;
        try {
        	if(Runner.yourdidit) {
        		image = ImageIO.read(BoardPanel.class.getResource("/Images/your did it.png"));
        	}
        	else {
                image = ImageIO.read(BoardPanel.class.getResource("/Images/desertTile.png"));
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
        vertices = new ArrayList<Vertex>();
        resource = Resource.DESERT;
    }
    public boolean validRobber() {
    	if(hasThief) {
    		return false;
    	}
        Player player = GameFrame.currentPlayer();
        for(Vertex v: vertices) {
            if(v.getSettlement() != null && v.getSettlement().getColour() != player.getColour()) {
                return true;
            }
        }
        return false;
    }
    public Tile(Resource resource) {
        vertices = new ArrayList<Vertex>();
        this.resource = resource;
        if(Runner.yourdidit) {
        	try {
            	image = ImageIO.read(BoardPanel.class.getResource("/Images/your did it.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(resource == Resource.WOOL) {
            try {
                image = ImageIO.read(BoardPanel.class.getResource("/Images/pastureTile.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(resource == Resource.WOOD) {
            try {
                image = ImageIO.read(BoardPanel.class.getResource("/Images/forestTile.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(resource == Resource.ORE) {
            try {
                image = ImageIO.read(BoardPanel.class.getResource("/Images/mountainTile.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(resource == Resource.CLAY) {
            try {
                image = ImageIO.read(BoardPanel.class.getResource("/Images/hillTile.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(resource == Resource.WHEAT) {
            try {
                image = ImageIO.read(BoardPanel.class.getResource("/Images/fieldTile.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public void setProp(int w, int h) {
        width = w;
        height = h;
    }
    public ArrayList<Resource> distribution() {
        if(!hasThief) {
            //Count of players on tile
            int count = 0;
            for(Vertex vertex: vertices) {
                if(vertex.getSettlement() != null) {
                    count++;
                }
            }

            //Actual Distribution
            ArrayList<Resource> distributedResources = new ArrayList<>();
            if (count <= GameFrame.getCount(resource)) {
                for(Vertex vertex: vertices) {
                    Resource temp = vertex.distributeResources(resource);
                    if(temp != null) {
                        distributedResources.add(temp);
                    }
                }
            }
            return distributedResources;
        }
        return null;
    }
    public void setThiefLocation(boolean hasThief) {
        this.hasThief = hasThief;
    }

    public BufferedImage getImage() {
        return image;
    }
    public void highlight(Graphics g) {
        int x = (int) (position[0] - (width / 2.0));
        int y = (int) (position[1] - (height / 2.0));
        g.fillOval(x, y, width, height);
    }
    public boolean checkIfClicked(int x, int y) {
        int distance = (x - position[0]) * (x - position[0]) + (y - position[1]) * (y - position[1]);
        if(distance <= radiusOfHighlight * radiusOfHighlight) {
            return true;
        }
        return false;
    }
    public void hightlight(Graphics g) {
        g.setColor(new Color(255 , 255, 150));
        g.fillOval(position[0] - radiusOfHighlight / 2, position[1] - radiusOfHighlight / 2, radiusOfHighlight, radiusOfHighlight);
        g.setColor(Color.BLACK);
    }
    public Resource getResource() {
        return resource;
    }
    public void setPropotions(int w, int h) {
        width = w/2;
        height = h/2;
    }
    public void setPosition(int[] pos) {
        position = pos;
    }
    public int[] getPosition() {
        return position;
    }
    public boolean isDesert() {
        return isDesert;
    }
    public int getRollValue() {
        return rollValue;
    }
    public void setRollValue(int val) {
        rollValue = val;
    }
    public boolean hasThief() {
        return hasThief;
    }
    public void setVertices(ArrayList<Vertex>  list) {
        vertices = list;
    }
    public ArrayList<Vertex> getVertices() {
        return vertices;
    }
    public void addVertex(Vertex v) {
        vertices.add(v);
    }
    public void removeVertes(Vertex v) {
        vertices.remove(v);
    }
    public String toString() {
        String str ="Tile Num: " + rollValue + " - Tile type : -" ;
        switch(resource) {
            case WOOL:
                str+= "WOOL";
                break;
            case WOOD:
                str+= "WOOD";
                break;
            case ORE:
                str+= "ORE";
                break;
            case CLAY:
                str+= "CLAY";
                break;
            case WHEAT:
                str+= "WHEAT";
                break;
            case DESERT:
                str+= "DESERT";
                break;
        }
        if(hasThief) {
            str += " CONTAINS THEIF";
        }
        return str;
    }
}
