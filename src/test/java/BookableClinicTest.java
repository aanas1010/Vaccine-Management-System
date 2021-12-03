import entities.*;
import org.junit.*;
import static org.junit.Assert.*;

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
        clinic = new BookableClinic(new Clinic.ClinicBuilder(1, "Shoppers Drug Mart - 279 Yonge Street").build());
        client = new Client("Joe Mama", "4206969");
        timePeriod = new TimePeriod(LocalDateTime.now(), 50);
        clinic.addTimePeriod(timePeriod, LocalDate.now());
        vaccineBatch = new VaccineBatch.BatchBuilder().brand("Pfizer").quantity(5).expiry(LocalDate.ofYearDay(2021, 365)).id(1234).reserve(4).build();
        appointment = new Appointment.AppointmentBuilder(client, timePeriod, "Pfizer", 10, vaccineBatch).build();
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
