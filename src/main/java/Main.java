public class Main {
    public static void main(String[] args) {
        //Create new CommandLine UI
        ManagementSystem system = new VaccineManagementSystem(10);
        CommandLine UI = new CommandLine(system);

        //Run
        UI.run();
    }
}
