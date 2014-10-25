/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lordmat.blackjacksimulator.statergy;

/**
 * Used to view the dealers first card only
 * @author mat
 */
public interface DealerCardObserver {
    void updateDealerCard(int cardValue);
}
