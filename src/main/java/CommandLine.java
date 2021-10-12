import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class CommandLine {
    public static void main(String[] args) {
        boolean isRunning = true;
        Scanner in = new Scanner(System.in);

        VaccineManagementSystem vms = new VaccineManagementSystem(10);

        //Get the Clinic ID
        int clinicId = -1;
        while(clinicId == -1) {
            System.out.println("Please provide your Clinic ID");
            System.out.print("> ");
            String userInput = in.nextLine();

            ArrayList a = vms.getClinicIds();
            Integer b = Integer.parseInt(userInput);

            if(a.contains(b)) {
                clinicId = Integer.parseInt(userInput);
            }else {
                System.out.println("That Clinic ID is invalid");
            }
        }

        System.out.println("Your ID is " + clinicId);
        //String clinicID = in.nextLine();


        in.close();
    }
}
