package entities;

import java.time.LocalDateTime;

public class WalkInClinic extends ClinicDecorator{


    //constructor
    public WalkInClinic(ServiceLocation decoratedClinic)
    {
        super(decoratedClinic);
    }

    // Log a past vaccination (NON-APPOINTMENT)
    public void logPastVaccinations(String vaccinationId, Client client, LocalDateTime dateTime, String vaccineBrand) {
        decoratedClinic.getVaccineLog().addToLog(vaccinationId, client, dateTime, vaccineBrand);
    }


    // //option if we choose not to use casting for clinics:

    // //empty method declaration to satisfy inheritance
    // @Override
    // public boolean addAppointment(Appointment ap) {return false;}

    // @Override
    // public Appointment getAppointmentRecord(int id) {return null;}

    // @Override
    // public boolean removeAppointment(Appointment ap) {return false;}

    // @Override
    // public boolean removeAppointmentById(int id) {return false;}

    // @Override
    // public void logPastVaccinations(Appointment appointmentRecord) {}
}
