package edu.csupomona.cs480;

import java.util.HashMap;

public abstract class CourseSuper {
   static HashMap<String, Double> distribution;
   HashMap originalDistribution;
   
   public CourseSuper(HashMap<String, Double> dist){
      distribution = dist;
   }
   public void setRestore(){
      originalDistribution = distribution;
  }
  
  public void restore(){  
          distribution = originalDistribution;
  }
   public abstract double finalCalculate(HashMap<String, Double> obtained, double goal);
   
   public String toString() {
      String strungClass = new String(distribution.toString());
      return strungClass;
   }
}
