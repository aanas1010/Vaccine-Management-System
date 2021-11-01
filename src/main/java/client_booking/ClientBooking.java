package client_booking;

import entities.BookableServiceLocation;

import entities.TimePeriod;
import entities.Client;
import entities.Appointment;

/**
 * This is the Use Case for Client Booking.
 * Every time the use case is needed, a new client_booking.ClientBooking instance is created
 * with the parameters of the appointment
 */

public class ClientBooking implements ClientBookingInterface{

    public ClientBooking()
    {}

    public boolean AppointmentBooking(Client client, BookableServiceLocation clinic,
                                      TimePeriod timePeriod, String vaccineBrand, int appointmentId)
    {
        AppointmentBooking book = new AppointmentBooking(client, clinic, timePeriod, vaccineBrand, appointmentId);
        return book.createAppointment();
    }

    public boolean AppointmentCancellation(int appointmentId, BookableServiceLocation clinic)
    {
        AppointmentCancellation cancel = new AppointmentCancellation(appointmentId, clinic);
        return cancel.deleteAppointment();
    }

    public String AppointmentViewing(int appointmentId, BookableServiceLocation clinic) {
        AppointmentViewing view = new AppointmentViewing(appointmentId, clinic);
        return view.appointmentDetails();
    }
}