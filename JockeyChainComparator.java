package com.manio.horserace;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class JockeyChainComparator implements Comparator<Jockey> {
 
    private List<Comparator<Jockey>> listComparators;
 
    @SafeVarargs
    public JockeyChainComparator(Comparator<Jockey>... comparators) {
        this.listComparators = Arrays.asList(comparators);
    }
 
    @Override
    public int compare(Jockey j1, Jockey j2) {
        for (Comparator<Jockey> comparator : listComparators) {
            int result = comparator.compare(j1, j2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}