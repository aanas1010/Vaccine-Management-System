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
     * @param name [description]
     * @param healthCareNumber [description]
     */
    public Client(String name, String healthCareNumber){
        this.name = name;
        this.healthCareNumber = healthCareNumber;
        this.hasAppointment = false;
    }

    /**
     * [description]
     */
    public void approveAppointment() {this.hasAppointment = true;}

    /**
     * [description]
     */
    public void disapproveAppointment() {this.hasAppointment = false;}

    // Getters
    /**
     * @return [description]
     */
    public String getName() {return name;}

    /**
     * @return // Getters
     */
    public String getHealthCareNumber() {return healthCareNumber;}

    /**
     * @return // Getters
     */
    public Boolean getHasAppointment() {return hasAppointment;}
}
