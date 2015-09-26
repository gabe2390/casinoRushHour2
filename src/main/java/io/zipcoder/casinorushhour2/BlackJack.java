package io.zipcoder.casinorushhour2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by ghumphrey on 9/24/15.
 * This is the class for a Game of BlackJack. It enables you to play a game if blackjack with 1 dealer and up to 5 other players, chosen at random
 */
public class BlackJack implements CardGame {

    GameState state = GameState.NOTRUNNING;
    Player player;
    Deck deck;
    Map<String, List<Card>> hands;
    String[] computerNames;

    /**
     * sets the deck field, initializes the deck, sets the player field, initialized the hands map, and creates an
     * array of String for potential computer player names
     *
     * @param deck
     */
    public BlackJack(Deck deck) {
        this.deck = deck;
        deck.init();

        player = deck.getPlayer();

        hands = new HashMap<String, List<Card>>();
        computerNames = new String[]{"Player2", "Player3", "Player4", "Player5", "Player6"};
    }

    /**
     * game loop that calls methods to run game logic, betting, winning, and terminating the game
     */
    public void playGame() {
        Scanner key = new Scanner(System.in);
        boolean wantToHit = true;
        state = GameState.RUNNING;

        int currentPot = 0;

        setAllCardPoints(deck);
        printJack();

        //game loop
        while (state == GameState.RUNNING) {
            //cards dealt
            //bet
            //askForHit ---move logic
            //done hitting
            //dealer hits if necessary
            //determine winner
            //if player is winner add to their bank

            dealCards();
            currentPot= askToBet(key);

            while(wantToHit){
                if(evaluatePoints(hands.get(player.getName())) < 21){
                    wantToHit=askForHit(key);
                }
                else{
                    break;
                }
            }

            if(checkForWinner())
            while (wantToHit && evaluatePoints(hands.get(player.getName())) < 21) {
                if (handContainsAce(hands.get(player.getName()))) {
                    System.out.println("Your cards: " + hands.get(player.getName()) + " You have " + evaluatePoints(hands.get(player.getName()))
                            + " or " + alternateAcePoints(evaluatePoints(hands.get(player.getName()))) + " points.");
                    System.out.println("Would you like to hit?");
                } else {
                    System.out.println("Your cards: " + hands.get(player.getName()) + " You have " + evaluatePoints(hands.get(player.getName()))
                            + " points.");
                    System.out.println("Would you like to hit?");
                }
                if (key.nextLine().equalsIgnoreCase("Y")) {
                    List<Card> newHand = hands.get(player.getName());
                    newHand.addAll(DEALER.dealCards(1, deck));
                    hands.put(player.getName(), newHand);
                } else {
                    wantToHit = false;
                }

            }
            /**
             * Add case for going over
             */
            System.out.println("Your cards: " + hands.get(player.getName()) + " You have " + evaluatePoints(hands.get(player.getName())) + " points.");
            System.out.println("Dealer has " + DEALER.getHand());


            while (evaluatePoints(DEALER.getHand()) < (evaluatePoints(hands.get(player.getName()))) && evaluatePoints(hands.get(player.getName())) <= 21) {
                System.out.println("DEALER HIT!!!!\n");
                DEALER.addToHand(DEALER.dealCards(1, deck));
                System.out.println("Dealer cards: " + DEALER.getHand() + " Dealer has " + evaluatePoints(DEALER.getHand()));
            }


            if ((evaluatePoints(DEALER.getHand()) > evaluatePoints(hands.get(player.getName())) && evaluatePoints(DEALER.getHand()) <= 21) || (evaluatePoints(hands.get(player.getName())) > 21)) {
                System.out.println("DEALER WON! YOU LOST " + currentPot + " DOLLARS\n");
            } else if (evaluatePoints(DEALER.getHand()) < evaluatePoints(hands.get(player.getName())) && evaluatePoints(hands.get(player.getName())) <= 21 || evaluatePoints((DEALER.getHand())) > 21) {

                player.addToBank(currentPot * 2);
                System.out.println(player.getName() + " WON " + (currentPot * 2));
            } else {
                player.addToBank(currentPot);
                System.out.println("It was a tie! Here's your money back... scrub.");
            }


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

    public boolean checkForWinner() {

        return false;
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
        if (key.nextLine().equalsIgnoreCase("Y")) {
            return true;
        }
        return false;
    }

    /**
     * initial dealing of cards for the user, each computer player, and the dealer
     */
    private void dealCards() {

        DEALER.shuffleDeck(deck);

        hands.put(player.getName(), DEALER.dealCards(2, deck));

        for (int i = 0; i < ((int) (Math.random() * 6) +1); i++) {
            hands.put(computerNames[i], DEALER.dealCards(2, deck));
        }

        DEALER.addToHand(DEALER.dealCards(2, deck));

        for(String name: hands.keySet()){
            System.out.println("Name: "+ name + " Hand: "+ hands.get(name));
        }

        System.out.println("Dealer's hand: " + DEALER.getHand().get(0) + " face up, and one card face down");

    }
    private int askToBet(Scanner key) {
        int bet;

        System.out.println("Your bank total is $" + player.getBank() + " dollars. ");
        System.out.println("Please enter bet amount as an Integer:");
        bet= player.bet(Integer.parseInt(key.nextLine()));
        System.out.println("Your bank is now at $"+player.getBank());
        return bet;
    }

    /**
     * evaluate the points in a player's hand
     *
     * @param hand
     * @return
     */
    private int evaluatePoints(List<Card> hand) {
        int points = 0;

        for (int i = 0; i < hand.size(); i++) {
            points += hand.get(i).getValue();
        }
        return points;
    }

    /**
     * returns the soft hand points of a player's hand
     *
     * @param i
     * @return
     */
    private int alternateAcePoints(int i) {
        if (i < 11) {
            return i + 10;
        } else {
            return i;
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
    public boolean giveCardsBack(List<Card> hand) {
        int i = deck.getCards().size();
        deck.getCards().addAll(hand);
        return deck.getCards().size() == i + hand.size();
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
