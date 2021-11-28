package clientbooking;

import constants.BookingConstants;
import constants.ManagementSystemException;
import entities.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the Use Case for logging records to the VaccinationLog
 * Every time the use case is needed, a new RecordAdding instance is created
 * with parameters being flexible based on how we want to log
 */
public class RecordAdding {

    final ClinicDecorator clinic;

    /**
     * creates Use Case for record adding.
     *
     * @param clinic an object of the abstract class for Clinic Decorator.
     */
    public RecordAdding(ClinicDecorator clinic){this.clinic = clinic;}

    /**
     * Log a given appointment based on an appointment ID.
     *
     * @param id id of the appointment being added.
     * @return string description of the logged appointment.
     * @throws ManagementSystemException if the doesn't exist in the location's record,
     * or the appointment time hasn't happened yet.
     */
    public String logAppointment(int id) throws ManagementSystemException {
        if(clinic.getAppointmentRecord(id) == null) {
            throw new ManagementSystemException(ManagementSystemException.APPOINTMENT_DOES_NOT_EXIST);
        }
        if(!clinic.getAppointmentRecord(id).appointmentTimePassed()) {
            throw new ManagementSystemException(ManagementSystemException.APPOINTMENT_NOT_PASSED);
        }

        clinic.logPastVaccinations(clinic.getAppointmentRecord(id));
        Appointment removedAppointment = clinic.getAppointmentRecord(id);
        clinic.removeAppointmentById(id);
        return removedAppointment.toString();
    }

    /**
     * Log a walk-in appointment given certain parameters.
     *
     * @param vaccinationID id of the walk in vaccination event.
     * @param client who walked in to get vaccinated.
     * @param dateTime when the walk-in vaccination happened.
     * @param brand used in the walk in event.
     * @return string of appointment description.
     * @throws ManagementSystemException if the date time hasn't happened yet.
     */
    public String logWalkIn(String vaccinationID, User client, LocalDateTime dateTime, String brand)
            throws ManagementSystemException {
        if (dateTime.isBefore(LocalDateTime.now())){
            clinic.logPastVaccinations(vaccinationID, client, dateTime, brand);
            return clinic.getVaccineLog().getRecordString(
                    BookingConstants.NON_APPOINTMENT_BASED_PREFIX + vaccinationID);
        }
        else{
            throw new ManagementSystemException(ManagementSystemException.TIME_NOT_PASSED);
        }
    }

    /**
     * Log all appointment on a certain date and time.
     *
     * @param dateTime for which the appointments are added.
     * @return StringBuilder of string appointments.
     * @throws ManagementSystemException if the time hasn't passed yet or does not exist in the clinic.
     */
    public StringBuilder logByDateTime(LocalDateTime dateTime) throws ManagementSystemException {
        if(dateTime.isAfter(LocalDateTime.now())) {
            throw new ManagementSystemException(ManagementSystemException.TIME_NOT_PASSED);
        }

        if(clinic.getTimePeriod(dateTime) == null) {
            throw new ManagementSystemException(ManagementSystemException.CLINIC_DOES_NOT_HAVE_TIMESLOT);
        }

        else{
            TimePeriod timePeriod = clinic.getTimePeriod(dateTime);
            List<Appointment> appointments = clinic.getAppointmentByTimePeriod(timePeriod);
            List<String> appointmentsString = new ArrayList<>();
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

    /**
     * Log all appointments on a given date.
     *
     * @param date for which the appointments are added.
     * @return StringBuilder of date time in a time period on a date in a location/
     * @throws ManagementSystemException if location does not have time slot that day
     */
    public StringBuilder logByDate(LocalDate date) throws ManagementSystemException {
        if (clinic.getTimePeriods(date) == null ||
                clinic.getTimePeriods(date).equals(new ArrayList<TimePeriod>())){
            throw new ManagementSystemException(ManagementSystemException.CLINIC_DOES_NOT_HAVE_TIMESLOT);
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
