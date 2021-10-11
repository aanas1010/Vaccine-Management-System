import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testClass {
    int m;
    int n;


    @Before
    public void setUp() throws Exception {
        m = 10;
        n = 16;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testTestMethod() { assertEquals(main.testMethod(m, n), 26);
    }
}
