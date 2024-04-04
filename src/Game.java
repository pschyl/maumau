import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private static Scanner scan = new Scanner(System.in);
    private static ArrayList<Card> pile = new ArrayList<>();
    private static int turn = 0;
    private static ArrayList<Player> players = new ArrayList<>();
    private static String[] jokerColor = new String[1];
    private static int sevenTrigger = 0;
    private static boolean eightTrigger = false;
    private static boolean gameOn = true;


    public static void setEightTrigger(boolean eightTrigger) {
        Game.eightTrigger = eightTrigger;
    }

    public static void incrementSevenTrigger() {
        Game.sevenTrigger += 1;
    }

    public static int getTurn() {
        return turn;
    }

    public static ArrayList<Card> getPile() {
        return pile;
    }

    public static String[] getJokerColor() {
        return jokerColor;
    }

    public static void setJokerColor(String jokerColor) {
        Game.jokerColor[0] = jokerColor;
    }

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

        Game.pile.addFirst(Card.drawFromDeck());
        System.out.println(" ");

        //check top of pile for 7
        if (pile.getFirst().getValue().equals("7")) {
            System.out.println("7 is on Pile");
            System.out.println("Player 0 draws 2 cards!");
            players.getFirst().getHand().add(Card.drawFromDeck());
            players.getFirst().getHand().add(Card.drawFromDeck());
        }

        //check top of pile for J
        if (pile.getFirst().getValue().equals("J")) {
            System.out.println("J is on Pile");
            System.out.println("Player 0 is free to play any card!");
        }

        System.out.println(" ");
        System.out.println(" ");

        mainLoop();

    }

    public static void mainLoop() {

        System.out.println("GAME BEGINS");
        //main Game-loop
        while (gameOn) {

            for (Player player : players) {

                //increment turn
                turn++;

                //Display top of pile to player
                System.out.println("| Player " + player.getId() + " |");
                System.out.println("On Pile: " + pile.getFirst());

                //check if joker was played before and if so display chosen color
                if (jokerColor[0] != null) {
                    System.out.println("Joker color: " + jokerColor[0]);
                }

                //check top of pile for 8
                if (pile.getFirst().getValue().equals("8") && !eightTrigger) {
                    eightTrigger = true;
                    System.out.println("Turn skipped!");
                    System.out.println(" ");
                    System.out.println(" ");
                    continue;
                }

                //check top of pile for 7
                if (pile.getFirst().getValue().equals("7") && sevenTrigger != 0) {
                    System.out.println(sevenTrigger + "x 7 is on pile!");
                }

                //display hand to player
                System.out.println("Your Hand: " + player.getHand());
                System.out.println("---------");

                //player choose card to play
                System.out.println("ID of card you want to play: ");
                int chosenCardID = scan.nextInt();

                Card chosenCard = Card.getCardById(chosenCardID, player.getHand());

                //7 is triggered
                if (sevenTrigger != 0) {
                    if (chosenCard != null && chosenCard.getValue().equals("7")) {
                        sevenTrigger += 1;
                        pile.addFirst(chosenCard);
                        player.getHand().remove(chosenCard);
                    } else {
                        System.out.println("Draw " + sevenTrigger*2 +" cards!");
                        for (int i = 0; i < sevenTrigger; i++) {
                            player.getHand().add(Card.drawFromDeck());
                            player.getHand().add(Card.drawFromDeck());
                        }
                        sevenTrigger = 0;
                    }
                    System.out.println(" ");
                    System.out.println(" ");
                    continue;
                }

                /// checks if card in hand // checks if chosen card can be played
                if (chosenCard == null || !chosenCard.isValid(pile.getFirst())) {
                    System.out.println("Not a valid card. Draw a card!");
                    System.out.println(" ");
                    System.out.println(" ");
                    player.getHand().add(Card.drawFromDeck());
                    continue;
                }

                // reset jokerColor if chosen card is valid
                jokerColor[0] = null;

                //play card
                player.playCard(chosenCard);

                System.out.println(" ");
                System.out.println(" ");

                //check condition for ending the game
                if (player.getHand().isEmpty()) {
                    gameOn = false;
                    scan.close();
                    Player.getScanJ().close();
                    System.out.println("GAME IS OVER!");
                    System.out.println("Player " + player.getId() + " wins!");
                    break;
                }
            }
        }
    }
}
