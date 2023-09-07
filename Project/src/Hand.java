import java.awt.*;
import java.util.TreeMap;

public class Hand {
    private TreeMap<Resource, Integer> resources;
    private TreeMap<Development, Integer> development;
    public int knightCooldown = 0, roadCooldown = 0, plentyCooldown = 0, monopolyCooldown = 0;

    public Hand() {
        int amount = 0;
        resources = new TreeMap<>();
        resources.put(Resource.CLAY, amount);
        resources.put(Resource.ORE, amount);
        resources.put(Resource.WHEAT, amount);
        resources.put(Resource.WOOD, amount);
        resources.put(Resource.WOOL, amount);
        development = new TreeMap<>();
        development.put(Development.KNIGHT, amount);
        development.put(Development.MONOPOLY, amount);
        development.put(Development.PLENTY, amount);
        development.put(Development.POINT, amount);
        development.put(Development.ROAD, amount);
    }
    public int getCount(Resource r) {
        return resources.get(r);
    }
    public int getCount(Development d) {
        return development.get(d);
    }
    public void add(Resource r) {
        int x = resources.get(r);
        resources.put(r, x + 1);
    }
    public void add(Development d) {
        int x = development.get(d);
        switch(d) {
            case KNIGHT:
                knightCooldown++;
                break;
            case ROAD:
                roadCooldown++;
                break;
            case PLENTY:
                plentyCooldown++;
                break;
            case MONOPOLY:
                monopolyCooldown++;
                break;
        }
        development.put(d, x + 1);
    }
    public Resource remove(Resource r) {
        int x = resources.get(r);
        if(x <= 0) { return null; }
        resources.put(r, x - 1);
        return r;
    }
    public Development remove(Development d) {
        int x = development.get(d);
        if(x <= 0) { return null; }
        development.put(d, x - 1);
        return d;
    }

    public int getSizeOfResources() {
        int sum = 0;
        for(int x: resources.values()) {
            sum += x;
        }
        return sum;
    }
    public int getSizeOfDevelopment() {
        int sum = 0;
        for(int x: development.values()) {
            sum += x;
        }
        return sum;
    }
    public TreeMap<Resource, Integer> getResource(){
        return resources;
    }
    public TreeMap<Development, Integer> getDevelopment(){
        return development;
    }
    public void resetCooldown() {
        knightCooldown = 0;
        roadCooldown = 0;
        plentyCooldown = 0;
        monopolyCooldown = 0;
    }
}
