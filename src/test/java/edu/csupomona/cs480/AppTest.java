package edu.csupomona.cs480;

import java.util.HashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
   /**
    * Create the test case
    *
    * @param testName name of the test case
    */
   public AppTest(String testName) {
      super(testName);
   }

   /**
    * @return the suite of tests being tested
    */
   public static Test suite() {
      return new TestSuite(AppTest.class);
   }

   /**
    * Rigourous Test :-)
    */
   public void testApp() {
      assertTrue(true);
   }

   public void testMissingValue() {
      Course c = new Course("Databases", 48043, "Smith", new HashMap<String, Integer>());
      double[] scores = { 30.0, 20.0, 10.0, 5.0 };
      double outOf = 100.0;

      assertEquals(c.calculateMissingValue(scores, outOf), 35.0, 0.0);

   }
}
