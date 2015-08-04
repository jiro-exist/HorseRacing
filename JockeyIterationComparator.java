package com.manio.horserace;
 
import java.util.Comparator;

public class JockeyIterationComparator implements Comparator<Jockey> {
 
    @Override
    public int compare(Jockey j1, Jockey j2) {
        // return j1.getRaceIteration() - j2.getRaceIteration(); // 2>1
        return j2.getRaceIteration() - j1.getRaceIteration(); // 1>2
    }
}