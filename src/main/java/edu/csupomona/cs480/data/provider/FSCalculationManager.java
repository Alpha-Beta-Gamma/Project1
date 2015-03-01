package edu.csupomona.cs480.data.provider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.csupomona.cs480.data.Calculation;
import edu.csupomona.cs480.data.CalculationMap;
import edu.csupomona.cs480.util.ResourceResolver;

public class FSCalculationManager implements CalculationManager{

	private static final ObjectMapper JSON = new ObjectMapper();
	private Object CalculationMap;
	
	private CalculationMap getCalculationMap() {
		CalculationMap calcMap = null;
		File calcFile = ResourceResolver.getCalcFile();
		 if (calcFile.exists()) {
	         try {
	            calcMap = JSON.readValue(calcFile, CalculationMap.class);
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      } else {
	         calcMap = new CalculationMap();
	      }
	      return calcMap;
	   }
   private void persistCalculationMap(CalculationMap calcMap) {
      try {
         // convert the user object to JSON format
         JSON.writeValue(ResourceResolver.getCalcFile(), CalculationMap);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
	@Override
	public void updateCalculation(Calculation calc) {
		CalculationMap CalculationMap = getCalculationMap();
		CalculationMap.put(calc.getId(), calc);
		persistCalculationMap(CalculationMap);
	}
	@Override
	public Calculation getCalculation(String calcId) {
		CalculationMap CalculationMap = getCalculationMap();
		return CalculationMap.get(calcId);
	}
	@Override
	public void deleteCalculation(String calcId) {
		CalculationMap CalculationMap = getCalculationMap();
      CalculationMap.remove(calcId);
      persistCalculationMap(CalculationMap);
		
	}
	@Override
	public List<Calculation> listAllCalculations() {
		CalculationMap CalculationMap = getCalculationMap();
		return new ArrayList<Calculation>(CalculationMap.values());
	}
   
}
