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
    BookableServiceLocation clinic;
    AppointmentViewing appointmentViewing1;
    AppointmentViewing appointmentViewing2;
    AppointmentBooking appointmentBooking1;
    AppointmentBooking appointmentBooking2;
    //AppointmentCancellation appointmentCancellation1;
    AppointmentCancellation appointmentCancellation2;
    BatchAdding batchAdd;

    @Before // Setting up before the tests
    public void setUp() throws Exception{
        Client client1 = new Client("client1", "qwertyuiop");
        Client client2 = new Client("client2", "asdfghjkl");

        TimePeriod timePeriod = new TimePeriod(LocalDateTime.of(2021, 11, 14, 12, 30), 5);

        VaccineBatch batch = new VaccineBatch("Pfizer", 100,
                LocalDate.of(2099, 10 , 30), 1234);

        ArrayList<VaccineBatch> newList = new ArrayList<>();
        newList.add(batch);
        VaccineSupply supply = new VaccineSupply(newList);

        clinic = new BookableClinic(1, supply);
        clinic.addTimePeriod(timePeriod, LocalDate.of(2021, 11, 14));
        clinic.setShift(LocalDate.of(2021, 11, 14), 20);

        //appointment1 = new Appointment(client1, timePeriod, "Pfizer", 99, batch);
        //appointment2 = new Appointment(client2, timePeriod, "Pfizer", 98, batch);

        appointmentBooking1 = new AppointmentBooking(client1, clinic, timePeriod, "Pfizer", 11);
        appointmentBooking2 = new AppointmentBooking(client2, clinic, timePeriod, "Pfizer", 22);

        // appointmentCancellation2 = new AppointmentCancellation(client1, clinic, timePeriod, 22);

        appointmentViewing1 = new AppointmentViewing(11, clinic);
        appointmentViewing2 = new AppointmentViewing(22, clinic);
    }

//    @Test(timeout = 100) // Testing an expired batch of vaccine doses does not get added
//    public void TestAppointmentDetailsExists(){
//        String message = "Hello client1" + ", \n" +
//                "your appointment for a " + "Pfizer" + " vaccine has been set for: \n" +
//                "14-11-2021 12:30:00 - at " + "1" + "\n";
//        assertEquals(message , appointmentViewing1.appointmentDetails());
//    }

   /* @Test(timeout = 100) // Testing an expired batch of vaccine doses does not get added
    public void TestAppointmentDetailsDoesNotExist() throws NullPointerException{
        String message = "Hello " + "client2" + ", \n" +
                "you do not have any appointment currently booked.";
        assertEquals(message , appointmentViewing2.appointmentDetails());
    }*/

}
