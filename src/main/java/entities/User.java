package entities;

/**
 * Interface class for the user.
 */

public interface User {

    /**
     * Approves the user's appointment.
     */
    void approveAppointment();

    /**
     * Disapproves the user's appointment.
     */
    void disapproveAppointment();

    // Getters

    /**
     * getter.
     *
     * @return the name of the user.
     */
    String getName();

    /**
     * getter.
     *
     * @return the health care number of the under.
     */
    String getHealthCareNumber();

    /**
     * getter.
     *
     * @return true if the user has an appointment; false otherwise.
     */
    Boolean getHasAppointment();
}
