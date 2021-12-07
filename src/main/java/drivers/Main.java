package drivers;

import constants.BookingConstants;
import databaseintegration.*;
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

            //FOR FULL DATABASE STORING AND RETRIEVING
            //Create connection and statement
            Connection connection = DriverManager.getConnection(
                    BookingConstants.DATABASE_CONNECTION_URL,
                    BookingConstants.DATABASE_CONNECTION_USERNAME,
                    BookingConstants.DATABASE_CONNECTION_PASSWORD);
            Statement statement = connection.createStatement();

            //Create database table driver classes
            DatabaseClinicInterface databaseClinic = new DatabaseClinic(statement);
            DatabaseTimePeriodsInterface databaseTimePeriods = new DatabaseTimePeriods(connection);
            DatabaseClientInterface databaseClient = new DatabaseClient(connection, statement);
            DatabaseBatchInterface databaseBatch = new DatabaseBatch(connection);
            DatabaseAppointmentInterface databaseAppointment = new DatabaseAppointment(connection);

            //Create database controllers
            DataRetrieval databaseRetrieval = new DatabaseRetrieval.RetrieverBuilder()
                    .clinic(databaseClinic)
                    .timePeriod(databaseTimePeriods)
                    .client(databaseClient)
                    .batch(databaseBatch)
                    .appointment(databaseAppointment)
                    .build();

            DataModification databaseModifier = new DatabaseModification.ModifierBuilder()
                    .timePeriod(databaseTimePeriods)
                    .client(databaseClient)
                    .batch(databaseBatch)
                    .appointment(databaseAppointment)
                    .build();

            //Create Use Case Manager
            UseCaseManagerInterface useCaseManager = new UseCaseManager(new Retriever(databaseRetrieval),
                    new Modifier(databaseModifier));


            //Alternatively, for retrieving from a mock class
//            UseCaseManagerInterface useCaseManager =
//                    new UseCaseManager(new Retriever(new ExampleRetrieval()), null);

            // Create the Management System
            ManagementSystem system = new VaccineManagementSystem(useCaseManager);
            CommandLine UI = new CommandLine(system);

            //Run the system
            UI.run();

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
