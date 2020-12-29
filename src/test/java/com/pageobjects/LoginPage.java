package com.pageobjects;

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
import com.project.utilities.genericUtils;
import com.project.utilities.projectVariables;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URI;
import java.net.URL;

public class LoginPage {


	//####################################################################################################
	//  Instance Variables Initialization
	//####################################################################################################

	public static WebDriver driver;
	genericUtils Generic = new genericUtils();
	projectVariables RRS_PROJ_VAR = new projectVariables();
	
	public static Map<String, String> oRelationshipData = new HashMap<String, String>();

	//####################################################################################################	

	public void loginUSTApp()  throws Throwable {

         String nodeURL="http://40.121.146.163:4444/wd/hub";
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "chrome");
		capabilities.setCapability("browserVersion", "87.0");
		capabilities.setCapability("enableVNC", true);
		capabilities.setCapability("name", "RRR");
		RemoteWebDriver driver = new RemoteWebDriver(
		    URI.create(nodeURL).toURL(), 
		    capabilities
		);
		
		driver.get("https://www.ust-global.com/");
		System.out.println("Application Launched Sucessfully:="+driver.getTitle());
		Thread.sleep(9000);
		Thread.sleep(9000);
		Thread.sleep(9000);
		System.out.println("Test trigeered Sucessfully");
		System.out.println("Jenkins Job completed");
		

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
		LoginPage.driver = driver;
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
