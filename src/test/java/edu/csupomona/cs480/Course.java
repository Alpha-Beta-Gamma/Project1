package edu.csupomona.cs480;

import java.util.*;

//import java.lang.Exception;//might be needed

public class Course {
   String name;
   int number;
   String professor;
   static HashMap<String, Integer> distribution;

   public Course(String name, int number, String professor, HashMap<String, Integer> dist) {
      this.name = name;
      this.number = number;
      this.professor = professor;
      distribution = dist;
   }

   //getter methods
   public String getName() {
      return name;
   }

   public int getNumber() {
      return number;
   }

   public String getProfessor() {
      return professor;
   }

   //setter methods if edits are needed (shouldn't be needed, just in case)
   public void setName(String name) {
      this.name = name;
   }

   public void setNumber(int number) {
      this.number = number;
   }

   public void setProfessor(String professor) {
      this.professor = professor;
   }

   /*
    Returns the current distribution -- to change grade distribution,
      use getDistribution(), change grade distribution, then call
      changeDistribution(newDistribution);
      !!Make sure total percentages add to up 100!!
   */
   public HashMap<String, Integer> getDistribution() {
      return distribution;
   }

   /*
    Changes the old grade distribution to a given grade distribution
    */
   public void changeDistribution(HashMap<String, Integer> changedDistribution) {
      distribution = changedDistribution;
   }

   /*
    Probably won't use checkGoodPercent, input verification to be
      handled elsewhere.
    */
   public boolean checkGoodPercent(HashMap<String, Integer> test) {
      int totalPercent = 0;
      for (int value : test.values()) {
         totalPercent += value;
      }
      return (totalPercent == 100);
   }

   /*
    * this takes in a list of values of scores recieved by the student
    * and caluclates much more percentage the student needs inorder to reach outOf
    * outOF will tipcally be 100 for percentage pased classes, or it will be how ever many points
    * the class is based on
    */
   public double calculateMissingValue(double[] scores, double outOf) {
      double total = 0;
      for (double cur : scores)
         total += cur;

      return outOf - total;
   }

   /*
    returns the characteristics of a class in the form:
      <Class Name> | <Class Number> | <Professor> |
      {distName1=distNum1, distName2=distNum2, ...}
      name, number, professor and distribution set 
      are delimited by the "|" vertical bar symbol
    */
   public String toString() {
      String strungClass = new String(name + " | " + number + " | " + professor + " | " + "\n" + distribution.toString());
      return strungClass;
   }

}
