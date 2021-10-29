package client_booking;

import entities.BookableServiceLocation;
import entities.Client;
import entities.TimePeriod;

public interface ClientBookingInterface {

    boolean AppointmentBooking(Client client, BookableServiceLocation clinic, TimePeriod timePeriod,
                               String vaccineBrand, int appointmentId);

    boolean AppointmentCancellation(Client client, BookableServiceLocation clinic,
                                    TimePeriod timePeriod, String vaccineBrand, int appointmentId);

    String AppointmentViewing(Client client, BookableServiceLocation clinic, TimePeriod timePeriod,
                              String vaccineBrand, int appointmentId);
}