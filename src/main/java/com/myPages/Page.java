package com.myPages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
	WebDriver driver;
	WebDriverWait wait;
	
	public Page(WebDriver driver)
	{
		this.driver=driver;
		wait= new WebDriverWait(this.driver, Duration.ofSeconds(90));
	}

	public abstract String getPageHeader(By locator);
	public abstract String getPageTitle();
	public abstract WebElement getElement(By locator);
	public abstract List<WebElement> getElements(By locator);
	public abstract void waitForElementPresent(By locator);
	public abstract void waitForElementClickable(By locator);
	public abstract void waitForTitle(String title);
	public abstract void enterText(By locator, String value);
	public abstract void clickElement(By locator);
	public abstract void loadUrl(String url);
	public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass)
	{
		try {
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return null;
	}
}
