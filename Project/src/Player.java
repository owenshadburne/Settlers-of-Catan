
import java.util.Locale;

public class Player {
    private Colour colour;
    private Hand hand;
    private boolean largestArmy;
    private boolean longestRoad;
    public int settlementsLeft;
    public int citiesLeft;
    public int roadsLeft;
    private int knightsPlayed;

    private int regularVictoryPoints, specialVictoryPoints;
    public int longestRoadCount;

    public Player(Colour c) {
        colour = c;
        hand = new Hand();
        settlementsLeft = 5;
        citiesLeft = 4;
        roadsLeft = 15;
    }

    public Colour getColour() {
        return colour;
    }
    public int getSizeOfResources() {
        return hand.getSizeOfResources();
    }
    public int getSizeOfDevelopment() {
        return hand.getSizeOfDevelopment();
    }
    public int getTotalVictoryPoints() {
        int special = 0;
        if(largestArmy) {
            special += 2;
        }
        if(longestRoad) {
            special += 2;
        }
        return regularVictoryPoints + specialVictoryPoints + special;
    }

    public int getRegularVictoryPoints() {
        return regularVictoryPoints;
    }
    public int getSpecialVictoryPoints() {
        return specialVictoryPoints;
    }
    public void setRegularVictoryPoints(int vp) {
        regularVictoryPoints = vp;
    }
    public void setSpecialVictoryPoints(int vp) {
        specialVictoryPoints = vp;
    }

    public Hand getHand() {
        return hand;
    }

    public void setLargArmy(boolean b) {
        largestArmy = b;
    }
    public boolean isLargArmy() {
        return largestArmy;
    }
    public void setLongRoad(boolean b) {
        longestRoad = b;
    }
    public boolean isLongRoad() {
        return longestRoad;
    }
    public void knightUsed() {
        knightsPlayed++;
    }
    public int getKnightsPlayed() {
        return knightsPlayed;
    }
    public int getSettlementsLeft() { return settlementsLeft; }
    public int getCitiesLeft() { return citiesLeft; }
    public int getRoadsLeft() { return roadsLeft; }

    public String toString() {
        return colour.toString().substring(0, 1) + colour.toString().substring(1).toLowerCase();
    }
}
