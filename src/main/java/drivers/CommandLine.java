package drivers;

import controllers.ManagementSystem;
import entities.Client;
import entities.TimePeriod;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

        //Ask what the user would like to do
        runCommands(in, managementSystem, clinicId);
    }

    // Determine which command to run
    private void runCommands(Scanner in, ManagementSystem managementSystem, int clinicId) {
        boolean isRunning = true;

        StringBuilder s = new StringBuilder("Commands: ");
        for(DataValidation.Commands command : DataValidation.Commands.values()) {
            s.append(command.toString()).append(", ");
        }

        String commandListString = s.substring(0, s.toString().length() - 2);

        while(isRunning) {
            String userInput = (String) DataValidation.getValue(in, commandListString, DataValidation.ParameterTypes.COMMAND);

            try {
                switch (DataValidation.Commands.valueOf(userInput)) {
                    case ADD_BATCH:
                        addBatch(in, managementSystem, clinicId);
                        break;
                    case SET_EMPLOYEES:
                        setEmployees(in, managementSystem, clinicId);
                        break;
                    case ADD_TIME_PERIOD:
                        addTimePeriod(in, managementSystem, clinicId);
                        break;
                    case REMOVE_TIME_PERIOD:
                        removeTimePeriod(in, managementSystem, clinicId);
                        break;
                    case ADD_TIME_PERIODS:
                        addTimePeriods(in, managementSystem, clinicId);
                        break;
                    case QUIT:
                        // Quit the program
                        isRunning = false;
                        System.out.println("Quitting Program");
                        break;
                    case BOOK_APPOINTMENT:
                        bookAppointment(in, managementSystem, clinicId);
                        break;
                    case CANCEL_APPOINTMENT:
                        cancelAppointment(in, managementSystem, clinicId);
                        break;
                    case VIEW_APPOINTMENT:
                        viewAppointment(in, managementSystem, clinicId);
                        break;

                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        in.close();
    }

    // Get the clinic ID
    private int getClinicId(Scanner in, ManagementSystem managementSystem) {
        while(true) {
            int userInput = (Integer) DataValidation.getValue(in, "Please provide your Clinic ID", DataValidation.ParameterTypes.NON_NEGATIVE_INT);

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
        String batchBrand = (String) DataValidation.getValue(in, "Batch Brand:", DataValidation.ParameterTypes.FREE_TEXT);
        int batchId = (Integer) DataValidation.getValue(in, "Batch ID:", DataValidation.ParameterTypes.NON_NEGATIVE_INT);
        int batchQuantity = (Integer) DataValidation.getValue(in, "Batch Quantity:", DataValidation.ParameterTypes.NON_NEGATIVE_INT);
        LocalDate batchExpiry = (LocalDate) DataValidation.getValue(in, "Batch Expiry Date (DD/MM/YYYY):", DataValidation.ParameterTypes.NON_PAST_DATE);

        // Add the batch via the managementSystem
        boolean output = managementSystem.addBatch(clinicId, batchBrand, batchQuantity, batchExpiry, batchId);

        // Output different message depending on result
        if(output) {
            System.out.println("Successfully added the batch");
            System.out.println(managementSystem.getSupplyByClinic(clinicId));
        }else {
            System.out.println("Could not add the batch");
        }
    }

    // Start setEmployees workflow
    private void setEmployees(Scanner in, ManagementSystem managementSystem, int clinicId) {
        // Ask for information for employee setting
        LocalDate date = (LocalDate) DataValidation.getValue(in, "Date (DD/MM/YYYY):", DataValidation.ParameterTypes.NON_PAST_DATE);
        int employees = (Integer) DataValidation.getValue(in, "Number of Employees for this date:", DataValidation.ParameterTypes.NON_NEGATIVE_INT);

        // Set employees through the managementSystem
        boolean output = managementSystem.setEmployees(clinicId, date, employees);

        if(output) {
            System.out.println("Successfully changed number of employees for that date");
        }else {
            System.out.println("Could not change the number of employees for that date");
        }
    }

    // Start the addTimePeriod workflow
    private void addTimePeriod(Scanner in, ManagementSystem managementSystem, int clinicId) {
        // Ask for information for adding a new time period
        LocalDateTime dateTime = (LocalDateTime) DataValidation.getValue(in, "Date and Time (24 hour time, DD/MM/YYYY HH:MM):", DataValidation.ParameterTypes.NON_PAST_DATETIME);
        // Add the time period through the managementSystem
        boolean output = managementSystem.addTimePeriod(clinicId, dateTime);

        if(output) {
            System.out.println("Successfully added the time period");
        }else {
            System.out.println("Could not add the time period");
        }
    }

    // Start the addTimePeriod workflow
    private void addTimePeriods(Scanner in, ManagementSystem managementSystem, int clinicId) {
        // Ask for information for adding a new time period
        LocalDateTime start = (LocalDateTime) DataValidation.getValue(in, "Start Date and Time (24 hour time, DD/MM/YYYY HH:MM):", DataValidation.ParameterTypes.NON_PAST_DATETIME);
        LocalDateTime end = (LocalDateTime) DataValidation.getValue(in, "End Date and Time (24 hour time, DD/MM/YYYY HH:MM):", DataValidation.ParameterTypes.NON_PAST_DATETIME);
        int interval = (Integer) DataValidation.getValue(in, "Length of each time period (minutes)", DataValidation.ParameterTypes.POSITIVE_INT);

        // Add the time period through the managementSystem
        int output = managementSystem.addMultipleTimePeriods(clinicId, start, end, interval);

        if(output > 0) {
            System.out.println("Successfully added the time periods");
        }else {
            System.out.println("Could not add the time periods");
        }
    }

    // Start the removeTimePeriod workflow
    private void removeTimePeriod(Scanner in, ManagementSystem managementSystem, int clinicId) {
        // Ask for information for which time period to remove
        LocalDateTime dateTime = (LocalDateTime) DataValidation.getValue(in, "Date and Time (24 hour time, DD/MM/YYYY HH:MM):", DataValidation.ParameterTypes.NON_PAST_DATETIME);
        // Try to remove the time period through the managementSystem
        boolean output = managementSystem.removeTimePeriod(clinicId, dateTime);

        if(output) {
            System.out.println("Successfully removed the time period");
        }else {
            System.out.println("No time period exists for the specified time");
        }
    }

    // Start the bookAppointment workflow
    private void bookAppointment(Scanner in, ManagementSystem managementSystem, int clinicId){
        // Ask for information for booking an appointment
        String clientName = (String) DataValidation.getValue(in, "Full Name:", DataValidation.ParameterTypes.FREE_TEXT);
        String healthCareNumber = (String) DataValidation.getValue(in, "Health Care Number:", DataValidation.ParameterTypes.FREE_TEXT);

        LocalDateTime appointmentTime = (LocalDateTime) DataValidation.getValue(in, "Appointment Date and Time (24 hour time, DD/MM/YYYY HH:MM):", DataValidation.ParameterTypes.NON_PAST_DATETIME);

        String vaccineBrand = (String) DataValidation.getValue(in, "Which Vaccine would you like:", DataValidation.ParameterTypes.FREE_TEXT);
        int appointmentId = (Integer) DataValidation.getValue(in, "Enter an Appointment ID:", DataValidation.ParameterTypes.POSITIVE_INT);

        // Try to book the appointment
        boolean output = managementSystem.bookAppointment(clinicId, clientName, healthCareNumber,
                appointmentTime, vaccineBrand, appointmentId);

        if(output) {
            System.out.println("Your appointment has been booked");
        }else {
            System.out.println("Can not book appointment");
        }
    }

    // Start the cancelAppointment workflow
    private void cancelAppointment(Scanner in, ManagementSystem managementSystem, int clinicId){
        // Ask for information for canceling an appointment
        int appointmentId = (Integer) DataValidation.getValue(in, "Enter your Appointment ID:", DataValidation.ParameterTypes.POSITIVE_INT);

        // Try to cancel the appointment
        boolean output = managementSystem.cancelAppointment(clinicId, appointmentId);

        if(output) {
            System.out.println("Your appointment has been cancelled");
        }else {
            System.out.println("Can not cancel appointment");
        }
    }

    // Start the viewAppointment workflow
    private void viewAppointment(Scanner in, ManagementSystem managementSystem, int clinicId){
        // Ask for information for viewing an appointment
        int appointmentId = (Integer) DataValidation.getValue(in, "Enter your Appointment ID:", DataValidation.ParameterTypes.POSITIVE_INT);

        // Try to view the appointment
        String output = managementSystem.viewAppointment(clinicId, appointmentId);

        System.out.println(Objects.requireNonNullElse(output, "You don't have an appointment booked"));
    }



}
