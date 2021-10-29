package drivers;

import controllers.ManagementSystem;
import java.time.LocalDate;
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
        while(isRunning) {
            String userInput = (String) DataValidation.getValue(in, "Commands: ADD_BATCH, QUIT", DataValidation.ParameterTypes.COMMAND);

            try {
                switch (DataValidation.Commands.valueOf(userInput)) {
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
            System.out.println("Batch added successfully");
            System.out.println(managementSystem.getSupplyByClinic(clinicId));
        }else {
            System.out.println("Batch could not be added");
        }
    }


}
