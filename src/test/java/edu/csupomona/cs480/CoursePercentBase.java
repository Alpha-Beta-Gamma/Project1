package edu.csupomona.cs480;

import java.util.*;
import java.lang.Math;

public class CoursePercentBase extends CourseSuper{

   public CoursePercentBase(String name, int number, String professor, HashMap<String, Double> dist) {
      super(dist);
      setRestore();
   }

   @Override
   public double finalCalculate(HashMap<String, Double> obtained, double goal) {
      double gradeNeeded = 0;
      double totalPercent = 0;//Will usually be 100
      double totalEarned = 0;//Sum of all grades before final
      double finalWeight = 0;//Percent contribution of final to class grade
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
}
