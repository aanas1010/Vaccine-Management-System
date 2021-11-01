package client_booking;

import entities.BookableServiceLocation;

import entities.TimePeriod;
import entities.Client;

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
        AppointmentCancellation cancel = new AppointmentCancellation(appointmentID, clinic);
        return cancel.deleteAppointment();
    }

    public String AppointmentViewing(int appointmentID, BookableServiceLocation clinic)
    {
//        AppointmentViewing view = new AppointmentViewing(appointmentID, clinic);
//        return view.appointmentDetails();
        return "empty on AppointmentViewing-ClientBooking";
    }


}