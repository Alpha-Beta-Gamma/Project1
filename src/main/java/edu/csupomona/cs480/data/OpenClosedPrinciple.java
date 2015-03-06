package edu.csupomona.cs480.data;

// driver class
public class OpenClosedPrinciple {
	public static void main(String[] args){
		Rectangle r = new Rectangle(10,5);
		System.out.println(r.area());
		
		Square s = new Square(10);
		System.out.println(s.area());
	}
}

// Class Shape that is closed for modification
// but its open for extension
abstract class Shape{
	public abstract double area();
}

// Class Square
class Square extends Shape{
	private int a;
	
	public Square(int a){
		this.a = a;
	}
	
	public double area(){
		return a * a;
	}
}

// Class Rectangle
class Rectangle extends Shape{
	private int a;
	private int b;
	
	public Rectangle(int a, int b){
		this.a = a;
		this.b = b;
	}
	
	public double area(){
		return a * b;
	}
}