import java.util.ArrayList;
import java.util.Collections;

public class Card {

    private static int total_cards = 0;
    private static final String[] COLORS = {"Herz", "Karo", "Pik", "Kreuz"};
    private static final String[] VALUES = {"7","8","9","10","J","Q","K","A"};
    private String color;
    private String value;
    private int id;

    public static ArrayList<Card> deck = new ArrayList<>();

    public Card(String color, String value) {
        id =  total_cards;
        this.color = color;
        this.value = value;
        total_cards += 1;
    }

    static void createDeck() {
        for (String color : COLORS) {
            for (String value : VALUES) {
                Card card = new Card(color, value);
                deck.add(card);
            }
        }
    }

    static void shuffleDeck() {
        Collections.shuffle(deck);
    }

    static Card drawFromDeck() {
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }

    @Override
    public String toString() {
        return color + value + " (ID:" + id + ")";
    }
}
