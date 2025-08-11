package stopwatch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StopWatchTest {

    @Test
    public void testAddMinutesIncreasesMinutes() {
        StopWatch sw = new StopWatch(8); // daily hours = 8
        sw.record(30);
        assertEquals(30, sw.getMinutes());
        assertEquals(0, sw.getHours());
        assertEquals(0, sw.getDays());
    }

    @Test
    public void testNegativeMinutesIgnored() {
        StopWatch sw = new StopWatch(8);
        sw.record(-15);
        assertEquals(0, sw.getMinutes());
    }


}
