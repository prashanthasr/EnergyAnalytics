package com.ge.predix.analytics;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EnergyGenEndPoint {
	Logger logger = LoggerFactory.getLogger(EnergyGenEndPoint.class);
	
	ObjectMapper mapper = new ObjectMapper();
	
	public String calcEnergy(String jsonInput, Map<String, byte[]> inputModels) throws Exception
	{
		if(inputModels==null)
		{
			throw new Exception("Input model is null");
		}
		
		byte[] powerBytes = inputModels.get("powerInWatts");
		
		if(powerBytes == null)
		{
			throw new Exception("Input model is power key variable is not available");
		}
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> jsonDataMap = mapper.readValue(jsonInput, HashMap.class);
		String assetId = jsonDataMap.get("assetid");
		

		long power = Long.parseLong(powerBytes.toString());
		
		/*if(!inputModels.containsKey("timeStamp"))
		{
			throw new Exception("Input model is Time key variable is not available");
		}*/
		
				
		
		AdderResponse output = null;
		output = new AdderResponse();
		
		output.setResult(power*60);
		
		//mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		return mapper.writeValueAsString(output);
	}
	
}
