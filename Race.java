package com.manio.horserace;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Set;

public class Race {

    private List<Jockey> jockeyList;
	private List<Jockey> jockeyFinalRanking;
    
    private int distance;
    private int racers;

    private Jockey winner;

    private boolean isFinished;
    private boolean raceStart;

    private int counter;

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setRacers(int racers) {
        this.racers = racers;
    }

    public int getRacers() {
        return this.racers;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public boolean getIsFinished() {
        return this.isFinished;
    }

    public void setRaceStart(boolean raceStart) {
        this.raceStart = raceStart;
    }

    public boolean getRaceStart() {
        return this.raceStart;
    }

    public void setJockeyList(List<Jockey> jockeyList) {
        if (jockeyList != null && !jockeyList.isEmpty()) {
            this.jockeyList = jockeyList;
        }
    }

    public List<Jockey> getJockeyList() {
        return this.jockeyList;
    }

    public void setWinner(Jockey winner) {
        if (winner != null) {
            this.winner = winner;
        }
    }

    public Jockey getWinner() {
        return this.winner;
    }

    public void addFinalRanking(Jockey jockey) {
        if (this.jockeyFinalRanking == null) {
            jockeyFinalRanking = new ArrayList<Jockey>();
        }
        this.jockeyFinalRanking.add(jockey);
    }

    // public void setFinalRanking(List<Jockey> jockeyList) {
    //     this.jockeyList = jockeyList;
    // }

    public List<Jockey> getFinalRanking() {
        return this.jockeyFinalRanking;
    }


}