import entities.Client;
import entities.VaccinationLog;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDateTime;

public class VaccinationLogTest {
    VaccinationLog vaccinationLog;
    LocalDateTime dateTime;
    Client client;


    @Before // Setting up before the tests
    public void setUp() throws Exception {
        dateTime = LocalDateTime.of(2021, 11, 14, 12, 30);
        client = new Client("Paul Gries","99678538CX");
        vaccinationLog = new VaccinationLog();
        vaccinationLog.addToLog("123", client, dateTime, "Moderna");
    }
    /* These methods in themselves test vaccinationLog.getVaccinationRecord() because all
    *  the methods tested below use it in their implementation making it redundant to test it.*/

    @Test(timeout = 100) // Testing that a client can be found from a vaccination id
    public void testGetClientByID(){
        assertEquals(vaccinationLog.getClientByVaccinationId("V123"), client);
    }

    @Test(timeout = 100) // Testing that null is returned when a record with the vaccination id doesn't exist
    public void testGetClientByIDNull(){
        assertNull(vaccinationLog.getClientByVaccinationId("V6969"));
    }

    @Test(timeout = 100) // Testing that a date and time can be found from a vaccination id
    public void testGetDateTimeByID(){
        assertEquals(vaccinationLog.getDateTimeByVaccinationId("V123"), dateTime);
    }

    @Test(timeout = 100) // Testing that null is returned when a record with the vaccination id doesn't exist
    public void testGetDateTimeByIDNull(){
        assertNull(vaccinationLog.getDateTimeByVaccinationId("V6969"));
    }

    @Test(timeout = 100) // Testing that a vaccine brand can be found from a vaccination id
    public void testGetBrandByID(){
        assertEquals(vaccinationLog.getVaccineBrandByVaccinationId("V123"), "Moderna");
    }

    @Test(timeout = 100) // Testing that null is returned when a record with the vaccination id doesn't exist
    public void testGetBrandByIDNull(){
        assertNull(vaccinationLog.getVaccineBrandByVaccinationId("V6969"));
    }

}
