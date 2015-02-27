package edu.csupomona.cs480;

import java.util.HashMap;
import java.util.*;
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
      Course c = new Course("Databases", 48043, "Smith", new HashMap<String, Double>());
      double[] scores = { 30.0, 20.0, 10.0, 5.0 };
      double outOf = 100.0;

      assertEquals(c.calculateMissingValue(scores, outOf), 35.0, 0.0);

   }
   
   
   public void testFinalPoints(){
	   HashMap<String, Double> possible = new HashMap<String, Double>();
	   HashMap<String, Double> got = new HashMap<String, Double>();
	   possible.put("Homework", 100.0);
	   possible.put("Quizzes", 100.0);
	   possible.put("Midterm", 150.0);
	   possible.put("Final", 200.0);
	   got.put("Homework", 100.0);
	   got.put("Quizzes", 100.0);
	   got.put("Midterm", 150.0);
	   Course testCourse = new Course("Test", 101, "Joh Doe", possible, false);
	   assertEquals(testCourse.finalCalculate(got, 100), 100, 0.0);
	   
	   
   }
   
   public void testProfessor() {
	   Course d = new Course("CS 480", 48043, "Saroka", new HashMap<String, Double>());
	   d.setProfessor("Sun");
	   
	   assertEquals("Sun", d.getProfessor());
   }
}
