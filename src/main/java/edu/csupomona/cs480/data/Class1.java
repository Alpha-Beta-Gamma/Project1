package edu.csupomona.cs480.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Class1 {

   /** The unique user Id */
   private String id; //is the schoolId + "_" + uniqueNumber, dont ask questions....
   private String name; //ex. "Software engineering" 
   private String school; //ex "Cal Poly Pomona"
   private String subject; //ex. CS, Art, Biology
   private String uniqueNumber; //should be unique number per school ex our class is 14403, needs to be string for some schools with letters maybe?
   private String instructor;
   private double total;
   private List<AttributeOfClass> classAttributes; //holds all the data for the class attributes

   /** The timestamp when the class is being created */
   private String creationTime = new Date(System.currentTimeMillis()).toString();

   public String getUniqueNumber() {
      return uniqueNumber;
   }

   public void setUniqueNumber(String uniqueNumber) {
      this.uniqueNumber = uniqueNumber;
   }

   public String getSubject() {
      return subject;
   }

   public void setSubject(String subject) {
      this.subject = subject;
   }

   public String getSchool() {
      return school;
   }

   public void setSchool(String school) {
      this.school = school;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getCreationTime() {
      return creationTime;
   }

   public void setCreationTime(String creationTime) {
      this.creationTime = creationTime;
   }

   public String getInstructor() {
      return instructor;
   }

   public void setInstructor(String instructor) {
      this.instructor = instructor;
   }

   public List<AttributeOfClass> getClassAttributes() {
      if (classAttributes == null) {
         classAttributes = new ArrayList<AttributeOfClass>();
      }

      return classAttributes;
   }

   public void setClassAttributes(List<AttributeOfClass> list) {
      classAttributes = list;
   }

   public void clearClassAttributes() {
      classAttributes = new ArrayList<AttributeOfClass>();
   }

   public void addClassAttributes(AttributeOfClass toAdd) {
      if (classAttributes == null) {
         classAttributes = new ArrayList<AttributeOfClass>();
      }

      classAttributes.add(toAdd);
   }

   public double getTotal() {
      return total;
   }

   public void setTotal(double total) {
      this.total = total;
   }
}
