import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Queue;

public class Deck {
    private static Map<Resource, Integer> resources;
    private static Queue<Development> dCards;
    private DeckPanel deckPanel;
    public static Deck instance;
    public Deck() {
        if(instance == null) {
            instance = this;
        }
        resources = new TreeMap<Resource, Integer>();
        dCards = new LinkedList<Development>();

        resources.put(Resource.CLAY, 0);
        resources.put(Resource.ORE, 0);
        resources.put(Resource.WHEAT, 0);
        resources.put(Resource.WOOD, 0);
        resources.put(Resource.WOOL, 0);
    }

    public int getCount(Resource r) {
        return resources.get(r);
    }

    public int getDevSize() { return dCards.size(); }

    public void add(Resource r) {
        int numRes = resources.get(r) + 1;
        resources.put(r, numRes);
        if(deckPanel != null) {
            deckPanel.repaint();
        }
    }

    public void add(Development dCards) {
        this.dCards.add(dCards);
        if(deckPanel != null) {
            deckPanel.repaint();
        }
    }

    public Resource remove(Resource r) {
        int numRes = resources.get(r) - 1;
        if(numRes < 0) {
            return null;
        }
        resources.put(r, numRes);
        if(deckPanel != null) {
            deckPanel.repaint();
        }
        return r;
    }
    public Development removeDevelopmentCard() {
        if(deckPanel != null) {
            deckPanel.repaint();
        }
        return dCards.poll();
    }

    public boolean checkIfClicked(int x, int y) {
        return false;
    }


    public void setDeckPanel(DeckPanel dp) {
        deckPanel = dp;
    }
    @Override
    public String toString() {
        String result = "";
        if(resources != null) {
            for(Resource r: resources.keySet()) {
                result += r.toString() + ": " + resources.get(r) + "\n";
            }
        }
        else {
            result += "Resources == null\n";
        }
        if(dCards != null) {
            for(Development d: dCards) {
                result += d.toString() + " ";
            }
        }
        else {
            result += "dCards == null\n";
        }
        return result;
    }

}
