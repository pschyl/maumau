import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static Scanner scan = new Scanner(System.in);

    public static ArrayList<Card> pile = new ArrayList<>();

    public static ArrayList<Player> players = new ArrayList<>();

    private static boolean gameOn = true;

    public static void startGame() {
        //create Deck
        Card.createDeck();
        Card.shuffleDeck();

        //input int for number of players to create
        System.out.println("Number of Players: ");

        int numPlayers = scan.nextInt();

        for (int i = 0; i < numPlayers; i++) {
            Player newPlayer = new Player();
            players.add(newPlayer);
        }

        Game.pile.add(0,Card.drawFromDeck());
        System.out.println("--------------------");

        mainLoop();

    }

    public static void mainLoop() {

        System.out.println("GAME BEGINS");
        //main Game-loop
        while (gameOn) {

            for (Player player : players) {
                //display hand and top of pile to player
                System.out.println("| Player " + player.getId() + " |");
                System.out.println("On Pile: " + pile.get(0));
                System.out.println("Your Hand: " + player.getHand());
                System.out.println("---------");

                //player choose card to play

                System.out.println("ID of card you want to play: ");

                int chosenCardID = scan.nextInt();

                System.out.println("--------------------");

                //player plays card or draws if card is not valid
                Card chosenCard = Card.getCardById(chosenCardID, player.getHand());

                if (chosenCard == null || player.playCard(chosenCard) == false) {
                    System.out.println("Not a valid card. Draw a card!");
                    System.out.println("--------------------");
                    player.getHand().add(Card.drawFromDeck());
                }

                //check condition for ending the game
                if (player.checkIfHandEmpty()) {
                    gameOn = false;
                    scan.close();
                    System.out.println("GAME IS OVER!");
                    System.out.println("Player " + player.getId() + " wins!");
                    break;
                }
            }


        }
    }

}
