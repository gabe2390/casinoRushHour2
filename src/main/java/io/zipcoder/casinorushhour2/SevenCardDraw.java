package io.zipcoder.casinorushhour2;

import java.util.*;

import static io.zipcoder.casinorushhour2.GameState.NOTRUNNING;
import static io.zipcoder.casinorushhour2.GameState.RUNNING;

/**
 * Created by emaron on 9/26/15.
 */
public class SevenCardDraw implements CardGame {

    /**
     * Enum of the kinds of possible Poker Hands
     */
    public enum Kinds {
        PAIR, THREEKINDS, FOURKINDS, TWOPAIRS, FULLHOUSE, STRAIGHT, FLUSH, NOFLUSH, NOGOODCARDS
    }

    /**
     * Required fields to interact with the player
     */

    Player player = new Player("Evan");

    Deck pokerDeck = new Deck(player);


    public Player pokerPlayer;

    public GameState currentState = NOTRUNNING;

    public List<Card> playerHand;

    int pot = 0;


    /**
     * Numerical evaluation of poker hands
     */

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

    public SevenCardDraw(Deck deck) {
        pokerDeck = deck;
        pokerDeck.init();
        playerHand = new ArrayList<Card>();

        pokerPlayer = pokerDeck.getPlayer();

        DEALER.shuffleDeck(pokerDeck);
        DEALER.shuffleDeck(pokerDeck);


    }


    /**
     * Changes game state to running
     * Executes all of the game methods
     */


    public void playGame() {
        currentState = RUNNING;
        Scanner key = new Scanner(System.in);
        while (currentState == GameState.RUNNING)

        {
            dealACardToPlayer();
            dealACardToPlayer();
            dealACardToPlayer();
            dealACardToPlayer();
            dealACardToPlayer();
            dealACardToPlayer();
            dealACardToPlayer();
            System.out.println("      /ymmmmmmmmmmmmmmmmNmNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNmy-      \n" +
                    "      hMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM:     \n" +
                    "     .MMMMMMdNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMo     \n" +
                    "     .MMMMMh/hMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMs     \n" +
                    "     .MMMMM+soMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMs     \n" +
                    "     -MMMMdoN+NMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMs     \n" +
                    "     -MMMM+hNshMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMy     \n" +
                    "     -MMMh+yysoMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMy     \n" +
                    "     :MMd+sNMh+hMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMy     \n" +
                    "     :MMNNNMMNNNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMy     \n" +
                    "     :MMMMMmsmMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMh     \n" +
                    "     /MMMNh+/+hMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMh     \n" +
                    "     /MMMy/////hMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMh     \n" +
                    "     /MMMMd+/omMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMh     \n" +
                    "     +MMMMMmhMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMh     \n" +
                    "     +MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd     \n" +
                    "     +MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd     \n" +
                    "     +MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNdMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd     \n" +
                    "     oMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMmhsdMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd     \n" +
                    "     oMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMh/yh+sNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMm     \n" +
                    "     oMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNs/odNo+omMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMm     \n" +
                    "     oMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMmo/oydmys//hMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMm     \n" +
                    "     oMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMh/////hN+////sNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMm     \n" +
                    "     oMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNs///////o//////+dMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMm     \n" +
                    "     oMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd+//////+os+///////yNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN     \n" +
                    "     oMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNs///+ysNyMmyMdmhss///+dMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN     \n" +
                    "     oMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMh+//smhNmddyyodyNhNdmh+//sNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN     \n" +
                    "     oMMMMMMMMMMMMMMMMMMMMMMMMMMMMMmo//+mmdmshdNmNohNmdydmmNy//+hMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN     \n" +
                    "     oMMMMMMMMMMMMMMMMMMMMMMMMMMMNy///oNddhsyhNMNM+sMMNdshdmmm///omMMMMMMMMMMMMMMMMMMMMMMMMMMMN     \n" +
                    "     sMMMMMMMMMMMMMMMMMMMMMMMMMMh+///oNmdsNMmdsodM+/mdshMMhdmmh////sNMMMMMMMMMMMMMMMMMMMMMMMMMN     \n" +
                    "     sMMMMMMMMMMMMMMMMMMMMMMMMmo/////hmmyhmmdyyohM+odshmmNNsNNm+/////hMMMMMMMMMMMMMMMMMMMMMMMMN     \n" +
                    "     yMMMMMMMMMMMMMMMMMMMMMMMd/sdh/ooyhy+syyyyyyho/yhhyyyyy+yhhos+ym//oMMMMMMMMMMMMMMMMMMMMMMMN     \n" +
                    "     yMMMMMMMMMMMMMMMMMMMMMMMMms/////ymmhdNNNdshs/NN+yshdmNsmmm+/////hMMMMMMMMMMMMMMMMMMMMMMMMN     \n" +
                    "     yMMMMMMMMMMMMMMMMMMMMMMMMMMd+///odddyNMmohm+/NN+oddMMyhddh////yNMMMMMMMMMMMMMMMMMMMMMMMMMN     \n" +
                    "     yMMMMMMMMMMMMMMMMMMMMMMMMMMMMh///sNmdhyymNMh/NdMNdhsydmNm///omMMMMMMMMMMMMMMMMMMMMMMMMMMMN     \n" +
                    "     yMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNs//+mmdmymmNN/NmMmdydddNs//+hMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN     \n" +
                    "     yMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd+//sdhNdddh+yyymyMdmd///sNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN     \n" +
                    "     hMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNs///+ssmyNhyMsNyss///+dMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM     \n" +
                    "     hMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd+//////++s+///////sNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM     \n" +
                    "     yMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNs//////+s//////+dMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM     \n" +
                    "     yMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd+////oh/////sNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM`    \n" +
                    "     yMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMmo/////////hMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM`    \n" +
                    "     yMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNy//////+mMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM`    \n" +
                    "     yMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd+///oNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM`    \n" +
                    "     yMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMmo/yNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM`    \n" +
                    "     yMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNdMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM`    \n" +
                    "     sMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM`    \n" +
                    "     oMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM`    \n" +
                    "     oMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNyNMMMMM`    \n" +
                    "     sMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd+/omMMMM`    \n" +
                    "     sMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNs/////yMMM`    \n" +
                    "     sMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMms///yNMMM`    \n" +
                    "     sMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMdomMMMMM`    \n" +
                    "     sMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNNMMMMMMM`    \n" +
                    "     oMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNy+yMmsohMM`    \n" +
                    "     oMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM/yhh+yMMM`    \n" +
                    "     oMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMsods/NMMM`    \n" +
                    "     oMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd+MoyMMMM`    \n" +
                    "     +MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM/y/NMMMM`    \n" +
                    "     +MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMs/sMMMMM`    \n" +
                    "     +MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMmsmMMMMM`    \n" +
                    "     :MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd     \n" +
                    "      /mMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNs`     \n" +
                    "        .::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::---------------....  " + "\n");

            System.out.println("Welcome to the Seven Card Draw table.");
            System.out.println("Try to keep a open mind." + "\n");

            promptPlayerToBet();
            checkForWinner();
            System.out.println("Do you want to play again?");

            if (!key.nextLine().equals("Y")) {
                exitGame();
            }

            pokerDeck.getCards().addAll(playerHand);
            playerHand.clear();

        }


    }

    /**
     * Deals a single card to the player
     */

    public void dealACardToPlayer() {
        playerHand.addAll(DEALER.dealCards(1, pokerDeck));

    }

    /**
     * Ask user what cards they want to exchange from their hand
     */

    public void takeCardsFromPlayer() {
        LinkedHashMap tempMap = new LinkedHashMap();
        Scanner scanner = new Scanner(System.in);
        String exchange;

        for (int i = 0; i < playerHand.size(); i++) {
            System.out.println("Would you like to exchange your " + playerHand.get(i).getName() + " of " + playerHand.get(i).getSuit() + "? (Y) or (N)");
            exchange = scanner.nextLine();

            if (!exchange.equals("N")) {
                pokerDeck.getCards().add(playerHand.get(i));
                DEALER.shuffleDeck(pokerDeck);
                tempMap.put(i, playerHand.get(i));
                List<Card> tempCardArray = DEALER.dealCards(playerHand.size(), pokerDeck);
                for (int j = 0; j < playerHand.size(); j++) {
                    if (tempMap.containsValue(playerHand.get(j))) {
                        playerHand.set(j, tempCardArray.get(j));
                    } else {
                        pokerDeck.getCards().add(tempCardArray.get(j));
                    }
                }
                DEALER.shuffleDeck(pokerDeck);
                tempCardArray.clear();
            } else {
                playerHand.set(i, playerHand.get(i));
            }
        }


        System.out.println("Let me know if it was worth it. Here is your new hand...");
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.print("A " + playerHand.get(i).getName() + " of " + playerHand.get(i).getSuit() + "\n");
        }

    }

    /**
     * Asks player the amount the wish to wager
     */

    public void promptPlayerToBet() {
        int x = 0;

        System.out.println("Here is your hand..." + "\n");
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.print("A " + playerHand.get(i).getName() + " of " + playerHand.get(i).getSuit() + "\n");
        }

        System.out.println("\n" + "It's never too late to try the odds. Is there anything you'd want to let go off?" + "\n");
        takeCardsFromPlayer();

        try {
            System.out.println("\n" + "How much are you willing to risk?");
            Scanner scanner = new Scanner(System.in);
            x = scanner.nextInt();
            pokerPlayer.bet(x);
            pot = x;
        } catch (InputMismatchException e) {
            System.out.println("If betting a 1000 is what you meant, then good. If not, then too bad." + "\n");
            x = 1000;
            pokerPlayer.bet(x);
            pot = x;
        }

    }


    /**
     * Leaves the current Poker game
     */

    public void exitGame() {

        System.out.println("\n" + "Ending Poker");
        currentState = NOTRUNNING;


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

    public Kinds InterpretSimilarCardsToPokerHand(Map hashMap) {

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

    public int returnPlayerScore(ArrayList<Card> hand) {
        if (checkForFlushCards(hand) == SevenCardDraw.Kinds.FLUSH) {
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

    public int returnDealerScore() {
        Random ran = new Random();
        int x = (ran.nextInt(1) + 1) * 100 + (ran.nextInt(1) - 1) * 100;
        if (x == 800) {
            System.out.println("A hand fit only for the best of the best.");
        } else if (x <= 700 && x >= 400) {
            System.out.println("A hand like this can only mean you're going down.");
        } else if (x <= 300 && x >= 200) {
            System.out.println("If every porkchop was perfect...");
        } else if (x <= 100) {
            System.out.println("I think I can make this work.");
        }


        return x;
    }

    /**
     * Compares the enum to an int skill then generates and compares to Dealer
     */

    public boolean checkForWinner() {

        if (returnPlayerScore((ArrayList<Card>) playerHand) >= returnDealerScore()) {
            System.out.println("\n" + "Okay, you won. Try not to overreact." + "\n");
            pokerPlayer.addToBank(pot * 2);
        } else {
            System.out.println("\n" + "Looks like you lost this round. I can't say I'm all that surprised." + "\n");
        }

        return true;
    }

}
