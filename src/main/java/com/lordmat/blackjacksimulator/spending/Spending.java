package com.lordmat.blackjacksimulator.spending;

/**
 *
 * @author mat
 */
public interface Spending {

    public int betAmount();

    /**
     * Will currentAmount += amount passed in so negative amounts can be passed
     * in to reduce it
     *
     * @param amount
     */
    public void changeAmount(int amount);
}
