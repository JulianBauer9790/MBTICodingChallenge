package org.challenge;

import java.util.Objects;

/**
 * This class represents an interval. An interval consists of a startTime, which is an int, as well
 * as an endTime, which is also an int. The endTime must not be less than the startTime, and both
 * startTime and endTime must be at least 0. In case this constraint is violated, an
 * IllegalArgumentException is thrown. This class also implements the Comparable interface. An
 * interval a is less than an interval b if and only if a's startTime is less than b's, that is,
 * a < b <=> a.startTime < b.startTime.
 */
public class Interval implements Comparable<Interval> {

    private int startTime;
    private int endTime;
    private final String endTimeGreaterThanStartTimeErrorMessage =
            "The endTime must be greater than or equal to the startTime.";
    private final String endStartTimeNotNegativeErrorMessage =
            "The startTime must not be negative.";

    public Interval(int startTime, int endTime) {
        if (startTime > endTime) {
            throw new IllegalArgumentException(this.endTimeGreaterThanStartTimeErrorMessage);
        }
        if (startTime < 0) {
            throw new IllegalArgumentException(this.endStartTimeNotNegativeErrorMessage);
        }

        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setStartTime(int startTime) {
        if (startTime > this.endTime) {
            throw new IllegalArgumentException(this.endTimeGreaterThanStartTimeErrorMessage);
        }
        if (startTime < 0) {
            throw new IllegalArgumentException(this.endStartTimeNotNegativeErrorMessage);
        }
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        if (this.startTime > endTime) {
            throw new IllegalArgumentException(this.endTimeGreaterThanStartTimeErrorMessage);
        }
        this.endTime = endTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    @Override
    public int compareTo(Interval otherInterval) {
        return Integer.compare(this.startTime, otherInterval.getStartTime());
    }

    /**
     * An interval is expressed as a string as "[startTime,endTime]".
     *
     * @return String representation of the interval
     */
    @Override
    public String toString() {
        return "[" + this.startTime + "," + this.endTime + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return startTime == interval.startTime && endTime == interval.endTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }
}
