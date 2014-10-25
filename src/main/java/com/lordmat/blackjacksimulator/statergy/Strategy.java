package com.lordmat.blackjacksimulator.statergy;

import com.lordmat.blackjacksimulator.RoundHand;

/**
 * A players strategy for a game of blackjack
 * @author mat
 */
public interface Strategy {
    Move nextMove(RoundHand roundHand);
}
