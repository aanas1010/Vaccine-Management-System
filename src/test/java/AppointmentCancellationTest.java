import constants.ManagementSystemException;
import clientbooking.AppointmentBooking;
import clientbooking.AppointmentCancellation;
import entities.*;
import org.junit.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AppointmentCancellationTest {
    ClinicDecorator clinic;
    AppointmentBooking appointmentBooking1;
    TimePeriod timePeriod;
    VaccineBatch batch;

    @Before // Setting up before the tests
    public void setUp() throws Exception {
        timePeriod = new TimePeriod(LocalDateTime.of(2021, 11, 14, 12, 30), 5);

        Client client1 = new Client("client1", "qwertyuiop");

        batch = new VaccineBatch.BatchBuilder().brand("Pfizer").quantity(100).expiry(LocalDate.of(2099, 10 , 30)).id(1234).build();

        ArrayList<VaccineBatch> newList = new ArrayList<>();
        newList.add(batch);
        VaccineSupply supply = new VaccineSupply(newList);

        clinic = new BookableClinic(new Clinic.ClinicBuilder(1, "Shoppers Drug Mart - 279 Yonge Street").Supply(supply).build());
        clinic.setShift(LocalDate.of(2021, 11, 14), 20);
        clinic.addTimePeriod(timePeriod, LocalDate.of(2021, 11, 14));

        appointmentBooking1 = new AppointmentBooking(client1, clinic, timePeriod, "Pfizer", 11);

        appointmentBooking1.createAppointment();
    }

    @Test(timeout = 100) // Testing that the selected timeslot is available
    public void TestDeleteAppointment() {
        AppointmentCancellation client1CancelledAppointment = new AppointmentCancellation(11,
                clinic);
        try{
            assertNotNull(client1CancelledAppointment.deleteAppointment());
        }catch(ManagementSystemException e) {
            fail();
        }
    }

}
