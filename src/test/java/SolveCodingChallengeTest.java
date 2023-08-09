import org.challenge.Interval;
import org.challenge.SolveCodingChallenge;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class SolveCodingChallengeTest {

    @Test
    void testWithInternet() {
        List<Interval> intervals2 = new ArrayList<>(Arrays.asList(new Interval(1, 3),
                                                                  new Interval(2, 6),
                                                                  new Interval(8, 10),
                                                                  new Interval(15, 18)));
        assertIterableEquals(Arrays.asList(new Interval(1, 6), new Interval(8, 10),
                                           new Interval(15, 18)),
                             SolveCodingChallenge.merge(intervals2));
        List<Interval> intervals3 = new ArrayList<>(Arrays.asList(new Interval(1, 4),
                                                                  new Interval(1, 5)));
        assertIterableEquals(List.of(new Interval(1, 5)), SolveCodingChallenge.merge(intervals3));
    }

    @Test
    void testMergeWithEmptyList() {
        List<Interval> intervals = new ArrayList<>();
        List<Interval> expected = new ArrayList<>();
        assertIterableEquals(expected, SolveCodingChallenge.merge(intervals));
    }

    @Test
    void testMergeWithOneInterval() {
        List<Interval> intervals = new ArrayList<>(List.of(new Interval(25, 30)));
        List<Interval> expected = List.of(new Interval(25, 30));
        assertIterableEquals(expected, SolveCodingChallenge.merge(intervals));
    }

    @Test
    void testMergeSomeOverlappingIntervals() {
        List<Interval> intervals = new ArrayList<>(Arrays.asList(new Interval(25, 30),
                                                                 new Interval(2, 19),
                                                                 new Interval(14, 23),
                                                                 new Interval(4, 8)));
        List<Interval> expected = Arrays.asList(new Interval(2, 23), new Interval(25, 30));
        assertIterableEquals(expected, SolveCodingChallenge.merge(intervals));
    }

    @Test
    void testMergeWithOnlyOverlappingIntervals() {
        List<Interval> intervals = new ArrayList<>(Arrays.asList(new Interval(2, 30),
                                                                 new Interval(2, 19),
                                                                 new Interval(14, 23),
                                                                 new Interval(4, 8)));
        List<Interval> expected = List.of(new Interval(2, 30));
        assertIterableEquals(expected, SolveCodingChallenge.merge(intervals));
    }

    @Test
    void testMergeWithNoOverlappingIntervals() {
        List<Interval> intervals = new ArrayList<>(Arrays.asList(new Interval(25, 30),
                                                                 new Interval(2, 19),
                                                                 new Interval(50, 51),
                                                                 new Interval(31, 49)));
        List<Interval> expected = Arrays.asList(new Interval(2, 19), new Interval(25, 30),
                                                new Interval(31, 49), new Interval(50, 51));
        assertIterableEquals(expected, SolveCodingChallenge.merge(intervals));
    }
}
