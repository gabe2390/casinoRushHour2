package io.zipcoder.casinorushhour2;

import java.util.*;

/**
 * Created by ghumphrey on 9/24/15.
 * This is the class for a Game of BlackJack. It enables you to play a game if blackjack with 1 dealer and up to 5 other players, chosen at random
 */
public class BlackJack implements CardGame {

    GameState state = GameState.NOTRUNNING;
    Player player;
    Deck deck;
    Map<String, List<Card>> hands;
<<<<<<< HEAD
    String[] computerNames;

    /**
     * sets the deck field, initializes the deck, sets the player field, initialized the hands map, and creates an
     * array of String for potential computer player names
     *
     * @param deck
     */
=======
    int currentPot;

>>>>>>> eb8147356373fc3cd53917a2ba0bb450111aef78
    public BlackJack(Deck deck) {
        this.deck = deck;
        deck.init();

        player = deck.getPlayer();

        hands = new LinkedHashMap<String, List<Card>>();
        computerNames = new String[]{"Player2", "Player3", "Player4", "Player5", "Player6"};
    }

    /**
     * game loop that calls methods to run game logic, betting, winning, and terminating the game
     */
    public void playGame() {
        Scanner key = new Scanner(System.in);
        boolean wantToHit = true;
        state = GameState.RUNNING;
<<<<<<< HEAD
=======

>>>>>>> eb8147356373fc3cd53917a2ba0bb450111aef78

        int currentPot = 0;

        setAllCardPoints(deck);
        printJack();

        //game loop
        while (state == GameState.RUNNING) {
            int playerPoints=0;
            //cards dealt
            //bet
            //askForHit ---move logic
            //done hitting
            //dealer hits if necessary
            //determine winner
            //if player is winner add to their bank

<<<<<<< HEAD
            currentPot = askToBet(key);
=======
            int totalPoints = evaluatePoints(hands.get(player.getName()));

            System.out.println("Dealer has a " + DEALER.getHand().size() + " cards. " + DEALER.getHand().get(0) + " face up ");
>>>>>>> eb8147356373fc3cd53917a2ba0bb450111aef78

            dealCards();

<<<<<<< HEAD
            printPoints(hands.get(player.getName()));

            while (wantToHit) {
                if (evaluatePoints(hands.get(player.getName())) < 21) {
                    wantToHit = askForHit(key);
=======
            while (wantToHit && evaluatePoints(hands.get(player.getName())) < 21) {


                System.out.println("Your cards: " + hands.get(player.getName()) + " You have " + evaluatePoints(hands.get(player.getName())) + " points.");
                System.out.println("Would you like to hit?");



                if (key.nextLine().equalsIgnoreCase("Y")) {
                    List<Card> newHand = hands.get(player.getName());
                    newHand.addAll(DEALER.dealCards(1, deck));
                    hands.put(player.getName(), newHand);
>>>>>>> eb8147356373fc3cd53917a2ba0bb450111aef78
                } else {
                    playerPoints= evaluatePoints(hands.get(player.getName()));
                    break;
                }
<<<<<<< HEAD
            }

            if(evaluatePoints(hands.get(player.getName())) >21){

            }
            printPoints(hands.get(player.getName()));

            if (checkForWinner()) {
                player.addToBank(currentPot);
            }

            giveCardsBack(DEALER.getHand());
            giveCardsBack(hands);

            /**
             * Add case for going over
             */
=======
                for( Card card: hands.get(player.getName())) {
                    if(card.getName().equals("Ace")){
                        System.out.println("working");
                    }

                };

            }
            if (evaluatePoints(hands.get(player.getName())) > 21) {
                System.out.println("Your cards: " + hands.get(player.getName()) + " BUST! with " + evaluatePoints(hands.get(player.getName())) + " points.");
            } else {
                System.out.println("Your cards: " + hands.get(player.getName()) + " You have " + evaluatePoints(hands.get(player.getName())) + " points.");
            }

            System.out.println("Dealer has " + DEALER.getHand());

>>>>>>> eb8147356373fc3cd53917a2ba0bb450111aef78

            while (evaluatePoints(DEALER.getHand()) < (evaluatePoints(hands.get(player.getName()))) && evaluatePoints(hands.get(player.getName())) <= 21) {
                System.out.println("DEALER HIT!!!!\n");
                DEALER.addToHand(DEALER.dealCards(1, deck));
                System.out.println("Dealer cards: " + DEALER.getHand() + " Dealer has " + evaluatePoints(DEALER.getHand()));
            }


            checkForWinner();

            System.out.println("Your bank is now " + player.getBank());


            state = GameState.NOTRUNNING;
        }


        System.out.println("Do you want to play again?");


        if (key.nextLine().equalsIgnoreCase("Y")) {

            giveCardsBack(DEALER.getHand());
            giveCardsBack(hands.get(player.getName()));

            DEALER.getHand().clear();
            hands.get(player.getName()).clear();

            playGame();
        } else {
            System.out.println("Thanks for playing Black Jack, come again soon and give me all of your money!");
        }
    }

    public void changeGameState() {

    }

    public void exitGame() {

    }

<<<<<<< HEAD
    public boolean checkForWinner() {

        return false;
=======

    public void checkForWinner() {
        if ((evaluatePoints(DEALER.getHand()) > evaluatePoints(hands.get(player.getName())) && evaluatePoints(DEALER.getHand()) <= 21) || (evaluatePoints(hands.get(player.getName())) > 21)) {
            System.out.println("DEALER WON! YOU LOST " + currentPot + " DOLLARS\n");
        } else if (evaluatePoints(DEALER.getHand()) < evaluatePoints(hands.get(player.getName())) && evaluatePoints(hands.get(player.getName())) <= 21 || evaluatePoints((DEALER.getHand())) > 21) {

            player.addToBank(currentPot * 2);
            System.out.println(player.getName() + " WON " + (currentPot * 2));
        } else {
            player.addToBank(currentPot);
            System.out.println("It was a tie! Here's your money back... scrub.");
        }
        currentPot = 0;
>>>>>>> eb8147356373fc3cd53917a2ba0bb450111aef78
    }

    /**
     * sets the point values for each card in blackjack
     *
     * @param deck
     */
    private void setAllCardPoints(Deck deck) {
        for (int i = 0; i < deck.getCards().size(); i++) {
            if (deck.getCards().get(i).getName().equals("Ace")) {
                deck.getCards().get(i).setValue(1);
            } else if (deck.getCards().get(i).getName().equals("Jack") || deck.getCards().get(i).getName().equals("Queen") || deck.getCards().get(i).getName().equals("King")) {
                deck.getCards().get(i).setValue(10);
            } else {
                deck.getCards().get(i).setValue(Integer.valueOf(deck.getCards().get(i).getName()));
            }
        }
    }

    /**
     * Adds a single card if the user enters 'y', and returns a true if a card was added
     *
     * @param key
     * @return boolean
     */
    private boolean askForHit(Scanner key) {
        System.out.println("Do you want to hit?\n");

        if (key.nextLine().equalsIgnoreCase("Y")) {
            hands.get(player.getName()).addAll(DEALER.dealCards(1, deck));
            System.out.println("Your hand: ");
            printPoints(hands.get(player.getName()));
            return true;
        } else {
            System.out.println();
            return false;
        }
    }

    /**
     * initial dealing of cards for the user, each computer player, and the dealer
     */
    private void dealCards() {

        DEALER.shuffleDeck(deck);
        hands.put(player.getName(), DEALER.dealCards(2, deck));

        for (int i = 0; i < ((int) (Math.random() * 6) + 1); i++) {
            hands.put(computerNames[i], DEALER.dealCards(2, deck));
        }

        DEALER.addToHand(DEALER.dealCards(2, deck));

        for (String name : hands.keySet()) {
            System.out.println("Name: " + name + " Hand: " + hands.get(name));
        }

        System.out.println("Dealer's hand: " + DEALER.getHand().get(0) + " face up, and one card face down");

    }

    /**
     * asks player to bet, removes the betting amount specified, and then returns that amount to be added to the current pot
     *
     * @param key
     * @return
     */
    private int askToBet(Scanner key) {
        int bet;

        System.out.println("Your bank total is $" + player.getBank() + " dollars. ");
        System.out.println("Please enter bet amount as an Integer:");
        bet = player.bet(Integer.parseInt(key.nextLine()));
        System.out.println("Your bank is now at $" + player.getBank());
        return bet;
    }

    /**
     * evaluate the points in a player's hand
     *
     * @param hand
     * @return
     */
    private int evaluatePoints(List<Card> hand){
        int points = 0;
        if(evaluatePoints() )

        for (int i = 0; i < hand.size(); i++) {
            points += hand.get(i).getValue();
        }
        return points;
    }

<<<<<<< HEAD
    /**
     * returns the soft hand points of a player's hand
     *
     * @param i
     * @return
     */
=======
>>>>>>> eb8147356373fc3cd53917a2ba0bb450111aef78
    private int alternateAcePoints(int i) {
        if (i < 11) {
            return i + 10;
        } else {
            return i;
        }
    }

<<<<<<< HEAD
    /**
     * prints all the cards in a player's or dealer's hand, as well as their points
     *
     * @param cards
     */
    private void printPoints(List<Card> cards) {
        if (handContainsAce(cards) && evaluatePoints(cards) <= 11) {
            System.out.println("Your cards: " + cards + " You have " + evaluatePoints(cards)
                    + " or " + alternateAcePoints(evaluatePoints(cards)) + " points.");
        } else {
            System.out.println("Your cards: " + cards + " You have " + evaluatePoints(cards) + " points.");
        }
    }

    /**
     * returns true if a player's hand contains an Ace
     *
     * @param cards
     * @return
     */
    private boolean handContainsAce(List<Card> cards) {
        boolean hasAce = false;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName().equals("Ace")) {
                hasAce = true;
            }
        }
        return hasAce;
    }

    /**
     * takes the cards from a player's hand and returns them to the deck
     *
     * @param hand
     * @return
     */
=======

>>>>>>> eb8147356373fc3cd53917a2ba0bb450111aef78
    public boolean giveCardsBack(List<Card> hand) {
        int deckSize = deck.getCards().size();

        for (int i = 0; i < hand.size(); i++) {
            deck.getCards().add(hand.remove(0));
        }

        return deck.getCards().size() == deckSize + hand.size();
    }

    /**
     * Returns the cards from every player in a map back to the deck
     *
     * @param hands
     * @return
     */
    private boolean giveCardsBack(Map<String, List<Card>> hands) {
        int deckSize = deck.getCards().size();
        int allPlayersCards = 0;

        for (String names : hands.keySet()) {
            allPlayersCards += hands.get(names).size();
            giveCardsBack(hands.get(names));
        }

        return deck.getCards().size() == deckSize + allPlayersCards;
    }


    /**
     * prints welcome message and ascii art jack of spades to the console
     */
    public void printJack() {
        String jack = "                                                       \n" +
                "                                                            \n" +
                "                                                            \n" +
                "         .................................,..,,,,,.    ,        \n" +
                "         ; ```        #;@;;;+#;;;;#;+;;;';+        +        \n" +
                "         ;            ,#;#''@+''''@''''@;+         +        \n" +
                "         ;      '@     +'@''@'''''+#''''+`         +        \n" +
                "         ;      @#,     @'@@@'+'''''+#@'+      ,,: +        \n" +
                "         ;     @@@@     +'''+####+++##++      ':,, +        \n" +
                "         ;    @@@@@@     +##;,,:;     `       ;;,, +        \n" +
                "     @   '   :@@@@@@+    ':,:'+'` ;: `         ,+. +        \n" +
                "     #@  '   @@@@@@@@    :'+':,: ` ,:'`        .:  +        \n" +
                "    @@@' '  @@@@@@@@@@  +',,:'+,  +`; .        :#: +        \n" +
                "   `@@@@ '  @@@@@@@@@@ `:'#';:,     ,  `      +#;, +        \n" +
                "   ,@@@@ '  @@@@@@@@@@.'#',;#;+      . ,       ;:  +        \n" +
                "    ..   '  @@@@.;@@@@`:,:'+''+:     `'.       ,,  +        \n" +
                "     @@  ;  @@@@ #@@@@ @;:'':,:,  +: +         ,:' +        \n" +
                "         ;   ;' +@ '' #'+':,;+'.  :+..+       ;;,: +        \n" +
                "         ;      @@   ,++,,:#',        :       '+,, +        \n" +
                "         '     :@@@  ,:;@;:,:; `      ;        ',. +        \n" +
                "         '         +'`'@##'+++++++;``;@       ',   +        \n" +
                "         ;+,     @#+;  @+::::::;:;;;:@,:'      `:. +        \n" +
                "         '++++ #'@#' + @+#+'@,@,@,@:@@,#++  ,  `:  +        \n" +
                "         '+++,+#'@#'.` @#''''''''+++@,#,@@;#  ..`; +        \n" +
                "         '+++ +@#@#++  @#''';:'''''@;:'#'@@#'` ;.+ +        \n" +
                "         '+++ #'###'`  @#'';:;:'''@@,#; '++@@;# ', +        \n" +
                "         '+++ #+'@:#;+ @#'''''''''@,', ':+#;@@#;,  +        \n" +
                "         '+++ #';'+:; `@#'+''''''@',#+',+:#++'@@'+ +        \n" +
                "         ;+++ '@'@+:,#@@@@@@@@#'#@:#, ;+#:# #''@@@;#        \n" +
                "         ;+++ +'+#;',,@'@#+@@@@#@,;:.+:`,@++#,+#'@@#        \n" +
                "         ;+++ +@+'+@+':@+#@#''@@@@@', +'+#: `,+,+'##        \n" +
                "         '+++:+'+,;',:,#+'@#@@@''@@ . #,++,++'##:+##        \n" +
                "         '++++.'#;@;#+@,+''#''+@#@@`'+,+++;#'#,#,++#        \n" +
                "         +,#++ +'+,:;;;@'''#+'+;'@@#+, +++#:, :###:@        \n" +
                "         +.+,#+:@+#+:';+'''+''+''@@#+'+; ++,+++,+#;#        \n" +
                "         ':++,@ +@#;#@+@:@+#++#++@@#''''+ ;#, +@,+,#        \n" +
                "         +@ +',# +'+:#;::@@@#;;;:@@',;+'++:.;#, #:++        \n" +
                "         +,@::+,+,#++':;:'@,,::::,@'++,++'#::,#+ #,#        \n" +
                "         '+:#+ #,,:++'#,:#@+',,,,@@@+'@#:+'#`#,++:#@        \n" +
                "         '##;@+++#:`#+#'@'@@@@@@@@@@,@';@,'+# #,# ++        \n" +
                "         '#;+,#:+,++ +###+@@'+''+''';#;+'@'''+,#,+,+        \n" +
                "         ':;,+,+ @+++, ++#@@'@''+''++'::+:;#++ ++',#        \n" +
                "         +#;'+'@++,+++'+  #@'@''@''@''''++@,''+;+++@        \n" +
                "         '#'#:+, ';#+#:, +#@#'+'@''#'#;#'@#;#'+ ++++        \n" +
                "         '@'+:#:#;##+,+++ ,@@@@'@'+@'+;',';@'## ++++        \n" +
                "         '@@'#+#@++;#+++;##,@@@@#@+'@'''+,';,++,++++        \n" +
                "         ;'@@@'#'`.:,+`;`:':@'#@@@@@@@@@@'#@,#;;++++        \n" +
                "         ;,'+@@+#++,#+' :#,@+'''''#@+@@+@+;+,+'+++++        \n" +
                "         ;  #'@@@':; :+++,+@'''' ''''@`+ #@@,+#+'+++        \n" +
                "         ;   .+'@@++`, ,#,@''':   '''@`+;++',+''++++        \n" +
                "         ; ,`+ +'#@@'#'#,@@''''' ''''@`++. @,#;:++++        \n" +
                "         ; ;`+.  +'@@@;:;@++#########@`++` @'+# ++++        \n" +
                "         ; ; +;  .:'+,#,@########@@@#@`+ + @';+,++++        \n" +
                "         ; : ++ ,   ##,#@:@;@++@;@'@;@`+,' @',   +++        \n" +
                "         ;  +; ;     .:@+#+++++######@`++  '       '        \n" +
                "         ;  ,+              ',,,;;,:#@:,+; ++'`    '        \n" +
                "         : ;;++               `,+';,,,,,'  +#@     '        \n" +
                "         , ',,,       ..,      ;,:';+;',.   @.     '        \n" +
                "         , ;':'       ' +`   `,,'+#,+@' @@@`@ @##. ;  `+`   \n" +
                "         ,  :,        `:  .  ',:;;#+''`;@@@@@#@@@@ ;   @`   \n" +
                "         ,  ';       :  `    .,#'+;#,+,@@@@@@@@@@@ ; @@@@@  \n" +
                "         ,  :@+          ;    `'+#':,, ;@@@@@@@@@@ ; @@@@@  \n" +
                "         , '##;       ' '.    +:,,:;+'  @@@@@@@@@+ ;  @@@   \n" +
                "         ,  ',        . +''   :'++;:,   #@@@@@@@@  ;  #@#   \n" +
                "         .  +,        :`:: + +;,,,;''    @@@@@@@:  ;   @    \n" +
                "         . `+;'       ,:++`  ,:;++':      @@@@@@   ;        \n" +
                "         . +:,,       #+;,..:+#@#@@++     '@@@@    :   .,   \n" +
                "         . :++:      +'';'+++''';'++@      @@@     :  @;;@  \n" +
                "         .  ;+      ;+@#@@'@@@@@+';@;+      #'     :  @  @  \n" +
                "         .          @'#''@'#'''@''''+#      +      : `+  `  \n" +
                "         .         #'@'''@''';;@;#;'#;+            :       \n" +
                "         `+++++++++++'''';;;;;;:::::,,,,,,,,,,,...``        \n" +
                "                                                            \n" +
                "                                                            \n" +
                "                                                          \n" +
                "                                                            ";

        System.out.println("Welcome to BlackJack!");
        System.out.println(jack);
    }

    public static void main(String[] args) {
        BlackJack j = new BlackJack(new Deck(new Player("Gabe")));
        j.playGame();
    }
}
