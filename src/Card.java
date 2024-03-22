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
    private boolean triggered;

    public static ArrayList<Card> deck = new ArrayList<>();

    public Card(String color, String value) {
        id =  total_cards;
        triggered = false;
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
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }

    static void pileToDeck() {
        deck.addAll(Game.pile);
        shuffleDeck();
        Game.pile.clear();
        Game.pile.add(0,Card.drawFromDeck());

    }


    public String getColor() {
        return color;
    }

    public boolean isValid(Card onPile) {
        String colorToPlay;
        if (Game.jokerColor[0] == null) {
            colorToPlay = onPile.getColor();
        }else {
            colorToPlay = Game.jokerColor[0];
        }

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

    public boolean isTriggered() {
        return triggered;
    }

    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }

    public String getValue() {
        return value;
    }

    public int getId() {
        return id;
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
