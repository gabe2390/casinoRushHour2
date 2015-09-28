package io.zipcoder.casinorushhour2;

import java.util.*;

import static io.zipcoder.casinorushhour2.GameState.NOTRUNNING;
import static io.zipcoder.casinorushhour2.GameState.RUNNING;

/**
 * Created by emaron on 9/25/15.
 */
public class SevenCardStud implements CardGame {

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

        public SevenCardStud(Deck deck) {
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
            Scanner key= new Scanner(System.in);
            while (currentState == GameState.RUNNING)

            {

                dealACardToPlayer();
                dealACardToPlayer();
                dealACardToPlayer();
                dealACardToPlayer();
                dealACardToPlayer();
                dealACardToPlayer();
                dealACardToPlayer();
                System.out.println("\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMhMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMy/yMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMhmh/dMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMhdMMs+mMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMdhmmmdooNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMmsMMMMMm/sNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMNmmNMMMMmmmmNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMNmmMMmmNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMNyNNmmNNhdMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMdNdNNmmdNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMmmNNmdNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMNdmmhmMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMdhNMMMMMMMMMMMMMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNmmmmNNNNMMMMMMMMMMMMMMMMMMNNNNmmmmNNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMmy+:-......-/ohNMMMMMMMMMMMMMMNho/-......-:+ymMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMNy:.``````````````.+hNMMMMMMMMNh+.``````````````.:yNMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMh:``````.:+oyyyso+:.``:dNNNNNNd:``.:+osyyyo+:.``````:hMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMNNy.````./hmNNNMNNNNNNmh+.`sNNNNs`.+hmNNNNNNMNNNmh/.````.yNNMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMNNNNh.```./dNNMNNNNNNNNNNNNNd+`+NN+`+dNNNNNNNNNNNNNMNNd/.```.hNNNNMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMNMMNN/....oNNNMMMMMNNNNNNNNNNNNh.oo.hNNNNNNNNNNNNMMMMMNNNo..../NNMMNMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMNMMMN-.../NNNNNNNNNNNNNNNNNMNNNNd--dNNNNMNNNNNNNNNNNNNNNNN/...-NMMMNMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMNNMMN-...hNNNNNNNNNNNNNNNNMNNNNNNhhNNNNNNMNNNNNNNNNNNNNNNNh...-NMMNNMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMNMMMNNNN/...hNNNNNNNNNNNNdyo+++oymNNNNNNmyo+++oydNNNNNNNNNNNNh.../NNNNMMMNMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMNNNNNNNNh-..yNNNNNNNNNNmo:-:+ooo+/+dNNd+/+ooo+:-:omNNNNNNNNNNy..-hNNNNNNNNMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMNNNNNNNNNNNo-./NNNNNNNNNm:.:ymNNNNNmhohhohmNNNNNmy:.:mNNNNNNNNN/.-oNNNNNNNNNNNMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMNNNNNNNNNNNN+-.sNNNNNNNNy.-mNNNNNNNNNNyyNNNNNNNNNNm-.yNNNNNNNNs.-+NNNNNNNNNNNNMMMMMMMMMMM\n" +
                        "MMMMMMNNNNNNNNNNNNNNNNNNo--sNNNNNNNh./NNNNNNNNNNNNNNNNNNNNNNNN/.hNNNNNNNs--oNNNNNNNNNNNNNNNNNNMMMMMM\n" +
                        "MMMMMNNNNNNNNNMNNNNNNNNNNs--smNNmNNN/-mNNNNNNNNNNNNNNNNNNNNNNm-/NNNmNNms--sNNNNNNNNNNMNNNNNNNNNMMMMM\n" +
                        "MMMMMMMNNNNNNNNNNNNNNNNNNNh/-/dNNNNNm/+mNNNNNNNNNNNNNNNNNNNNm+/mNNNNNd/-/hNNNNNNNNNNNNNNNNNNNMMMMMMM\n" +
                        "MMMMMMMMMMNNNNNNNNNNNNNNNNNmo-:ymmmNNmo+dNNNNNNNNNNNNNNNNNNd+omNNmmmy:-omNNNNNNNNNNNNNNNNNMMMMMMMMMM\n" +
                        "MMMMMMMMMNNNNNNNNNNNNNNNNNNNNh/:odmNmmNh+yNNNNNNNNNNNNNNNNy+hNmmNmdo:/hNNNNNNNNNNNNNNNNNNNNMMMMMMMMM\n" +
                        "MMMMMMMMMNNNNNNNNNNNNNNNNNNNNNms/+hmNNNNmoodNNNNNNNNNNNNdoomNNNNmh+/smNNNNNNNNNNNNNNNNNNNNNMMMMMMMMM\n" +
                        "MMMMMMMMMNNNMNNNNNNNNNNNNNmmNNNNdo+smNNNNNh+ymNNNNNNNNmy+hNNNNNms+odNNNNmmNNNNNNNNNNNNNMNNNMMMMMMMMM\n" +
                        "MMMMMMMMMNNNNNNNNNNNNNNNNNNNNNNNNmy+odNNNNNmssmNNNNNNmssmNNNNNdo+ymNNNNNNNNNNNNNNNNNNNNNNNNMMMMMMMMM\n" +
                        "MMMMMMMMMNNNNNNNNNNNNNNNNNNNNNNNmmmdsoymNNNNNhsdNNNNdshNNNNNmyosdmmmNNNNNNNNNNNNNNNNNNNNNNNMMMMMMMMM\n" +
                        "MMMMMMMMMMNNNNNNNNNNNNNNNNNNNNNNdddmmyosdNNNNNmydNNdymNNNNNdsoymmdddNNNNNNNNNNNNNNNNNNNNNNMMMMMMMMMM\n" +
                        "MMMMMMMNNNNNNNNNNNNNNNNNNNNNNmNmh//smNdsohNNNNNmymmymNNNNNhosdNms//hmNmNNNNNNNNNNNNNNNNNNNNNNMMMMMMM\n" +
                        "MMMMMNNNNNNNNNMNNNNNNNNNNNNNNNNNmdo:+hNmysymNNNNmyymNNNNmysymNh+:odmNNNNNNNNNNNNNNNNNMNNNNNNNNNMMMMM\n" +
                        "MMMMMMNNNNNNNNNNNNNNNNNNNNNNNNNNNNNh//smNdssdNNNNmmNNNNdssdNms//hNNNNNNNNNNNNNNNNNNNNNNNNNNNNNMMMMMM\n" +
                        "MMMMMMMMMMMNNNNNNNNNNNNNNNNNNNNNNNNNmo/odNmyshNNNNNNNNhsymNdo/omNNNNNNNNNNNNNNNNNNNNNNNNNMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMNNNNNNNNNNNNNNNNNNNNNNNNNNNh+ohNNhyhNNNNNNhyhNNho+hNNNNNNNNNNNNNNNNNNNNNNNNNNNMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMNNNNNNNNNNNNNNNNNNNNNNNNNNmsoymNdydNNNNdydNmyosmNNNNNNNNNNNNNNNNNNNNNNNNNNMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMNMMMNNNNNNNNNNNNNNNNNNNNNNNNhsymNdydNNdydNmyshNNNNNNNNNNNNNNNNNNNNNNNNMMMNMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMNNMMNNNNNNNNNNNNNNNNNNNNNNmyhmNdhNNhdNmhymNNNNNNNNNNNNNNNNNNNNNNMMNNMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMNMMMNNNNNNNNNNNNNNNNNNNNNNNNdhmNddddNmhdNNNNNNNNNNNNNNNNNNNNNNNNMMMNMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMNMMNNMNMNNNNNNMMMMMNNNNNNNNNNddmNddNmddNNNNNNNNNNMMMMMNNNNNNMNMNNMMNMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMNNNNNMNNNNNNNNMNNNNNNNNNNNNNNmdmmmmdmNNNNNNNNNNNNNNMNNNNNNNNMNNNNNMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMNMMMMMNNNNNNNNNMNNNNNNNNNNNmdNNdmNNNNNNNNNNNMNNNNNNNNNMMMMMNMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMNNNNNNNNMMMNMNNNNNNNNNNNmmmmNNNNNNNNNNNMNMMMNNNNNNNNMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMNNNNNNMMMMMNNNNNMMNMMMNddNMMMNMMNNNNNMMMMMNNNNNNMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNMMNNNMMMMMMMMMmmMMMMMMMMMNNNMMNNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNNMNNNNMMMMMMMMMMMMMMMMMMNNNNMNNNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMNMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMNhmMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMmhmddMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNdmNNdmMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMmmmNNMmNdMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMymNmmmNNyMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNddmMNmdNMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNNMMMMMNNNNMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMmodMMMMNs/hNMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNymNNNd/yMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNsNNm+sMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMmyMo+NMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMdo+mMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMhdMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM" + "\n");

                System.out.println("This is the Seven Card Stud table.");
                System.out.println("Don't get in over your head." + "\n");

                promptPlayerToBet();
                checkForWinner();
                System.out.println("Do you want to play again?");

                if(!key.nextLine().equals("Y")) {
                    exitGame();
                }

                pokerDeck.getCards().addAll(playerHand);
                playerHand.clear();

            }


        }
        public void dealACardToPlayer() {
            playerHand.addAll(DEALER.dealCards(1, pokerDeck));

        }
        /**
         *
         */
        public void promptPlayerToBet() {
            int x=0;

            System.out.println("Here is your hand..." + "\n");
            for (int i = 0; i < playerHand.size(); i++) {
                System.out.print("A " + playerHand.get(i).getName() + " of " + playerHand.get(i).getSuit() + "\n");
            }
            try{
                System.out.println("\n" + "Place your bet now");
                Scanner scanner = new Scanner(System.in);
                x = scanner.nextInt();
                pokerPlayer.bet(x);
                pot = x;
            }
            catch (InputMismatchException e) {
                System.out.println("I've got a no mumbling policy at this table. I'll just take 1000 this round." + "\n");
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
            int x = (ran.nextInt(3) + 1) * 100 + (ran.nextInt(3) - 1) * 100;
            if (x == 800) {
                System.out.println("That's a nice hand you've got. Too bad it's not a Royal Flush!");
            } else if (x <= 700 && x >= 400) {
                System.out.println("I couldn't have asked for a better hand.");
            } else if (x <= 300 && x >= 200) {
                System.out.println("It's okay, some people just aren't born as lucky as me.");
            } else if (x <= 100) {
                System.out.println("I'd swear this deck was stacked if I wasn't the one shuffling it.");
            }

            return x;
        }

        /**
         * Compares the enum to an int skill then generates and compares to Dealer
         */

        public void checkForWinner() {

            if (returnPlayerScore((ArrayList<Card>) playerHand) >= returnDealerScore()) {
                System.out.println("\n" + "How quaint. Looks like you won this round." + "\n");
                pokerPlayer.addToBank(pot * 2);
            } else {
                System.out.println("\n" + "What a terrible shame. Looks like you lose." + "\n");
            }



        }


        /////////////////////Where Code Runs/////////////////////////


    }

