import clinic_management.BatchAdding;
import entities.Clinic;
import entities.VaccineBatch;
import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

public class BatchAddingTest {
    VaccineBatch batch;
    VaccineBatch badBatch;
    Clinic clinic;
    BatchAdding expiredAdd;
    BatchAdding batchAdd;

    @Before // Setting up before the tests
    public void setUp() throws Exception{
        batch = new VaccineBatch("Pfizer", 100,
                LocalDate.of(2099, 10 , 30), 1234);
        badBatch = new VaccineBatch("Pfizer", 100,
                LocalDate.of(2021, 10 , 10), 1234);
        clinic = new Clinic(1);
        batchAdd = new BatchAdding(clinic, batch);
        expiredAdd = new BatchAdding(clinic, badBatch);
    }

    @Test(timeout = 100) // Testing an expired batch of vaccine doses does not get added
    public void TestExpiredBatch(){assertFalse(expiredAdd.addBatch());}

    @Test(timeout = 100) // Testing a valid batch of vaccine doses is added
    public void TestAddBatch(){assertTrue(batchAdd.addBatch());}

    @Test(timeout = 100) // Testing for correct list of vaccine batches after expired addition
    public void TestEmptyContents(){
        expiredAdd.addBatch();
        ArrayList<VaccineBatch> testBatchList = new ArrayList<VaccineBatch>();
        assertEquals(testBatchList, clinic.getSupply());
    }

    @Test(timeout = 100) // Testing for correct list of vaccine batches after valid addition
    public void TestContents(){
        batchAdd.addBatch();
        ArrayList<VaccineBatch> testBatchList = new ArrayList<VaccineBatch>();
        testBatchList.add(batch);
        assertEquals(testBatchList, clinic.getSupply());
    }

}
