import entities.VaccineBatch;
import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

public class VaccineBatchTest {
    VaccineBatch batch;
    VaccineBatch badBatch;



    @Before // Setting up before the tests
    public void setUp() throws Exception {
        batch = new VaccineBatch("Pfizer", 100,
                LocalDate.of(2099, 10 , 30), 1234);
        badBatch = new VaccineBatch("Moderna", 69,
                LocalDate.of(2021, 10 , 10), 1234);
    }

    @Test(timeout = 100) // Testing that a vaccine batch is expired
    public void testSetVaccineBatchReserve(){
        batch.setReserve(25);
        assertEquals(batch.getReserve(), 25);
    }

    @Test(timeout = 100) // Testing that a vaccine batch is not expired
    public void testVaccineBatchNotExpired(){
        assertFalse(batch.isExpired());
    }

    @Test(timeout = 100) // Testing that a vaccine batch is expired
    public void testVaccineBatchExpired(){
        assertTrue(badBatch.isExpired());
    }



}