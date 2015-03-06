package edu.csupomona.cs480;

import java.util.*;
import java.lang.Math;

//import java.lang.Exception;//might be needed

public class Course {
   String name;
   int number;
   String professor;
   static HashMap<String, Double> distribution;
   Boolean percentBased;
   Course originalCourse;
   String originalName;
   int originalNumber;
   String originalProfessor;
   HashMap originalDistribution;

   public Course(String name, int number, String professor, HashMap<String, Double> dist) {
      this.name = name;
      this.number = number;
      this.professor = professor;
      distribution = dist;
      this.percentBased = true;
      setRestore();
   }

   public Course(String name, int number, String professor,
      HashMap<String, Double> dist, Boolean percentBased) {
      this.name = name;
      this.number = number;
      this.professor = professor;
      this.distribution = dist;
      this.percentBased = percentBased;
      setRestore();
   }
   
   //implements the momento design pattern, ensuring the instance can
   //return to its original state
   public void setRestore(){
       originalName = name;
       originalNumber = number;
       originalProfessor = professor;
       originalDistribution = distribution;
   }
   
   public void restore(){
      name = originalName;
           number = originalNumber;
           professor = originalProfessor;
           distribution = originalDistribution;
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
   
   public boolean getPercentBased(){
      return percentBased;
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
   public HashMap<String, Double> getDistribution() {
      return distribution;
   }

   /*
    Changes the old grade distribution to a given grade distribution
    */
   public void changeDistribution(HashMap<String, Double> changedDistribution) {
      distribution = changedDistribution;
   }

   /*
    Probably won't use checkGoodPercent, input verification to be
      handled elsewhere.
    */
   public boolean checkGoodPercent(HashMap<String, Double> test) {
      int totalPercent = 0;
      for (Double value : test.values()) {
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
    In this method, the output is the grade needed on the final
      that would cause the entire class grade to reach goal.  It reads
      the percentages given to it by distribution, and the percentages
      obtained by the user in the class in obtained, and then calculates
      what percent of the final grade needs to be earned to reach the
      goal percentage.  Calculate will return -1 if the goal is not
      reachable given a max grade on the final.
      * Note: obtained should not include a final entry, as this is what
      * is being calculated.  So if distribution contained 5 elements,
      * obtained would contain 4, as it would not include final.
    */
   public double finalCalculate(HashMap<String, Double> obtained, double goal)
   {
       double gradeNeeded = 0;
       double totalPercent = 0;//Will usually be 100
       double totalEarned = 0;//Sum of all grades before final
       double finalWeight = 0;//Percent contribution of final to class grade
       
       if(!percentBased){
         double totalPoints = 0;
         double obtainedPoints = 0;
           for (Double value : distribution.values()){
              totalPoints += value;//find out how many points class is out of
           }
           for(Double value : obtained.values()){
            obtainedPoints += value;//find out how many points were obtained
           }
           finalWeight = ((distribution.get("Final")/totalPoints) * 100);//find out what percent of the class the final is
           totalPercent = 100 - finalWeight;//remove final weight from pre-calculation total
           totalEarned = ((obtainedPoints/totalPoints) * 100);//find total percent earned
            //System.out.println("Final Weight: " + finalWeight);
            //System.out.println("Earned: " + totalEarned);
            //System.out.println("Goal: " + goal);
       }
       else{
          for(Double value : distribution.values()){
              totalPercent += value;
          }
          /*
           We will now remove final from the total percent calculation, as
             this is what is being calculated.
           */
          totalPercent -= distribution.get("Final");
          //System.out.println("Total Percent: " + totalPercent);-------------------
          for(Double value : obtained.values()){
              totalEarned += value;
          }
          finalWeight = distribution.get("Final");
       }
       //System.out.println("Final Weight: " + finalWeight);
       //System.out.println("Earned: " + totalEarned);
       //System.out.println("Goal: " + goal);
       /*
        Here, the total - goal is subtracted from the total percent to lower
          the grade needed on the final.  Without this offset, the grade
          needed on the final would be the grade needed to get a perfect
          score in the class, and would therefore be very difficult to obtain.
          * An example: Total class percent = 100% : goal percent = 75%
          * total earned = 70%.  The calculation would yield
          * 100% - (100% - 75%) - 70% = 100% - 25% - 70% = 5%
          * so the method would return 5, as that is the percent needed
          * of the final to receive the goal percent.  It will then calculate
          * what percent that will be for the final.
        */
       gradeNeeded = (totalPercent - (totalPercent - goal)) - totalEarned;
       if(gradeNeeded > finalWeight){//goal unobtainable
           return (Integer)(-1);
       }
       if(gradeNeeded < 0){//goal already met
         return (Integer)0;
       }
       /*
        The calculation below makes gradeNeeded be the percent needed on the
          final, as opposed to the current value of percent of the class
          needed.
        */
       gradeNeeded *= (100 / finalWeight);
       
       return Math.round(gradeNeeded);
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