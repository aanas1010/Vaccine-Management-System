import client_booking.AppointmentBooking;
// import client_booking.AppointmentBookingBackUp;
import client_booking.AppointmentCancellation;
import client_booking.AppointmentViewing;
import clinic_management.BatchAdding;
import entities.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentBookingTest {
    BookableClinic clinic;
    AppointmentViewing appointmentViewing1;
    AppointmentViewing appointmentViewing2;
    AppointmentBooking appointmentBooking1;
    AppointmentBooking appointmentBooking2;
    //AppointmentCancellation appointmentCancellation1;
    AppointmentCancellation appointmentCancellation2;
    BatchAdding batchAdd;
    Appointment appointment1;
    Appointment appointment2;
    TimePeriod timePeriod;

    @Before // Setting up before the tests
    public void setUp() throws Exception{
        Client client1 = new Client("client1", "qwertyuiop");
        // Client client2 = new Client("client2", "asdfghjkl");

        timePeriod = new TimePeriod(LocalDateTime.of(2021, 11, 14, 12, 30), 5);

        VaccineBatch batch = new VaccineBatch("Pfizer", 100,
                LocalDate.of(2099, 10 , 30), 1234);

        ArrayList<VaccineBatch> newList = new ArrayList<>();
        newList.add(batch);
        VaccineSupply supply = new VaccineSupply(newList);

        clinic = new BookableClinic(1, supply);
        clinic.setShift(LocalDate.of(2021, 11, 14), 20);
        clinic.addTimePeriod(timePeriod, LocalDate.of(2021, 11, 14));

        appointmentBooking1 = new AppointmentBooking(client1, clinic, timePeriod, "Pfizer", 11);
        appointmentBooking1.assignVaccineDose();
        appointmentBooking1.createAppointment();

        //  = new AppointmentBooking(client2, clinic, timePeriod, "Pfizer", 22);

        // appointmentCancellation2 = new AppointmentCancellation(client1, clinic, timePeriod, 22);

        // appointmentViewing1 = new AppointmentViewing(clinic, 11);
        // appointmentViewing2 = new AppointmentViewing(clinic, 22);
    }

    @Test(timeout = 100) // Testing that the selected timeslot is available
    public void TestAssignVaccineDose() {
        assertTrue(timePeriod.getAvailableSlots() > 0);
    }

    @Test(timeout = 100) // Testing that the selected timeslot is available
    public void TestAssignExpiredVaccineDose() {
        assertTrue(timePeriod.getAvailableSlots() > 0);
    }

    @Test(timeout = 100) // Testing that the selected timeslot is available
    public void TestCreateAppointment() {
        assertTrue(timePeriod.getAvailableSlots() > 0);
    }

}
