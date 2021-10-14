import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

//Class for the User Interface using the command line
//User can input commands and parameters
public class CommandLine {
    private static final String commandLinePrompt = "> ";

    public void run() {
        //Create scanner for command line and initialize a new vms
        Scanner in = new Scanner(System.in);
        VaccineManagementSystem vms = new VaccineManagementSystem(10);

        //Get the Clinic ID
        int clinicId = -1;
        while(clinicId == -1) {
            String userInput = getValue(in, "Please provide your Clinic ID");

            if(vms.getClinicIds().contains(parseInt(userInput))) {
                //If ID is valid, set clinicId and exit loop
                clinicId = parseInt(userInput);
            }else {
                //If ID is invalid, error message
                System.out.println("That Clinic ID is invalid");
            }
        }

        //Ask what the user would like to do
        System.out.println("You are now managing Clinic #" + clinicId);
        boolean isRunning = true;

        while(isRunning) {
            String userInput = getValue(in, "Commands: ADD_BATCH, QUIT");

            try {
                switch (userInput) {
                    case "ADD_BATCH":

                        // int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId

                        String batchBrand = getValue(in, "Batch Brand:");
                        int batchQuantity = parseInt(getValue(in, "Batch Quantity:"));

                        String batchExpiry = getValue(in, "Batch Expiry Date (DD/MM/YYYY):");
                        //convert String to LocalDate
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                        LocalDate batchExpiryDate = LocalDate.parse(batchExpiry, formatter);

                        int batchId = parseInt(getValue(in, "Batch ID:"));

                        boolean output = vms.addBatch(clinicId, batchBrand, batchQuantity, batchExpiryDate, batchId);
                        if(output) {
                            System.out.println("Batch added successfully");
                        }else {
                            System.out.println("Batch was not added");
                        }
                        break;
                    case "QUIT":
                        isRunning = false;
                        System.out.println("Quitting Program");

                        break;
                }
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }

        String clinicID = in.nextLine();


        in.close();
    }
    private static String getValue(Scanner in, String prompt) {
        System.out.println(prompt);
        System.out.print(commandLinePrompt);

        return in.nextLine();
    }
}
