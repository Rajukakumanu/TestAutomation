package com.myPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BasePage extends Page {

	public BasePage(WebDriver driver) {
		super(driver);

	}

	@Override
	public String getPageHeader(By locator) {

		return getElement(locator).getText();
	}

	@Override
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			waitForElementPresent(locator);
			element = driver.findElement(locator);
			return element;
		} catch (Exception e) {
			System.out.println("Some error has occured while creating element :" + element.toString());
			e.printStackTrace();
		}

		return element;
	}

	@Override
	public List<WebElement> getElements(By locator) {
		List<WebElement> element = null;
		try {
			waitForElementPresent(locator);
			element = driver.findElements(locator);
			return element;
		} catch (Exception e) {
			System.out.println("Some error has occured while searching elements :" + element.toString());
			e.printStackTrace();
		}

		return element;
	}

	@Override
	public void waitForElementPresent(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			System.out.println("Some exception has occured while waiting for element :" + locator.toString());
		}

	}

	@Override
	public void waitForElementClickable(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			System.out.println("Some exception has occured while waiting for element :" + locator.toString());
		}
	}

	@Override
	public void waitForTitle(String title) {

		try {
			wait.until(ExpectedConditions.titleContains(title));
		} catch (Exception e) {
			System.out.println("Some exception has occured while waiting for title :" + title);
		}

	}

	@Override
	public void enterText(By locator, String value) {
		waitForElementClickable(locator);
		getElement(locator).click();
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}

	@Override
	public void clickElement(By locator) {
		waitForElementClickable(locator);
		getElement(locator).click();
	}

	@Override
	public void loadUrl(String url) {
		driver.get(url);
	}

	@Override
	public String getPageTitle() {
		return driver.getTitle();
	}

}
