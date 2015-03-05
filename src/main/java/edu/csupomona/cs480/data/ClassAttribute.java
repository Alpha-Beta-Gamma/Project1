package edu.csupomona.cs480.data;

public class ClassAttribute {

   private String name;
   private double value;

   public ClassAttribute(String name, double value) {
      this.name = name;
      this.value = value;
   }

   public ClassAttribute() {
      name = "";
      value = 0d;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public double getValue() {
      return value;
   }

   public void setValue(double value) {
      this.value = value;
   }
}
