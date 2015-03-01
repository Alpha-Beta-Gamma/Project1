package edu.csupomona.cs480.data.provider;

import java.util.List;

import edu.csupomona.cs480.data.Calculation;

public interface CalculationManager {

	public void updateCalculation(Calculation calc);
	
	public Calculation getCalculation(String calcId);
	
	public void deleteCalculation(String calcId);
	
	public List<Calculation> listAllCalculations();
}
