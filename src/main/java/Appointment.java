import java.time.LocalDate;

/**
 * This is the Appointment entity for each appointment instance,
 * which keeps track of the client,
 */

public class Appointment {
    Client client;
    LocalDate datetime;
    String vaccineBrand;

    public Appointment(Client client, LocalDate datetime, String vaccineBrand){
        this.client = client;
        this.datetime = datetime;
        this.vaccineBrand = vaccineBrand;
    }

    //getters
    public Client getClient() { return client; }

    public Object getDaytime() { return datetime; }

    public String getVaccineBrand() { return vaccineBrand;}
}
