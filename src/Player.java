import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Player {

    static int total_players = 0;
    private final int startingHandSize = 7;
    private ArrayList<Card> hand;
    private int id;


    public Player() {
        id = total_players;
        hand = getStartingHand();
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

    public ArrayList<Card> getHand() {
        return hand;
    }


    public boolean checkIfHandEmpty() {
        if (hand.size() == 0) {
            return true;
        }
        return false;
    }


    public boolean playCard(Card card) {
        Card topCard = Game.pile.get(0);
        if (card.getColor() != topCard.getColor() && card.getValue() != topCard.getValue() && card.getValue() != "J") {
            return false;
        }
        Game.pile.add(0,card);
        hand.remove(card);
        return true;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " " + hand;
    }
}
