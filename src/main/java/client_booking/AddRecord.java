package client_booking;

import entities.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AddRecord {

    ClinicDecorator clinic;

    // Constructor
    public AddRecord(ClinicDecorator clinic){
        this.clinic = clinic;
    }

    // Log a given appointment based on an appointment ID
    public boolean logAppointment(int id){
        if (clinic.getAppointmentRecord(id) == null ||
                !clinic.getAppointmentRecord(id).appointmentTimePassed()){
            return false;
        }
        else{
            clinic.logPastVaccinations(clinic.getAppointmentRecord(id));
            clinic.removeAppointmentById(id);
            return true;
        }
    }

    // Log a walk-in appointment given certain parameters
    public boolean logWalkIn(String vaccinationID, User client, LocalDateTime dateTime, String brand) {
        if (dateTime.isBefore(LocalDateTime.now())){
            clinic.logPastVaccinations(vaccinationID, client, dateTime, brand);
            return true;
        }
        else{
            return false;
        }
    }

    // Log all appointment on a certain date and time
    public boolean logByDateTime(LocalDateTime dateTime){
        if (dateTime.isAfter(LocalDateTime.now()) ||
                clinic.getTimePeriod(dateTime) == null){
            return false;
        }
        else{
            TimePeriod timePeriod = clinic.getTimePeriod(dateTime);
            ArrayList<Appointment> appointments = clinic.getAppointmentByTimePeriod(timePeriod);
            for (Appointment appointment: appointments){
                clinic.logPastVaccinations(appointment);
                clinic.removeAppointmentById(appointment.getAppointmentId());
            }
            return true;
        }
    }

    // Log all appointments on a given date
    public boolean logByDate(LocalDate date){
        if (clinic.getTimePeriods(date) == null ||
                clinic.getTimePeriods(date).equals(new ArrayList<TimePeriod>())){
            return false;
        }
        else{
            for (TimePeriod timePeriod : clinic.getTimePeriods(date)){
                logByDateTime(timePeriod.getDateTime());
            }
            return true;
        }
    }
}
