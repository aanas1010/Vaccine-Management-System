package entities;

public interface User {
    /**
     * [description]
     */
    void approveAppointment();

    /**
     * [description]
     */
    void disapproveAppointment();


    /**
     * @return [description]
     */
    // Getters
    String getName();

    /**
     * @return [description]
     */
    String getHealthCareNumber();

    /**
     * @return [description]
     */
    Boolean getHasAppointment();
}
