import client_booking.AddRecord;
import client_booking.AppointmentBooking;
import entities.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AddRecordTest {
    BookableClinic clinic;
    AddRecord addRecord;
    TimePeriod timePeriod;
    VaccineBatch batch;
    Client client1;
    LocalDateTime dateTime;


    @Before // Setting up before the tests
    public void setUp() throws Exception{
        dateTime = LocalDateTime.of(2020, 11, 14, 12, 30);
        timePeriod = new TimePeriod(dateTime, 5);
        batch = new VaccineBatch("Pfizer", 100, LocalDate.of(2099, 10 , 30), 1234);


        // Setting up the clients
        client1 = new Client("client1", "qwertyuiop");

        // Setting up appointments
        Appointment appointment1 = new Appointment(client1, timePeriod, "Pfizer", 1, batch);

        Clinic clinic1 = new Clinic(1, "Toronto");
        clinic = new BookableClinic(clinic1);
        clinic.setShift(dateTime.toLocalDate(), 5);
        clinic.addTimePeriod(timePeriod, dateTime.toLocalDate());

        clinic.addAppointment(appointment1);

        addRecord = new AddRecord(clinic);

    }

    @Test(timeout = 150) // Testing that appointments can be logged by appointment ID
    public void TestLogAppointment() {
        assertNotNull(addRecord.logAppointment(1));
        assertTrue(clinic.getAppointmentByTimePeriod(timePeriod).isEmpty());
        assertTrue(clinic.getVaccineLog().containsId("A1"));
    }

    @Test(timeout = 150) // Testing that walk ins can be logged
    public void TestLogWalkIn() {
        addRecord.logWalkIn("2", client1, dateTime, "Pfizer");
        assertTrue(clinic.getVaccineLog().containsId("V2"));
    }

    @Test(timeout = 150) // Testing that all appointment on a certain date and time can be logged
    public void TestLogByDateTime() {
        assertNotNull(addRecord.logByDateTime(dateTime));
        assertTrue(clinic.getAppointmentByTimePeriod(timePeriod).isEmpty());
        assertTrue(clinic.getVaccineLog().containsId("A1"));
    }

    @Test(timeout = 150) // Testing that all appointments on a certain date can be logged
    public void TestLogByDate() {
        assertNotNull(addRecord.logByDate(dateTime.toLocalDate()));
        assertTrue(clinic.getAppointmentByTimePeriod(timePeriod).isEmpty());
        assertTrue(clinic.getVaccineLog().containsId("A1"));
    }

}

