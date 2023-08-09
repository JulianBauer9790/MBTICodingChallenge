package org.challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This final class provides the static merge method for merging overlapping intervals. Since this
 * class only has a static method, it has a private constructor to prevent the instantiation of
 * objects.
 */
public final class SolveCodingChallenge {

    private SolveCodingChallenge() {
    }

    /**
     * This method takes a list of intervals, merges overlapping intervals, and ignores intervals
     * that do not overlap. The algorithm first checks if the provided list of intervals is empty.
     * If so, it returns by design an empty list. Otherwise, it first sorts the intervals in
     * ascending order, and then checks for each two adjacent intervals if the overlap. Two adjacent
     * intervals a and b, which are sorted ascendingly, overlap if and only if b's startTime is less
     * than a's endTime. These two intervals can then be merged by setting a's endTime to the
     * maximum value of a's and b's endTimes. If b's startTime is greater than a's endTime, both
     * intervals do not overlap, and a can be added to the list of resulting, merged intervals.
     *
     * @param intervals List of intervals that shall be merged
     * @return A list of merged intervals in ascending order. If an interval in the input does not
     * overlap with any other, it is still included in the output list.
     */
    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return new ArrayList<>();
        }
        Collections.sort(intervals);
        List<Interval> results = new ArrayList<>();
        Interval currentInterval = intervals.get(0);

        for (int i = 1; i < intervals.size(); i++) {
            Interval nextInterval = intervals.get(i);
            if (currentInterval.getEndTime() >= nextInterval.getStartTime()) {
                currentInterval.setEndTime(Math.max(currentInterval.getEndTime(),
                                                    nextInterval.getEndTime()));
            } else {
                results.add(currentInterval);
                currentInterval = nextInterval;
            }
        }
        results.add(currentInterval);
        return results;
    }
}
