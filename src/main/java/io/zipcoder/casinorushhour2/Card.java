package io.zipcoder.casinorushhour2;

/**
 * Created by Gabriel Humphrey on 9/23/15.
 */
public class Card {
    private String name;
    private Suit SUIT;
    private int number;

    /**
     * Costructor takes an integer to decide the number fo the card- will be adjusted in other methods to determine
     * the name of the card.
     */
    public Card(int i) {
        number = i;//number of card based on count - every 13th card will have the same name;
    }

    /**
     * Initializes every card with a suit and name by calling setSuit and setName methods
     */
    public void init() {
        setSuit();
        setName();
    }


    /**
     * sets suit based on the card number. number 1-13 = HEARTS, 14-26= DIAMONDS, 27-39= CLUBS, 40-52= "SPADES"
     */
    private void setSuit() {
        if (number > 13 && number <= 26) {
            SUIT = Suit.DIAMONDS;
        } else if (number > 26 && number <= 39) {
            SUIT = Suit.CLUBS;
        } else if (number > 39 && number <= 52) {
            SUIT = Suit.SPADES;
        } else {
            SUIT = Suit.HEARTS;
        }
    }

    /**
     * Gives each card a name. Each suit has 13 cards. Each card should be named as follows 1= "Ace", 11= "Jack"
     * 12= "Queen", 13= "King", all others = value of number (2 through 10);
     */
    private void setName() {
        number = adjustNumber(number);

        switch (number) {
            case (1): {
                name = "Ace";
                break;
            }
            case (11): {
                name = "Jack";
                break;
            }
            case (12): {
                name = "Queen";
                break;
            }
            case (13): {
                name = "King";
                break;
            }
            default: {
                name = String.valueOf(number);
            }
        }
    }

    /**
     *
     * @param n - will be based on the number assigned at instantiation
     * @return int - int returned will only be used to assign a name to a card
     */
    private int adjustNumber(int n) {
        if (number > 13 && number <= 26) {
            return number - 13;
        } else if (number > 26 && number <= 39) {
            return number - 26;
        } else if (number > 39) {
            return number - 39;
        } else {
            return n;
        }
    }

    /**
     * @return String -returns the suit of the card in string form
     */
    public String getSuit() {
        return SUIT.name();
    }

    /**
     * @return String - returns the name of the card, "Ace' through "King"
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return String - returns the full name of a Card - eg. "Ace of Hearts"
     */
    public String toString() {
        return name + " of " + getSuit();
    }

    /**
     * Suit enum limits the possible suits to the 4 listed
     */
    enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }
}