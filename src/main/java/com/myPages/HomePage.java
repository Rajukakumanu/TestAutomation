package com.myPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class HomePage extends BasePage{
private By registration =By.xpath("//input[@placeholder='Enter Registration']");
private By freeCarCheck= By.xpath("//button[contains(text(),'Free Car Check')]");
private By vehicleIdentity = By.xpath("//h4[text()='Vehicle Identity']//parent::div//following-sibling::div//dl");

	public HomePage(WebDriver driver) {
		
		super(driver);
	}
	
	public String getHomePageTitle()
	{
		return getPageTitle();
	}

	private void enterRegistration(String registrationNum)
	{
		enterText(registration,registrationNum);
	}

	private void clickFreeCarCheck()
	{
		clickElement(freeCarCheck);
	}

	private int getVehicleIdentityDetailsCount()
	{
          return getElements(vehicleIdentity).size();
	}

	private LinkedHashMap<String, String> addCarDetails()
	{
		LinkedHashMap<String, String>  map= new LinkedHashMap<>();
		int getVehicleIdentityDetailsCount = getVehicleIdentityDetailsCount();
		for(int i=1;i<=getVehicleIdentityDetailsCount;i++)
		{
			By locator =By.xpath("//h4[text()='Vehicle Identity']//parent::div//following-sibling::div//dl["+i+"]//dt");
            waitForElementPresent(locator);
			By title= By.xpath("//h4[text()='Vehicle Identity']//parent::div//following-sibling::div//dl["+i+"]//dt");
			By value= By.xpath("//h4[text()='Vehicle Identity']//parent::div//following-sibling::div//dl["+i+"]//dd");
			String getTitle= getElement(title).getText();
			String getValue= getElement(value).getText();
			map.put(getTitle, getValue);
		}
		return map;
	}

	private LinkedHashMap<String, String> getCarDetails(String url, String registrationNum)
	{
		loadUrl(url);
		enterRegistration(registrationNum);
		clickFreeCarCheck();
		return addCarDetails();
	}

	public ArrayList<String> getCarDetailsGUI(String url, String registrationNum) throws InterruptedException {
		LinkedHashMap<String, String> carDetails= getCarDetails(url, registrationNum);
		Set<String> set= carDetails.keySet();
		ArrayList<String> list= new ArrayList<>();
		for(String s: set)
		{
			list.add(carDetails.get(s));
		}
		return list;
	}


}
