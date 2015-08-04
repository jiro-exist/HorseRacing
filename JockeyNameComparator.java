package com.manio.horserace;
 
import java.util.Comparator;

public class JockeyNameComparator implements Comparator<Jockey> {
 
    @Override
    public int compare(Jockey j1, Jockey j2) {
        //return j2.getName().compareTo(j1.getName()); //if Jockey2 > Jockey1
        return j2.getName().compareTo(j1.getName()); //if Jockey1 > Jockey2
    }
}