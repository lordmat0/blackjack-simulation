package com.lordmat.blackjacksimulator;

import com.lordmat.blackjacksimulator.statergy.Move;

/**
 *
 * @author mat
 */
public interface Player {
    
    void setRoundHand(RoundHand roundHand);
    void drawCard(Card card);
    Move getMove();
    int getBestScore();
    
    int betAmount();
    int lastBestAmount();
    
    void winAmount(int amount);
}
