import entities.VaccineBatch;
import entities.VaccineSupply;
import org.junit.*;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.HashMap;

public class VaccineSupplyTest {
    VaccineSupply supply;
    VaccineBatch batch;
    VaccineBatch batch1;



    @Before // Setting up before the tests
    public void setUp() throws Exception {
        batch = new VaccineBatch("Pfizer", 100,
                LocalDate.of(2099, 10 , 30), 1234);
        batch1 = new VaccineBatch("Moderna", 69,
                LocalDate.of(2022, 10 , 10), 1234);
        supply = new VaccineSupply();
        supply.getBatchList().add(batch);
        supply.getBatchList().add(batch1);
    }
    @Test(timeout = 100) // Testing that get available vaccines returns the proper brand and amount
    public void testGetAvailableVaccines(){
        HashMap<String, Integer> mapVaccines = new HashMap<>();
        mapVaccines.put("Pfizer", 100);
        mapVaccines.put("Moderna", 69);
        assertEquals(supply.getAvailableVaccines(), mapVaccines);
    }

}
