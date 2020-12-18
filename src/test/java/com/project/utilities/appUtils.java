package com.project.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.project.teststeps.USTsteps;

public class appUtils {


	//####################################################################################################
	//  Instance Variables Initialization
	//####################################################################################################

	public static WebDriver driver;
	genericUtils Generic = new genericUtils();
	projectVariables RRS_PROJ_VAR = new projectVariables();
	
	public static Map<String, String> oRelationshipData = new HashMap<String, String>();

	//####################################################################################################	

	public void loginUSTApp()  throws Throwable {


		System.setProperty("webdriver.chrome.driver","./Browsers/chromedriver.exe" );
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("ignoreProtectedModeSettings",true);                
		driver = new ChromeDriver(capabilities);         
		driver.manage().window().maximize(); 	
		driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(RRS_PROJ_VAR.URL);

		try{    
		    
				
		}  catch(Exception e){
			driver.quit();
			genericUtils.gfn_ReportEvent("Login not Successful ,Failed due to Exception "+e,"Failed");
		} 


	}

	//####################################################################################################	
	
	public void fn_NAVIGATION(String sTab ,String slink ) throws InterruptedException{

		boolean blnTabClick =genericUtils.gfn_Click_On_Object(driver,"span", sTab);
		if (!blnTabClick){	
			driver.quit();
			genericUtils.gfn_ReportEvent(sTab+" Tab not clicked Successfully","Failed");			
		}

		boolean blnClick =genericUtils.gfn_Click_On_Object(driver,"a", slink);
		if (!blnClick){	
			driver.quit();
			genericUtils.gfn_ReportEvent(slink+" Not clicked succesfully","Failed");			
		}

		fn_Loading(driver);

	}



	//####################################################################################################	
	
	public static WebDriver getDriver() {
		return driver;
	}

	//####################################################################################################	
	
	public static void setDriver(WebDriver driver) {
		appUtils.driver = driver;
	}

	//####################################################################################################	
	
	public void fn_Loading(WebDriver driver) throws InterruptedException{

		boolean blnFlag = false;
		String strXpath = "//span[contains(text(),'Please wait...')]";

		Thread.sleep(2000);
		int i=1;
		try{   
			do{
				blnFlag = false;
				if (driver.findElement(By.xpath(strXpath)).isDisplayed()){
					blnFlag = true;
				}
				i++;
				Thread.sleep(1000);
			}while ((i<=100) && (blnFlag == true));

		}   catch(Exception e){
			System.out.println("Screen Loaded Successfully" );
		} 

	}

	//####################################################################################################	
	
	
	
	//####################################################################################################
}
