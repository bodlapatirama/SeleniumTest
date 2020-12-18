package com.project.utilities;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;


public class genericUtils {


	public static void highlightElement(WebDriver driver, WebElement element) {
		try {
			for (int i = 0; i < 10; i++) 
			{
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "color: green; border: 2px solid green;");
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");      
			}
		}catch (Exception e) {
			System.err.println(e);
			driver.close();
			driver.quit();
		}
	}

	//####################################################################################################
	
	public static boolean isPresent(WebDriver driver, String webElName, String method) {
		List<WebElement> appButtons = new ArrayList<WebElement>();
		try {
			if (method.equals("name")) {
				appButtons = driver.findElements(By.name(webElName));
			} else if (method.equals("xpath")) {
				appButtons = driver.findElements(By.xpath(webElName));
			} else if (method.equals("id")) {
				appButtons = driver.findElements(By.id(webElName));
			} else if (method.equals("css")) {
				appButtons = driver.findElements(By.cssSelector(webElName));
			} else if (method.equals("class")) {
				appButtons = driver.findElements(By.className(webElName));
			} else if (method.equals("link")) {
				appButtons = driver.findElements(By.linkText(webElName));
			} else if (method.equals("partialLink")) {
				appButtons = driver.findElements(By.partialLinkText(webElName));
			} else if (method.equals("tag")) {
				appButtons = driver.findElements(By.tagName(webElName));
			} 

			if (appButtons.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Element not present: " + webElName);
			System.err.println(e);
			driver.close();
			return false;
		}
	}   

	//####################################################################################################
	
	public static boolean gfn_Click_On_Object(WebDriver driver,String sTagName, String sText) throws InterruptedException{

		boolean blnFlag = false;
		int iTimer = 0;
		String strXpath = "//"+sTagName+"[text()= '"+sText+"']";

		try{  
			do {

				List<WebElement> sList = driver.findElements(By.xpath(strXpath));
				
				if (sList.size() > 0) {
					for (int i=0;i < sList.size();i++){
						if (sList.get(i).isDisplayed() && sList.get(i).isEnabled()){
							highlightElement(driver,sList.get(i));	
							sList.get(i).click();           	  
							blnFlag = true;  
							Thread.sleep(projectVariables.MIN_TIME_OUT);             
							gfn_ReportEvent("Clicked on "+sText+" Object Successfully ","Passed");
							break;
						}
					}
				}

				Thread.sleep(projectVariables.MIN_TIME_OUT);
				iTimer = iTimer+1;  
			} while ((blnFlag != true) && (iTimer != 15));

			if ( blnFlag != true) {
				System.out.println(sText+" object not found");
				blnFlag = false ;
			}

		}    catch(Exception e){	   			
			System.err.println(e); 
			driver.quit(); 
			gfn_ReportEvent(sText+" not clicked Successfully","Failed" ); 
		} 

		return blnFlag;

	}

	//####################################################################################################
	
	public static boolean gfn_Verify_Object_Exist(WebDriver driver,String sTagName, String sText) throws InterruptedException{

		boolean blnFlag = false;
		int iTimer = 0;
		String strXpath = "//"+sTagName+"[text()='"+sText+"']";

		try{  
			do {

				List<WebElement> sList = driver.findElements(By.xpath(strXpath));

				if (sList.size() > 0){
					for (int i=0;i < sList.size();i++){
						if (sList.get(i).isDisplayed()){
							highlightElement(driver,sList.get(i));           	      	  
							blnFlag = true;  
							Thread.sleep(projectVariables.MIN_TIME_OUT);             
							gfn_ReportEvent(sText+" is displayed Successfully ","Passed");
						}
					}
				}

				Thread.sleep(projectVariables.MIN_TIME_OUT);
				iTimer = iTimer+1;  

			} while ((blnFlag != true) && (iTimer != 10));

			if ( blnFlag != true) {
				System.out.println(sText+" object not found");
				blnFlag = false ;
			}

		}    catch(Exception e){	   			
			System.err.println(e); 
			driver.quit(); 
			gfn_ReportEvent(sText+" not displayed","Failed" ); 
		} 

		return blnFlag;

	}

	//####################################################################################################
	
	public static boolean gfn_click_on_object_by_Xpath(WebDriver driver, String sXpath){

		boolean blnFlag = false;
		int iTimer = 0;

		try{  
			do {

				List<WebElement> sList = driver.findElements(By.xpath(sXpath));
				
				if (sList.size() > 0) {
					for (int i=0;i < sList.size();i++){
						if (sList.get(i).isDisplayed() && sList.get(i).isEnabled()){
							highlightElement(driver,sList.get(i));	
							sList.get(i).click();           	  
							blnFlag = true;  
							Thread.sleep(projectVariables.MIN_TIME_OUT);             
							gfn_ReportEvent("Clicked on the Object Successfully ","Passed");
							break;
						}
					}
				}

				Thread.sleep(projectVariables.MIN_TIME_OUT);
				iTimer = iTimer+1;  
			} while ((blnFlag != true) && (iTimer != 15));

			if ( blnFlag != true) {	          
				System.out.println("object not found");
				blnFlag = false ;
			}

		}    catch(Exception e){			 			  
			System.err.println(e); 
			driver.quit();  
			gfn_ReportEvent("Object not clicked Successfully", "Failed" );
		} 

		return blnFlag;

	}

	//####################################################################################################
	
	public static boolean gfn_Verify_object_by_Xpath(WebDriver driver, String sXpath , String sStep){

		boolean blnFlag = false;
		int iTimer = 0; 
		try{  
			do {

				List<WebElement> sList = driver.findElements(By.xpath(sXpath));

				if (sList.size() > 0){
					for (int i=0;i < sList.size();i++){
						if (sList.get(i).isDisplayed()){
							highlightElement(driver,sList.get(i));           	      	  
							blnFlag = true;  
							Thread.sleep(projectVariables.MIN_TIME_OUT);             
							gfn_ReportEvent(sStep+" is displayed Successfully ","Passed");
						}
					}
				}
				Thread.sleep(projectVariables.MIN_TIME_OUT); 
				iTimer = iTimer+1; 		  
			} while ((blnFlag != true) && (iTimer != 30));

			if ( blnFlag != true) {	          
				System.out.println(sStep+" not found");
				blnFlag = false ;
			}

		}   catch(Exception e){  	   
			System.err.println(e); 
			driver.quit();  
			gfn_ReportEvent(sStep+" not Displayed" , "Failed"); 
		} 

		return blnFlag;

	}

	//####################################################################################################
	
	public static boolean gfn_SetVal(WebDriver driver, String sXpath , String sVal){

		boolean blnFlag = false;
		int iTimer = 0; 
		try{  
			do {

				if (driver.findElement(By.xpath(sXpath)).isDisplayed()){
					highlightElement(driver,driver.findElement(By.xpath(sXpath))); 
					if ( !((sVal == "") || sVal.isEmpty()) ){
						driver.findElement(By.xpath(sXpath)).clear();
						driver.findElement(By.xpath(sXpath)).sendKeys(sVal);}
					else{
						driver.findElement(By.xpath(sXpath)).clear();
					}       		  
					blnFlag = true;  
					Thread.sleep(projectVariables.MIN_TIME_OUT);                 
					gfn_ReportEvent(sVal+" is Entered Successfully ","Passed");
				}
				iTimer = iTimer+1; 
			} while ((blnFlag != true) && (iTimer != 30));

			if ( blnFlag != true) {	          
				System.out.println(sVal+" not Entered");
				blnFlag = false ;
			}

		}    catch(Exception e){  	   
			System.err.println(e); 
			driver.quit();  
			gfn_ReportEvent(sVal+" not Entered" , "Failed"); 
		} 

		return blnFlag;

	}

	//####################################################################################################
	
	public static WebDriver gfn_Switch_Browser(WebDriver driver , String strTitle){
		
		WebDriver oDriver = null;
		
		Set<String> NoofTabs = driver.getWindowHandles();	           

		for(String winHandle : NoofTabs){	    	  
			oDriver = driver.switchTo().window(winHandle);
			String sTitle = oDriver.getTitle();
			if (sTitle.trim().equalsIgnoreCase(strTitle.trim())){
				break;
			}
		} 
		return oDriver;
	}

	//####################################################################################################
	
		public static int  gfn_Get_Window_Count(WebDriver driver){
			
			WebDriver oDriver = null;
			
			String currentWindow = driver.getWindowHandle(); 
			Set<String> NoofTabs = driver.getWindowHandles();	           
			int iOpenedBrowsers = NoofTabs.size();
			for(String winHandle : NoofTabs){	   
				oDriver = driver.switchTo().window(winHandle);
				String sTitle = oDriver.getTitle();
				gfn_ReportEvent("Opened Browser title is "+sTitle,"Passed" );
			} 
			
			driver.switchTo().window(currentWindow); 
			
			return iOpenedBrowsers;
		}
		
	//####################################################################################################
	
	public static void gfn_ReportEvent(String sStepDetails,String sStatus){

		if (sStatus.equalsIgnoreCase("Passed")){			  
			System.out.println(sStepDetails); 				
			Assert.assertTrue(sStepDetails, true);
		}else if (sStatus.equalsIgnoreCase("Failed")) {	  
			System.out.println(sStepDetails); 			  
			Assert.assertTrue(sStepDetails, false);
		}	 
	}

	//####################################################################################################
	
	public static boolean gfn_SelectValueFromList(WebDriver driver,WebElement element, String sSelectVal) throws Exception {

		boolean blnFlag = false;
		try{  

			Select dropDown = new Select(element);
			List<WebElement> options = dropDown.getOptions();

			for (int i = 0; i < options.size(); i++) {
				String sText = options.get(i).getText();
				if (sSelectVal.equalsIgnoreCase(sText)){
					dropDown.selectByVisibleText(sSelectVal);
					blnFlag = true;
					break;
				}
			}

			if ( blnFlag != true) {	          
				System.out.println(sSelectVal+" not found in the list");
				blnFlag = false ;
			}

		}catch(Exception e){    	      
			System.err.println(e); 
			driver.quit(); 
			gfn_ReportEvent(sSelectVal+" not Displayed","Failed" );
		} 		
		return blnFlag;
	} 

	//####################################################################################################
	
	public static String gfn_GetValuefromList(WebDriver driver,WebElement Element) throws Exception{
		String sText = null;
		try{  

			Select dropDown = new Select(Element);
			List<WebElement> options = dropDown.getOptions();
			sText = options.get(0).getText();

		}catch(Exception e){    	      
			System.err.println(e); 
			driver.quit(); 
			gfn_ReportEvent("List box is not Displayed","Failed" );
		} 		
		return sText;
	} 

	//####################################################################################################
	
	public static String gfn_decode(String value) throws Exception {
		byte[] decodedValue = Base64.getDecoder().decode(value); 
		return new String(decodedValue, StandardCharsets.UTF_8);
	}

	//####################################################################################################
	
	public static String gfn_GENERATE_RANDOM_NUMBER(int CharLength){

		return String.valueOf(CharLength < 1 ? 0 : new Random().nextInt((9 * (int) Math.pow(10, CharLength - 1)) - 1)+ (int) Math.pow(10, CharLength - 1));
	}

	//####################################################################################################
	
	public static String fn_CHECK_SORT_ORDER(ArrayList<String> sArray,String sOrder) throws Exception{
		boolean blnStatus = true;
		String sStatus = null;
		try{

			switch  (sOrder.toUpperCase()){
	
				case "ASCENDING":
					  for (int l = 0;l<sArray.size()-1;l++){
					    	String str1 = sArray.get(l);
					    	String str2 = sArray.get(l+1);
					    	if (str1.compareToIgnoreCase(str2) > 0){
					    		blnStatus = false;
					    		genericUtils.gfn_ReportEvent(str1+" is not smaller than "+str2,"Failed");
					    		break;
					    	}
					    	genericUtils.gfn_ReportEvent(str1+" is smaller than "+str2,"Passed");
					    }
					  break;
					  
				case "DESCENDING":
					  for (int l = 0;l<sArray.size()-1;l++){
					    	String str1 = sArray.get(l);
					    	String str2 = sArray.get(l+1);
					    	if (str1.compareToIgnoreCase(str2) < 0){
					    		blnStatus = false;
					    		genericUtils.gfn_ReportEvent(str1+" is not Bigger than "+str2,"Failed");
					    		break;
					    	}
					    	genericUtils.gfn_ReportEvent(str1+" is Bigger than "+str2,"Passed");
					    }
					 break;
				}
				
				if (blnStatus == false){
					genericUtils.gfn_ReportEvent("Values are not in "+sOrder,"Failed");
					sStatus ="FAIL";}
					else{
					genericUtils.gfn_ReportEvent("Values are  in "+sOrder,"Passed");
					sStatus ="PASS";
					}
				
			}catch(Exception e){    	      
				System.err.println(e);				
				gfn_ReportEvent("List box is not Displayed","Failed");
			}
		
		return sStatus; 
		
	}	
	
	//####################################################################################################
	
	public static void gfn_SelectValueFromDropdown (WebDriver driver,WebElement sDropDownList,String sValue) throws InterruptedException{
	
		try{
			
			sDropDownList.click();
			Thread.sleep(projectVariables.MIN_TIME_OUT);
			Actions builder = new Actions(driver);
			Action mouseover = builder.moveToElement(sDropDownList).build();
			mouseover.perform();			
			Action sEnterGroup = builder.sendKeys(sValue).build();
			sEnterGroup.perform();
			//Thread.sleep(projectVariables.MIN_TIME_OUT);
			String sXpath = "//div[contains(@id ,'innerListBox')][contains(@class,'expanded')]/descendant::span[text() = '"+sValue+"']";
			//List<WebElement> sLists = driver.findElements(By.xpath("//span[text() = '"+sValue+"'][@class = 'ngx-listitem-state-normal ngx-item ngx-rc-all ngx-listitem-state-selected ngx-fill-state-pressed']"));
			//String sCount = Integer.toString(sLists.size());
			//WebElement sXpath = driver.findElement(By.xpath(("(//span[text() = '"+sValue+"'][@class = 'ngx-listitem-state-normal ngx-item ngx-rc-all ngx-listitem-state-selected ngx-fill-state-pressed'])["+sCount+"]")));
			Action sClickGroup = builder.sendKeys(sXpath, Keys.ENTER).build();				
			sClickGroup.perform();		
			Thread.sleep(projectVariables.MIN_TIME_OUT);
			
		}catch(Exception e){		
			System.err.println(e);				
			gfn_ReportEvent("Value not Selected, Failed due to Excepton" +e,"Failed");
		}
}
	
	//####################################################################################################
	
	public static void gfn_Validate_MaxSize_of_Field(WebDriver driver,WebElement oField,char cToRepeat,int iMaxLength){
		
		try{
		
			String sMaxLength = StringUtils.repeat(cToRepeat,iMaxLength);
	    	String sExceededLength = sMaxLength+"B";
	    	String sInnerText = null;
	    	int sValue;
	 	
	    	oField.clear();
			oField.sendKeys(sExceededLength);
			sInnerText = oField.getAttribute("value");
	
			sValue = sInnerText.indexOf("B", 0);
			if (sValue < 0 ){
				gfn_ReportEvent("Unable to Enter more than "+iMaxLength+" Characters in Comments Field","Passed");}    	    
				else {
				driver.quit();
				gfn_ReportEvent("Able to Enter "+(iMaxLength+1)+" Characters in Comments Field","Failed");			    
			 } 
			
		}catch(Exception e){					
			driver.quit();
			gfn_ReportEvent("MaxLength Validation Failed due to Exception" +e,"Failed");
		}
		
	}

	//####################################################################################################
	
	public static boolean gfn_validate_disabled_status(WebDriver driver, String sXpath, String sExpectedStatus){
		
		boolean blnStatus = false;				
		boolean blnFlag = false;
		int iTimer = 0; 
		try{  
			do {

				List<WebElement> sList = driver.findElements(By.xpath(sXpath));
				if (sList.size() > 0){
					for (int i=0;i < sList.size();i++){
						if (sList.get(i).isDisplayed()){
							highlightElement(driver,sList.get(i)); 
							String strStatus = sList.get(i).getAttribute("disabled");
							blnFlag = true; 						
							
							switch (sExpectedStatus.toUpperCase()){
							
								case "ENABLED":
									blnStatus  = StringUtils.isBlank(strStatus);
									break;
								case"DISABLED" :
									blnStatus = strStatus.equalsIgnoreCase("true");
									break;
							}
							
							Thread.sleep(projectVariables.MIN_TIME_OUT);             
						}
					}
				}
				
				Thread.sleep(projectVariables.MIN_TIME_OUT); 
				iTimer = iTimer+1; 		  
			} while ((blnFlag != true) && (iTimer != 30));

			if ( blnFlag != true) {	          
				gfn_ReportEvent("Object is not found","Failed");
				blnFlag = false ;
			}
			
			if (blnStatus){
				genericUtils.gfn_ReportEvent("object is in "+sExpectedStatus+" Status","Passed");}
			else{
				driver.quit();
				genericUtils.gfn_ReportEvent("Object is not in "+sExpectedStatus+" Status","Failed");	
			}
			
		}   catch(Exception e){  	   
			System.err.println(e); 
			driver.quit();  
			gfn_ReportEvent("Object not Entered" , "Failed"); 
		}
		return blnFlag; 

	}
	
	//####################################################################################################
	
	public static boolean gfn_Verify_String_with_RegularExpression(String sRegEx , String sToValidate){

		boolean blnMatched= false;
		try{  
			
			Pattern sOutPattern = Pattern.compile(sRegEx);
			Matcher matcher = sOutPattern.matcher(sToValidate);
			blnMatched = matcher.lookingAt();
			genericUtils.gfn_ReportEvent("Expected Message is:"+sToValidate+" and Actual Expression is: "+sRegEx,"Passed");
		}   catch(Exception e){  	   
			System.err.println(e); 
		} 

		return blnMatched; 
	}
}