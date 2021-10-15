


# Vaccine Management System - Progress Report

 ## Specification Summary:

Our project scope is to create a Vaccine Management System that streamlines the booking appointment processes for patients and helps automate the vaccine inventory management for individual clinics. The intended users of this system would be the client patients and clinic administrators. Client patients identified with their health card number can book, view and cancel their appointments, with booking parameters including clinic, timeslot and vaccine brand. Clinic administrators identified with the clinic ID can register their clinic, manage time slots and add vaccine batches.

 ## CRC Model Summary:

The CRC Model consists of base entities such as Clinic, Client, VaccineBatch, Appointments - all of which are core to the system and the resulting functionalities. Next we have service requests such as AddBatch, BookAppointment, SetTimePeriods which create functionality for the system using the base entities mentioned beforehand. We then have an overarching VaccinationBookingSystem controller that manages all aspects of the system and bridges between the core functionality and command line interface.

 ## Scenario Walkthrough Summary:

Our scenario walkthrough involves a clinic administrator entering the ID of the clinic they are managing and adding a new batch of vaccines to the clinic’s supply. They enter the vaccine ID, brand, amount and expiry date and once the batch is added to the supply, the necessary calculations can be made to validate whether there are enough vaccines to be administered for appointments and walk-ins.

 ## Skeleton Code Summary:

The skeleton code that we constructed adheres to and allows us to carry out our scenario walkthrough. We have base entity classes like Clinic, VaccineBatch and VaccineSupply, which interact with the BatchAdding service to add a batch of new vaccines to the existing supply. The BatchAdding service is part of ClinicManagement, which itself is controlled by the VaccineManagementSystem class. The VaccineManagement class provides a bridge between our Business Use Cases and the CommandLine interface that the manager will be inputting the information into.

 ## Group Contributions:

  * Task 1: Contributed to deciding on Project Domain (Shayaan, Markus, Aabid, Matt, Diana)

  * Task 2: Contributed to writing out and refining the Specification (Shayaan, Markus, Aabid, Matt, Diana)

  * Task 3: Contributed to writing and refining CRC Cards and Clean Architecture diagrams (Shayaan, Markus, Aabid, Matt, Diana)

  * Task 4: Contributed to writing and refining Scenario Walkthrough (Markus)

  * Task 5: Contributed to writing, testing, debugging, refining and commenting on Skeleton Code (Shayaan, Markus, Aabid, Matt, Diana)

 * Task 6: Contribute to writing and refining Progress Report (Shayaan)

Next plans for the whole group are to refine our design and skeleton code to best prepare us for working on the next phases of the project. We could also consider adding more features to the scope of our project if it is feasible and would enhance the capabilities of the application.

 ## Project Phase 0 Design Strengths:

Since we decided on some sort of management system for our project domain, we were able to construct class responsibilities and map interactions more easily than if we decided on something else. We also used a Clean Architecture diagram to assess where exactly our interactions and layers would be, which ended up helping a lot with constructing our CRC cards.

## Project Phase 0 Weaknesses:

Our Project Phase 0 weaknesses were that there were some major issues with communication with one of our team members, who we couldn’t get in contact with. One major question that we were struggling with during the design of the project was how exactly the database would slot into our CRC model and clean architecture diagram.

