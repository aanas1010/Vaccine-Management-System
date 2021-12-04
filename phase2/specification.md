# Specification

## Vaccine Management System

Key:
* **Bold**: class
* _Italics_: function

Every **clinic** has an ID associated with it, and they each keep track of their location, the number of employees available for a given date, the **time slots**- which is determined by the number of employees for that day-  and its **vaccine supply**, which includes different **batches of vaccines** _that can be added_. 
The clinic’s location is already stored in the database, but it is the **clinic manager**’s job to _specify the number of employees_ and _the length of each timeslot_. 
The batches, which are branded and have a unique number identifier, also contain a date of expiration and the number of doses available compared to doses already reserved.

The **Client** tries to **_book an appointment_** through an external source, where they insert their name and health card number, followed by selecting a location, choosing a vaccine brand, and picking the date and time of the appointment. 
The clinic manager then takes this information from this external source and **inputs them into our program**. 
It is possible that the appointment does not get booked, for reasons such as all _the appointments already being booked for the chosen time slot_, _no doses available for the specified brand_, or _the client already having a booked appointment_. 
It is important to note that not all clinics accept booked appointments: Some clinics only accept walk-ins while others accept both walk-ins (assuming that _there are time slots available_) and booked appointments. 
A client would not see the clinics that only accept walk-ins when booking.

Once an appointment is booked, it can be **_viewed_** or **_cancelled_**. 
If it is cancelled, a time slot opens up and the number of doses in the vaccine batch increases by 1. 
Assuming the client does not cancel, when the time of the appointment passes, _it is deleted and its details are logged in the **Vaccination Log_**. 
On the other hand, if done through a walk-in, _the person’s name is recorded and put in the log_. 
In either case, _one dose is subtracted from the “Reserved” amount of the batch_ from which the client’s dose came from.
