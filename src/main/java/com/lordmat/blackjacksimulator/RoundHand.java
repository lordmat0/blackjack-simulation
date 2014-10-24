package com.lordmat.blackjacksimulator;

import java.util.ArrayList;
import java.util.List;

public class RoundHand {

    private boolean hasLastestValues = false;
    private List<Integer> possibleValues;
    
    private int betAmount;
    private final List<Card> cards;
    private final static int MAX_SCORE = 21;

    public RoundHand() {
        cards = new ArrayList<>();
    }
    
    public void setBetAmount(int betAmount){
        this.betAmount = betAmount;
    }
    
    public int getBetAmount(){
        return betAmount;
    }

    private List<Integer> getPossibleValues() {
        if(hasLastestValues){
            return possibleValues;
        }
        
        
        List<Integer> scores = new ArrayList<>();

        boolean hasAce = false;
        int cardTotal = 0;
        for (Card card : cards) {
            cardTotal += card.getValue();

            if (card.isAce()) {
                hasAce = true;
            }
        }

        scores.add(cardTotal);

        // 10+ is when Ace is high
        if (hasAce && cardTotal + 10 <= MAX_SCORE) {
            scores.add(cardTotal + 10);
        }
        
        hasLastestValues = true;
        possibleValues = scores;

        return possibleValues;
    }

    public boolean isBust() {
        List<Integer> scores = getPossibleValues();

        return scores.stream().allMatch((score) -> (score > MAX_SCORE));
    }

    public boolean hasTwoHands() {
        return getPossibleValues().size() == 2;
    }

    public int getFirstHand() {
        return getPossibleValues().get(0);
    }

    public int getSecondHand() {
        List<Integer> hands = getPossibleValues();
        if (hands.size() == 2) {
            return hands.get(1);
        }

        return -1;
    }
    
    public int getBestScore(){
        
        if(isBust()){
            return 0;
        }
        
        if(hasTwoHands()){
            return getSecondHand();
        }
        
        return getFirstHand();
    }

    public boolean isBlackJack() {
        List<Integer> scores = getPossibleValues();

        return scores.stream().anyMatch((score) -> (score == MAX_SCORE));
    }

    public void drawCard(Card card) {
        hasLastestValues = false;
        cards.add(card);
    }
}
