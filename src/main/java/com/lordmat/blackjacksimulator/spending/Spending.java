package com.lordmat.blackjacksimulator.spending;

/**
 * A players spending for the next bet in a game of blackjack
 *
 * @author mat
 */
public interface Spending {

    public int getNextBetAmount();

    /**
     * Will currentAmount += amount passed in so negative amounts can be passed
     * in to reduce it
     *
     * @param amount
     */
    public void changeAmount(int amount);

    public int getTotalMoney();
}
