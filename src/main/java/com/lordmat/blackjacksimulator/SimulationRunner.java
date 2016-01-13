package com.lordmat.blackjacksimulator;

import com.lordmat.blackjacksimulator.spending.BigSpender;
import com.lordmat.blackjacksimulator.statergy.DealerStrategy;
import com.lordmat.blackjacksimulator.statergy.SafePlayer;
import com.lordmat.blackjacksimulator.statergy.WatchesDealersCard;

/**
 *
 * @author mat
 */
public class SimulationRunner {

    //TODO threading for multiple BlackJackTables to run at the same time
    private BlackJackTable table;
    private int simulationRuns;
    private int totalRoundCount;
    private int longestRound = Integer.MIN_VALUE;
    private int shortestRound = Integer.MAX_VALUE;

    public SimulationRunner() {
    }

    public int getSimulationRuns() {
        return simulationRuns;
    }

    public int getShortestRound() {
        return shortestRound;
    }

    public double getAverageRoundCount() {
        return (double) totalRoundCount / simulationRuns;
    }

    private int getLongestRound() {
        return longestRound;
    }

    public void runSimulation() {
        simulationRuns++;
        System.out.println("Playing rounds ID:" + simulationRuns);

        table = new BlackJackTable(new DealerStrategy());

        table.addPlayer(new AI_Player(new SafePlayer(), new BigSpender(10000)));
        table.addPlayer(new AI_Player(new SafePlayer(), new BigSpender(10000)));
        table.addPlayer(new AI_Player(new SafePlayer(), new BigSpender(10000)));
        table.addPlayer(new AI_Player(new SafePlayer(), new BigSpender(10000)));

        WatchesDealersCard strategy = new WatchesDealersCard();
        table.addPlayer(new AI_Player(strategy, new BigSpender(10000)));

        table.addDealerCardObserver(strategy);

        while (table.hasPlayers()) {
            //System.out.println(table.playRound());
            table.playRound();
        }

        int roundCount = table.getRoundCount();

        if (roundCount < shortestRound) {
            shortestRound = table.getRoundCount();
        }

        if (roundCount > longestRound) {
            longestRound = table.getRoundCount();
        }

        totalRoundCount += roundCount;
    }

    public static void main(String[] args) {
        SimulationRunner simulationRunner = new SimulationRunner();

        for (int i = 0; i < 1000000; i++) {
            simulationRunner.runSimulation();
        }

        int simulationRuns = simulationRunner.getSimulationRuns();
        int longestRound = simulationRunner.getLongestRound();
        int shortestRound = simulationRunner.getShortestRound();
        double averageRoundLength = simulationRunner.getAverageRoundCount();

        System.out.println("Final outcome:");
        System.out.println("Number of times run: " + simulationRuns);
        System.out.println("Longest Round " + longestRound);
        System.out.println("Shortest Round " + shortestRound);
        System.out.println("Average Round " + averageRoundLength);
    }

}
