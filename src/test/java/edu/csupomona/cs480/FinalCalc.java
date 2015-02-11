package edu.csupomona.cs480;

import java.util.*;

public class FinalCalc {
	public FinalCalc(){/*Utility only class*/}
	
	
	/*
	 In this method, the output is the grade needed on the final
	   that would cause the entire class grade to reach goal.  It reads
	   the percentages given to it by distribution, and the percentages
	   obtained by the user in the class in obtained, and then calculates
	   what percent of the final grade needs to be earned to reach the
	   goal percentage.  Calculate will return -1 if the goal is not
	   reachable given a max grade on the final.
	   * Note: obtained should not include a final entry, as this is what
	   * is being calculated.  So if distribution contained 5 elements,
	   * obtained would contain 4, as it would not include final.
	 */
	public static double Calculate(HashMap<String, Integer> distribution,
	                        HashMap<String, Integer> obtained, int goal)
	{
	    double gradeNeeded = 0;
	    double totalPercent = 0;//Will usually be 100
	    double totalEarned = 0;//Sum of all grades before final
	    double finalWeight = 0;//Percent contribution of final to class grade
	    for(int value : distribution.values()){
	        totalPercent += value;
	    }
	    /*
	     We will now remove final from the total percent calculation, as
	       this is what is being calculated.
	     */
	    totalPercent -= distribution.get("Final");
	    System.out.println("Total Percent: " + totalPercent);
	    for(int value : obtained.values()){
	        totalEarned += value;
	    }
	    finalWeight = distribution.get("Final");
	    System.out.println("Final Weight: " + finalWeight);
	    System.out.println("Earned: " + totalEarned);
	    System.out.println("Goal: " + goal);
	    /*
	     Here, the total - goal is subtracted from the total percent to lower
	       the grade needed on the final.  Without this offset, the grade
	       needed on the final would be the grade needed to get a perfect
	       score in the class, and would therefore be very difficult to obtain.
	       * An example: Total class percent = 100% : goal percent = 75%
	       * total earned = 70%.  The calculation would yield
	       * 100% - (100% - 75%) - 70% = 100% - 25% - 70% = 5%
	       * so the method would return 5, as that is the percent needed
	       * of the final to receive the goal percent.  It will then calculate
	       * what percent that will be for the final.
	     */
	    gradeNeeded = (totalPercent - (totalPercent - goal)) - totalEarned;
	    if(gradeNeeded > finalWeight){//goal unobtainable
	        return -1;
	    }
	    /*
	     The calculation below makes gradeNeeded be the percent needed on the
	       final, as opposed to the current value of percent of the class
	       needed.
	     */
	    gradeNeeded *= (100 / finalWeight);
	    
	    return gradeNeeded;
	}
}
