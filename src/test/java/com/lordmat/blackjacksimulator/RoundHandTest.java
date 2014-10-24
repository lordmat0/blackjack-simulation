/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lordmat.blackjacksimulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        instance.drawCard(new Card(Suit.SPADE, 1, "Ace"));
        instance.drawCard(new Card(Suit.SPADE, 2, "Two"));

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

        instance.drawCard(new Card(Suit.SPADE, 9, "Nine"));
        instance.drawCard(new Card(Suit.SPADE, 7, "Seven"));
        instance.drawCard(new Card(Suit.CLUB, 10, "Ten"));

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
        instance.drawCard(new Card(Suit.SPADE, 5, "Five"));
        instance.drawCard(new Card(Suit.SPADE, 9, "Nine"));

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

        instance.drawCard(new Card(Suit.SPADE, 1, "Ace"));
        instance.drawCard(new Card(Suit.SPADE, 10, "King"));

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

        instance.drawCard(new Card(Suit.SPADE, 1, "Ace"));
        instance.drawCard(new Card(Suit.SPADE, 1, "Ace"));
        
        Boolean expResult = false;
        Boolean result = instance.isBlackJack();
        assertEquals(expResult, result);

    }

}
