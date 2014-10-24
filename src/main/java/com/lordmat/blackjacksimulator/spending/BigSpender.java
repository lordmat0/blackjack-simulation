package com.lordmat.blackjacksimulator.spending;

/**
 * Uses 30% of money for every bet, just wants to waste money! Doesn't care
 * about previous wins
 *
 * @author mat
 */
public class BigSpender implements Spending {

    private int money;

    public BigSpender(int money) {
        this.money = money;
    }

    @Override
    public int betAmount() {
        if (money <= 0) {
            return -1; // No money left!
        }

        int betAmount = (int) (money * 0.5);

        if (betAmount <= 500 && money >= 500) {
            betAmount = 500;
        } else if(money <= 500){
            betAmount = money;
        }
        money -= betAmount;

        return betAmount;

    }

    @Override
    public void winAmount(int amount) {
        money += amount;
    }

    @Override
    public String toString() {
        return "Big Spender";
    }

}
