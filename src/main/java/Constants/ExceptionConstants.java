package Constants;

/**
 * These are the constants for error messages primarily thrown by the use cases
 */

public class ExceptionConstants {
    public final static String INVALID_PARAMETER = "That value is invalid. Please try again";
    public final static String HCN_ALREADY_EXISTS = "A client already exists with the given Health Card Number";
    public final static String HCN_DOES_NOT_EXIST = "No client exists with the provided Health Card Number";
    public final static String CLINIC_ID_ALREADY_EXISTS = "Could not add the clinic because a clinic already exists with the provided ID";

    public final static String BATCH_ID_ALREADY_EXISTS = "Could not add the batch because a batch already exists with the provided ID";
    public final static String BATCH_EXPIRED = "Could not add the batch because it is expired";
    public final static String NO_SHIFT_AVAILABLE = "Could not add the time period because no shift has been set for the given time";
    public final static String TIME_PERIOD_ALREADY_EXISTS = "Could not add the time period because a time period already exists for the given time";
    public final static String TIME_PERIOD_DOES_NOT_EXIST = "Could not remove the time period because it does not exist";
    public final static String INVALID_RANGE_OR_INTERVAL = "The time range or interval provided are invalid";

    public final static String APPOINTMENT_DOES_NOT_EXIST = "No appointment exists for the provided ID";
    public final static String APPOINTMENT_NOT_PASSED = "Could not log the appointment because it has not yet passed";
    public final static String TIME_NOT_PASSED = "The time provided has not yet passed";
    public final static String CLINIC_DOES_NOT_HAVE_TIMESLOT = "The provided timeslot could not be found for this clinic";

    public final static String APPOINTMENT_ID_ALREADY_EXISTS = "Could not book the appointment because an appointment already exists with the provided ID";
    public final static String TIME_SLOT_UNAVAILABLE = "The timeslot provided has no availabilities";
    public final static String CLIENT_ALREADY_HAS_APPOINTMENT = "Could not book the appointment because this client already has an appointment";
    public final static String BRAND_DOES_NOT_EXIST = "This clinic does not have any batches for the provided vaccine brand";

}