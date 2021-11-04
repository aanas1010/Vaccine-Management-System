package client_booking;

import entities.Appointment;
import entities.BookableServiceLocation;

public class AppointmentViewing {

    private final int appointmentID;
    private final BookableServiceLocation clinic;

    public AppointmentViewing(int appointmentID, BookableServiceLocation clinic)
    {
        this.clinic = clinic;
        this.appointmentID = appointmentID;
    }

    /*
    produces a message:
    "Hello [client name],
     your appoinment for a [brand] vaccine has been set for:
     [date] - at  [location]"

     or

     "Hello [client name],
      your appoinment for a [brand] vaccine at:
      [date] - [location]
      has passed.
      Thank you for using our services"

     or

     "Hello [client name],
     you do not have any appointment currently booked."
     */

    public String appointmentDetails()
    {
        if(this.clinic.getAppointmentRecord(appointmentID) != null)
            return appointmentBooked_message(this.clinic.getAppointmentRecord(appointmentID));

        else
            if(this.clinic.getVaccineLog().getVaccinationRecord("A" + String.valueOf(appointmentID)) != null) //booked passed_appointment
                return appointmentPassed_message("A" + String.valueOf(appointmentID));
            else if(this.clinic.getVaccineLog().getVaccinationRecord("V" + String.valueOf(appointmentID)) != null) //walk-in passed_appointment
                return appointmentPassed_message("V" + String.valueOf(appointmentID));
            else
                return noAppointmentBooked_message();
    }

    // message methods
    private String appointmentBooked_message(Appointment appointment)
    {

        String clientName = appointment.getClient().getName();
        String vaccineBrand = appointment.getVaccineBrand();
        String location = Integer.toString((this.clinic.getServiceLocationId())); //adjust the location detail if neccary

        //LocalDateTime date = this.appointment.getTimePeriod().getDateTime();
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //String strDate = dateFormat.format(date);

        return "Hello " + clientName + ", \n" +
                "your appoinment for a " + vaccineBrand + " vaccine has been set for: \n" +
                "strDate" + " - at " + location + "\n";
    }

    private String appointmentPassed_message(String appointmentID_str)
    {
        String clientName =  this.clinic.getVaccineLog().getClientByVaccinationId(appointmentID_str).getName();
        String vaccineBrand = this.clinic.getVaccineLog().getVaccineBrandByVaccinationId(appointmentID_str);
        String location = String.valueOf(this.clinic.getServiceLocationId());

        //LocalDateTime date = this.appointment.getTimePeriod().getDateTime();
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //String strDate = dateFormat.format(date);

        return "Hello " + clientName + ", \n " +
                "your appoinment for a " + vaccineBrand + " vaccine at: \n" +
                "strDate" + " - " + location + "\n" +
                "has passed. \n" +
                "Thank you for using our services";
    }

    private String noAppointmentBooked_message()
    {
        return "Hello, \n" +
               "you do not have any appointment currently booked.";
    }
}