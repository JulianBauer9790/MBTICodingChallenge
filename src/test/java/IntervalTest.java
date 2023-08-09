import org.challenge.Interval;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntervalTest {

    @Test
    void testGreaterStartTimeThanEndTimeFails() {
        String expectedMessage = "The endTime must be greater than or equal to the startTime.";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Interval(2, 1);
        });
        assertTrue(exception.getMessage().contains(expectedMessage));

        Interval interval = new Interval(1, 2);

        exception = assertThrows(IllegalArgumentException.class, () -> {
            interval.setStartTime(3);
        });
        assertTrue(exception.getMessage().contains(expectedMessage));

        exception = assertThrows(IllegalArgumentException.class, () -> {
            interval.setEndTime(0);
        });
        assertTrue(exception.getMessage().contains(expectedMessage));

        exception = assertThrows(IllegalArgumentException.class, () -> {
            interval.setEndTime(-1);
        });
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void testNegativeStartTimeFails() {
        String expectedMessage = "must not be negative.";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Interval(-1, 1);
        });
        assertTrue(exception.getMessage().contains(expectedMessage));

        Interval interval = new Interval(1, 2);
        exception = assertThrows(IllegalArgumentException.class, () -> {
            interval.setStartTime(-1);
        });
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void testCompareTo() {
        Interval first = new Interval(1, 2);
        Interval second = new Interval(2, 3);
        Interval third = new Interval(1, 3);

        assertEquals(-1, first.compareTo(second));
        assertEquals(1, second.compareTo(first));
        assertEquals(0, first.compareTo(third));
    }
}
