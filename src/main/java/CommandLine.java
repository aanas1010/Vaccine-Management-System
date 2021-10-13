import java.util.ArrayList;
import java.util.Scanner;

//Class for the User Interface using the command line
//User can input commands and parameters
public class CommandLine {
    public static void main(String[] args) {
        //Create scanner for command line and initialize a new vms
        Scanner in = new Scanner(System.in);
        VaccineManagementSystem vms = new VaccineManagementSystem(10);

        //Get the Clinic ID
        int clinicId = -1;
        while(clinicId == -1) {
            System.out.println("Please provide your Clinic ID");
            System.out.print("> ");
            String userInput = in.nextLine();

            ArrayList<Integer> clinicIds = vms.getClinicIds();
            Integer userPromptClinicId = Integer.parseInt(userInput);

            if(clinicIds.contains(userPromptClinicId)) {
                //If ID is valid, set clinicId and exit loop
                clinicId = Integer.parseInt(userInput);
            }else {
                //If ID is invalid, error message
                System.out.println("That Clinic ID is invalid");
            }
        }

        //Ask what the user would like to do
        System.out.println("You are now managing Clinic #" + clinicId);
//        boolean isRunning = true;
//        while(isRunning) {
//            System.out.println("Commands: ADD_BATCH, ");
//            System.out.println("");
//        }

        //String clinicID = in.nextLine();


        in.close();
    }
}
