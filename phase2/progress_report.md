# Progress Report
 
Our group aimed to finish most of the code for Phase 1, which we were able to do. Because of this, most of our work for Phase 2 was on integrating the database, while also making adjustments based on the feedback given following Phase 1.
 
## Contributions since Phase 1:
**Markus**: Created use cases for storing and retrieving data from controllers using mock classes. Added data retrieval functionality from the database using drivers specific to each of 5 tables from the database and converted ResultSet objects into JsonObjects.
* Link to Pull Request(s):
    * https://github.com/CSC207-UofT/course-project-git-good-was-taken/pull/95
    * https://github.com/CSC207-UofT/course-project-git-good-was-taken/pull/106
* Why this Pull Request is a significant contribution: Retrieving data is an important part of this project and was lacking in phase 1. This process involves adhering tightly to clean architecture via dependency inversion so that our system is as open to addition and closed to modification as possible so that we could easily change the location of retrieval or modification in the future.

**Diana**: Wrote JavaDoc for the Use cases, Use Case Booking, and entities. Set the base for the report on which the team members could work on. Organized notes on which we relied moving forward. Wrote and edited the accessibility report. 
* Link to Pull Request(s):
    * https://github.com/CSC207-UofT/course-project-git-good-was-taken/pull/94
* Why this Pull Request is a significant contribution: In this pull request I have integrated the Java docs listed above - which are important in order to abide by the principles of coding in Java. As well the foundation for the phase2 report along with a finished file for the accessibility report; which is a significant contribution as reports are important in any team project. 

**Matt**: Implemented the builder design pattern for the VaccineBatch, Appointment and Clinic. Also updated the use case Modifier and the implementation of UseCaseManager related to Modifier. Below are the pull requests for the builder design pattern. The first pull request is the original builder implementation, and the last three are the final corrections. 
* Link to pull requests
    * https://github.com/CSC207-UofT/course-project-git-good-was-taken/pull/88
    * https://github.com/CSC207-UofT/course-project-git-good-was-taken/pull/102
    * https://github.com/CSC207-UofT/course-project-git-good-was-taken/pull/104
    * https://github.com/CSC207-UofT/course-project-git-good-was-taken/pull/105
* Why this Pull Request is a significant contribution: In these pull requests, I implemented the builder design pattern to provide more flexibility when constructing these objects. It was important because it was one of the larger refactors that happened in phase 2 and the code was also used in other parts of the project. DatabaseRetrieval and DatabaseModification also implement builders based on the builders I created for the three entities.  

**Aabid**: Wrote Javadoc for the Use Cases, Use Case Managers, Drivers, and Constants; Fixed issues raised by code inspection; Helped with written report.
* https://github.com/CSC207-UofT/course-project-git-good-was-taken/pull/72#issue-1046217317
* In this pull request, I integrated the use cases (which I had previously implemented) into the controllers, and then from the controllers I implemented the Command Line, which is a driver. This was done to abide by clean architecture, and it handles the cases where the Clinic Manager inputs information about the appointment (i.e., to book/cancel/view the appointment) through the driver, as well as what to do with that information in the controller. Booking appointments is what our program is all about, and so it was crucial that this was implemented while following clean architecture.

**Shayaan**: Created files and methods for adding, updating, deleting and loading data to and from the database, integrating with the controllers and implementing instances in use cases. Fixed many of the bugs and issues that were raised by the program.
* Link to Pull Request(s):
    * https://github.com/CSC207-UofT/course-project-git-good-was-taken/pull/103
    * https://github.com/CSC207-UofT/course-project-git-good-was-taken/pull/109
* Why this Pull Request is a significant contribution: The entire process of data persistence in our project was using the database, and since we did not have it in production for Phase 1, I spent the entirety of Phase 2 integrating the database functionality with the rest of the program and fixing the bugs that arose while integrating. To abide by Clean Architecture, I used the controllers as an adapter to enact the loading and modifying of data within the program, and it ended up working successfully.
