import client_booking.AppointmentBooking;
import client_booking.AppointmentCancellation;
import client_booking.AppointmentViewing;
import entities.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AppointmentViewingTest {
    BookableClinic clinic;

    AppointmentViewing appointmentViewing1; //appointment booked                  (view)
    AppointmentViewing appointmentViewing2; //appointment cancelled               (view)
    AppointmentViewing appointmentViewing3; //appointment passed-booked           (view)
    AppointmentViewing appointmentViewing4; //appointment never existed           (view)
    AppointmentViewing appointmentViewing5; //appointment passed-not_booked       (view)

    AppointmentBooking appointmentBooking1; //appointment booked                  (book)
    AppointmentBooking appointmentBooking2; //appointment cancelled               (book)
    AppointmentBooking appointmentBooking3; //appointment passed                  (book)

    AppointmentCancellation appointmentCancellation2; //appointment cancelled     (cancel)

    TimePeriod timePeriod;
        @Before // Setting up before the tests
    public void setUp() throws Exception{
        Client client1 = new Client("client1", "healthCareNumber1"); //appointment booked
        Client client2 = new Client("client2", "healthCareNumber2"); //appointment cancelled
        Client client3 = new Client("client3", "healthCareNumber3"); //appointment passed-booked
        Client client5 = new Client("client5", "healthCareNumber5"); //appointment passed-notBooked

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
        appointmentCancellation2.deleteAppointment();

        appointmentBooking3 = new AppointmentBooking(client3, clinic, timePeriod, "Pfizer", 33);
        appointmentBooking3.assignVaccineDose();
        appointmentBooking3.createAppointment();
        clinic.logPastVaccinations(this.clinic.getAppointmentRecord(33));
        clinic.removeAppointmentById(33);

        clinic.logPastVaccinations("55", client5, timePeriod.getDateTime(), "Pfizer");

        appointmentViewing1 = new AppointmentViewing(11, clinic);
        appointmentViewing2 = new AppointmentViewing(22, clinic);
        appointmentViewing3 = new AppointmentViewing(33, clinic);
        appointmentViewing4 = new AppointmentViewing(44, clinic);
        appointmentViewing5 = new AppointmentViewing(55, clinic);
    }

     @Test(timeout = 100) // Testing that the selected timeslot is available
     public void TestAppointmentDetails_appointmentExists() {
         String message_correct = "Hello " + "client1" + ", \n" +
                 "your appoinment for a " + "Pfizer" + " vaccine has been set for: \n" +
                 "strDate" + " - at " + "1" + "\n";
         String message_method = appointmentViewing1.appointmentDetails();

         assertEquals(message_correct, message_method);
     }

    @Test(timeout = 100) // Testing that the selected timeslot is available
    public void TestAppointmentDetails_appointmentCanceled() {
        String message_correct = "Hello, \n" +
                "you do not have any appointment currently booked.";
        String message_method = appointmentViewing2.appointmentDetails();

        assertEquals(message_correct, message_method);
    }

     @Test(timeout = 100) // Testing that the selected timeslot is available
     public void TestAppointmentDetails_appointmentPassed_wasBooked() {
         String message_correct = "Hello " + "client3" + ", \n " +
                 "your appoinment for a " + "Pfizer" + " vaccine at: \n" +
                 "strDate" + " - " + "1" + "\n" +
                 "has passed. \n" +
                 "Thank you for using our services";
         String message_method = appointmentViewing3.appointmentDetails();

         assertEquals(message_correct, message_method);
     }

    @Test(timeout = 100) // Testing that the selected timeslot is available
    public void TestAppointmentDetails_appointmentPassed_wasNotBooked() {
        String message_correct = "Hello " + "client5" + ", \n " +
                "your appoinment for a " + "Pfizer" + " vaccine at: \n" +
                "strDate" + " - " + "1" + "\n" +
                "has passed. \n" +
                "Thank you for using our services";
        int extra = 5;
        String message_method = appointmentViewing5.appointmentDetails();

        assertEquals(message_correct, message_method);
    }

    @Test(timeout = 100) // Testing that the selected timeslot is available
    public void TestAppointmentDetails_appointmentNeverExisted() {
        String message_correct = "Hello, \n" +
                "you do not have any appointment currently booked.";
        String message_method = appointmentViewing4.appointmentDetails();

        assertEquals(message_correct, message_method);
    }


}