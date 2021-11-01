package client_booking;

import entities.Appointment;
import entities.BookableServiceLocation;
import entities.Client;
import entities.TimePeriod;

public interface ClientBookingInterface {

    boolean AppointmentBooking(Client client, BookableServiceLocation clinic, TimePeriod timePeriod,
                               String vaccineBrand, int appointmentId);

    boolean AppointmentCancellation(int appointmentId, BookableServiceLocation clinic);

    String AppointmentViewing(int idAppointment, BookableServiceLocation clinic);
}