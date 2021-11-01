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

public class AppointmentViewingTest {
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
        Client client1 = new Client("client1", "healthCareNumber1");
        Client client2 = new Client("client2", "healthCareNumber2");

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

        appointmentBooking2 = new AppointmentBooking(client2, clinic, timePeriod, "Pfizer", 22);
        appointmentBooking2.assignVaccineDose();
        appointmentBooking2.createAppointment();

        appointmentCancellation2 = new AppointmentCancellation(22, clinic);
        // appointmentCancellation2.deleteAppointment();

        // appointmentViewing1 = new AppointmentViewing(11, clinic);
        // appointmentViewing2 = new AppointmentViewing(22, clinic);
    }

    @Test(timeout = 100) // Testing that the selected timeslot is available
    public void TestAppointmentDetails_noAppointmentExists() {
        String message_correct = "Hello " + "client1" + ", \n" +
                "your appoinment for a " + "Pfizer" + " vaccine has been set for: \n" +
                "strDate" + " - at " + "1" + "\n";
        String message_method = appointmentViewing2.appointmentDetails();
        messageassertEquals(message_correct, message_method);
    }

    // @Test(timeout = 100) // Testing that the selected timeslot is available
    // public void TestAppointmentDetails_appointmentExists() {
    //     String message = "Hello " + "client2" + ", \n" +
    //             "you do not have any appointment currently booked.";
    //     String message_method = appointmentViewing2.appointmentDetails();
    //     assertEquals(message_correct, message_method);
    // }
}