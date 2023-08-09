package org.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>(Arrays.asList(new Interval(1, 3),
                                                                 new Interval(1, 4),
                                                                 new Interval(6, 8),
                                                                 new Interval(9, 10)));

        List<Interval> results = SolveCodingChallenge.merge(intervals);

        System.out.println(Arrays.toString(results.toArray()));
    }
}