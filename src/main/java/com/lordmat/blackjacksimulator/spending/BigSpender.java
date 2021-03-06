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
    public int getNextBetAmount() {
        if (money <= 0) {
            return -1; // No money left!
        }

        int betAmount = (int) (money * 0.5);

        if (betAmount <= 500 && money >= 500) {
            betAmount = 500;
        } else if(money <= 500){
            betAmount = money;
        }

        return betAmount;
    }

    @Override
    public void changeAmount(int amount) {
        money += amount;
    }

    @Override
    public int getTotalMoney() {
        return money;
    }
    
    

    @Override
    public String toString() {
        return "Big Spender";
    }

}
