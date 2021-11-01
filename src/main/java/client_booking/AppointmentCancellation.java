package client_booking;

import entities.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentCancellation {

    int appointmentId;
    BookableServiceLocation clinic;

    public AppointmentCancellation(int appointmentId, BookableServiceLocation clinic){
        this.appointmentId = appointmentId;
        this.clinic = clinic;
    }

    public boolean deleteAppointment(){
        Appointment appointment = clinic.getAppointmentRecord(this.appointmentId);
        if (appointment.getClient().getHasAppointment()) {
            appointment.getClient().disapproveAppointment();
            clinic.removeAppointmentById(this.appointmentId);
            appointment.getTimePeriod().addAvailableSlot();
            return true;
        }
        return false;
    }


}
