package edu.csupomona.cs480.data;

import java.util.Date;

public class School {

   /** The unique user Id */
   private String id; //randomly generated?
   private String name; //ex. "Cal Poly Pomona" 

   //private String sectionNumber; //not sure if necessary 

   /** The timestamp when the class is being created */
   private String creationTime = new Date(System.currentTimeMillis()).toString();

   public String getId() {
      return id;
   }

   public void setId(String id) {
      //must be ordered from 0 to infinity, check before adding which id you should use
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getCreationTime() {
      return creationTime;
   }

   public void setCreationTime(String creationTime) {
      this.creationTime = creationTime;
   }

}
