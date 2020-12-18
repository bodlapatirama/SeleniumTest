package com.project.teststeps;

import cucumber.api.Scenario;
import cucumber.api.java.After;


public class CucumberHooks {

    @After 
    public void cleanUp(Scenario sScenario) throws Exception{ 
    	
    	 /* String sStatus = sScenario.getStatus();
    	 
    	String sTCname = sScenario.getName();
    	String sFeatureName = sScenario.getId().split(";")[0].replace("-", " ");

    	if (sStatus.equalsIgnoreCase("failed")){   		
    		
    	}    
	    
    	String sDirectory = System.getProperty("user.dir");
    	String sDriverPath = sDirectory+"\\src\\test\\resources\\Data";
		String sExcelPath = sDirectory+"\\src\\test\\resources\\Data\\Mail.xlsm";
    	  
	    int iScenarioCount = ExcelUtils.SetCellDataXlsm(sFeatureName,sTCname,sStatus,sExcelPath);

    	if (iScenarioCount % 20 == 0)
    		Runtime.getRuntime().exec(new String[] { "wscript.exe", sDriverPath+"\\Trigger.vbs",sExcelPath});*/ 
 
    }
   
}
