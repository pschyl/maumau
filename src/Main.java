
public class Main {


    public static void main(String[] args) {


        Card.createDeck();
        Card.shuffleDeck();

        Player player1 = new Player();
        Player player2 = new Player();

        Game.pile.add(Card.drawFromDeck());

        System.out.println(Game.pile);

        System.out.println(player1.getHand());
        player1.playCard(player1.getHand().get(1));

        System.out.println(Game.pile);

        System.out.println(player2.getHand());
        player2.playCard(player2.getHand().get(4));

        System.out.println(Game.pile);


    }
}

