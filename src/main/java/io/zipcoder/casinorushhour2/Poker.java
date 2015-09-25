package io.zipcoder.casinorushhour2;

import com.sun.tools.internal.ws.wsdl.document.Kinds;

import java.util.*;

/**
 * Created by emaron on 9/24/15.
 */
public class Poker implements Game {

    /**
     * Enum of the kinds of possible Poker Hands
     */
    public enum Kinds {
        PAIR, THREEKINDS, FOURKINDS, TWOPAIRS, FULLHOUSE, STRAIGHT, FLUSH, ROYALFLUSH, NOFLUSH
    }

    /**
     * Required fields to interact with the player
     */

    private Deck pokerDeck;

    private Player pokerPlayer;

    private List<Card> cards;

    public static GameState state = GameState.NOTRUNNING;

    //Hand Rankings

    int royalFlush = 800;
    int fourOfAKind = 700;
    int fullHouse = 600;
    int flush = 500;
    int straight = 400;
    int threeOfAKind = 300;
    int twoPair = 200;
    int onePair = 100;
    //int noRankedHand = 0;



    /**
     * This is the constructor of the Poker game
     */

    public Poker(Deck deck) {
        pokerDeck = deck;
        pokerPlayer = pokerDeck.getPlayer();
        cards = pokerDeck.getCards();
    }

    /**
     * Changes game state to running
     */

    public void playGame() {
        this.state = GameState.RUNNING;
    }

    /**
     * Changes the GameState to the opposite of it's current state
     */

    public void changeGameState() {
        if (state == GameState.NOTRUNNING) {
            this.state = GameState.RUNNING;
        } else {
            this.state = GameState.NOTRUNNING;
        }
    }

    /**
     * Checks the cards in hand for any similar names
     *
     * @return Map
     */

    public Map checkForSimilarNamedCards(ArrayList<Card> hand) {
        Map<String, Integer> kindMap = new HashMap<String, Integer>();
        Iterator<Card> cardIterator = hand.iterator();
        System.out.println(hand.size());
        while (cardIterator.hasNext()) {
            Card tempCard = cardIterator.next();
            if (kindMap.containsKey(tempCard.getName())) {
                Integer occurance = kindMap.get(tempCard.getName());
                kindMap.put(tempCard.getName(), occurance + 1);
            } else {
                kindMap.put(tempCard.getName(), 1);
            }
        }
        return kindMap;
    }

    /**
     * Checks the hand for a flush
     *
     * @return Kinds enum
     */

    public Kinds checkForFlushCards(ArrayList<Card> hand) {
        Map<String, Integer> kindMap = new HashMap<String, Integer>();
        Iterator<Card> cardIterator = hand.iterator();


        while (cardIterator.hasNext()) {
            Card tempCard = cardIterator.next();
            if (kindMap.containsKey(tempCard.getSuit())) {
                Integer occurance = kindMap.get(tempCard.getSuit());
                kindMap.put(tempCard.getSuit(), occurance + 1);
            } else {
                kindMap.put(tempCard.getSuit(), 1);
            }
        }
        if (kindMap.size() == 5) {
            return Kinds.FLUSH;
        }

        return Kinds.NOFLUSH;
    }

    /**
     * Returns the enum associated to that Poker hand
     *
     * @param hashMap
     * @return
     */

    public Kinds InterpretSimilarCardsToPokerHand(Map hashMap) {

        List<Kinds> kindsList = new ArrayList<Kinds>();
        int count = 0;
        Map<String, Integer> map = hashMap;

        System.out.println(map.keySet());

        for (String string : map.keySet()) {
            if (count < 5) {
                switch (map.get(string)) {
                    case 2:
                        kindsList.add(Kinds.PAIR);
                        count += 2;
                        break;
                    case 3:
                        kindsList.add(Kinds.THREEKINDS);
                        count += 3;
                        break;
                    case 4:
                        kindsList.add(Kinds.FOURKINDS);
                        count += 4;
                        break;
                    case 5:
                        kindsList.add(Kinds.STRAIGHT);
                        break;
                }
            }
        }

        if (kindsList.size() == 2) {
            if (kindsList.contains(Kinds.THREEKINDS)) {
                return Kinds.FULLHOUSE;
            } else {
                return Kinds.TWOPAIRS;
            }
        } else {
            return kindsList.get(0);
        }
    }
/*
    public int returnPlayerScore() {
        if (checkForFlushCards() == Kinds.FLUSH) {
            return flush;
        } else {
            switch (InterpretSimilarCardsToPokerHand(checkForSimilarNamedCards())) {
                case Kinds.FOURKINDS:
                    return fourOfAKind;
                break;
                case Kinds.FULLHOUSE:
                    return;
                fullHouse;
                break;
                case Kinds.PAIR:
                    return onePair;
                break;
                case Kinds.THREEKINDS:
                    return threeOfAKind;
                break;
                case Kinds.TWOPAIRS:
                    return twoPair;
                break;
                case Kinds.STRAIGHT:
                    return straight;
                break;
                default:
                    return royalFlush;
            }
        }
    }*/

    /**
     * Compares the enum to an int skill then generates and compares to Dealer
     */

    public void checkForWinner() {

        System.out.println("Hello");

    }

    /**
     * Leaves the current Poker game
     */

    public void exitGame() {

    }

   // public ArrayList<Card> hand;

    /////////////////////Where Code Runs/////////////////////////
    public static void main(String[] args) {

        int pot = 0;



//Dummy Hand

        Card firstCard = new Card();
        firstCard.setName("King");
        firstCard.setSuit(Suit.DIAMONDS);
        Card secondCard = new Card();
        secondCard.setName("King");
        secondCard.setSuit(Suit.CLUBS);
        Card thirdCard = new Card();
        thirdCard.setName("Nine");
        thirdCard.setSuit(Suit.DIAMONDS);
        Card fourthCard = new Card();
        fourthCard.setName("Nine");
        fourthCard.setSuit(Suit.SPADES);
        Card fifthCard = new Card();
        fifthCard.setName("King");
        fifthCard.setSuit(Suit.HEARTS);

        ArrayList<Card> hand = new ArrayList<Card>();


        hand.add(firstCard);
        hand.add(secondCard);
        hand.add(thirdCard);
        hand.add(fourthCard);
        hand.add(fifthCard);


        //List<Card> testMatchedCardsInHand = new ArrayList<Card>();


/**
 * Method calls all the appropriate constructors
 * */
        Player player = new Player("Evan");
        Deck deck = new Deck(player);
        Poker poker = new Poker(deck);

/**
 * This Method changes the current GameState to start the game loop
 */
        poker.changeGameState();

        while (state == GameState.RUNNING) {

            System.out.println("?????????????????????????????????????? . ######################################\n" +
                    "?????????????????????????????????????  %  #####################################\n" +
                    "????????????????????????????????????  %*:  ####################################\n" +
                    "???????????????????????????????????  %#*?:  ###################################\n" +
                    "?????????????????????????????????  ,%##*??:.  #################################\n" +
                    "???????????????????????????????  ,%##*?*#*??:.  ###############################\n" +
                    "?????????????????????????????  ,%###*??*##*???:.  #############################\n" +
                    "???????????????????????????  ,%####*???*###*????:.  ###########################\n" +
                    "?????????????????????????  ,%####**????*####**????:.  #########################\n" +
                    "???????????????????????  ,%#####**?????*#####**?????:.  #######################\n" +
                    "??????????????????????  %######**??????*######**??????:  ######################\n" +
                    "?????????????????????  %######**???????*#######**??????:  #####################\n" +
                    "????????????????????  %######***???????*#######***??????:  ####################\n" +
                    "????????????????????  %######***???????*#######***??????:  ####################\n" +
                    "????????????????????  %######***???????*#######***??????:  ####################\n" +
                    "?????????????????????  %######**??????***######**??????:  #####################\n" +
                    "??????????????????????  '%######****:^%*:^%****??????:'  ######################\n" +
                    "????????????????????????   '%####*:'  %*:  '%*????:'   ########################\n" +
                    "??????????????????????????           %#*?:           ##########################\n" +
                    "?????????????????????????????????  ,%##*??:.  #################################\n" +
                    "???????????????????????????????  .%###***???:.  ###############################\n" +
                    "??????????????????????????????                   ##############################\n" +
                    "???????????????????????????????????????*#######################################" + "\n");

            System.out.println("Welcome to the Five Card Stud table.");
            System.out.println("Don't lose your head." + "\n");

/**
 * Deals cards to player
 */


/**
 *Player prompted to bet
 */
            System.out.println("Your hand contains..." + "\n");
        for(int i = 0; i<hand.size(); i++){
            System.out.print("A " + hand.get(i).getName() + " of " + hand.get(i).getSuit() + "\n");

        }
            System.out.println("\n" + "Enter the amount you wish to wager.");
            Scanner scanner = new Scanner(System.in);
            int x = scanner.nextInt();
            player.bet(x);
            pot = x;


/**
 * Evaluates the current hand states for Player and Dealer
 */
            System.out.println(poker.checkForSimilarNamedCards(hand));
            System.out.println(poker.InterpretSimilarCardsToPokerHand(poker.checkForSimilarNamedCards(hand)));


/**
 * Checks to see who the winner is
 */


/**
 * This Method exits the Poker game loop
 */System.out.println("Ending Poker");
            poker.changeGameState();
        }
    }

}












