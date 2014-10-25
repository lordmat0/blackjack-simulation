/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lordmat.blackjacksimulator;

/**
 *
 * @author mat
 */
public enum Value {

    ACE("Ace", 1), TWO("Two", 2), THREE("Three", 3), FOUR("Four", 4),
    FIVE("Five", 5), SIX("Six", 6), SEVEN("Seven", 7), EIGHT("Eight", 8),
    NINE("Nine", 9), TEN("Ten", 10), JACK("Jack", 10), QUEEN("Queen", 10),
    KING("King", 10);

    private final int value;
    private final String name;

    private Value(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public boolean isAce(){
        return this == ACE;
    }

    @Override
    public String toString() {
        return name;
    }

}
