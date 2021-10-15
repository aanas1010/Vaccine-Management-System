# VACCINE MANAGEMENT SYSTEM:

Key:
* **Bold**: class
* <ins>Underline</ins>: functions

**Vaccine clinics** keep track of their address, employees per day, and **time slots**, as well as their vaccine supply, which includes different **batches of vaccines** <ins>that can be added</ins>. Batches have a unique number identifier, number of doses, and vaccine distributor, which includes a brand name and a vaccine name. Vaccine clinics can either be appointment-based or drop-in. The number of **time slots** available for a given clinic on a given day <ins>is determined by the number of employees for that day</ins>. <ins>**Clinic Managers** specify how many employees work on a given day</ins>. If there are slots available, this clinic may accept drop-ins as well.

<ins>**A user** can log in</ins> to a **web interface** with a personal health number and <ins>select an **appointment-based clinic**</ins>. Once logged in, they <ins>select from the available **time slots**</ins> and <ins>specify which brand of vaccine they want to be administered</ins>, according to the clinic’s **batch** availability. Once the **user** <ins>confirms the vaccine brand, location, date, and time for the time slot</ins>, an <ins>**appointment** record is created</ins> with this information, referencing the **time slot**. As well, a **batch** of the specified vaccine brand <ins>keeps track of how many individual doses are marked as “Available” or “Reserved.”</ins> Later, when the same **user** logs in, they may <ins>delete their **appointment**.</ins>

Each clinic has a single **Vaccination Log** object. When an **appointment**’s time comes, <ins>it is deleted and its details are logged in the **Vaccination Log**.</ins> On the other hand, if done through drop-in, <ins>a person’s name is recorded and put in the log</ins>. In either case, <ins>a **batch** of the corresponding vaccine brand subtracts one from its “Reserved” property.</ins>
