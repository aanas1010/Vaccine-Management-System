import entities.TimePeriod;
import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

public class TimePeriodTest {
    TimePeriod timePeriod;
    TimePeriod timePeriodNoSlots;



    @Before // Setting up before the tests
    public void setUp() {
        timePeriod = new TimePeriod(LocalDateTime.of(2021, 11, 14, 12, 30), 5);
        timePeriodNoSlots = new TimePeriod(LocalDateTime.of(2021, 11, 14, 12, 0), 0);
    }
    @Test(timeout = 100) // Testing that a spot is not reserved for a time period
    public void testVaccineBatchNotExpired(){
        assertFalse(timePeriodNoSlots.findAndReserveSlot());
    }

    @Test(timeout = 100) // Testing that a spot is reserved for a time period
    public void testVaccineBatchExpired(){
        assertTrue(timePeriod.findAndReserveSlot() && timePeriod.getAvailableSlots() == 4);
    }

}