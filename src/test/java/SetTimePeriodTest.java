import clinic_management.SetTimePeriod;
import entities.Clinic;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SetTimePeriodTest {
    SetTimePeriod setTimePeriod;
    Clinic clinic;
    LocalDateTime testDateTime;
    LocalDate testDate;
    @Before // Setting up before the tests
    public void setUp() throws Exception{
        clinic = new Clinic(1, "Shoppers Drug Mart - 279 Yonge Street");
        setTimePeriod = new SetTimePeriod(clinic);
        testDateTime = LocalDateTime.of(2021, 10, 31, 12, 0);
        testDate = LocalDate.of(2021, 11, 4);
    }

    @Test(timeout = 100) // Testing setting the number of employees
    public void TestSetEmployees(){
        setTimePeriod.setEmployees(testDate, 5);
        assertEquals(clinic.getShiftForDate(testDate), 5);
    }

    @Test(timeout = 100) // Testing a time period is added
    public void TestAddTimePeriodTrue(){
        setTimePeriod.setEmployees(testDateTime.toLocalDate(), 5);
        assertNotNull(setTimePeriod.addTimePeriod(testDateTime));
    }

    @Test(timeout = 100) // Testing a time period is not added
    public void TestAddTimePeriodFalse(){
        assertNull(setTimePeriod.addTimePeriod(testDateTime));
    }

    @Test(timeout = 100) // Testing a time period is not added after it has already been added
    public void TestAddTimePeriodNotAdded(){
        setTimePeriod.setEmployees(testDateTime.toLocalDate(), 5);
        setTimePeriod.addTimePeriod(testDateTime);
        assertTrue(clinic.checkTimePeriod(testDateTime));
    }

    @Test(timeout = 100) // Testing a time period is removed
    public void TestRemoveTimePeriodTrue(){
        setTimePeriod.setEmployees(testDateTime.toLocalDate(), 5);
        setTimePeriod.addTimePeriod(testDateTime);
        setTimePeriod.removeTimePeriod(testDateTime);
        assertFalse(clinic.checkTimePeriod(testDateTime));
    }

    @Test(timeout = 100) // Testing that a time period is not removed when it does not exist
    public void TestRemoveTimePeriodFalse(){
        assertNull(setTimePeriod.removeTimePeriod(testDateTime));
    }

    @Test(timeout = 100) // Testing the correct number of time period is added
    public void TestAddMultipleTimePeriods(){
        setTimePeriod.setEmployees(testDateTime.toLocalDate(), 5);
        assertEquals(setTimePeriod.addMultipleTimePeriods(testDateTime,
                testDateTime.plusMinutes(120), 30), 4);
    }
    @Test(timeout = 100) // Testing the correct number of time period is added after a time period is added
    public void TestAddMultipleTimePeriodsSelected(){
        setTimePeriod.setEmployees(testDateTime.toLocalDate(), 5);
        setTimePeriod.addTimePeriod(testDateTime);
        assertEquals(setTimePeriod.addMultipleTimePeriods(testDateTime,
                testDateTime.plusMinutes(120), 30), 3);
    }
    }
