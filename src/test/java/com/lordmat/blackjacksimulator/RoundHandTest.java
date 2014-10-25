/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lordmat.blackjacksimulator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mat
 */
public class RoundHandTest {

    public RoundHandTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getPossibleValues method, of class RoundHand.
     */
    @Test
    public void testHasAceValue() {
        System.out.println("testHasAceValue");

        RoundHand instance = new RoundHand();

        Card card1 = new Card(Suit.SPADE, Value.ACE);
        Card card2 = new Card(Suit.CLUB, Value.TWO);

        card1.flipCard();
        card2.flipCard();

        instance.drawCard(card1);
        instance.drawCard(card2);

        boolean hasTwoHands = instance.hasTwoHands();
        assertTrue(hasTwoHands);
    }

    /**
     * Test of isBust method, of class RoundHand.
     */
    @Test
    public void testIsBust() {
        System.out.println("testIsBust");

        RoundHand instance = new RoundHand();

        Card card1 = new Card(Suit.SPADE, Value.NINE);
        Card card2 = new Card(Suit.CLUB, Value.SEVEN);
        Card card3 = new Card(Suit.CLUB, Value.TEN);

        card1.flipCard();
        card2.flipCard();
        card3.flipCard();

        instance.drawCard(card1);
        instance.drawCard(card2);
        instance.drawCard(card3);

        Boolean expResult = true;
        Boolean result = instance.isBust();
        assertEquals(expResult, result);
    }

    /**
     * Test of isBust method, of class RoundHand.
     */
    @Test
    public void testIsNotBust() {
        System.out.println("testIsNotBust");

        RoundHand instance = new RoundHand();

        Card card1 = new Card(Suit.SPADE, Value.NINE);
        Card card2 = new Card(Suit.CLUB, Value.FIVE);

        card1.flipCard();
        card2.flipCard();

        instance.drawCard(card1);
        instance.drawCard(card2);

        Boolean expResult = false;
        Boolean result = instance.isBust();
        assertEquals(expResult, result);
    }

    /**
     * Test of isBlackJack method, of class RoundHand.
     */
    @Test
    public void testIsBlackJack() {
        System.out.println("isBlackJack");

        RoundHand instance = new RoundHand();
        Card card1 = new Card(Suit.SPADE, Value.ACE);
        Card card2 = new Card(Suit.CLUB, Value.TEN);

        card1.flipCard();
        card2.flipCard();

        instance.drawCard(card1);
        instance.drawCard(card2);

        Boolean expResult = true;
        Boolean result = instance.isBlackJack();
        assertEquals(expResult, result);

    }

    /**
     * Test of isBlackJack method, of class RoundHand.
     */
    @Test
    public void testIsNotBlackJack() {
        System.out.println("testIsNotBlackJack");

        RoundHand instance = new RoundHand();

        Card card1 = new Card(Suit.SPADE, Value.ACE);
        Card card2 = new Card(Suit.CLUB, Value.ACE);

        card1.flipCard();
        card2.flipCard();

        instance.drawCard(card1);
        instance.drawCard(card2);

        Boolean expResult = false;
        Boolean result = instance.isBlackJack();
        assertEquals(expResult, result);

    }

    /**
     * Test of isBlackJack method, of class RoundHand.
     */
    @Test
    public void testIs21() {
        System.out.println("testIsNotBlackJack");

        RoundHand instance = new RoundHand();

        Card card1 = new Card(Suit.SPADE, Value.ACE);
        Card card2 = new Card(Suit.CLUB, Value.ACE);
        Card card3 = new Card(Suit.CLUB, Value.NINE);

        card1.flipCard();
        card2.flipCard();
        card3.flipCard();

        instance.drawCard(card1);
        instance.drawCard(card2);
        instance.drawCard(card3);

        int expectedResult = 21;
        int actualResult = instance.getBestScore();
        System.out.println(actualResult);
        assertTrue(expectedResult == actualResult);

    }

}
