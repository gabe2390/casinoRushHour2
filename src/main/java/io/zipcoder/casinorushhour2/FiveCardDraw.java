package io.zipcoder.casinorushhour2;

import java.util.*;

import static io.zipcoder.casinorushhour2.GameState.NOTRUNNING;
import static io.zipcoder.casinorushhour2.GameState.RUNNING;

/**
 * Created by emaron on 9/25/15.
 */
public class FiveCardDraw implements CardGame {


    /**
     * Enum of the kinds of possible Poker Hands
     */
    public enum Kinds {
        PAIR, THREEKINDS, FOURKINDS, TWOPAIRS, FULLHOUSE, STRAIGHT, FLUSH, NOFLUSH, NOGOODCARDS
    }

    /**
     * Required fields to interact with the player
     */

    //public Deck pokerDeck;

    Player player = new Player("Evan");

    Deck pokerDeck = new Deck(player);


    public Player pokerPlayer;

    // public List<Card> cards;

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

    public FiveCardDraw(Deck deck) {
        pokerDeck = deck;
        pokerDeck.init();
        playerHand = new ArrayList<Card>();

        pokerPlayer = pokerDeck.getPlayer();
        DEALER.shuffleDeck(pokerDeck);

        DEALER.shuffleDeck(pokerDeck);


    }


    /**
     * Changes game state to running
     */


    public void playGame() {
        currentState = RUNNING;
        Scanner key = new Scanner(System.in);
        while (currentState == GameState.RUNNING)

        {
            //playerHand.addAll(DEALER.dealCards(5, pokerDeck));
            dealACardToPlayer();
            dealACardToPlayer();
            dealACardToPlayer();
            dealACardToPlayer();
            dealACardToPlayer();
            System.out.println(" /syyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyys/  \n" +
                    "`mMMNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNMMm`\n" +
                    "-MMm`  `    `y--..-`-                                                                          `mMM-\n" +
                    "-MMd` -..+.sdmshyNMy` `                                                                         hMM-\n" +
                    "-MMd``+soNNmmmddhNMNhy-                                                                         hMM-\n" +
                    "-MMd .oNMm/+//-dNsMh/.                                                                          hMM-\n" +
                    "-MMd  oMMods+.`/MhMMd:                                                                          hMM-\n" +
                    "-MMd `oMMmmhdo+ hmMMMo`                                                                         hMM-\n" +
                    "-MMd  hMMmyh/.o`/MMMMh.                                                                         hMM-\n" +
                    "-MMd +.sNmddhoy+`ddNNs:`                                                                        hMM-\n" +
                    "-MMd`` :dsooMNMm.omm/./`                                                                        hMM-\n" +
                    "-MMd   -//NoyNNs/-y+ ``                                                                         hMM-\n" +
                    "-MMd      m.-:so` `: `                                                                          hMM-\n" +
                    "-MMd      m-  -y   `                                                                            hMM-\n" +
                    "-MMd      d-   h-                                                                               hMM-\n" +
                    "-MMd      y:   -/                                                                               hMM-\n" +
                    "-MMd      o/                                                                                    hMM-\n" +
                    "-MMd      -s                                                                                    hMM-\n" +
                    "-MMd       s                                                                                    hMM-\n" +
                    "-MMd       `                                                                                    hMM-\n" +
                    "-MMd                                                                                            hMM-\n" +
                    "-MMd                                                                                            hMM-\n" +
                    "-MMd                                                    ``                                      hMM-\n" +
                    "-MMd                                                                                            hMM-\n" +
                    "-MMd                                      `         `  ` `                                      hMM-\n" +
                    "-MMd                                `              s:`.``            .`                         hMM-\n" +
                    "-MMd                                       .` ``-`-++y-`             :                          hMM-\n" +
                    "-MMd                             ` `` `   `-.        ```.                                       hMM-\n" +
                    "-MMd                          ``         `                           o-                         hMM-\n" +
                    "-MMd                               `   ``.                  ` `     `-`                         hMM-\n" +
                    "-MMd                     `         `   ./                  ```  `` `o`                          hMM-\n" +
                    "-MMd                          ::` -.  .-/                  -dy-.` :-`  ``                       hMM-\n" +
                    "-MMd                       `` `yo`m+`-sdh                  :MMMy.s/.. :sd`                      hMM-\n" +
                    "-MMd                        ``.-sysoymMMM/                `mMMMMMms-ho/:-                       hMM-\n" +
                    "-MMd                      `   `:yMMMMNNmmm+              .hmmmNNMMNoso-.                        hMM-\n" +
                    "-MMd                     .-`   /MMh+-`    ``             `     .:sNMyy-  o+                     hMM-\n" +
                    "-MMd                     .:-``./o.                                `+NNN+```                     hMM-\n" +
                    "-MMd                      ``  oo                                    .mMM+                       hMM-\n" +
                    "-MMd                         `/                                      .-.`                       hMM-\n" +
                    "-MMd                       .ss:                                       ` +:.`                    hMM-\n" +
                    "-MMd                      /:-:-                                      `d:-..+`                   hMM-\n" +
                    "-MMd                      `-oy/                                      sho`                       hMM-\n" +
                    "-MMd                      .:yyo`               .:  `/              `oo+ys:-`                    hMM-\n" +
                    "-MMd                   `-sdoydNhso-          .oN/   mh:`         `++h:.``--`-..                 hMM-\n" +
                    "-MMd                   `  `...//:soyyo//:/+ymNMM`   sMMNho+/:/+sdNhy-` .``    ``                hMM-\n" +
                    "-MMd                         :  `.:+shd+mMMNhs+s    -MNNmmddNNNmMhso:`            `             hMM-\n" +
                    "-MMd                       ` `        `.-/:`` --     hNdNNN/ss+oM:o/.                           hMM-\n" +
                    "-MMd                                 `       -+      -NMNss:`o:.d+```` `-`:                     hMM-\n" +
                    "-MMd                                 .     `:/        -hy.`--.+ y/   `  ` `                     hMM-\n" +
                    "-MMd                                `       `  +s-.s/ :s..  .`- /                            `  hMM-\n" +
                    "-MMd                                           m:``s/`mo    - . +                               hMM-\n" +
                    "-MMd                                           m   +/ :`    -`  +`                              hMM-\n" +
                    "-MMd                                           d   /o       `+  .                               hMM-\n" +
                    "-MMd                                           h.  :/       `o                                  hMM-\n" +
                    "-MMd                                           o-  `         ..                                 hMM-\n" +
                    "-MMd                                           ``                                               hMM-\n" +
                    "-MMd                                                                                            hMM-\n" +
                    "-MMd                                                                                    .       hMM-\n" +
                    "-MMd                                                                                    y       hMM-\n" +
                    "-MMd                                                                                    y`      hMM-\n" +
                    "-MMd                                                                               `    o:      hMM-\n" +
                    "-MMd                                                                               o-   +o      hMM-\n" +
                    "-MMd                                                                               -y   +y      hMM-\n" +
                    "-MMd                                                                            .   d`  /y      hMM-\n" +
                    "-MMd                                                                            /` -h+s./y      hMM-\n" +
                    "-MMd                                                                         ```sy:/hNNshmyy-   hMM-\n" +
                    "-MMd                                                                        `+.yMMNhdmddhmMd.`- hMM-\n" +
                    "-MMd                                                                        `-hMMm/+:--sMyNNy:: hMM-\n" +
                    "-MMd                                                                        ``dMMsdys-..MdNMM+  hMM-\n" +
                    "-MMd                                                                         `sNMmmhhoy omMMM:  hMM-\n" +
                    "-MMd                                                                          -yMNyd+./.-MMMM/  hMM-\n" +
                    "-MMd                                                                          ++hmhdhhsh sddN+. hMM-\n" +
                    "-MMd                                                                        `:sydysoMNNMs/h-o-``hMM-\n" +
                    "-MMd                                                                           `omd++:ho- : ``  hMM-\n" +
                    "-MMd                                                                           .`   `.+         hMM-\n" +
                    "-MMm`                                                                                          `dMM-\n" +
                    "`mMMNmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmNMMm`\n" +
                    "  /syyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyys/ " + "\n");

            System.out.println("Welcome to the Five Card Draw table.");
            System.out.println("Try to keep a level head." + "\n");

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

    public void dealACardToPlayer() {
        playerHand.addAll(DEALER.dealCards(1, pokerDeck));

    }

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


        System.out.println("I hope you're happy with what you got. Here is your new hand...");
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.print("A " + playerHand.get(i).getName() + " of " + playerHand.get(i).getSuit() + "\n");
        }

    }

    /**
     *
     */

    public void promptPlayerToBet() {
        int x = 0;

        System.out.println("Here is your hand..." + "\n");
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.print("A " + playerHand.get(i).getName() + " of " + playerHand.get(i).getSuit() + "\n");
        }

        System.out.println("\n" + "Anything good? I'll exchange whatever you don't want." + "\n");
        takeCardsFromPlayer();

        try {
            System.out.println("\n" + "How much do you want to gamble?");
            Scanner scanner = new Scanner(System.in);
            x = scanner.nextInt();
            pokerPlayer.bet(x);
            pot = x;
        } catch (InputMismatchException e) {
            System.out.println("I'll take that gibberish to mean you're betting a 1000" + "\n");
            x = 1000;
            pokerPlayer.bet(x);
            pot = x;
        }

    }


    /**
     * Changes the GameState to the opposite of it's current state
     */

    public void changeGameState() {
        if (state == NOTRUNNING) {
            currentState = RUNNING;
        } else {
            currentState = NOTRUNNING;
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
        int x = (ran.nextInt(1) + 1) * 100 + (ran.nextInt(1) - 1) * 100;
        if (x == 800) {
            System.out.println("I'll try to not to gloat when I win this round.");
        } else if (x <= 700 && x >= 400) {
            System.out.println("I couldn't have wished for a better hand.");
        } else if (x <= 300 && x >= 200) {
            System.out.println("Nobody gets a hand as good as this. Unless you're me of course.");
        } else if (x <= 100) {
            System.out.println("Another hand as bad as this and I just might quit.");
        }


        return x;
    }

    /**
     * Compares the enum to an int skill then generates and compares to Dealer
     */

    public boolean checkForWinner() {

        if (returnPlayerScore((ArrayList<Card>) playerHand) >= returnDealerScore()) {
            System.out.println("\n" + "Yeah, yeah, you won. No big deal." + "\n");
            pokerPlayer.addToBank(pot * 2);
        } else {
            System.out.println("\n" + "Looks like you lost this one. No hard feelings though, right?" + "\n");
        }

        return true;
    }


    /////////////////////Where Code Runs/////////////////////////


}

