package edu.csupomona.cs480.data;

/**
 * The basic Attribute object.
 * Used for storing String Double pairs. Ex. Test1 50.0
 */
public class AttributeOfClass {

   private String name; //name of assignment/test
   private double value; //value of assignment relative to amount the class is worth

   public AttributeOfClass(String name, double value) {
      this.name = name;
      this.value = value;
   }

   public AttributeOfClass() {
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
