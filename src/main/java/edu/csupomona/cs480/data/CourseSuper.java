package edu.csupomona.cs480.data;

import java.util.HashMap;

public abstract class CourseSuper {
   static HashMap<String, Double> distribution;
   static HashMap<String, Double> maxDistribution;
   HashMap originalDistribution;
   HashMap originalMaxDistribution;
   
   public CourseSuper(HashMap<String, Double> obtained, HashMap<String, Double> max){
      distribution = obtained;
      maxDistribution = max;
   }
   public void setRestore(){
      originalDistribution = distribution;
      originalMaxDistribution = maxDistribution;
  }
  
  public void restore(){  
          distribution = originalDistribution;
          maxDistribution = originalMaxDistribution;
  }
   public abstract void finalCalculate(double goal);
   
   public String toString() {
      String strungClass = new String(distribution.toString());
      return strungClass;
   }
}
