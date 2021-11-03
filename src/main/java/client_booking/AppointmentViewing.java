package client_booking;

import entities.Appointment;
import entities.BookableServiceLocation;

import java.net.SocketOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class AppointmentViewing {

    private final Appointment appointment;
    private final BookableServiceLocation clinic;

    public AppointmentViewing(int appointmentID, BookableServiceLocation clinic)
    {
        this.clinic = clinic;
        this.appointment = clinic.getAppointmentRecord(appointmentID);
    }

    /*
  TODO: fill out AppointmentDetails
 */
    /*
    produces a message:
    "Hello [client name],
     your appoinment for a [brand] vaccine has been set for:
     [date] - at  [location]"

     or

     "Hello [client name],
     you do not have any appointment currently booked."
     */

    public String appointmentDetails()
    {
        if (this.appointment.getClient().getHasAppointment())
            return appointmentBooked_message();
        else
            return noAppointmentBooked_message();
    }

    // message methods
    private String appointmentBooked_message()
    {

        String clientName = this.appointment.getClient().getName();
        String vaccineBrand = this.appointment.getVaccineBrand();
        String location = Integer.toString((this.clinic.getServiceLocationId())); //adjust the location detail if neccary

        //LocalDateTime date = this.appointment.getTimePeriod().getDateTime();
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //String strDate = dateFormat.format(date);

        return "Hello " + clientName + ", \n" +
                "your appoinment for a " + vaccineBrand + " vaccine has been set for: \n" +
                "strDate" + " - at " + location + "\n";
    }

    private String noAppointmentBooked_message()
    {
        String clientName = this.appointment.getClient().getName();

        return "Hello " + clientName + ", \n" +
                "you do not have any appointment currently booked.";
    }
}