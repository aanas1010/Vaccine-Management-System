package drivers;

import constants.ManagementSystemException;
import managers.*;

/**
 * This is the Main class in our program. Running this file allows you to use the program.
 */
public class Main {
    public static void main(String[] args) {

        // Create the useCaseManager that stores the service locations
        UseCaseManagerInterface useCaseManager = new UseCaseManager();

        try {
            // Create the clinics
            useCaseManager.addBookableClinic(1, "Shoppers Drug Mart - 279 Yonge Street");
            useCaseManager.addClinic(2, "Pharmasave - 4218 Lawrence Avenue East");
            useCaseManager.addBookableClinic(3, "Walmart - 900 Dufferin Street");
            useCaseManager.addClinic(4, "Harbourfront Medicine Cabinet - 8 York Street");
            useCaseManager.addBookableClinic(5, "Loblaws - 10 Lower Jarvis Street");
            useCaseManager.addClinic(6, "Shoppers Drug Mart - 1473 Queen Street West");

            // Create the clients
            useCaseManager.addClient("Amy Ashcroft", "1111-111-111-AA");
            useCaseManager.addClient("Bart Black", "2222-222-222-BB");
            useCaseManager.addClient("Cameron Cooper", "3333-333-333-CC");
            useCaseManager.addClient("Denis Dick", "4444-444-444-DD");
            useCaseManager.addClient("Emily Edmonds", "5555-555-555-EE");
        }catch(ManagementSystemException e) {
            System.out.println(e.getMessage());
        }

        // Create the management system given the useCaseManager
        ManagementSystem system = new VaccineManagementSystem(useCaseManager);
        CommandLine UI = new CommandLine(system);

        //Run the system
        UI.run();
    }
}
