package com.manio.horserace;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Collections;

public class RaceRunnable implements Runnable {
	
	private Race race;

	public RaceRunnable(Race race) {
		this.race = race;
	}


	public void run() {

    	RaceService raceService = new RaceService();

    	List<Jockey> jockeyList = race.getJockeyList();

        List<Thread> threadList = startRaceThreads(jockeyList, race);

        checkTheGates(race, jockeyList);

        System.out.println(Constant.HORSES_IN_GATES);

        raceService.countDownRace();

        //wait 500ms
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            System.out.println("TIMER " + Constant.INTERRUPT_ERROR);
        }

        race.setRaceStart(true);

        synchronized(jockeyList) {
            jockeyList.notifyAll();
        }

        raceService.postRanking(race);

        joinThreads(threadList);

	}

    private void checkTheGates(Race race, List<Jockey> jockeyList) {
        int jockeyAtGate = 0;
        //run until all jockeys are at the gates
        while (jockeyAtGate != race.getRacers()) {
            jockeyAtGate = 0;
            for (Jockey j : jockeyList) {
                if (j.getJockeyStatus().equals(JockeyStatus.GATE)) {
                    ++jockeyAtGate;
                }
            }
        }
    }

    private List<Thread> startRaceThreads (List<Jockey> jockeyList, Race race) {

        List<Thread> threadList = new ArrayList<Thread>();

        int counter = 0;

        for (Jockey j : jockeyList) {
            threadList.add(new Thread(new JockeyRunnable(j, race), j.getName()));
            threadList.get(counter).start();
            counter++;
        }

        return threadList;

    }

    private void joinThreads(List<Thread> threadList) {
        for (Thread t : threadList) {
            try {
                t.join();
            }
            catch (InterruptedException e) {
                System.out.println("Join " + Constant.INTERRUPT_ERROR);
            }
        }
    }

}