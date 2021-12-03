import constants.ManagementSystemException;
import clientbooking.AppointmentBooking;
import clientbooking.AppointmentCancellation;
import clientbooking.AppointmentViewing;
import entities.*;
import org.junit.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

//test cases for the appointmentViewing class
public class AppointmentViewingTest {
    ClinicDecorator clinic; //bookable clinic

    AppointmentViewing appointmentViewing1; //appointment booked                  (view)
    AppointmentViewing appointmentViewing2; //appointment cancelled               (view)
    AppointmentViewing appointmentViewing3; //appointment never existed           (view)

    AppointmentBooking appointmentBooking1; //appointment booked                  (book)
    AppointmentBooking appointmentBooking2; //appointment cancelled               (book)

    AppointmentCancellation appointmentCancellation2; //appointment cancelled     (cancel)

    TimePeriod timePeriod;
        @Before // Setting up before the tests
    public void setUp() throws Exception{
        Client client1 = new Client("client1", "healthCareNumber1"); //appointment booked
        Client client2 = new Client("client2", "healthCareNumber2"); //appointment cancelled

        timePeriod = new TimePeriod(LocalDateTime.of(2021, 11, 14, 12, 30), 5);

        VaccineBatch batch = new VaccineBatch.BatchBuilder().brand("Pfizer").quantity(100).expiry(LocalDate.of(2099, 10 , 30)).id(1234).build();

        ArrayList<VaccineBatch> newList = new ArrayList<>();
        newList.add(batch);
        VaccineSupply supply = new VaccineSupply(newList);

        clinic = new BookableClinic(new Clinic.ClinicBuilder(1, "Shoppers Drug Mart - 279 Yonge Street").Supply(supply).build());
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


        appointmentViewing1 = new AppointmentViewing(11, clinic);
        appointmentViewing2 = new AppointmentViewing(22, clinic);
        appointmentViewing3 = new AppointmentViewing(44, clinic);

    }

     @Test(timeout = 100) // Testing the use case an appointment is booked and active
     public void TestAppointmentDetails() {
         String message_correct = "----------------APPOINTMENT #" + "11" + "----------------" +
                 "\nCLIENT: " + "client1" +
                 "\nTIME: " + "2021-11-14T12:30" +
                 "\nBRAND: " + "Pfizer" +
                 "\nBATCH: " + "1234";

         try{
             String message_method = appointmentViewing1.appointmentDetails();
             assertEquals(message_correct, message_method);
         }catch(ManagementSystemException e) {
             fail();
         }
     }

    @Test(timeout = 100) // Testing the use case a booked appointment was canceled
    public void TestAppointmentDetailsCancelled() {
        try{
            appointmentViewing2.appointmentDetails();
            fail();
        }catch(ManagementSystemException ignored) {}
    }

    @Test(timeout = 100) // Testing the use case an appointment never existed
    public void TestAppointmentDetailsDoesNotExist() {
        try{
            appointmentViewing3.appointmentDetails();
            fail();
        }catch(ManagementSystemException ignored) {}
    }
}
