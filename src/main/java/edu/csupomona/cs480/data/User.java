package edu.csupomona.cs480.data;

import java.util.Date;

/**
 * The basic user object.
 */
public class User {

   /** The unique user Id */
   private String id;
   private String name;
   private String school;
   private String major;

   /** The timestamp when the user is being created */
   private String creationTime = new Date(System.currentTimeMillis()).toString();

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSchool() {
      return school;
   }

   public void setSchool(String school) {
      this.school = school;
   }

   public String getMajor() {
      return major;
   }

   public void setMajor(String major) {
      this.major = major;
   }

   public String getCreationTime() {
      return creationTime;
   }

   public void setCreationTime(String creationTime) {
      this.creationTime = creationTime;
   }
}
