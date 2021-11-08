package drivers;

import controllers.*;
import entities.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Create the useCaseManager that stores the service locations
        UseCaseManagerInterface useCaseManager = new UseCaseManager();

        // Create the clinics
        useCaseManager.addBookableClinic(1, "Shoppers Drug Mart - 279 Yonge Street");
        useCaseManager.addClinic(2, "Pharmasave - 4218 Lawrence Avenue East");
        useCaseManager.addBookableClinic(3, "Walmart - 900 Dufferin Street");
        useCaseManager.addClinic(4, "Harbourfront Medicine Cabinet - 8 York Street");
        useCaseManager.addBookableClinic(5, "Loblaws - 10 Lower Jarvis Street");
        useCaseManager.addClinic(6, "Shoppers Drug Mart - 1473 Queen Street West");

        // Create the management system given the useCaseManager
        ManagementSystem system = new VaccineManagementSystem(useCaseManager);
        CommandLine UI = new CommandLine(system);

        //Run
        UI.run();
    }
}
