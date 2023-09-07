import java.awt.*;
import java.util.ArrayList;

public class Vertex {
    private Settlement settlement;
    private ArrayList<Tile> tiles;
    private ArrayList<Edge> edges;
    private Port port;
    private int[] position;
    private final int radiusOfHighlight = 20;

    public int index;

    public Vertex(int[] pos) {
        index = 0;
        position = pos;
        tiles = new ArrayList<Tile>();
    }
    public Vertex(int i) {
        index = i;
        position = new int[2];
        tiles = new ArrayList<Tile>();
        edges = new ArrayList<Edge>();
        port = null;
    }
    public void setPort(Port p) {
        port = p;
    }
    public Port getPort() {
        return port;
    }
    public int getRadius() {
        return radiusOfHighlight;
    }
    public boolean isEqual(Vertex v) {
        if(position[0] == v.getPosition()[0] && position[1] == v.getPosition()[1]) {
            return true;
        }
        else {
            return false;
        }
    }
    public Resource distributeResources(Resource r) {
        if(settlement != null) {
            for(Player player: GameFrame.players) {
                if(player.getColour() == settlement.getColour()) {
                    player.getHand().add(r);
                    return r;
                }
            }
        }
        return null;
    }
    public Settlement getSettlement() {
        return settlement; }
    public void setSettlement(Settlement s) {
        settlement = s; }
    public void checkPorts() {
        for(Tile tile: tiles) {
            //get tile.getPortTrade()
        }
    }
    public void highlight(Graphics g) {
        if(settlement == null || BoardPanel.isBuildingCity()) {
            g.setColor(new Color(255, 255, 150));
            g.fillOval(position[0], position[1], radiusOfHighlight, radiusOfHighlight);
            //g.drawString(index +"",position[0], position[1]);
        }
        else {
            settlement.draw(g, position);
        }
        g.setColor(Color.BLACK);
    }
    public void fill(Graphics g) {
        g.fillOval(position[0], position[1], radiusOfHighlight, radiusOfHighlight);
    }
    public void draw(Graphics g) {
        if(settlement != null) {
            settlement.draw(g, position);
        }
    }
    public boolean checkIfClicked(int x, int y) {
        int distance = (x - position[0]) * (x - position[0]) + (y - position[1]) * (y - position[1]);
        if(distance <= radiusOfHighlight * radiusOfHighlight) {
            return true;
        }
        return false;
    }
    public int[] getPosition() {
        return position;
    }

    public boolean validSettlementDistanceCheck() {
        boolean isValid = true;
        if(settlement != null) { return false; }
        for(Edge e: edges) {
            isValid = e.validSettlementDistanceCheck(this) ? isValid : false;
        }
        return isValid;
    }
    public boolean validSettlementConnectionCheck() {
        Player player = GameFrame.currentPlayer();
        for(Edge e: edges) {
            if(e.getRoad() != null && e.getRoad().getColour() == player.getColour()) {
                return true;
            }
        }
        return false;
    }
    public boolean validRoadPlacement(Edge fromEdge, Colour playerColour) {
        for(Edge e: edges) {
            if(e != fromEdge && e.getRoad() != null && e.getRoad().getColour() == playerColour) {
                return true;
            }
        }
        return false;
    }
    public void addTiles(Tile t) {
        tiles.add(t);
    }
    public ArrayList<Tile> getTiles(){
        return tiles;
    }
    public void setPosition(int[] pos) {
        position = pos;
    }
    public void addEdge(Edge e) {
        edges.add(e);
    }
    public ArrayList<Edge> getEdges() {
        return edges;
    }
    public String toString() {
        return "Vertex " + index + " Tiles : " + tiles.toString();
    }
}
