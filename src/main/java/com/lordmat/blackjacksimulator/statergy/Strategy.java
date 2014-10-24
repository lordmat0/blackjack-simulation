package com.lordmat.blackjacksimulator.statergy;

import com.lordmat.blackjacksimulator.RoundHand;

/**
 *
 * @author mat
 */
public interface Strategy {
    Move nextMove(RoundHand roundHand);
}
