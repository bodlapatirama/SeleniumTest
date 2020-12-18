package com.project.teststeps;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import com.pageobjects.LoginPage;

import com.project.utilities.appUtils;
import com.project.utilities.genericUtils;
import com.project.utilities.projectVariables;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

public class USTsteps extends appUtils{

	//####################################################################################################
	//  Global Variable Declarations
	//####################################################################################################	
	
	//  Instance Variable Declarations
	//####################################################################################################	

	genericUtils Generic = new genericUtils();
	LoginPage ologinpage = new LoginPage();
	projectVariables RRS_PROJ_VAR = new projectVariables();


	//####################################################################################################
	@Given("^user launch into UST application$")
	public void user_launch_into_UST_application() throws Throwable {
		ologinpage.loginUSTApp();
	}

	//####################################################################################################	


	
	}
