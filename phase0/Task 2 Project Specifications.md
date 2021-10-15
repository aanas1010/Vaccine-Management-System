

Task 2 — Writing the specification

*Once your group has decided on a domain, your first task is to develop a specification for the*

*system you will be designing. We have provided a minimal list of requirements below to help*

*ensure your specification is sufficiently rich to get started. We are looking for something of similar*

*scale to the JShell case study specification from the Week 2 slides. There should be enough*

*complexity that all of your group members have substantial work to do. **Everyone should design***

***and write code throughout the project!** The following requirements are in terms of the layers of*

*Clean Architecture, which are covered in Week 3.*

*Requirements: A reasonable CRC model that satisfies your specification should consist of*

*●*

*●*

*●*

*●*

*at least three entity classes,*

*at least two use case classes,*

*at least one controller,*

*and at least a basic command line interface.*

***As groups start working on their specifications, we may provide further guidance to make***

***sure everyone is on track. You are encouraged to share your draft specification with other***

***groups and on piazza to get feedback about the scale of your project and just so we can see***

***what everyone is working on!***

Necessary Classes:

\-

\-

\-

\-

User

Store - Location/Address

Vaccine brand i.e., Moderna, Pfizer, Astrazeneca

Employees available

Colour key:

●

Blue: Enterprise Business Rules (Entities)

●

●

Green: Application Business Rules (Use Cases)

Orange: Interface Adapters

●

Other:

●

Red: Frameworks and Drivers

Bold: Potential classes

Underline: Functions

●

VACCINE MANAGEMENT SYSTEM:

**Vaccine clinics** keep track of their address, **employees**, **shifts**, and **time slot**, as well as their

vaccine supply, which includes different **batches of vaccines** that can be added. Batches have a

unique number identifier, number of doses, and vaccine distributor, which includes a brand name

and a vaccine name. Vaccine clinics can either be appointment-based or drop-in. A clinic’s **shifts**

determine how many **time slots** are available and when (for example, there could be 5 slots

available for 3:15pm and 1 available for 4:00pm). **Clinic Managers** can put in the **shifts** of their





**Employees** into the system, but only for the **clinic(s)** in which the Employees are associated. If

there are slots available, this clinic may accept drop-ins as well.

**A user** can log in to a **web interface** with a personal health number and select an

**appointment-based clinic**. Once logged in, they make a **booking request** for an available **time**

**slot** and specify which brand of vaccine they want to be administered, according to the clinic’s

**batch** availability. Once the **user** confirms the vaccine brand, location, date, and time for the time

slot, the **booking request** is deleted and an **appointment** record is created with this information,

referencing the **time slot**. As well, a **batch** of the specified vaccine brand keeps track of how many

individual doses are marked as “Available” or “Reserved.” Later, when the same **user** logs in, they

may delete their **appointment**.

Each clinic has a single Vaccination Log object. When an **appointment’s** time comes, it is deleted

and its details are logged in the **Vaccination Log**. On the other hand, if done through drop-in, a

person’s name is recorded and put in the log. In either case, a **batch** of the corresponding vaccine

brand subtracts one from its “Reserved” property.

