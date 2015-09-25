package io.zipcoder.casinorushhour2;

import com.sun.tools.internal.ws.wsdl.document.Kinds;

import java.util.*;

import static io.zipcoder.casinorushhour2.GameState.*;

/**
 * Created by emaron on 9/24/15.
 */
public class Poker implements Game {

    /**
     * Enum of the kinds of possible Poker Hands
     */
    public enum Kinds {
        PAIR, THREEKINDS, FOURKINDS, TWOPAIRS, FULLHOUSE, STRAIGHT, FLUSH, NOFLUSH, NOGOODCARDS
    }

    /**
     * Required fields to interact with the player
     */

    private Deck pokerDeck;

    private Player pokerPlayer;

    private List<Card> cards;

    public static GameState state = NOTRUNNING;

    //Hand Rankings

    static int royalFlush = 800;
    static int fourOfAKind = 700;
    static int fullHouse = 600;
    static int flush = 500;
    static int straight = 400;
    static int threeOfAKind = 300;
    static int twoPair = 200;
    static int onePair = 100;
    static int noRankedHand = 0;


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
        this.state = RUNNING;
    }

    /**
     * Changes the GameState to the opposite of it's current state
     */

    public void changeGameState() {
        if (state == NOTRUNNING) {
            this.state = RUNNING;
        } else {
            this.state = NOTRUNNING;
        }
    }

    /**
     * Checks the cards in hand for any similar names
     *
     * @return Map
     */

    public static Map checkForSimilarNamedCards(ArrayList<Card> hand) {
        Map<String, Integer> kindMap = new HashMap<String, Integer>();
        Iterator<Card> cardIterator = hand.iterator();
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

    public static Kinds checkForFlushCards(ArrayList<Card> hand) {
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
        if (kindMap.size() == 1) {
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

    public static Kinds InterpretSimilarCardsToPokerHand(Map hashMap) {

        List<Kinds> kindsList = new ArrayList<Kinds>();
        int count = 0;
        Map<String, Integer> map = hashMap;

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
        } else if (kindsList.size() == 0) {
            return Kinds.NOGOODCARDS;
        } else {
            return kindsList.get(0);
        }
    }

    /**
     * Returns a number rank of a players hand for comparison to the Dealer's hand
     *
     * @param hand
     * @return
     */

    public static int returnPlayerScore(ArrayList<Card> hand) {
        if (checkForFlushCards(hand) == Kinds.FLUSH) {
            return flush;
        } else if (InterpretSimilarCardsToPokerHand(checkForSimilarNamedCards(hand)) == Kinds.FOURKINDS) {
            return fourOfAKind;
        } else if (InterpretSimilarCardsToPokerHand(checkForSimilarNamedCards(hand)) == Kinds.FULLHOUSE) {
            return fullHouse;
        } else if (InterpretSimilarCardsToPokerHand(checkForSimilarNamedCards(hand)) == Kinds.PAIR) {
            return onePair;
        } else if (InterpretSimilarCardsToPokerHand(checkForSimilarNamedCards(hand)) == Kinds.TWOPAIRS) {
            return twoPair;
        } else if (InterpretSimilarCardsToPokerHand(checkForSimilarNamedCards(hand)) == Kinds.THREEKINDS) {
            return threeOfAKind;
        } else if (InterpretSimilarCardsToPokerHand(checkForSimilarNamedCards(hand)) == Kinds.STRAIGHT) {
            return straight;
        } else if (InterpretSimilarCardsToPokerHand(checkForSimilarNamedCards(hand)) == Kinds.NOGOODCARDS) {
            return noRankedHand;
        } else {
            return royalFlush;
        }

    }

    public static int returnDealerScore() {
        Random ran = new Random();
        int x = (ran.nextInt(6) + 3) * 100;
        if (x == 800) {
            System.out.println("Tough luck chump, I got a Royal Flush!");
        } else if (x <= 700 && x >= 500) {
            System.out.println("Don't take it personal when I win this round, not everyone is as lucky as me.");
        } else if (x <= 400 && x > 300) {
            System.out.println("I've got a knack for getting just what I need when I need it.");
        } else if (x <= 200) {
            System.out.println("The world needs its losers. I just never thought I'd be one.");
        }
        return x;
    }

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
        firstCard.setName("Four");
        firstCard.setSuit(Suit.DIAMONDS);
        Card secondCard = new Card();
        secondCard.setName("Five");
        secondCard.setSuit(Suit.HEARTS);
        Card thirdCard = new Card();
        thirdCard.setName("Six");
        thirdCard.setSuit(Suit.SPADES);
        Card fourthCard = new Card();
        fourthCard.setName("Eight");
        fourthCard.setSuit(Suit.DIAMONDS);
        Card fifthCard = new Card();
        fifthCard.setName("Seven");
        fifthCard.setSuit(Suit.DIAMONDS);

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
            System.out.println("Here is your hand..." + "\n");
            for (int i = 0; i < hand.size(); i++) {
                System.out.print("A " + hand.get(i).getName() + " of " + hand.get(i).getSuit() + "\n");

            }
            System.out.println("\n" + "Enter the amount you wish to wager.");
            Scanner scanner = new Scanner(System.in);
            int x = scanner.nextInt();
            player.bet(x);
            pot = x;

/**
 * Checks to see who the winner is
 */

            System.out.println(returnPlayerScore(hand));


            if (returnPlayerScore(hand) > returnDealerScore()) {
                System.out.println("\n" + "Well now, look at that. You won!");
                player.addToBank(pot * 2);
            } else {
                System.out.println("\n" + "Sorry kiddo, you lost! Looks like you're all outta luck.");
            }


/**
 * This Method exits the Poker game loop
 */
            System.out.println("\n" + "Ending Poker");
            poker.changeGameState();
        }
    }

}












