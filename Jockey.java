package com.manio.horserace;

public class Jockey implements Comparable<Jockey> {

    private JockeyStatus jockeyStatus;
    
    private String name;

    private int currPosi;
    private int currDist;
    private int raceIteration;

    {
        this.jockeyStatus = JockeyStatus.STABLE;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getName () {
        return this.name;
    }

    public void setPosi (int currPosi) {
        this.currPosi = currPosi;
    }

    public int getPosi () {
        return this.currPosi;
    }

    public void setCurrDist (int currDist) {
        this.currDist = currDist;
    }

    public int getCurrDist () {
        return this.currDist;
    }

    public void setJockeyStatus (JockeyStatus jockeyStatus) {
        this.jockeyStatus = jockeyStatus;
    }

    public JockeyStatus getJockeyStatus () {
        return this.jockeyStatus;
    }

    public void setRaceIteration (int raceIteration) {
        this.raceIteration = raceIteration;
    }

    public int getRaceIteration () {
        return this.raceIteration;
    }

    public Jockey (String name, int currPosi) {
        this.name = name;
        this.currPosi = currPosi;
    }

    //Comparable of key and value
    public int compareTo(Jockey jockey) {
        
        int cmp = this.currDist > jockey.currDist ? +1 : this.currDist < jockey.currDist ? -1 : 0;

        //if comparable only
        /*int cmp = this.currDist > horse.currDist ? +1 : this.currDist < horse.currDist ? -1 : 0;
        
        //move to comparator
        if (cmp == 0) {
            cmp = this.iterate > horse.iterate ? +1 : this.iterate < horse.iterate ? -1 : this.name.compareTo(horse.name);
        }*/

        return cmp;
        
    }

}