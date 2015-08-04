package com.manio.horserace;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Collections;

public class RaceService {


    public List<Jockey> initRacers(int racers) {

        List<Jockey> jockeyList = new ArrayList<Jockey>();
        for(int a = 0; a < racers; a++) {
            jockeyList.add(new Jockey("Jockey " + (a + 1), a + 1));
        }
        return jockeyList;

    }

    public int horseRun (Jockey jockey, Race race, int speed, int distance) {

        Random rand = new Random();

        int ran = rand.nextInt(speed) + 1; //+1 to prevent 0
        // ran = 5 + 1; // testing

        int currDist = (jockey.getCurrDist() + ran) > distance ? distance : jockey.getCurrDist() + ran;
        
        jockey.setCurrDist(currDist);
        incIteration(jockey, 1);

        return ran;

    }

    public void updateRanking (Race race) {

        List<Jockey> jockeyList = race.getJockeyList();
        Thread t = Thread.currentThread();

        // Collections.sort(jockeyList, Collections.reverseOrder());
        Collections.sort(jockeyList, Collections.reverseOrder(
                                        new JockeyChainComparator(
                                            new JockeyDistanceComparator(),
                                            new JockeyIterationComparator(),
                                            new JockeyNameComparator()
                                        )
                                    )
        );
 
        System.out.println();
        System.out.println("Request by: " + t.getName());
        System.out.println("Printing Ranking:");
        int rank = 1;
        for(Jockey j : jockeyList) {
            System.out.println("Rank " + rank + " : " + j.getName() + ", with distance of: " + j.getCurrDist());
            j.setPosi(rank++);
        }
        System.out.println();
    }

    public void postRanking(Race race) {
        while (!race.getIsFinished()) {
            updateRanking(race);
            try {
                Thread.sleep(Constant.RACE_INTERVAL);
            }
            catch (InterruptedException e) {
                System.out.println("TIMER " + Constant.INTERRUPT_ERROR);
            }
        }
    }

    // public void addFinalRanking(Jockey jockey, Race race) {
    //     List<Jockey> jockeyHolder = race.getFinalRanking();
    //     if (jockeyHolder == null) {
    //         jockeyHolder = new ArrayList<Jockey>();
    //     }
    //     jockeyHolder.add(jockey);
    //     System.out.println(jockey.getName() + ":" + jockeyHolder.size() );
    //     race.setFinalRanking(jockeyHolder);
    // }



    public String getWarCry () {
        Random rand = new Random();
        return Constant.WIN_WARCRY[rand.nextInt(Constant.WIN_WARCRY.length)];
    }

    public void incIteration(Jockey jockey, int inc) {
        int hold = jockey.getRaceIteration();
        hold += inc;
        jockey.setRaceIteration(hold);
    }

    public void countDownRace() {

        System.out.println(Constant.RACE_START);

        for(int a = 5; a > 0; a--) {
            System.out.println(a);
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                System.out.println("TIMER " + Constant.INTERRUPT_ERROR);
            }
        }

    }

    public void printLastRank (Race race) {
        int counter = 1;
        List<Jockey> jockeyList = race.getFinalRanking();
        for(Jockey j : jockeyList ) {
            System.out.println("Rank " + counter++ + " : " + j.getName());
        }
    }

}