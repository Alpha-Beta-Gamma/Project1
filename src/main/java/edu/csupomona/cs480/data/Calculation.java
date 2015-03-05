package edu.csupomona.cs480.data;

import java.util.HashMap;

public class Calculation {

   private HashMap<String, Double> totalScores; //hashmap: string is for the type like
                                                //midterms..  the double is maximum value
   private String id; //this should be the id of the class to link the calculator to the class

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public void addGrade(String scoreType, double score) {
      totalScores.put(scoreType, score);
   }
}
