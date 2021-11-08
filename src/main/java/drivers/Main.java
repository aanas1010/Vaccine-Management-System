package drivers;

import controllers.ManagementSystem;
import controllers.VaccineManagementSystem;
import entities.Clinic;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Create new user_interface.CommandLine UI
        Clinic clinic1 = new Clinic("Shoppers Drug Mart - 279 Yonge Street");
        Clinic clinic2 = new Clinic("Pharmasave - 4218 Lawrence Avenue East");
        Clinic clinic3 = new Clinic("Walmart - 900 Dufferin Street");
        Clinic clinic4 = new Clinic("Harbourfront Medicine Cabinet - 8 York Street");
        Clinic clinic5 = new Clinic("Loblaws - 10 Lower Jarvis Street");
        Clinic clinic6 = new Clinic("Shoppers Drug Mart - 1473 Queen Street West");

        ArrayList<Clinic> listOfClinics = new ArrayList<>();

        listOfClinics.add(clinic1);
        listOfClinics.add(clinic2);
        listOfClinics.add(clinic3);
        listOfClinics.add(clinic4);
        listOfClinics.add(clinic5);
        listOfClinics.add(clinic6);

        ManagementSystem system = new VaccineManagementSystem(6, listOfClinics);
        CommandLine UI = new CommandLine(system);

        //Run
        UI.run();
    }
}
