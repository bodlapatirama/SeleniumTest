package com.project.driver;

import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Features",
		glue ={"com.project.teststeps"},
		tags = {"@QASmoke"},
		plugin = {"html:target/cucumber-html-report","json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
				"usage:target/cucumber-usage.json","junit:target/cucumber-results.xml"}
	)
public class TestRunner {
	
	@BeforeClass
	public static void killExcel() throws Exception{		
		Runtime.getRuntime().exec("taskkill /F /IM EXCEL.EXE");
	  
	}

    @AfterClass
    public static void fn_SendMail() throws IOException{
 	
    }
}