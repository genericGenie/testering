package org.eric;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {

	public WebDriver wd;
	public PageObjModel pom;

	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().browserVersion("91.0.4472.19").setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("enable-automation");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-browser-side-navigation");
		wd = new ChromeDriver(options);
		pom = new PageObjModel(wd);
	}

	@AfterTest
	public void cleanup() {
		wd.quit();
	}

	@Test(priority = 0)
	public void ChallengeTest1() throws InterruptedException {
		pom.navigate(BasicRepository.googleURLText);
		pom.waitUntilPageLoad(2);
		// wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		pom.pressEnterKeyAfterTypingStringInElement(BasicRepository.googleTextBox, BasicRepository.spaceLabsSoundText);
		// WebElement search = wd.findElement(BasicRepository.googleTextBox);
		// search.sendKeys(BasicRepository.spaceLabsSoundText);
		// search.sendKeys(Keys.ENTER);
		WebElement webElem = pom.waitUntilElementClickable(5, BasicRepository.spaceLabsGoogleLink);
		// WebElement webelem = new WebDriverWait(wd, 5)
		// 		.until(ExpectedConditions.elementToBeClickable(BasicRepository.spaceLabsGoogleLink));
		pom.validateWebElementExist(webElem);
		//Assert.assertTrue(webelem.isDisplayed());

	}

	@Test(priority = 1)
	public void ChallengeTest2() {
		pom.navigate(BasicRepository.googleURLText);
		pom.waitUntilPageLoad(2);
		pom.pressEnterKeyAfterTypingStringInElement(BasicRepository.googleTextBox, BasicRepository.spaceLabsSoundText);
		pom.waitUntilPageLoad(2);
		pom.printAllURLsContainingText(BasicRepository.safeNSoundText);
		// wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// WebElement search = wd.findElement(BasicRepository.googleTextBox);
		// search.sendKeys(BasicRepository.spaceLabsSoundText);
		// search.sendKeys(Keys.ENTER);
		// WebDriverWait waitPageLoad = new WebDriverWait(wd, 10);
		// waitPageLoad.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState !== 'loading'"));

		// List<WebElement> anchorList = wd.findElements(By.tagName("a"));

		// anchorList.forEach(x -> {
		// 	if (x.getText().contains(BasicRepository.safeNSoundText)) {
		// 		System.out.println(x.getAttribute("href"));
		// 	}
		// });
	}
}
