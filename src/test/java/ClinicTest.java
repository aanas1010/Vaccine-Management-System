import entities.Client;
import entities.Clinic;
import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClinicTest {
    Clinic clinic;
    Client client;

    @Before // Setting up before the tests
    public void setUp() {
        clinic = new Clinic(1);
        client = new Client("Barack Obama", "1776");

    }

    @Test(timeout = 100) // Testing logging a past vaccination
    public void TestLogPastVaccinations() {
        clinic.logPastVaccinations("10", client, LocalDateTime.now(), "Pfizer");

        String name = clinic.getVaccineLog().getClientByVaccinationId("V10").getName();
        String healthCareNumber = clinic.getVaccineLog().getClientByVaccinationId("V10").getHealthCareNumber();

        assertEquals(name, client.getName());
        assertEquals(healthCareNumber, client.getHealthCareNumber());
    }

    @Test(timeout = 100) // Testing logging a past vaccination
    public void TestShift() {
        LocalDate date = LocalDate.now();
        int numOfShapes = 20;

        clinic.setShift(date, numOfShapes);

        assertTrue(clinic.containsShift(date));
        assertTrue(clinic.shiftAvailable(date));
        assertEquals(clinic.getShiftForDate(date), numOfShapes);
    }
}
