package drivers;

import controllers.ManagementSystem;
import controllers.VaccineManagementSystem;
import drivers.CommandLine;

public class Main {
    public static void main(String[] args) {
        //Create new user_interface.CommandLine UI
        ManagementSystem system = new VaccineManagementSystem(10);
        CommandLine UI = new CommandLine(system);

        //Run
        UI.run();
    }
}
