package drivers;

import controllers.ManagementSystem;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * This is the class for the Command Line UI
 * User can input commands and parameters sequentially
 */

public class CommandLine {
    protected static final String commandLinePrompt = "> ";
    private final ManagementSystem managementSystem;

    // Constructor accepts a management system
    public CommandLine(ManagementSystem system) {
        this.managementSystem = system;
    }

    public void run() {
        //Create scanner for command line
        Scanner in = new Scanner(System.in);

        //Get the clinic ID from the user
        int clinicId = getClinicId(in, managementSystem);

        boolean isBookableClinic = managementSystem.getBookableClinicIds().contains(clinicId);

        // Get the list of acceptable commands
        ArrayList<Enum<?>> acceptableCommands = new ArrayList<>(Arrays.asList(DataValidation.CoreCommands.values()));

        String commandListString = createAcceptableCommandsString(isBookableClinic, acceptableCommands);

        //Ask what the user would like to do
        runCommands(in, clinicId, commandListString, acceptableCommands);
    }

    // Determine which command to run
    private void runCommands(Scanner in, int clinicId, String commandListString, ArrayList<Enum<?>> acceptableCommands) {
        boolean isRunning = true;

        while(isRunning) {
            try {

                Enum<?> userInput = DataValidation.getCommand(in, commandListString, acceptableCommands);

                if (DataValidation.CoreCommands.ADD_BATCH.equals(userInput)) {
                    addBatch(in, managementSystem, clinicId);
                } else if (DataValidation.CoreCommands.SET_EMPLOYEES.equals(userInput)) {
                    setEmployees(in, managementSystem, clinicId);
                } else if (DataValidation.CoreCommands.ADD_TIME_PERIOD.equals(userInput)) {
                    addTimePeriod(in, managementSystem, clinicId);
                } else if (DataValidation.CoreCommands.REMOVE_TIME_PERIOD.equals(userInput)) {
                    removeTimePeriod(in, managementSystem, clinicId);
                } else if (DataValidation.CoreCommands.ADD_TIME_PERIODS.equals(userInput)) {
                    addTimePeriods(in, managementSystem, clinicId);
                } else if (DataValidation.CoreCommands.QUIT.equals(userInput)) {
                    isRunning = false;
                    System.out.println("Quitting Program");
                } else if (DataValidation.BookableCommands.BOOK_APPOINTMENT.equals(userInput)) {
                    bookAppointment(in, managementSystem, clinicId);
                } else if (DataValidation.BookableCommands.CANCEL_APPOINTMENT.equals(userInput)) {
                    cancelAppointment(in, managementSystem, clinicId);
                } else if (DataValidation.BookableCommands.VIEW_APPOINTMENT.equals(userInput)) {
                    viewAppointment(in, managementSystem, clinicId);
                } else if(DataValidation.BookableCommands.LOG_APPOINTMENT.equals(userInput)) {
                    logAppointment(in, managementSystem, clinicId);
                } else if(DataValidation.BookableCommands.LOG_WALK_IN.equals(userInput)) {
                    logWalkIn(in, managementSystem, clinicId);
                } else if(DataValidation.BookableCommands.LOG_BY_DATETIME.equals(userInput)) {
                    logByDateTime(in, managementSystem, clinicId);
                } else if(DataValidation.BookableCommands.LOG_BY_DATE.equals(userInput)) {
                    logByDate(in, managementSystem, clinicId);
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        in.close();
    }

    private String createAcceptableCommandsString(boolean isBookableClinic, ArrayList<Enum<?>> acceptableCommands) {
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
    private int getClinicId(Scanner in, ManagementSystem managementSystem) {
        while(true) {
            int userInput = (Integer) DataValidation.getValue(in, "Please provide your Clinic ID",
                    DataValidation.ParameterTypes.NON_NEGATIVE_INT);

            if(managementSystem.getClinicIds().contains(userInput)) {
                //If ID is valid, set clinicId and exit loop
                System.out.println("You are now managing Clinic #" + userInput);
                return userInput;
            }else {
                //If ID is invalid, error message
                System.out.println("A clinic with that ID does not exist");
            }
        }
    }

    // Start addBatch workflow
    private void addBatch(Scanner in, ManagementSystem managementSystem, int clinicId) {
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
    private void setEmployees(Scanner in, ManagementSystem managementSystem, int clinicId) {
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
    private void addTimePeriod(Scanner in, ManagementSystem managementSystem, int clinicId) {
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
    private void addTimePeriods(Scanner in, ManagementSystem managementSystem, int clinicId) {
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
    private void logAppointment(Scanner in, ManagementSystem managementSystem, int clinicId) {
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

    private void logWalkIn(Scanner in, ManagementSystem managementSystem, int clinicId) {
        // Ask for information for logging a walk-in
        String vaccinationID = (String) DataValidation.getValue(in, "Vaccination ID:", DataValidation.ParameterTypes.NON_NEGATIVE_INT);
        String clientHCN = (String) DataValidation.getValue(in, "Client Health Card Number:", DataValidation.ParameterTypes.FREE_TEXT);
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

    private void logByDateTime(Scanner in, ManagementSystem managementSystem, int clinicId) {
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


    private void logByDate(Scanner in, ManagementSystem managementSystem, int clinicId) {
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
    private void removeTimePeriod(Scanner in, ManagementSystem managementSystem, int clinicId) {
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
    private void bookAppointment(Scanner in, ManagementSystem managementSystem, int clinicId){
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
    private void cancelAppointment(Scanner in, ManagementSystem managementSystem, int clinicId){
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
    private void viewAppointment(Scanner in, ManagementSystem managementSystem, int clinicId){
        // Ask for information for viewing an appointment
        int appointmentId = (Integer) DataValidation.getValue(in,
                "Enter your Appointment ID:",
                DataValidation.ParameterTypes.POSITIVE_INT);

        // Try to view the appointment
        String output = managementSystem.viewAppointment(clinicId, appointmentId);

        System.out.println(Objects.requireNonNullElse(output, "You don't have an appointment booked"));
    }



}
