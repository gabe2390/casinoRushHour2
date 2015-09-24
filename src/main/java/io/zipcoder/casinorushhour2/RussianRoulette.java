package io.zipcoder.casinorushhour2;

import java.util.Scanner;

/**
 * Created by Gabriel Humphrey on 9/23/15.
 * This is the class for a game of RussianRoulette. Almost all of the functionality is contained in the playGame()
 * that contains the game loop and calls other method to determine other actions.
 */
public class RussianRoulette {
    Gun gun;
    Player player;
    GameState state = GameState.RUNNING;

    public RussianRoulette(Gun gun) {
        this.gun = gun;
        this.player = gun.getPlayer();
    }

    public void playGame() {
        state = GameState.RUNNING;
        Scanner key = new Scanner(System.in);

        System.out.println("Look here ya broke bastard, you owe " + (-player.getBank()) + ". So here take this gun, and shoot at yourself!");

        while (state == GameState.RUNNING) {
            gun.spinChamber();

            long currentTime = System.nanoTime();

            while ((System.nanoTime() - currentTime) <= 2000000000) {

            }

            if (checkForWinner()) {
                System.out.println("BANG!!!!!!!");
                System.out.println("How unlucky are we? You just killed yourself. Better luck next life!");
                state = GameState.NOTRUNNING;
            } else {
                player.addToBank(1000);

                if (player.getBank() < 0) {
                    System.out.println("Click!\nOk, you didn't die but, you still owe sucka! Until you pay what you owe, shoot it again!\n");
                    System.out.println("You still owe " + (-player.getBank()) + "\n");
                } else {
                    System.out.println("Click!\nLucky bastard! You're free to go. You've got " + player.getBank() + "\n\nHowever, if you want to earn a quick $1000, you can play again");
                    System.out.println("Do you want to play again? Enter 'Y' to play again or any other key to exit.");

                    if (key.nextLine().equalsIgnoreCase("Y")) {
                        System.out.println("Dumb-ass! Here we go again!");
                    } else {
                        System.out.println("Thanks for playing, don't lose an eye on your way out!");
                        state = GameState.NOTRUNNING;
                    }
                }
            }
        }
    }

    private boolean checkForWinner() {
        return gun.shoot();
    }

    public static void main(String[] args) {
        RussianRoulette r = new RussianRoulette(new Gun(new Player()));

        r.playGame();
    }
}

enum GameState {
    RUNNING, NOTRUNNING
}