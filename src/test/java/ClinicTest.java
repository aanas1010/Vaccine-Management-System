import entities.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClinicTest {
    ServiceLocation clinic;
    ServiceLocation clinicWalkIn;
    ServiceLocation clinicBookingOpt;
    Client client;

    @Before // Setting up before the tests
    public void setUp() {
        clinicWalkIn = new Clinic.ClinicBuilder(1, "Shoppers Drug Mart - 279 Yonge Street").build();
        clinicBookingOpt = new BookableClinic(new Clinic.ClinicBuilder(2, "Rexall - 63 Wellesley Street East").build());
        clinic = new Clinic.ClinicBuilder(3, "Walmart - 900 Dufferin Street").build();
        client = new Client("Barack Obama", "1776");

    }

    @Test(timeout = 100) // Testing logging a past vaccination
    public void TestLogPastVaccinations() {
        clinicWalkIn.logPastVaccinations("10", client, LocalDateTime.now(), "Pfizer");

        String name = clinicWalkIn.getVaccineLog().getClientByVaccinationId("V10").getName();
        String healthCareNumber = clinicWalkIn.getVaccineLog().getClientByVaccinationId("V10").getHealthCareNumber();

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
