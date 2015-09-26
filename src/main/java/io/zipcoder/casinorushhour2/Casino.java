package io.zipcoder.casinorushhour2;

import java.util.Scanner;

/**
 * Created by pwatson on 9/24/15.
 */


public class Casino {
    private GameSelection currentGame;
    private RussianRoulette russian;
    private BlackJack blackJack;
    private Player player;

    public Casino() {
        Scanner key = new Scanner(System.in);
        String name;

        System.out.println("Welcome to Killer Kasino!!!! What is yo name FOO!");
        name = key.nextLine();

        player = new Player(name);
        player.addToBank(-5000);
    }

    public void setCurrentGame(String userInput) {
        switch (Integer.parseInt(userInput)) {
            case 1:
                currentGame = GameSelection.Blackjack;
                break;
            case 2:
                currentGame = GameSelection.Poker;
                break;
            case 3:
                currentGame = GameSelection.Russian_Roulette;
                break;
            case 4:
                currentGame = GameSelection.Leave_Casino;
                break;
            case 500:
                currentGame = GameSelection.Idle_Game;
                break;
            default:
                System.out.println("Unknown User input: " + userInput);
        }
        enterGame(getCurrentGame());
    }

    public void enterGame(GameSelection game) {
        switch(game){
            case Blackjack:{
                blackJack= new BlackJack(new Deck(player));
                blackJack.playGame();
                break;
            }
            case Leave_Casino: {
                if(player.getBank() <0) {
                    russian = new RussianRoulette(new Gun(player));
                    russian.playGame();
                    break;
                }
            }
        }
    }

    public GameSelection getCurrentGame() {
        return currentGame;
    }



    /*
    public Poker getPoker() {
        return poker;
    }

    public void setPoker(Poker poker) {
        this.poker = poker;
    }

    public RussianRoulette getRussianRoulette() {
        return russianRoulette;
    }

    public void setRussianRoulette(RussianRoulette russianRoulette) {
        this.russianRoulette = russianRoulette;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    } */

    public static void main(String[] args) {

        Casino killer = new Casino();
        Scanner s = new Scanner(System.in);

        do {

            System.out.println("Select a game:\n 1 for Blackjack\n 2 for Poker\n 3 for Russian Roulette \n 4 for Leave Casino");
            String x = s.nextLine();
            killer.setCurrentGame(x);

        } while (killer.getCurrentGame() != GameSelection.Leave_Casino);

        System.out.println("Thanks for playing");
    }
}




        /*String graphics = "__________________________________________________\n" +
                "__________________¶¶¶¶¶¶¶¶¶¶¶¶¶¶__________________\n" +
                "______________¶¶¶¶_____________¶¶¶¶¶______________\n" +
                "___________¶¶¶_____________________¶¶¶¶___________\n" +
                "________¶¶¶¶__________________________¶¶¶_________\n" +
                "_______¶¶_______________________________¶¶¶_______\n" +
                "______¶¶__________________________________¶¶______\n" +
                "____¶¶_____________________________________¶¶_____\n" +
                "____¶________________________________________¶____\n" +
                "___¶¶________________________________________¶¶___\n" +
                "__¶¶_____________¶¶¶__________________________¶___\n" +
                "___¶___________¶¶_____________________________¶¶__\n" +
                "___¶___________¶________________¶¶¶___________¶¶__\n" +
                "___¶_____¶¶_¶¶_¶¶¶¶¶_¶____________¶¶¶__________¶__\n" +
                "___¶_________¶___¶¶¶¶¶¶¶¶¶¶¶¶______¶¶¶_________¶__\n" +
                "___¶¶______¶¶¶¶___¶¶¶¶¶¶¶¶¶¶¶¶¶¶____¶¶¶_¶¶____¶¶¶_\n" +
                "___¶¶____¶¶¶¶¶¶___¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶__¶¶¶¶¶¶¶¶__¶¶__\n" +
                "___¶____¶¶¶¶¶¶¶____¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶__¶¶¶¶¶¶¶¶¶¶¶___\n" +
                "__¶¶___¶¶¶¶¶¶¶¶____¶¶¶¶¶¶¶¶¶¶¶¶¶¶__¶¶¶¶¶¶¶¶¶¶¶¶___\n" +
                "____¶¶¶¶¶¶¶¶¶¶______¶¶¶¶¶¶¶¶¶¶¶¶¶__¶¶¶¶¶¶¶¶¶¶¶____\n" +
                "______¶¶¶¶¶¶¶___¶¶_____¶¶¶¶¶¶¶¶¶____¶¶¶¶¶¶¶¶¶¶____\n" +
                "______¶__¶¶¶____¶¶¶___________________¶¶¶¶¶¶¶_____\n" +
                "_____¶¶________¶¶¶¶¶¶__¶________________¶¶¶_______\n" +
                "_____¶¶______¶¶¶____¶¶¶¶______________¶¶¶¶________\n" +
                "______¶¶______¶_______¶¶_________¶¶¶¶¶¶¶¶¶________\n" +
                "_______¶¶¶¶¶_______________¶_¶¶¶¶¶¶¶¶¶¶¶¶¶________\n" +
                "___________¶¶¶____¶¶¶¶_¶¶_¶¶¶___¶¶¶¶¶___¶¶________\n" +
                "____________¶¶¶¶¶_¶__¶¶_¶_¶_¶____¶¶_____¶_________\n" +
                "_____________¶_____________¶¶_____¶_____¶_________\n" +
                "______________¶¶¶¶¶_¶¶¶¶¶¶________¶¶____¶_________\n" +
                "____________________¶¶¶¶¶¶_________¶____¶_________\n" +
                "_____________________¶¶_¶¶_________¶¶___¶¶________\n" +
                "______________________¶¶¶¶¶_________¶____¶________\n" +
                "_______________________¶¶¶¶¶_______¶¶____¶¶_______\n" +
                "_______________________¶¶¶¶¶______¶_¶_____¶_______\n" +
                "_______________________¶_¶¶¶_____¶__¶¶____¶_______\n" +
                "_______________________¶¶¶¶¶____¶¶__¶¶___¶________\n" +
                "_______________________¶¶¶¶¶__¶¶¶__¶¶___¶_________\n" +
                "________________________¶¶¶¶¶¶¶__¶¶¶___¶¶_________\n" +
                "_________________________¶¶_____¶¶¶____¶__________\n" +
                "____________________________¶¶¶¶______¶___________\n" +
                "______________________________¶¶_____¶¶___________\n" +
                "_______________________________¶¶¶¶¶¶¶";

        System.out.println(graphics); */