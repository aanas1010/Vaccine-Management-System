package entities;

/**
 * This is the Client entity which consists of a person's data
 * from an external data source.
 */

public class Client implements User{
    private final String name;
    private final String healthCareNumber;
    private Boolean hasAppointment;

    /**
     * construct a client object.
     *
     * @param name name of the client.
     * @param healthCareNumber number of the client.
     */
    public Client(String name, String healthCareNumber){
        this.name = name;
        this.healthCareNumber = healthCareNumber;
        this.hasAppointment = false;
    }

    /**
     * Approves the user's appointment.
     */
    public void approveAppointment() {this.hasAppointment = true;}

    /**
     * Disapproves the user's appointment.
     */
    public void disapproveAppointment() {this.hasAppointment = false;}

    // Getters

    /**
     * getter.
     *
     * @return the name of the user.
     */
    public String getName() {return name;}

    /**
     * getter.
     *
     * @return the health care number of the under.
     */
    public String getHealthCareNumber() {return healthCareNumber;}

    /**
     * getter.
     *
     * @return true if the user has an appointment; false otherwise.
     */
    public Boolean getHasAppointment() {return hasAppointment;}
}
