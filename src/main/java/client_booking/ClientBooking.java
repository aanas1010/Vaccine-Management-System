package client_booking;

import entities.BookableServiceLocation;

import entities.BookableServiceLocation;
import entities.TimePeriod;
import entities.VaccineBatch;
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
        return book.createAppoinment();
    }

    public boolean AppointmentCancellation(Client client, BookableServiceLocation clinic,
                                           TimePeriod timePeriod, String vaccineBrand, int appointmentId)
    {
        AppointmentCancellation cancel = new AppointmentCancellation(client, clinic, timePeriod, vaccineBrand, appointmentId);
        return cancel.deleteAppoinment();
    }

    public boolean AppointmentViewing(Client client, BookableServiceLocation clinic,
                                      TimePeriod timePeriod, String vaccineBrand, int appointmentId)
    {
        AppointmentViewing view = new AppointmentViewing(client, clinic, timePeriod, vaccineBrand, appointmentId);
        return view.AppointmentDetails();
    }


}
