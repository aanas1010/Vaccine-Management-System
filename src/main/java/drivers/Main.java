package drivers;

import constants.BookingConstants;
import databaseintegration.DatabaseRetrieval;
import managers.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * This is the Main class in our program. Running this file allows you to use the program.
 */
public class Main {
    public static void main(String[] args) {

        try {
            // Create the useCaseManager that stores the service locations
            UseCaseManagerInterface useCaseManager = new UseCaseManager(new DatabaseRetrieval(), null);

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
