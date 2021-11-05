import entities.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookableClinicTest {
    ServiceLocation clinic;
    Client client;
    Appointment appointment;
    TimePeriod timePeriod;
    VaccineBatch vaccineBatch;

    @Before // Setting up before the tests
    public void setUp() {
        clinic = new BookableClinic(new Clinic((1)));
        client = new Client("Joe Mama", "4206969");
        timePeriod = new TimePeriod(LocalDateTime.now(), 50);
        ((BookableClinic)clinic).addTimePeriod(timePeriod, LocalDate.now());
        vaccineBatch = new VaccineBatch("Pfizer", 5, LocalDate.ofYearDay(2021, 365), 1234, 4);
        appointment = new Appointment(client, timePeriod, "Pfizer", 10, vaccineBatch);
    }

    @Test(timeout = 100) // Testing adding appointments
    public void TestAddAppointment() {
        assertTrue(((BookableClinic)clinic).addAppointment(appointment));
        assertEquals(((BookableClinic)clinic).getAppointmentRecord(10), appointment);
    }

    @Test(timeout = 100) // Testing removing appointments
    public void TestRemoveAppointment() {
        ((BookableClinic)clinic).addAppointment(appointment);
        assertTrue(((BookableClinic)clinic).removeAppointment(appointment));
        assertNull(((BookableClinic)clinic).getAppointmentRecord(10));
        assertEquals(vaccineBatch.getReserve(), 3);
    }

    @Test(timeout = 100) // Testing removing appointments by its ID
    public void TestRemoveAppointmentById() {
        ((BookableClinic)clinic).addAppointment(appointment);
        assertTrue(((BookableClinic)clinic).removeAppointmentById(10));
        assertNull(((BookableClinic)clinic).getAppointmentRecord(10));
    }
}
