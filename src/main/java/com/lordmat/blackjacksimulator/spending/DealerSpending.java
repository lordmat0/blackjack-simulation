package com.lordmat.blackjacksimulator.spending;

/**
 *
 * @author mat
 */
public class DealerSpending implements Spending {

    private int dealerIncome;
    
    @Override
    public int getNextBetAmount() {
        return 0; // Dealer never needs to bet
    }

    @Override
    public void changeAmount(int amount) {
        dealerIncome += amount;
    }

    @Override
    public int getTotalMoney() {
        return dealerIncome;
    }

    @Override
    public String toString(){
        return "";
    }

    
}
