import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Card {

    private static int total_cards = 0;
    private static final String[] COLORS = {"Herz", "Karo", "Pik", "Kreuz"};
    private static final String[] VALUES = {"7","8","9","10","J","Q","K","A"};
    private final String color;
    private final String value;
    private final int id;

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
        if (deck.isEmpty()) {
            pileToDeck();
        }
        Card card = deck.getFirst();
        deck.removeFirst();
        return card;
    }

    static void pileToDeck() {
        deck.addAll(Game.getPile());
        shuffleDeck();
        Game.getPile().clear();
        Game.getPile().addFirst(Card.drawFromDeck());
    }

    public String getColor() {
        return color;
    }

    public boolean isValid(Card onPile) {

        //check special case if there is a J on pile at turn 1
        if (Game.getTurn() == 1 && Objects.equals(onPile.getValue(), "J")) {
            return true;
        }

        //determine if there is a joker color to serve or just normal case
        String colorToPlay;
        if (Game.getJokerColor()[0] == null) {
            colorToPlay = onPile.getColor();
        }else {
            colorToPlay = Game.getJokerColor()[0];
        }

        //check if card is valid choice
        if (!Objects.equals(this.getColor(), colorToPlay)
                && !Objects.equals(this.getValue(), onPile.getValue())
                && !Objects.equals(this.getValue(), "J")) {
            return false;
        }
        return true;
    }

    public static String[] getColors() {
        return COLORS;
    }

    public String getValue() {

        return value;
    }

    static Card getCardById(int id, ArrayList<Card> cards) {
        for (Card card : cards) {
            if (card.id == id) {
                return card;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return color + value + " (ID:" + id + ")";
    }
}
