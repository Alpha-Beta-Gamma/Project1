package edu.csupomona.cs480;

import java.util.*;

public class Course {
   String name;
   int number;
   String professor;
   static HashMap<String, Integer> distribution;

   public Course(String name, int number, String professor) {
      this.name = name;
      this.number = number;
      this.professor = professor;
      distribution = new HashMap<String, Integer>();
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
    adds a "Source" of grade points, for example:
    To add Homework: 20%, call
    [Course instance].addSource("Homework", 20)
    */
   public void addSource(String name, int percent){
	   distribution.put(name, percent);
   }
}
