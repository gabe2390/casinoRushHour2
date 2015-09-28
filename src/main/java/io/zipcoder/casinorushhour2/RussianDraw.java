package io.zipcoder.casinorushhour2;

import java.util.Scanner;

/**
 * Created by emaron on 9/28/15.
 * Class for a gam of Russian Draw, aka Quick Draw
 */
public class RussianDraw implements Game {

    Player player;

    Gun playerGun;
    Gun dealerGun;
    GameState gameStatus;

    int playerFire = 0;
    int dealerFire = 0;

    /**
     * Constructor for RussianDraw
     *
     * @param gun
     */
    public RussianDraw(Gun gun) {
        playerGun = gun;
        player = gun.getPlayer();
        dealerGun = new Gun(new Player("dealer"));
    }


    /**
     * Begins the game loop then calls all relevant game methods needed to run
     */
    public void playGame() {
        int pot = 0;
        gameStatus= GameState.RUNNING;

        printHat();
        playerGun.init();
        dealerGun.init();
        playerGun.spinChamber();
        dealerGun.spinChamber();

        while (gameStatus == GameState.RUNNING) {

            pot = askToBet();
            fireGuns();

            if (checkForWinner()) {
                player.addToBank(pot * 2);
                resetGuns();
            } else {
                System.out.println("\n" + "You are dead. No big surprise. Goodbye.");
                System.exit(0);
            }
            exitGame();
        }

    }

    /**
     * Initializes and then looks through both guns for the true boolean
     */
    public void fireGuns() {

        playerGun.spinChamber();
        dealerGun.spinChamber();


        for (int i = 0; i < 6; i++) {
            if (playerGun.getBulletChamber().get(i)) {
                break;
            } else {
                playerFire++;
            }
        }

        for (int i = 0; i < 6; i++) {
            if (dealerGun.getBulletChamber().get(i)) {
                break;
            } else {
                dealerFire++;
            }
        }


    }


    /**
     * Prints a hat, and a welcome message
     */
    private void printHat() {
        System.out.println("\n" +
                "                       o$$\"\"\"$oo$$\"\"\"\"\"$o\n" +
                "                      $\"      $$\"      $$$$\n" +
                "                     $\"      \"\"        $$$$$o\n" +
                "                     $                  $$$$$o\n" +
                "                    $                    $$$$$$\n" +
                "                   $\"                    \"$$$$$\n" +
                "                   $                      $$$$$$\n" +
                "                  $\"                      $$$$$$\n" +
                "                  $                        $$$$$\n" +
                "                  $                       o$$$$$\n" +
                "                  $                       $$$$$$\n" +
                "                  $                      o$$$$$$\n" +
                "               ooo                      o$$$$$$$\n" +
                "       ooo$$$$\"\" $                   oo$$$$$\"\"\"\"\"\"oooo\n" +
                "    oo\"$$$$$$$ oo\"\" oooooooooooooooo$$\"\"\"           o$$\"oo\n" +
                "   o\"  $$$$$$$ \"$o           oo$$$$$\"               $$$$o\"$o\n" +
                "  $    $$$$$$$  \" \"\"oooooooooo$$$$\"         o$      $$$$$$o\"$\n" +
                " o     $$\"\"               oo$$$\"           o$$     o$$$$$$$o$\n" +
                " \"o    $$             oo$$$$\"\"            o$$$   o$$$$$$$$$$$\n" +
                "  \"$o  $$$oo                           $$$$$$$   ooo$$$$$\"\"\n" +
                "    \"$$oooo \"\"            ooo$$$$      $$$$$$$$$$$$$$\"\"\n" +
                "        \"\"\"\"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\"\"\"\"\"\"\n" +
                "                  \"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"" + "\n");
        System.out.println("Welcome to Quick Draw." + "\n" + "Keep a level head or lose it all." + "\n");
    }

    /**
     * Resets both the player and dealer guns
     */
    private void resetGuns() {
        playerGun.getBulletChamber().clear();
        dealerGun.getBulletChamber().clear();

        playerGun.init();
        dealerGun.init();

        playerFire = 0;
        dealerFire = 0;
    }

    /**
     * Ends the game loop
     */
    public void exitGame() {
        Scanner key = new Scanner(System.in);
        System.out.println("\n" + "Huh, you're still standing. Impressive! Do you want me to find another challenger? (Y) or (N)");
        String playAgainResponse = key.nextLine();

        if (!playAgainResponse.equals("Y")) {
            gameStatus = GameState.NOTRUNNING;
        }
    }

    /**
     * Returns a boolean that is true if player survives and false for a tie or dealer win
     *
     * @return
     */
    public boolean checkForWinner() {
        return playerFire < dealerFire;
    }

    /**
     * Prompts player to bet
     */
    public int askToBet() {
        int bet = 0;

        Scanner key = new Scanner(System.in);
        System.out.println("What amount will you wager on your life?");
        try {
            bet = Integer.parseInt(key.nextLine());
            player.bet(bet);
        } catch (Exception e) {
            System.out.println("Really? Your life is only worth $10? Well, alright then.");
            bet = 10;
            player.bet(bet);
        }
        return bet;
    }

    public static void main(String[] args){
        RussianDraw r= new RussianDraw(new Gun(new Player("Gabe")));
        r.playGame();
    }
}