import java.util.ArrayList;

public class Player {

    static int total_players = 0;
    private int startingHandSize = 7;
    private ArrayList<Card> hand;
    private int id;

    public Player() {
        id = total_players;
        this.hand = getStartingHand();
        total_players += 1;
    }

    public ArrayList<Card> getStartingHand() {

        ArrayList<Card> startingHand = new ArrayList<>();

        for (int i = 0; i < startingHandSize; i++) {
            Card card = Card.drawFromDeck();
            startingHand.add(card);
        }
        return startingHand;
    }

    @Override
    public String toString() {
        return id + " " + hand;
    }
}
