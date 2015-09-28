package io.zipcoder.casinorushhour2;

import java.util.Scanner;

/**
 * Created by pwatson on 9/24/15.
 */


public class Casino {
    private GameSelection currentGame;
    private RussianRoulette russian;
    private BlackJack blackJack;
    /* private Poker poker;
     private FiveCardDraw fiveDraw;
     private SevenCardStud sevenStud;
     private SevenCardDraw sevenDraw;
   */  private Player player;


    public Casino() {
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setCurrentGame(int userInput) {
        switch (userInput) {
            case 1: {
                currentGame = GameSelection.Blackjack;
                break;
            }
            case 2: {
                currentGame = GameSelection.Poker;
                break;
            }
            case 3: {
                currentGame = GameSelection.FiveCardDraw;
                break;
            }
            case 4: {
                currentGame = GameSelection.SevenCardStud;
                break;
            }
            case 5: {
                currentGame = GameSelection.SevenCardDraw;
                break;
            }
            case 6: {
                currentGame = GameSelection.Russian_Roulette;
                break;
            }
            case 7: {
                currentGame = GameSelection.Leave_Casino;
                break;
            }
            default:
                currentGame = GameSelection.Russian_Roulette;
        }
    }

    public void enterGame(GameSelection game) {
        switch (game) {
            case Blackjack: {
                blackJack = new BlackJack(new Deck(player));
                blackJack.playGame();
                break;
            }
        /*    case Poker: {
                poker = new Poker(new Deck(player));
                poker.playGame();
                break;
            }
           case FiveCardDraw: {
                fiveDraw = new FiveCardDraw(new Deck(player));
                fiveDraw.playGame();
                break;
            }
            case SevenCardStud: {
                sevenStud = new SevenCardStud(new Deck(player));
                sevenStud.playGame();
                break;
            }
            case SevenCardDraw: {
                sevenDraw = new SevenCardDraw(new Deck(player));
                sevenDraw.playGame();
                break;
            }
*/
            case Russian_Roulette: {
                russian = new RussianRoulette(new Gun(player));
                russian.playGame();
                break;
            }
            case Leave_Casino: {
                if (player.getBank() < 0) {
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

    public void printSkull() {

        String graphics = "__________________________________________________\n" +
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

        System.out.println(graphics);
    }

    public static void main(String[] args) {

        Casino casino = new Casino();
        casino.printSkull();
        System.out.println("Welcome to the Killer Kasino!!! What's yo name FOO!!!!!");

        Scanner s = new Scanner(System.in);
        casino.setPlayer(new Player(s.nextLine()));

        int x = 0;

        do {
            System.out.println("Select a game:\n1 for Blackjack\n2 for 5 Card Stud\n3 for 5 Card Draw\n4 for 7 Card Stud\n5 for 7 Card Draw" +
                    "\n6 for Russian Roulette\n7 for Leave Casino.\nI would " +
                    "advise against entering any other character besides the ones listed.");
            try {
                x = Integer.parseInt(s.nextLine());
            } catch (Exception e) {
                System.out.println("Since you didn't heed my warning, you're going to play Russian Roulette!\n");
                x = 3;
            }
            casino.setCurrentGame(x);
            casino.enterGame(casino.getCurrentGame());
        } while (casino.getCurrentGame() != GameSelection.Leave_Casino);

        System.out.println("Thanks for coming to Killer Kasino. Don't die out there!");
    }
}




