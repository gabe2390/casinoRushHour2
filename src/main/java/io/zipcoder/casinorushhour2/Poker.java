package io.zipcoder.casinorushhour2;

import com.sun.tools.internal.ws.wsdl.document.Kinds;

import java.util.*;

import static io.zipcoder.casinorushhour2.GameState.*;

/**
 * Created by emaron on 9/24/15.
 */
public class Poker implements CardGame {

    Player player;
    Deck pokerDeck;
    GameState state = GameState.NOTRUNNING;
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
    public Poker(Deck deck) {
        pokerDeck = deck;
        pokerDeck.init();
        player = deck.getPlayer();
        playerHand = new ArrayList<Card>();
    }


    /**
     * Changes game state to running
     */


    public void playGame() {
        Scanner key = new Scanner(System.in);

        DEALER.shuffleDeck(pokerDeck);
        DEALER.shuffleDeck(pokerDeck);

        state = GameState.RUNNING;

        while (state == GameState.RUNNING) {
            printImage();

            System.out.println("Welcome to the Five Card Stud table.");
            System.out.println("Don't lose your head." + "\n");

            DEALER.shuffleDeck(pokerDeck);
            DEALER.shuffleDeck(pokerDeck);
            dealACardToPlayer(5);

            printCards(playerHand);
            pot = askToBet(key);


            if (checkForWinner()) {
                player.addToBank(pot);
                System.out.println("\n" + "Well now, look at that. You won!" + "\n");
            } else {
                System.out.println("\n" + "Sorry kiddo, you lost! Looks like you're all outta luck." + "\n");
            }

            System.out.println(giveCardsBack(playerHand));

            System.out.println("Do you want to play again?");

            if (!key.nextLine().equals("Y")) {
                exitGame();
            }
        }
    }

    private void printImage() {
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
    }

    public void dealACardToPlayer(int i) {
        playerHand.addAll(DEALER.dealCards(i, pokerDeck));
    }

    public boolean giveCardsBack(List<Card> hand) {
        int deckSize = pokerDeck.getCards().size();

        for (int i = 0; i < hand.size(); i++) {
            pokerDeck.getCards().add(hand.remove(0));
        }
        hand.clear();

        return pokerDeck.getCards().size() == deckSize + hand.size();
    }


    private int askToBet(Scanner key) {
        int bet;

        System.out.println("Your bank total is $" + player.getBank() + " dollars.");
        System.out.println("Please enter bet amount as an Integer:");

        bet = player.bet(Integer.parseInt(key.nextLine()));

        System.out.println("Your bank is now at $" + player.getBank());
        return bet;
    }

    private void printCards(List<Card> cards) {
        System.out.println(cards);
    }

    /**
     * Leaves the current Poker game
     */

    public void exitGame() {
        System.out.println("\n" + "Ending Poker");
        state = NOTRUNNING;
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

    public int returnDealerScore() {
        Random ran = new Random();
        int x = (ran.nextInt(1) + 1) * 100 + (ran.nextInt(3) - 1) * 100;
        if (x == 800) {
            System.out.println("Tough luck chump, I got a Royal Flush!");
        } else if (x <= 700 && x >= 400) {
            System.out.println("Don't take it personal when I win this round, not everyone is as lucky as me.");
        } else if (x <= 300 && x >= 200) {
            System.out.println("I've got a knack for getting just what I need when I need it.");
        } else if (x <= 100) {
            System.out.println("The world needs its losers. I just never thought I'd be one.");
        }


        return x;
    }

    /**
     * Compares the enum to an int skill then generates and compares to Dealer
     */

    public boolean checkForWinner() {
        return returnPlayerScore((ArrayList<Card>) playerHand) >= returnDealerScore();
    }


    /////////////////////Where Code Runs/////////////////////////

    /**
     * Enum of the kinds of possible Poker Hands
     */
    enum Kinds {
        PAIR, THREEKINDS, FOURKINDS, TWOPAIRS, FULLHOUSE, STRAIGHT, FLUSH, NOFLUSH, NOGOODCARDS
    }

    public static void main(String[] args) {
        Player p = new Player("Gabe");
        Poker poker = new Poker(new Deck(p));
        poker.playGame();
    }
}











