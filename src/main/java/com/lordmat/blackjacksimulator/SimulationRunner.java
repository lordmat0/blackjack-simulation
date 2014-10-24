package com.lordmat.blackjacksimulator;

import com.lordmat.blackjacksimulator.spending.BigSpender;
import com.lordmat.blackjacksimulator.statergy.DealerStrategy;
import com.lordmat.blackjacksimulator.statergy.SafePlayer;

/**
 *
 * @author mat
 */
public class SimulationRunner {

    private final Dealer dealer;

    public SimulationRunner() {
        dealer = new Dealer(new DealerStrategy());

        dealer.addPlayer(new AI_Player(new SafePlayer(), new BigSpender(3000)));
        dealer.addPlayer(new AI_Player(new SafePlayer(), new BigSpender(3000)));
        dealer.addPlayer(new AI_Player(new SafePlayer(), new BigSpender(3000)));
        dealer.addPlayer(new AI_Player(new SafePlayer(), new BigSpender(3000)));
        dealer.addPlayer(new AI_Player(new SafePlayer(), new BigSpender(3000)));

    }

    public void runSimulation() {
        System.out.println("Playing rounds...");

        while (dealer.hasPlayers()) {
            //System.out.println(dealer.playRound());
            dealer.playRound();
        }
    }

    public static void main(String[] args) {
        SimulationRunner simulationRunner = new SimulationRunner();

        simulationRunner.runSimulation();

        simulationRunner.printHouseIncome();
        simulationRunner.printRoundCount();

    }

    private void printHouseIncome() {
        System.out.println("House Income: " + dealer.getHouseIncome());
    }

    private void printRoundCount() {
        System.out.println("Number of rounds: " + dealer.getRoundCount());
    }

}
