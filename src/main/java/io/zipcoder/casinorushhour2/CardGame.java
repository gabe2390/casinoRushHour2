package io.zipcoder.casinorushhour2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rsparks on 9/22/15.
 * Adds a dealer object to a CardGame type
 */
public interface CardGame extends Game {

    /**
     *Creates a Dealer object
     */
    Dealer DEALER = new Dealer();
}
