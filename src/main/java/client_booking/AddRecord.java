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
    public String logAppointment(int id){
        if (clinic.getAppointmentRecord(id) == null ||
                !clinic.getAppointmentRecord(id).appointmentTimePassed()){
            return null;
        }
        else{
            clinic.logPastVaccinations(clinic.getAppointmentRecord(id));
            Appointment removedAppointment = clinic.getAppointmentRecord(id);
            clinic.removeAppointmentById(id);
            return removedAppointment.toString();
        }
    }

    // Log a walk-in appointment given certain parameters
    public String logWalkIn(String vaccinationID, User client, LocalDateTime dateTime, String brand) {
        if (dateTime.isBefore(LocalDateTime.now())){
            clinic.logPastVaccinations(vaccinationID, client, dateTime, brand);
            return clinic.getVaccineLog().getRecordString("V" + vaccinationID);
        }
        else{
            return null;
        }
    }

    // Log all appointment on a certain date and time
    public StringBuilder logByDateTime(LocalDateTime dateTime){
        if (dateTime.isAfter(LocalDateTime.now()) ||
                clinic.getTimePeriod(dateTime) == null){
            return null;
        }
        else{
            TimePeriod timePeriod = clinic.getTimePeriod(dateTime);
            ArrayList<Appointment> appointments = clinic.getAppointmentByTimePeriod(timePeriod);
            ArrayList<String> appointmentsString = new ArrayList<>();
            for (Appointment appointment: appointments){
                clinic.logPastVaccinations(appointment);
                appointmentsString.add(appointment.toString());
                clinic.removeAppointmentById(appointment.getAppointmentId());
            }
            StringBuilder output = new StringBuilder();
            for(String appointment : appointmentsString){
                output.append(appointment);
            }
            return output;
        }
    }

    // Log all appointments on a given date
    public StringBuilder logByDate(LocalDate date){
        if (clinic.getTimePeriods(date) == null ||
                clinic.getTimePeriods(date).equals(new ArrayList<TimePeriod>())){
            return null;
        }
        else{
            StringBuilder object = new StringBuilder();
            for (TimePeriod timePeriod : clinic.getTimePeriods(date)){
                object.append(logByDateTime(timePeriod.getDateTime()));
            }
            return object;
        }
    }
}
