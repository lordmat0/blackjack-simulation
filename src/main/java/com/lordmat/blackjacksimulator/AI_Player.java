package com.lordmat.blackjacksimulator;

import com.lordmat.blackjacksimulator.spending.Spending;
import com.lordmat.blackjacksimulator.statergy.Move;
import com.lordmat.blackjacksimulator.statergy.Strategy;

/**
 *
 * @author mat
 */
public class AI_Player implements Player {

    // ID of the player
    private static int idIndex = 1;
    private int id = idIndex++;

    // Holds the current round hand
    private RoundHand roundHand;

    // Players strategy
    private final Strategy strategy;

    // Players Spending habbits
    private final Spending spending;

    public AI_Player(Strategy strategy, Spending spending) {
        this.strategy = strategy;
        this.spending = spending;
    }

    /**
     * Gets the next move
     *
     * @return
     */
    @Override
    public Move getMove() {
        return strategy.nextMove(roundHand);
    }

    /**
     * Gets the next bet
     *
     * @return
     */
    @Override
    public int betAmount() {
        int betAmount = spending.getNextBetAmount();

        if (betAmount > 0) {
            roundHand.setBetAmount(betAmount);
        }

        return betAmount;
    }

    /**
     * Changes the amount of money the player has, this is added on to their
     * money, if a negative number is passed then it is instead reduced
     *
     * @param amount
     */
    @Override
    public void changeAmount(int amount) {
        spending.changeAmount(amount);
    }

    /**
     * Sets the current round hand
     *
     * @param roundHand
     */
    @Override
    public void setRoundHand(RoundHand roundHand) {
        this.roundHand = roundHand;
    }

    /**
     * Gets the current round hand
     *
     * @return
     */
    @Override
    public RoundHand getRoundHand() {
        return roundHand;
    }

    /**
     * Player draws a card
     *
     * @param card
     */
    @Override
    public void drawCard(Card card) {
        roundHand.drawCard(card);
    }

    /**
     * Returns the best score the player can achieve with their cards
     *
     * @return
     */
    @Override
    public int getBestScore() {
        return roundHand.getBestScore();
    }

    /**
     * Gets their last bet amount
     *
     * @return
     */
    @Override
    public int lastBestAmount() {
        return roundHand.getBetAmount();
    }

    @Override
    public String toString() {
        return "id: " + id + " " + strategy.toString() + " " + spending.toString();
    }

    /**
     * Gets total money the player has
     *
     * @return
     */
    @Override
    public int getTotalMoney() {
        return spending.getTotalMoney();
    }

}
