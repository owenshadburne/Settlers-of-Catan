import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.geom.Line2D;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
public class Edge {
    private Road road;
    ArrayList<Vertex> vertices;

    private int[] position;
    private int[] center;
    private int radiusOfHighlight = 20;
    private int index;

    public Edge(Vertex v1, Vertex v2) {
        index = 0;
        vertices = new ArrayList<>();
        vertices.add(v1);
        vertices.add(v2);

    }

    public Road getRoad() {
        return road;
    }
    public void setIndex(int i) {
        index = i;
    }
    public void setRoad(Road r) {
        road = r;
    }
    public boolean isEqual(Edge e) {
        if(e.getVertices().get(0).toString().equals(vertices.get(0).toString()) && e.getVertices().get(1).toString().equals(vertices.get(1).toString())) {
            //	System.out.println(e.toString() + " equals" + this.toString());
            return true;
        }
        if(vertices.get(0).toString().equals(e.getVertices().get(1).toString()) && vertices.get(1).toString().equals(e.getVertices().get(0).toString())) {
            //	System.out.println(e.toString() + " equals" + this.toString());
            return true;
        }
        //System.out.println(e.toString() + " DOES NOT equals" + this.toString());
        return false;
    }
    @Override
    public boolean equals(Object o) {
        if(center == null) {
            setCenter();
        }
        return o != null && index == ((Edge)o).getIndex();
    }
    public int getIndex() {
        return index;
    }
    public void highlight(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
//    	g2.drawLine(vertices.get(0).getPosition()[0] + vertices.get(0).getRadius()/2,
//    			vertices.get(0).getPosition()[1]  + vertices.get(0).getRadius()/2,
//    			vertices.get(1).getPosition()[0] + vertices.get(0).getRadius()/2,
//    			vertices.get(1).getPosition()[1]+ vertices.get(0).getRadius()/2);
//    	g.setColor(Color.RED);
//    	int x = (vertices.get(0).getPosition()[0] + vertices.get(0).getRadius()/2) +(vertices.get(1).getPosition()[0] + vertices.get(0).getRadius()/2);
//    	int y = (vertices.get(0).getPosition()[1]  + vertices.get(0).getRadius()/2 +vertices.get(1).getPosition()[1]+ vertices.get(0).getRadius()/2);
//    	g.drawString(index + "", x/2, y/2);

        if(BoardPanel.isBuildingRoad()) {
            setCenter();
            if(road == null) {
                g.setColor(new Color(255, 255, 150));
                g.fillOval(center[0], center[1], radiusOfHighlight, radiusOfHighlight);
            }
        }

        g.setColor(Color.BLACK);
    }
    public void draw(Graphics g) {
        if(road != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(5));
            Color color = Colour.getColor(road.getColour());
            g.setColor(color);
            g2.drawLine(vertices.get(0).getPosition()[0] + vertices.get(0).getRadius()/2,
                    vertices.get(0).getPosition()[1]  + vertices.get(0).getRadius()/2,
                    vertices.get(1).getPosition()[0] + vertices.get(0).getRadius()/2,
                    vertices.get(1).getPosition()[1]+ vertices.get(0).getRadius()/2);
            g.setColor(Color.BLACK);
        }
    }

    public boolean checkIfClicked(int x, int y) {
//        int distance = (x - position[0]) * (x - position[0]) + (y - position[1]) * (y - position[1]);
//        if(distance <= radiusOfHighlight * radiusOfHighlight) {
//            return true;
//        }
//        return false;
        setCenter();
        int distance = (x - center[0]) * (x - center[0]) + (y - center[1]) * (y - center[1]);
        if(distance <= radiusOfHighlight * radiusOfHighlight) {
            return true;
        }
        return false;
    }
    //  //  private int[][] getPoints(Vertex v1, Vertex v2) {
//        int[] xPoints = new int[4];
//        int[] yPoints = new int[4];
//
//        int x1 = v1.getPosition()[0];
//        int y1 = v1.getPosition()[1];
//        int x2 = v2.getPosition()[0];
//        int y2 = v2.getPosition()[1];
//        if(x2 - x1 != 0) {
//        	double slope = (y2 - y1) / (x2 - x1);
//        	double angle = Math.atan(slope);
//            double perpAngle = Math.atan(-1 / slope);
//            double backPerpAngle = perpAngle + Math.PI;
//            if(backPerpAngle > 2 * Math.PI) { backPerpAngle -= 2 * Math.PI; }
//        }else {
//
//        }
//
//        double angle = Math.atan(slope);
//        double perpAngle = Math.atan(-1 / slope);
//        double backPerpAngle = perpAngle + Math.PI;
//        if(backPerpAngle > 2 * Math.PI) { backPerpAngle -= 2 * Math.PI; }
//
//        double outerX1 = Math.cos(angle) * radiusOfHighlight + x1;
//        double outerY1 = Math.sin(angle) * radiusOfHighlight + y1;
//        xPoints[0] = (int) (Math.cos(perpAngle) * radiusOfHighlight + outerX1);
//        yPoints[0] = (int) (Math.sin(perpAngle) * radiusOfHighlight + outerY1);
//        xPoints[1] = (int) (Math.cos(backPerpAngle) * radiusOfHighlight + outerX1);
//        yPoints[1] = (int) (Math.sin(backPerpAngle) * radiusOfHighlight + outerY1);
//
//        double outerX2 = Math.cos(angle + Math.PI) * radiusOfHighlight + x2;
//        double outerY2 = Math.sin(angle + Math.PI) * radiusOfHighlight + y2;
//        xPoints[2] = (int) (Math.cos(perpAngle) * radiusOfHighlight + outerX2);
//        yPoints[2] = (int) (Math.sin(perpAngle) * radiusOfHighlight + outerY2);
//        xPoints[3] = (int) (Math.cos(backPerpAngle) * radiusOfHighlight + outerX2);
//        yPoints[3] = (int) (Math.sin(backPerpAngle) * radiusOfHighlight + outerY2);
//
//        return new int[][]{xPoints, yPoints};
//    }
    public void setPosition(int[] pos) {
        position = pos;
    }
    public int[] getPosition() {
        return position;
    }
    public int[] getPoints(Vertex v1, Vertex v2) {
        int[] temp = new int[2];
        temp[0] = (v2.getPosition()[0] + v1.getPosition()[0]) / 2;
        temp[1] = (v2.getPosition()[1] + v1.getPosition()[1]) / 2;
        return temp;
    }
    private void setCenter() {
        if(center == null) {
            if(vertices.get(0).getPosition()[0] < vertices.get(1).getPosition()[0]) {
                center = getPoints(vertices.get(0), vertices.get(1));
            }
            else {
                center = getPoints(vertices.get(1), vertices.get(0));
            }
        }
    }
    public boolean validRoadPlacement() {
        Player player = GameFrame.currentPlayer();

        for(Vertex v: vertices) {
            if(!GameFrame.startOfGame) {
                if(v.getSettlement() != null) {
                    if(v.getSettlement().getColour() == player.getColour()) {
                        return true;
                    }
                }
                else {
                    if(v.validRoadPlacement(this, player.getColour())) {
                        return true;
                    }
                }
            }
            else {
                if(v.getSettlement() != null && v.getSettlement().getColour() == player.getColour()) {
                    if(v.getSettlement() == BoardSetup.getMostRecentSettlement()) {
                        return true;
                    }
                    //System.out.println(v.getSettlement() + " vs " + BoardSetup.getMostRecentSettlement());
                }
            }
        }

        return false;
    }
    public boolean validSettlementDistanceCheck(Vertex fromVertex) {
        for(Vertex x: vertices) {
            if(x != fromVertex) {
                if(x.getSettlement() != null) {
                    return false;
                }
            }
        }
        return true;
    }
    public ArrayList<Vertex> getVertices(){
        return vertices;
    }
    public String toString() {
        return "Edge " +index ;
    }
}
