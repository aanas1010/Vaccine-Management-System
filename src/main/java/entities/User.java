package entities;

public interface User {
    void approveAppointment();

    void disapproveAppointment();


    // Getters
    String getName();

    String getHealthCareNumber();

    Boolean getHasAppointment();
}
