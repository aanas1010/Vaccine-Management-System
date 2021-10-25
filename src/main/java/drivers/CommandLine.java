package drivers;

import controllers.ManagementSystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * This is the class for the Command Line UI
 * User can input commands and parameters sequentially
 */

public class CommandLine {
    private static final String commandLinePrompt = "> ";
    private final ManagementSystem managementSystem;
    public enum ParameterTypes {
        NON_NEGATIVE_INT,
        NON_PAST_DATE,
        COMMAND,
        FREE_TEXT
    }
    public enum Commands {
        ADD_BATCH,
        QUIT;

        // Return whether s is a valid element of Commands
        public static boolean contains(String s) {
            for (Commands c : Commands.values()) {
                if (c.name().equals(s)) {
                    return true;
                }
            }
            return false;
        }
    }

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
        while(isRunning) {
            String userInput = (String) getValue(in, "Commands: ADD_BATCH, QUIT", ParameterTypes.COMMAND);

            try {
                switch (Commands.valueOf(userInput)) {
                    case ADD_BATCH:
                        addBatch(in, managementSystem, clinicId);
                        break;
                    case QUIT:
                        // Quit the program
                        isRunning = false;
                        System.out.println("Quitting Program");

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
            int userInput = (Integer) getValue(in, "Please provide your Clinic ID", ParameterTypes.NON_NEGATIVE_INT);

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
        String batchBrand = (String) getValue(in, "Batch Brand:", ParameterTypes.FREE_TEXT);
        int batchId = (Integer) getValue(in, "Batch ID:", ParameterTypes.NON_NEGATIVE_INT);
        int batchQuantity = (Integer) getValue(in, "Batch Quantity:", ParameterTypes.NON_NEGATIVE_INT);
        LocalDate batchExpiry = (LocalDate) getValue(in, "Batch Expiry Date (DD/MM/YYYY):", ParameterTypes.NON_PAST_DATE);

        // Add the batch via the managementSystem
        boolean output = managementSystem.addBatch(clinicId, batchBrand, batchQuantity, batchExpiry, batchId);

        // Output different message depending on result
        if(output) {
            System.out.println("Batch added successfully");
        }else {
            System.out.println("Batch was not added");
        }
    }

    // Helper function for input and output to command line
    private static Object getValue(Scanner in, String prompt, ParameterTypes type) {
        while(true) {
            System.out.println(prompt);
            System.out.print(commandLinePrompt);

            String input = in.nextLine();

            boolean hasValidValue;
            Object formattedValue;
            switch(type) {
                case NON_NEGATIVE_INT:
                    formattedValue = tryParseInt(input);
                    hasValidValue = (Integer) formattedValue != -1;
                    break;
                case NON_PAST_DATE:
                    formattedValue = isNonPastDate(input);
                    hasValidValue = formattedValue != null;
                    break;
                case COMMAND:
                    formattedValue = input;
                    hasValidValue = Commands.contains(input);
                    break;
                default:
                    formattedValue = input;
                    hasValidValue = true;
                    break;
            }

            if(hasValidValue) {
                return formattedValue;
            }else {
                System.out.println("That value is invalid. Please try again");
            }
        }
    }

    // Return the parsed int if it is a valid non-negative integer
    private static int tryParseInt(String value) {
        try{
            return Math.max(parseInt(value), -1);
        }catch(Exception ex){
            return -1;
        }
    }

    // Return the parsed date if it is a valid non-past date
    private static LocalDate isNonPastDate(String value) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            LocalDate dateObj = LocalDate.parse(value, formatter);
            if(dateObj.isAfter(LocalDate.now())) {
                return dateObj;
            } else {
                return null;
            }
        }catch(Exception ex){
            return null;
        }
    }
}
