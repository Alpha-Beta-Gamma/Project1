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
      Course c = new Course("Databases", 48043, "Smith", new HashMap<String, Integer>());
      double[] scores = { 30.0, 20.0, 10.0, 5.0 };
      double outOf = 100.0;

      assertEquals(c.calculateMissingValue(scores, outOf), 35.0, 0.0);

   }
   
   public void testFinalGradeNeeded() {
	   HashMap<String, Integer> possible = new HashMap<String, Integer>();
	   HashMap<String, Integer> got = new HashMap<String, Integer>();
	   possible.put("Homework", 20);
	   possible.put("Quizzes", 30);
	   possible.put("Midterm", 25);
	   possible.put("Final", 25);
	   got.put("Homework", 20);
	   got.put("Quizzes", 30);
	   got.put("Midterm",  25);
	   
	   assertEquals(FinalCalc.Calculate(possible, got, 87.5), 50, 0.0);
   }
   
   public void testFinalPoints(){
	   HashMap<String, Integer> possible = new HashMap<String, Integer>();
	   HashMap<String, Integer> got = new HashMap<String, Integer>();
	   possible.put("Homework", 100);
	   possible.put("Quizzes", 100);
	   possible.put("Midterm", 150);
	   possible.put("Final", 200);
	   got.put("Homework", 100);
	   got.put("Quizzes", 100);
	   got.put("Midterm", 150);
	   Course testCourse = new Course("Test", 101, "Joh Doe", possible, false);
	   assertEquals(testCourse.finalCalculate(got, 100), 100, 0.1);
	   
	   
   }
   
   public void testProfessor() {
	   Course d = new Course("CS 480", 48043, "Saroka", new HashMap<String, Integer>());
	   d.setProfessor("Sun");
	   
	   assertEquals("Sun", d.getProfessor());
   }
}
