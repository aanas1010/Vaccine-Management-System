CRC Model

Entities

Clinic

Responsibilities Collaborators![](Aspose.Words.2d59f302-66e1-4db4-9e7d-5ef5fe723f7b.001.png)

- **supply** ● Bookable Clinic
- **clinicId** ● Time Period
- getClinicId ● Vaccination Log
  - Vaccine Supply


Bookable Clinic (Subclass of Clinic)![](Aspose.Words.2d59f302-66e1-4db4-9e7d-5ef5fe723f7b.001.png)

Responsibilities Collaborators

- **appointmentList** ● Clinics
- addAppointment ● Appointment
- logPastAppointments

Time Period![](Aspose.Words.2d59f302-66e1-4db4-9e7d-5ef5fe723f7b.001.png)

Responsibilities Collaborators

- **datetime (tracks date and time)**  ● Clinics 
- **availabilities** ● Appointment
- **bookedSlots**
- addAvailabilities
- removeAvailability
- getAvailabilities

●

Client

Responsibilities Collaborators![](Aspose.Words.2d59f302-66e1-4db4-9e7d-5ef5fe723f7b.001.png)

- **name** ● Appointment
- **healthcareNumber**
- **hasAppointment**
- get name
- get healthcareNumber
- get hasAppointment
- approveAppointment


Vaccine Supply![](Aspose.Words.2d59f302-66e1-4db4-9e7d-5ef5fe723f7b.001.png)

Responsibilities Collaborators

- **batchList** ● Clinics
- isEmpty ● Vaccine Batch
- getBatchList
- getAvailbleVaccines
- toString

Vaccine Batch![](Aspose.Words.2d59f302-66e1-4db4-9e7d-5ef5fe723f7b.001.png)

Responsibilities Collaborators

- **expiry** ● Vaccine Supply
- **Brand**
- **Quantity**
- **id**
- **reserve**
- isExpired
- getExpiry
- getAvailable
- getId
- getBrand

Appointment![](Aspose.Words.2d59f302-66e1-4db4-9e7d-5ef5fe723f7b.001.png)

Responsibilities Collaborators

- **client** ● Client
- **datetime** ● Bookable Clinic
- **vaccineBrand** ● Time Period

Vaccination Log![](Aspose.Words.2d59f302-66e1-4db4-9e7d-5ef5fe723f7b.001.png)

Responsibilities Collaborators

- **log** ● Clinics
- addToLog

Use Cases

AppointmentBooking![](Aspose.Words.2d59f302-66e1-4db4-9e7d-5ef5fe723f7b.001.png)

Responsibilities Collaborators

- Allow the user to book a vaccine  ● Clinic appointment

AppointmentCancellation![](Aspose.Words.2d59f302-66e1-4db4-9e7d-5ef5fe723f7b.001.png)

Responsibilities Collaborators

- Allow the user to cancel their vaccine  ● Clinic appointment

AppointmentViewing![](Aspose.Words.2d59f302-66e1-4db4-9e7d-5ef5fe723f7b.001.png)

Responsibilities Collaborators

- Allow the user to view their vaccine  ● Clinic appointment

ClientBooking![](Aspose.Words.2d59f302-66e1-4db4-9e7d-5ef5fe723f7b.001.png)

Responsibilities Collaborators

- Manages the client requests. ● AppointmentViewing
  - AppointmentBooking
  - AppointmentCancellation

ClinicManagement![](Aspose.Words.2d59f302-66e1-4db4-9e7d-5ef5fe723f7b.001.png)

Responsibilities Collaborators

- **clinics** ● ClinicDatabase
- getClinicIds ● AddBatch
- getClinicById ● SetTimePeriodAvailabilities
- addBatch
- getClinics

SetTimePeriodAvailabilities![](Aspose.Words.2d59f302-66e1-4db4-9e7d-5ef5fe723f7b.001.png)

Responsibilities Collaborators

- Set the available vaccination appointments  ● Clinic based on the number of employees 

available and vaccine supply.

- Validates the vaccine supply to check if 

enough non-expired vaccines are 

available.

BatchAdding![](Aspose.Words.2d59f302-66e1-4db4-9e7d-5ef5fe723f7b.001.png)

Responsibilities Collaborators

- **clinic** ● Clinic
- **batch**
- addBatch

Controllers

VaccineManagamentSystem![](Aspose.Words.2d59f302-66e1-4db4-9e7d-5ef5fe723f7b.001.png)

Responsibilities Collaborators

- **ClinincManagment** ● Client
- getClinic ● Clinics
- getClinicIds
- getClinicManager
- addBatch
- getClientBooker

Frameworks and Drivers

Command Line![](Aspose.Words.2d59f302-66e1-4db4-9e7d-5ef5fe723f7b.001.png)

Responsibilities Collaborators

- **commandLinePrompt** ● Vaccine Booking System
- getValue

Take userInput and passes it to VaccineBookingSystem where it is parsed and get result to be displayed

https://miro.com/app/board/o9J\_lr-wuew=/
