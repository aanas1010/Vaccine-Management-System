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

    private void runCommands(Scanner in, ManagementSystem managementSystem, int clinicId) {
        boolean isRunning = true;
        while(isRunning) {
            String userInput = getValue(in, "Commands: ADD_BATCH, QUIT");

            if (isValidCommand(userInput)){continue;}

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

    private boolean isValidCommand(String userInput) {
        if(!Commands.contains(userInput)) {
            System.out.println("'" + userInput + "' is an invalid command");
            return true;
        }
        return false;
    }

    private void addBatch(Scanner in, ManagementSystem managementSystem, int clinicId) {
        // Ask for information for a new batch
        String batchBrand = getValue(in, "Batch Brand:");
        int batchId = parseInt(getValue(in, "Batch ID:"));
        int batchQuantity = parseInt(getValue(in, "Batch Quantity:"));
        String batchExpiry = getValue(in, "Batch Expiry Date (DD/MM/YYYY):");
        //convert String to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate batchExpiryDate = LocalDate.parse(batchExpiry, formatter);

        // Add the batch via the managementSystem
        boolean output = managementSystem.addBatch(clinicId, batchBrand, batchQuantity, batchExpiryDate, batchId);

        // Output different message depending on result
        if(output) {
            System.out.println("Batch added successfully");
        }else {
            System.out.println("Batch was not added");
        }
    }

    private int getClinicId(Scanner in, ManagementSystem managementSystem) {
        //Get the Clinic ID
        int clinicId = -1;
        while(clinicId == -1) {
            String userInput = getValue(in, "Please provide your Clinic ID");

            if(managementSystem.getClinicIds().contains(parseInt(userInput))) {
                //If ID is valid, set clinicId and exit loop
                clinicId = parseInt(userInput);
            }else {
                //If ID is invalid, error message
                System.out.println("That Clinic ID is invalid");
            }
        }
        System.out.println("You are now managing Clinic #" + clinicId);
        return clinicId;
    }

    // Helper function for input and output to command line
    private static String getValue(Scanner in, String prompt) {
        System.out.println(prompt);
        System.out.print(commandLinePrompt);

        return in.nextLine();
    }
}
