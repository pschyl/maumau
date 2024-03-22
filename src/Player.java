import java.util.*;

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


    public void playCard(Card card) {

        if (card.getValue().equals("J")) {
            while(true) {
                List colorList = Arrays.asList(Card.getColors());
                System.out.println("Which color do you choose? (" + colorList.get(0) + ", " + colorList.get(1) + ", " + colorList.get(2) + ", " + colorList.get(3) + ")");
                String jokerColor = Game.scan.nextLine();

                if (colorList.contains(jokerColor)) {
                    Game.jokerColor[0] = jokerColor;
                    break;
                }
                System.out.println("Not a valid color!");
            }
        }

        Game.pile.add(0,card);
        hand.remove(card);

    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " " + hand;
    }
}
