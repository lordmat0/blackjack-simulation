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
            int value = 1;
            newDeck.push(new Card(suit, value++, "Ace"));
            newDeck.push(new Card(suit, value++, "Two"));
            newDeck.push(new Card(suit, value++, "Three"));
            newDeck.push(new Card(suit, value++, "Four"));
            newDeck.push(new Card(suit, value++, "Five"));
            newDeck.push(new Card(suit, value++, "Six"));
            newDeck.push(new Card(suit, value++, "Seven"));
            newDeck.push(new Card(suit, value++, "Eight"));
            newDeck.push(new Card(suit, value++, "Nine"));
            newDeck.push(new Card(suit, value++, "Ten"));
            newDeck.push(new Card(suit, 10, "Jack"));
            newDeck.push(new Card(suit, 10, "Queen"));
            newDeck.push(new Card(suit, 10, "King"));
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
