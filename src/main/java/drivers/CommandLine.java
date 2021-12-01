package drivers;

import constants.ManagementSystemException;
import managers.ManagementSystem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * This is the class for the Command Line UI
 * User can input commands and parameters sequentially
 */

public class CommandLine {
    private final ManagementSystem managementSystem;
    private final Scanner in;
    private int clinicId;

    /**
     * @param system a management system that calls the use cases
     */
    public CommandLine(ManagementSystem system) {
        this.managementSystem = system;
        this.in = new Scanner(System.in);
        this.clinicId = -1;
    }

    /**
     * Runs the program. The way the program runs is based on the inputs by the user i.e., the clinic manager
     */
    public void run() {
        // Load the initial data
        this.managementSystem.loadInitialData();

        // Get the clinic ID from the user
        this.clinicId = getClinicId();

        boolean isBookableClinic = managementSystem.getBookableClinicIds().contains(clinicId);

        // Load the data for this clinic
        this.managementSystem.loadClinicData(clinicId);

        // Get the list of acceptable commands
        List<Enum<?>> acceptableCommands = new ArrayList<>(Arrays.asList(DataValidation.CoreCommands.values()));

        String commandListString = createAcceptableCommandsString(isBookableClinic, acceptableCommands);

        //Ask what the user would like to do
        runCommands(commandListString, acceptableCommands);
    }

    // Determine which command to run
    private void runCommands(String commandListString,
                             List<Enum<?>> acceptableCommands) {
        boolean isRunning = true;

        /* Current implementation uses large if-else block and not a switch statement from phase 0.
        * This is due to the limitations of switch statements of only being able to take a primitive data
        * type as the switch variable. This is incompatible with our program whose specifications require
        * different Enum types for different clinic types (e.g. enums of type BookableCommands are only
        * applicable to BookableClinic, not to the core functionality of clinic). This means that no interface,
        * abstract class, or any other "union" of two enums is possible in a switch statement, thus requiring
        * a more complex if-else block.
        * */

        while(isRunning) {
            try {

                //Get the command that the user inputs
                Enum<?> command = DataValidation.getCommand(in, commandListString, acceptableCommands);

                //Core Commands
                if (command.equals(DataValidation.CoreCommands.ADD_BATCH)) {
                    addBatch();
                } else if (command.equals(DataValidation.CoreCommands.SET_EMPLOYEES)) {
                    setEmployees();
                } else if (command.equals(DataValidation.CoreCommands.ADD_TIME_PERIOD)) {
                    addTimePeriod();
                } else if (command.equals(DataValidation.CoreCommands.REMOVE_TIME_PERIOD)) {
                    removeTimePeriod();
                } else if (command.equals(DataValidation.CoreCommands.ADD_TIME_PERIODS)) {
                    addTimePeriods();
                } else if (command.equals(DataValidation.CoreCommands.QUIT)) {
                    isRunning = false;
                    System.out.println("Quitting Program");


                //Bookable Commands
                } else if (command.equals(DataValidation.BookableCommands.BOOK_APPOINTMENT)) {
                    bookAppointment();
                } else if (command.equals(DataValidation.BookableCommands.CANCEL_APPOINTMENT)) {
                    cancelAppointment();
                } else if (command.equals(DataValidation.BookableCommands.VIEW_APPOINTMENT)) {
                    viewAppointment();
                } else if(command.equals(DataValidation.BookableCommands.LOG_APPOINTMENT)) {
                    logAppointment();
                } else if(command.equals(DataValidation.BookableCommands.LOG_WALK_IN)) {
                    logWalkIn();
                } else if(command.equals(DataValidation.BookableCommands.LOG_BY_DATETIME)) {
                    logByDateTime();
                } else if(command.equals(DataValidation.BookableCommands.LOG_BY_DATE)) {
                    logByDate();
                }
            }
            //If at any point there is an exception thrown, print the message to the command line
            catch(ManagementSystemException e) {
                System.out.println(e.getMessage());
            }
        }
        in.close();
    }

    // Return a string of the acceptable commands depending on whether the clinic is bookable
    private String createAcceptableCommandsString(boolean isBookableClinic, List<Enum<?>> acceptableCommands) {
        // Depending on whether isBookableClinic, accept different types of commands
        if(isBookableClinic) {
            acceptableCommands.addAll(Arrays.asList(DataValidation.BookableCommands.values()));
        }

        // Construct the list of commands presented to the user
        StringBuilder s = new StringBuilder("Commands: ");
        for(Enum<?> command : acceptableCommands) {
            s.append(command.toString()).append(", ");
        }

        return s.substring(0, s.toString().length() - 2);
    }

    // Get the clinic ID
    private int getClinicId() {
        while(true) {
            try {
                int userInput = (Integer) DataValidation.getValue(in, "Please provide your Clinic ID",
                        DataValidation.ParameterTypes.NON_NEGATIVE_INT);

                if (managementSystem.getClinicIds().contains(userInput)) {
                    //If ID is valid, set clinicId and exit loop
                    System.out.println("You are now managing Clinic #" + userInput);
                    return userInput;
                } else {
                    //If ID is invalid, error message
                    System.out.println("A clinic with that ID does not exist");
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Start addBatch workflow
    private void addBatch() throws ManagementSystemException {
        // Ask for information for a new batch
        String batchBrand = (String) DataValidation.getValue(in,
                "Batch Brand:",
                DataValidation.ParameterTypes.FREE_TEXT);
        int batchId = (Integer) DataValidation.getValue(in,
                "Batch ID:",
                DataValidation.ParameterTypes.NON_NEGATIVE_INT);
        int batchQuantity = (Integer) DataValidation.getValue(in,
                "Batch Quantity:",
                DataValidation.ParameterTypes.NON_NEGATIVE_INT);
        LocalDate batchExpiry = (LocalDate) DataValidation.getValue(in,
                "Batch Expiry Date (DD/MM/YYYY):",
                DataValidation.ParameterTypes.NON_PAST_DATE);

        // Add the batch via the managementSystem
        String output = managementSystem.addBatch(clinicId, batchBrand, batchQuantity, batchExpiry, batchId);

        // Output different message depending on result
        if(output != null) {
            System.out.println("You just added the following batch: ");
            System.out.println(output);
            System.out.println(managementSystem.getSupplyByClinic(clinicId));
        }else {
            System.out.println("Could not add the batch");
        }
    }

    // Start setEmployees workflow
    private void setEmployees() {
        // Ask for information for employee setting
        LocalDate date = (LocalDate) DataValidation.getValue(in,
                "Date (DD/MM/YYYY):",
                DataValidation.ParameterTypes.NON_PAST_DATE);
        int employees = (Integer) DataValidation.getValue(in,
                "Number of Employees for this date:",
                DataValidation.ParameterTypes.NON_NEGATIVE_INT);

        // Set employees through the managementSystem
        String output = managementSystem.setEmployees(clinicId, date, employees);

        System.out.println(Objects.requireNonNullElse(output,
                "Could not change the number of employees for that date"));
    }

    // Start the addTimePeriod workflow
    private void addTimePeriod()
            throws ManagementSystemException {
        // Ask for information for adding a new time period
        LocalDateTime dateTime = (LocalDateTime) DataValidation.getValue(in,
                "Date and Time (24 hour time, DD/MM/YYYY HH:MM):",
                DataValidation.ParameterTypes.NON_PAST_DATETIME);
        // Add the time period through the managementSystem
        String output = managementSystem.addTimePeriod(clinicId, dateTime);

        if(output != null) {
            System.out.println("Successfully added the following time period: ");
            System.out.println(output);
        }else {
            System.out.println("Could not add the time period");
        }
    }

    // Start the addTimePeriod workflow
    private void addTimePeriods()
            throws ManagementSystemException {
        // Ask for information for adding a new time period
        LocalDateTime start = (LocalDateTime) DataValidation.getValue(in,
                "Start Date and Time (24 hour time, DD/MM/YYYY HH:MM):",
                DataValidation.ParameterTypes.NON_PAST_DATETIME);
        LocalDateTime end = (LocalDateTime) DataValidation.getValue(in,
                "End Date and Time (24 hour time, DD/MM/YYYY HH:MM):",
                DataValidation.ParameterTypes.NON_PAST_DATETIME);
        int interval = (Integer) DataValidation.getValue(in,
                "Length of each time period (minutes):",
                DataValidation.ParameterTypes.POSITIVE_INT);

        // Add the time period through the managementSystem
        int output = managementSystem.addMultipleTimePeriods(clinicId, start, end, interval);

        if(output > 0) {
            System.out.println("Successfully added the time periods");
        }else {
            System.out.println("Could not add the time periods");
        }
    }

    // Start the logAppointment workflow
    private void logAppointment()
            throws ManagementSystemException {
        // Ask for information for logging an appointment
        int appointmentId = (Integer) DataValidation.getValue(in,
                "Appointment ID:",
                DataValidation.ParameterTypes.POSITIVE_INT);

        // Try to log the appointment
        String output = managementSystem.logAppointment(clinicId, appointmentId);

        if(output != null) {
            System.out.println("The following appointment has been logged:");
            System.out.println(output);
        }else {
            System.out.println("Could not log the appointment");
        }
    }

    private void logWalkIn()
            throws ManagementSystemException {
        // Ask for information for logging a walk-in
        String vaccinationID = (String) DataValidation.getValue(
                in, "Vaccination ID:", DataValidation.ParameterTypes.NON_NEGATIVE_INT);
        String clientHCN = (String) DataValidation.getValue(
                in, "Client Health Card Number:", DataValidation.ParameterTypes.FREE_TEXT);
        LocalDateTime dateTime = (LocalDateTime) DataValidation.getValue(in,
                "Date and Time (24 hour time, DD/MM/YYYY HH:MM):",
                DataValidation.ParameterTypes.NON_PAST_DATETIME);
        String vaccineBrand = (String) DataValidation.getValue(in,
                "Which Vaccine would you like:",
                DataValidation.ParameterTypes.FREE_TEXT);

        // Try to log the walk-in
        String output = managementSystem.logWalkIn(clinicId, vaccinationID, clientHCN, dateTime, vaccineBrand);

        if(output != null) {
            System.out.println("The following walk-in has been logged:");
            System.out.println(output);
        }else {
            System.out.println("Could not log the walk-in");
        }
    }

    private void logByDateTime()
            throws ManagementSystemException {
        // Ask for information for logging all appointments for a given dateTime
        LocalDateTime dateTime = (LocalDateTime) DataValidation.getValue(in,
                "Date and Time to be logged (24 hour time, DD/MM/YYYY HH:MM):",
                DataValidation.ParameterTypes.NON_PAST_DATETIME);

        // Try to log the appointments
        StringBuilder output = managementSystem.logByDateTime(clinicId, dateTime);

        if (output != null) {
            System.out.println("The system has logged all the following appointments for " + dateTime);
            System.out.println(output);
        } else
            System.out.println("Could not log appointments");
    }


    private void logByDate()
            throws ManagementSystemException {
        // Ask for information for logging all appointments for a given dateTime
        LocalDate date = (LocalDate) DataValidation.getValue(in,
                "Date to be logged (24 hour time, DD/MM/YYYY):",
                DataValidation.ParameterTypes.NON_PAST_DATE);

        // Try to log the appointments
        StringBuilder output = managementSystem.logByDate(clinicId, date);

        if(output != null) {
            System.out.println("The system has logged all appointments for this date");
            System.out.println(output);
        }else {
            System.out.println("Could not log appointments");
        }
    }


    // Start the removeTimePeriod workflow
    private void removeTimePeriod()
            throws ManagementSystemException {
        // Ask for information for which time period to remove
        LocalDateTime dateTime = (LocalDateTime) DataValidation.getValue(in,
                "Date and Time (24 hour time, DD/MM/YYYY HH:MM):",
                DataValidation.ParameterTypes.NON_PAST_DATETIME);
        // Try to remove the time period through the managementSystem
        String output = managementSystem.removeTimePeriod(clinicId, dateTime);

        if(output != null) {
            System.out.println("Successfully removed the following time period: ");
            System.out.println(output);
        }else {
            System.out.println("No time period exists for the specified time");
        }
    }

    // Start the bookAppointment workflow
    private void bookAppointment()
            throws ManagementSystemException {
        // Ask for information for booking an appointment
        String healthCareNumber = (String) DataValidation.getValue(in,
                "Health Care Number:",
                DataValidation.ParameterTypes.FREE_TEXT);

        LocalDateTime appointmentTime = (LocalDateTime) DataValidation.getValue(in,
                "Appointment Date and Time (24 hour time, DD/MM/YYYY HH:MM):",
                DataValidation.ParameterTypes.NON_PAST_DATETIME);

        String vaccineBrand = (String) DataValidation.getValue(in,
                "Which Vaccine would you like:",
                DataValidation.ParameterTypes.FREE_TEXT);
        int appointmentId = (Integer) DataValidation.getValue(in,
                "Enter an Appointment ID:",
                DataValidation.ParameterTypes.POSITIVE_INT);

        // Try to book the appointment
        String output = managementSystem.bookAppointment(clinicId, healthCareNumber,
                appointmentTime, vaccineBrand, appointmentId);

        if(output != null) {
            System.out.println("The following appointment has been booked");
            System.out.println(output);
        }else {
            System.out.println("Can not book appointment");
        }
    }

    // Start the cancelAppointment workflow
    private void cancelAppointment()
            throws ManagementSystemException {
        // Ask for information for canceling an appointment
        int appointmentId = (Integer) DataValidation.getValue(in,
                "Enter your Appointment ID:",
                DataValidation.ParameterTypes.POSITIVE_INT);

        // Try to cancel the appointment
        String output = managementSystem.cancelAppointment(clinicId, appointmentId);

        if(output != null) {
            System.out.println("The following appointment has been cancelled: ");
            System.out.println(output);
        }else {
            System.out.println("Can not cancel appointment");
        }
    }

    // Start the viewAppointment workflow
    private void viewAppointment()
            throws ManagementSystemException {
        // Ask for information for viewing an appointment
        int appointmentId = (Integer) DataValidation.getValue(in,
                "Enter your Appointment ID:",
                DataValidation.ParameterTypes.POSITIVE_INT);

        // Try to view the appointment
        String output = managementSystem.viewAppointment(clinicId, appointmentId);

        System.out.println(Objects.requireNonNullElse(output, "You don't have an appointment booked"));
    }



}
