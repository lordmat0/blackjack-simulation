package com.lordmat.blackjacksimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author mat
 */
public class Deck {

    private final Stack<Card> cards;

    public Deck() {
        this(5);
    }

    public Deck(int numberOfDecks) {
        cards = new Stack<>();

        for (int i = 0; i < numberOfDecks; i++) {
            cards.addAll(getNewDeck());
        }
        shuffle();
    }

    private Stack<Card> getNewDeck() {
        Stack<Card> newDeck = new Stack<>();
        for (Suit suit : Suit.values()) {
            for(Value value: Value.values()){
                newDeck.push(new Card(suit, value));
            }
        }
        
        return newDeck;
    }

    public Card drawNextCard() {
        if(cards.isEmpty()){
            cards.addAll(getNewDeck());
            shuffle();
        }
        
        return cards.pop();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public String toString() {
        List<Card> remainingCards = new ArrayList<>(cards);

        return remainingCards.toString();
    }
}
