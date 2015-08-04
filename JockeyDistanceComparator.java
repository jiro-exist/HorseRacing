package com.manio.horserace;
 
import java.util.Comparator;

public class JockeyDistanceComparator implements Comparator<Jockey> {
 
    @Override
    public int compare(Jockey j1, Jockey j2) {
        return j1.getCurrDist() - j2.getCurrDist();
    }
}