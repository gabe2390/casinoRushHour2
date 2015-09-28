package io.zipcoder.casinorushhour2;

import java.util.Scanner;

/**
 * Created by Gabriel Humphrey on 9/23/15.
 * This is the class for a game of RussianRoulette. Almost all of the functionality is contained in the playGame()
 * that contains the game loop and calls other method to determine other actions.
 */
public class RussianRoulette implements Game {
    Gun gun;
    Player player;
    GameState state = GameState.RUNNING;
    int count = 0;

    public RussianRoulette(Gun gun) {
        this.gun = gun;
        this.player = this.gun.getPlayer();
        gun.init();
    }

    /**
     * Has the flow control for the RussianRoulette Game. Uses console prompts and a Scanner to guide player through the game
     */
    public void playGame() {

        //change GameState and instantiate a Scanner for user input
        state = GameState.RUNNING;
        Scanner key = new Scanner(System.in);


        System.out.println("Look here ya broke bastard, you owe " + (-player.getBank()) + ". So here take this gun, and shoot at yourself!");
        printPicture();

        while (state == GameState.RUNNING) {

            count++; //the more you play, the higher the money multiplier is

            //spin gun chamber twice
            gun.spinChamber();
            gun.spinChamber();

            waitTwoSecondsToShoot();

            //Check is player lost. If so end game and loop, else add 1000 to the player's bank
            if (checkForWinner()) {
                System.out.println("BANG!!!!!!!");
                System.out.println("How unlucky are we? You just killed yourself. Better luck next life!");
                state = GameState.NOTRUNNING;
            } else {
                player.addToBank(1000 * count);

                //If the player still owes, make them play again, else ask if they want to play again for more money
                if (player.getBank() < 0) {
                    System.out.println("Click!\nOk, you didn't die but, you still owe sucka! Until you pay what you owe, shoot it again!\n");
                    System.out.println("You still owe " + (-player.getBank()) + "\n");
                } else {
                    System.out.println("Click!\nLucky bastard! You're free to go. You've got " + player.getBank() + "\n\n" +
                            "However, if you want to earn a quick " + (1000 * (1+count)) + ", you can play again");
                    System.out.println("Do you want to play again? Enter 'Y' to play again or any other key to exit.");

                    //If player chooses to play again, re-enter the loop, else change GameState, thus ending the game loop, and the program
                    if (key.nextLine().equalsIgnoreCase("Y")) {
                        System.out.println("Dumb-ass! Here we go again!");
                    } else {
                        System.out.println("Thanks for playing, don't lose an eye on your way out!");
                        exitGame();
                    }
                }
            }
        }
    }

    public void exitGame() {
        state= GameState.NOTRUNNING;
    }

    /**
     * If the gun shoots a bullet (returns true), player loses
     *
     * @return boolean
     */
    public boolean checkForWinner() {
        return gun.shoot();
    }

    /**
     * Pause the flow of the game for 2 seconds to add to "realism"
     */
    public void waitTwoSecondsToShoot() {
        long currentTime = System.nanoTime();
        while ((System.nanoTime() - currentTime) <= 2000000000) {
        }
    }
    public void printPicture(){
        String gun= "                                                                                                                                                                                \n" +

                "                                                          @ ;@@@: @                                                                                                                  ;@@@: @+@          \n" +
                "                                                         @@ ;,#@: @#,                                                                                                             +  @,#@:@@@:'@        \n" +
                "                                                          @.;@#@  `#, @ ,: @                   @                                                                                @ +@   #@ @`@@ @        \n" +
                "                                                          #. @@.:@@#';@#'@+@':@@.;@#@ ,@@@@`#@            : @.;'       :+ @            ;@         @'                @       '     @@   @@:              \n" +
                "                                                          #.@@@@ ,`#@ . @;+ '@+@+;,@@@,`@@ ``@; `,@@`,;@@@:'@   @ ;@  ';    .# ;  +:,`@  ` ; @@, .`  +  @ @ @    ,@    #@; @  +`  @ . ,@. '  @ `#       \n" +
                "                                                          @ @, @ ,`@, . @@+ '@@ @ ,#@ ,`@@ ` @;@` @@`, @ @ @` ;'  @@ @  @ @  #@;  +@,  ;,# @  @  .` ;@ '@+@ @,   @@ @ `# ;  '@+ @ @   ,@.  #+@ @        \n" +
                "                                                          @.@@@@ ,@#,;.#@;+`'@@  ;,#@ ,`@@@` @@+                                                                                                        \n" +
                "                                        ,@`@             @@. @@. ,@ ';+#'@+#':+  ;,@@@,@@@ `@@@.` :+`, .@@ ,@+@'  ;@#` :@@, @@@;  +@'  ;@# ;@@@,;@@,;  '@+  @'#. ,@@: `#@:. '@+` :+ . ,#.: `+@'         \n" +
                "                                        @@`':+         @  @. @#@:@ ` ; #'; @ :+@@ @@@ ,@+@ `@';@  :+@  . @ @`@@    @ ` @@ , @#,@  + ' @; # @, @, @@@    @ @ @@ .  @.@  #@ . ' +  :+@  ,#@  ` @ @        \n" +
                "                                             @@@@. @@@` @@#. @@@:@@               @@   @@@@`` @@` @@@, @@@:@@+@' +@@#@'@+@@:@@@@.@@@@`@@@#.;@@@, @#@; #'@+@ ;@# ;,@@@,`@@;.#@@+` @@@.@@@@:,@+:' #       \n" +
                "                                               ,;  @@ ` @@`.@@#@ ,`          ;.@+;,#. ,@+@ ``@@ `@@@`,@@@.: @@:'@+;'# ':@@ :.@@ . @:'  @@`.:@@`,@@@,;@#@;+@+@'#@;@#.: @@@:.`@@.`@@@`. @@.:'@+@'@#       \n" +
                "                                               ,;  '@   ;'@ @,#@ ,``'      @ :+@ ;,#.@ @+@ `@@@  @:@` @.@.: @@@ @ ;'# ':+@ :.@@ . @:'  @'@. @#@ @.@@ @ @@ @+@@# ;@@.: @#,@  @;@ @:+@. @@.: @ @ @        \n" +
                "                                                   @@@`@@@ .@@@@:@@@@;@#'@  ':@               @@` @+`, @@@ ,        # '  @ :     @      @    # , @@@;.#@;+@+@@# ;,@@:,@@@@.@@@@` @@ .@@@@:,@.@'@#       \n" +
                "                                                    @ ` @@@ ;,@.: @#, . @;  '@+@. ,@@: @@@'  ';+`@@+@,@@@. ,#       #    @             ;@    #`,@@@@;+#'@ ` @@#@;@#@;,@#@ . @@ `@@@@. @@.:'@@@ `        \n" +
                "                                                  @'@ ` @@  ;,#@: @#, . @@+@ @@ . @@.@ @+ '  ';+ @:@` @.@@:@@.;      @    @ +       @  ; @ @ @` @.@@; #'; ` @@# ;@@.@ @#@ . @@  @:@ . @@.: @ @ `        \n" +
                "                                             .@@;        @ .@@@@: @@@;@#'@+`':@@.;,@@:,`@@@`#@@+`'@@`,@@@@:@@@:'@+;@#`'@@@,:@@@@.@@@'` @@@.@@                                                           \n" +
                "                                          @, @@@;  '      @ ;,@.:@@#,; #@;+#'@+@+;@#@;,@@@ ``@@.`,@@@, @@.: @+@ ` @'##':+@':.@@ . @@,`@@'#. @                                                           \n" +
                "                                           , @#,@ @       ` ;,#@ @`#,; #@@ @ @@ @ @@.@ @+@ ` @;@` @@`, @@@: @+: ` @@ @ :+`@ .@@ . @:@ @;'#. @                                                           \n" +
                "                                   ;@   ,#@@:@@               @.: @@@;@#'@+@'@+@.;@#@:,`@@ ` @  `@@@@, @@@: @@:'@+@@#@'@@@@:@@@@.@@: `@@@# ;'                                                           \n" +
                "                                '  @'  ;,#`, .@@;           @,@@:@@@@; #@;+ ':@@+;@#@;,@@@'`@@  ` @@`,@@@.:@@@:' +;'# ':+@ :@@@ . @:'  @@` ;,                                                           \n" +
                "                                   @@ @ @@`, .@,@           @@@. @`@@; #@@+ ':+ @ @@.@ @+ ' @'  ` @@` @.@. @`@@' +;'# ':+@ :@#@ . @@ ` @'@.@                                                            \n" +
                "                                '# @'   ,@@,:@@@@.       ' .@@@.:'  '; #':     @@;,@@:,@@: ` @  ` @.#, @@. @                                                                                            \n" +
                "                                '#+;@#. @#@':.@@ .          ;,@.:'@ '; #'@+`  @ . @#@;,`@: `@@     +    @.                                                                                              \n" +
                "                                 @+;' . @@`@ .@@ .          ;,#@ @@ '   @; ` :@ . @@.@ `@@@ @'     @@   @+                                                                                              \n" +
                "                                ' +;@#.;,@@,:.@@ .        @ ;,@+:,@#,;.#'@+ ':@@ ;,@@:,`@@'`@@  ` @@`,; @                                                                                               \n" +
                "                                '@+@,#+;@#@':@@@  @@      @ ;,@@:'@#@;+#@;+@'@+@@;@#@;,@@@,` @@+`@@@@,;@@                                                                                               \n" +
                "                                 @ ;@ @ @@`@ @# @    @ @   .;@#.: @ @; #@@+ '@@@ ;@@.@ @+:@  @ + @:@` @@@                                                                                               \n" +
                "                              +@' +  #@;,@@@ @@@;.#@@@ @@@@. @#@:@@#@;@#@@+@'@@@@;,#@@,  @'` '; `'@@@,@@@                                                                                               \n" +
                "                              @@'#+@'# ;@#@@:.@@:.`@@@` @@ .@@#@ ,`@,; #'; ` @@@ ;,@@ ,`+@,` '; `,@@`, @@                                                                                               \n" +
                "                              +@ @ @@# ;@@`':.@,@  @;.` @@  @,@. ,`@@; #'; ` @@@ ; @@ , +:@ @ @@` @+@, @@                                                                                               \n" +
                "                            + +@' +;    ,`@@            @@#.@@@.: @@@; #@@+@ @@@@;@@@ ,`@@'` @     @@, @                                                                                                \n" +
                "                            +       @   ,@@@:.#@; #@ + @@@`. @@@: @#,;@#';+@':+@ ;,#@@,`@@,`@@@+ '@@`,@@                                                                                                \n" +
                "                                   :    @#`': # ; @'@+ @;'@. @ @  `#, @ '; #':+@ ;,#@ , +:@ @' + @:+@ @.                                                                                                \n" +
                "                         #,     ' +@'#@ @@@ :@@@;.#@@+`. @@. @@.: @@,;  '@  '       @ ,`@@@  @                                                                                                          \n" +
                "                                ' +:@   @@@,:@#@:.`@@.`.@@ .@@@@   #@;@  : #           @@@@`                                                                                                            \n" +
                "                                     #@;@@ , @#,@  @;@ @@@  @,#@   #,;  @ +            @+:,`                                                                                                            \n" +
                "                          ' .@@    @@#.;@#@@:@@@;.@@@@ +@@@.@@           @              +                                                                                                               \n" +
                "                      , .@  .@@ '  ;'#+;@#@ :.@@:. @;+ +@@  ;,          '@              +                                                                                                               \n" +
                "                       @@@@;@ @@ @ ;' @ @@@ :.@,@  @;+ @;@  ;,        @ @:             #+                                                                                                               \n" +
                "                      , @`@;@@@@'@+;'# ;,@@@ @@@@.#@@@`@@@@. @          '@                                                                                                                              \n" +
                "                    @@,@@@@ . @@ ` ;@#.;@#@@:@#@ .`@;+ +;@ .@@          @@             #                                                                                                                \n" +
                "                     @ @. @ . @: `  @ . @@`':@#@ . @;+ +;@  @,          '@             `@                                                                                                               \n" +
                "                   :+`,@@@,;@@+@ `+@@#@;,@@,:.@@ . @@ ` @@#.@@            + '       @: #                                                                                                                \n" +
                "                 ` :+`, @@,; @@: `+;'# ;@#@':@@@@.@@@@`@@@`. @            +`'@@     @@                                                                                                                  \n" +
                "                  @ @#, @@'; @@@'@ ;'# ;@@`@ @#@ .@'@  @;'@. @             ` @+   '`@:                                                                                                                  \n" +
                "                  @ @`,@@#@; @@@' +@@#.;,@@,:@@               @@:'            @@. @#                                                                                                                    \n" +
                "                  @ @` :.@,;@@@@'@+;'#+;@#@':                 @.:'@@,;  '@  ':@@.                                                                                                                       \n" +
                "                  @ +@ :.@@ @ @@ @ ;' @ @@`@                  @. @@@@ @ @@ @ @+ @                                                                                                                       \n" +
                "               ' `@@.# :@@  . + ' +;@# ;@@`                                                                                                                                                             \n" +
                "               '  ':+  :@@, .  @'@+@'#@;,#`                                                                                                                                                             \n" +
                "               @` ':@@,@.@@;.@.  @ @@# ;,#@'                                                                                                                                                            \n" +
                "              ;@@`'@@@,; @@;  +@'@+@@#.;@@                                                                                                                                                              \n" +
                "              ;@@`,@+@,;@@@;  @: ` @@#+;,#                                                                                                                                                              \n" +
                "             @;'`` @+@ @@@@ @ @@ ` @@ @ ,#                                                                                                                                                              \n" +
                "              @@@`@@@@, @@,; @+@' +@@#@ ,@                                                                                                                                                              \n" +
                "              @@@ ':@`,@@@,; @@:'@+@@#@;@#                                                                                                                                                              \n" +
                "              ;'` ':@` @.@@   @@ @ @@ +;@@                                                                                                                                                              \n" +
                "           '  @@#`'@@@, @@@;@@@:'# ;,#.;,@                                                                                                                                                              \n" +
                "           ' @@@``,@+@,@@#,; @@:'#+;@#+;@#                                                                                                                                                              \n" +
                "           @ @;'@` @+@ @.#,; @@; @+@' @ @@                                                                                                                                                              \n" +
                "          : ` @@@ '@@`, @@@; @@@ `+@'#@;@@                                                                                                                                                              \n" +
                "          : `@@@@ ':@@, @@@;@@@@ `+@' . @@                                                                                                                                                              \n" +
                "          @@ @;'``'@+@  .@@ @ @@'@ @@ . @@                                                                                                                                                              \n" +
                "          @ ` @@ ` @@@,@@@,; @+@' +;'#@;,@                                                                                                                                                              \n" +
                "         +@@`@@@@`,@@`, @@,;@@@:'@+;@# ;,@                                                                                                                                                              \n" +
                "        `+@ `@;@  @:+@, @@@ @ @@ @  @# ;'                                                                                                                                                               \n" +
                "        @@@'`@@@ `@@@@, @#@; @@@'@+@'#@;,`                                                                                                                                                              \n" +
                "        @+@,`+;@# ':+@,@@@,;@@@@' +;@# ;,@                                                                                                                                                              \n" +
                "        @+:@ +; # ':+@ @.@@ @ @@' +;'# ;@#                                                                                                                                                              \n" +
                "      @ `@@'`@@@#`'@@@, @#@;.@+;' +;@#.;                                                                                                                                                                \n" +
                "     @@,@@@,`+;@``,@@`,@@@,;+@+@'@+@'#+;@                                                                                                                                                               \n" +
                "      @,@+:@ +;'@` @@` @.@@; @@: @ @@ @ @`                                                                                                                                                              \n" +
                "     @:,`@: `@@@ `@@@@,@@@@;@@+@' +@@ . @                                                                                                                                                               \n" +
                "     @;,@@: ` @@@ ':@`, @#,; @@:'@+;' . @                                                                                                                                                               \n" +
                "     .@ @+@@  @@  ':+@, @#,; @@@ @ ;'# ;@                                                                                                                                                               \n" +
                "     @:,@@@@` @' ` @@`,@@#@;@@+:'@+;@#.;,@                                                                                                                                                              \n" +
                "        `@@ `@@'#`@@@@, @@,; @+@ ` @'#+; `                                                                                                                                                              \n" +
                "        `@@ `@;@  @:@`, @@@; @ @ ` @@ @ @                                                                                                                                                               \n" +
                "                       @    +    @                                                                                                                                                                      \n" +
                "                         #,; @+@              ";
        System.out.println(gun);
    }
}