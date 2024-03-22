import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static Scanner scan = new Scanner(System.in);

    public static ArrayList<Card> pile = new ArrayList<>();

    public static ArrayList<Player> players = new ArrayList<>();

    public static String[] jokerColor = new String[1];

    public static int sevenDraw = 0;

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

                //Display top of pile to player
                System.out.println("| Player " + player.getId() + " |");
                System.out.println("On Pile: " + pile.get(0));
                if (jokerColor[0] != null) {
                    System.out.println("Joker color: " + jokerColor[0]);
                }

                //check top of pile for 8
                if (pile.get(0).getValue().equals("8") && !pile.get(0).isTriggered()) {
                    pile.get(0).setTriggered(true);
                    System.out.println("Turn skipped!");
                    System.out.println("--------------------");
                    continue;
                }

                //display hand to player
                System.out.println("Your Hand: " + player.getHand());
                System.out.println("---------");

                //player choose card to play
                System.out.println("ID of card you want to play: ");
                int chosenCardID = scan.nextInt();

                //player plays card or draws if card is not valid
                Card chosenCard = Card.getCardById(chosenCardID, player.getHand());
                /// checks if card in hand // checks if chosen card can be played
                if (chosenCard == null || !chosenCard.isValid(pile.get(0))) {
                    System.out.println("Not a valid card. Draw a card!");
                    System.out.println("--------------------");
                    player.getHand().add(Card.drawFromDeck());
                    continue;
                }

                jokerColor[0] = null;
                player.playCard(chosenCard);

                System.out.println("--------------------");

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
