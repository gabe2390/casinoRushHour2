package io.zipcoder.casinorushhour2;

import java.util.Scanner;

/**
 * Created by ghumphrey on 9/23/15.
 */
public class RussianRoulette {
    Gun gun;
    Player player;
    GameState state = GameState.NOTRUNNING;

    public RussianRoulette(Gun gun) {
        this.gun = gun;
        this.player = gun.getPlayer();
    }

    public void playGame() {
        state = GameState.running;
        Scanner key= new Scanner(System.in);

        while (state == GameState.running) {
            gun.spinChamber();

            if(checkForWinner()){
                System.out.println("How unlucky are we? You just killed yourself. Better luck next life!");
                state= GameState.ended;
            }
            else{
                player.addToBank(1000);

                if(player.getBank() < 0){
                    System.out.println("You still owe sucka! Until you pay what you owe, shoot it again!");
                }
                else{
                    System.out.println("You're free to go. However, if you want to earn a quick $1000, you can play again");
                    System.out.println("Do you want to play again? Enter 'Y' to play again or any other key to exit.");

                    if(key.nextLine().equalsIgnoreCase("Y")){
                        System.out.println("Dumb-ass! Here we go again!");
                    }
                    else{
                        System.out.println("Thanks for playing, don't lose an eye on your way out!");
                        state= GameState.NOTRUNNING;
                    }
                }
            }
        }
        exitGame();
    }

    private boolean checkForWinner() {
        return gun.shoot();
    }

    private void exitGame() {

    }

    public static void main(String[] args){
        RussianRoulette r= new RussianRoulette(new Gun(new Player("Gabe")));

        r.playGame();
    }
}