package drivers;

import databaseintegration.DatabaseRetrieval;
import databaseintegration.DatabaseModification;
import databaseintegration.ExampleRetrieval;
import managers.*;

/**
 * This is the Main class in our program. Running this file allows you to use the program.
 */
public class Main {
    public static void main(String[] args) {

        try {
            // Create the useCaseManager that stores the service locations

            //Use this line for reading and writing to the database
            UseCaseManagerInterface useCaseManager = new UseCaseManager(new DatabaseRetrieval(), new DatabaseModification());

            // Use this line for only reading from a mock class
            //UseCaseManagerInterface useCaseManager = new UseCaseManager(new ExampleRetrieval(), null);


            // Create the management system given the useCaseManager
            ManagementSystem system = new VaccineManagementSystem(useCaseManager);
            CommandLine UI = new CommandLine(system);

            //Run the system
            UI.run();

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
