import client_booking.AppointmentBooking;
import entities.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AppointmentBookingTest {
    BookableClinic clinic;
    BookableClinic clinic2;
    AppointmentBooking appointmentBooking1;
    AppointmentBooking appointmentBooking2;
    TimePeriod timePeriod;
    VaccineBatch batch;
    VaccineBatch expiredBatch;

    @Before // Setting up before the tests
    public void setUp() throws Exception{
        timePeriod = new TimePeriod(LocalDateTime.of(2021, 11, 14, 12, 30), 5);

        // Setting up the first client
        Client client1 = new Client("client1", "qwertyuiop");

        batch = new VaccineBatch("Pfizer", 100, LocalDate.of(2099, 10 , 30), 1234);

        ArrayList<VaccineBatch> newList = new ArrayList<>();
        newList.add(batch);
        VaccineSupply supply = new VaccineSupply(newList);

        clinic = new BookableClinic(new Clinic(1, supply, "Shoppers Drug Mart - 279 Yonge Street"));
        clinic.setShift(LocalDate.of(2021, 11, 14), 20);
        clinic.addTimePeriod(timePeriod, LocalDate.of(2021, 11, 14));

        appointmentBooking1 = new AppointmentBooking(client1, clinic, timePeriod, "Pfizer", 11);

        // Setting up the second client
        Client client2 = new Client("client2", "asdfghjkl");

        expiredBatch = new VaccineBatch("Pfizer", 100, LocalDate.of(2020, 10 , 30), 1234);

        ArrayList<VaccineBatch> newList2 = new ArrayList<>();
        newList2.add(expiredBatch);
        VaccineSupply supply2 = new VaccineSupply(newList2);

        clinic2 = new BookableClinic(new Clinic(1, supply2, "Rexall - 63 Wellesley Street East"));
        clinic2.setShift(LocalDate.of(2021, 11, 14), 20);
        clinic2.addTimePeriod(timePeriod, LocalDate.of(2021, 11, 14));

        appointmentBooking2 = new AppointmentBooking(client2, clinic2, timePeriod, "Pfizer", 11);
    }

    @Test(timeout = 100) // Testing that the correct vaccine batch has been assigned
    public void TestAssignVaccineDose() {
        VaccineBatch assignedDose = appointmentBooking1.assignVaccineDose();
        assertEquals(batch, assignedDose);
    }

    @Test(timeout = 100) // Testing that an expired vaccine dose isn't assigned
    public void TestAssignExpiredVaccineDose() {
        assertNull(appointmentBooking2.assignVaccineDose());
    }

    @Test(timeout = 100) // Testing that an appointment has indeed been created
    public void TestCreateAppointment() {
        assertTrue(appointmentBooking1.createAppointment());
    }

}
