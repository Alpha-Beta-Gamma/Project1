package edu.csupomona.cs480.data;

import java.util.*;
import java.lang.Math;


public class CourseNonPercentBase extends CourseSuper{
   double score;

   public double getScore() {
      return score;
   }


   public void setScore(double score) {
      this.score = score;
   }


   public CourseNonPercentBase(HashMap<String, Double> dist, HashMap<String, Double> max) {
      super(dist, max);
      score = 0.0;
      setRestore();
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
   public void finalCalculate(double goal)
   {
      double gradeNeeded = 0;
      double totalPercent = 0;//Will usually be 100
      double totalEarned = 0;//Sum of all grades before final
      double finalWeight = 0;//Percent contribution of final to class grade
      double maxPercent[] = new double[maxDistribution.size()]; 
         double totalPoints = 0;
         double obtainedPoints = 0;
         int i = 0;
           for (Double value : maxDistribution.values()){
              totalPoints += value;//find out how many points class is out of
              maxPercent[i] = (value/100);
              i++;
           }
           i = 0;
           for(Double value : distribution.values()){
            obtainedPoints += (value*maxPercent[i]);//find out how many points were obtained
            i++;
           }
           finalWeight = ((maxDistribution.get("Final")/totalPoints) * 100);//find out what percent of the class the final is
           totalPercent = 100 - finalWeight;//remove final weight from pre-calculation total
           totalEarned = ((obtainedPoints/totalPoints) * 100);//find total percent earned
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
           score = (Integer)(-1);
           return;
       }
       if(gradeNeeded < 0){//goal already met
         score = (Integer)0;
         return;
       }
       /*
        The calculation below makes gradeNeeded be the percent needed on the
          final, as opposed to the current value of percent of the class
          needed.
        */
       gradeNeeded *= (100 / finalWeight);
       
       score = Math.round(gradeNeeded);
   }





}
