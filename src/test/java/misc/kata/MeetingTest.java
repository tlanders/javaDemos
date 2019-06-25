package misc.kata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MeetingTest {
    @Test
    public void meetingTest() {
        assertEquals(
                "(CORWILL, ALFRED)(CORWILL, FRED)(CORWILL, RAPHAEL)(CORWILL, WILFRED)(TORNBULL, BARNEY)(TORNBULL, BETTY)(TORNBULL, BJON)",
                Meeting.meeting("Fred:Corwill;Wilfred:Corwill;Barney:Tornbull;Betty:Tornbull;Bjon:Tornbull;Raphael:Corwill;Alfred:Corwill"));
    }
}
