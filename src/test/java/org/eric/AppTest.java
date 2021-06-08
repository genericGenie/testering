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
	}

	@AfterTest
	public void cleanup() {
		wd.quit();
	}


	@Test(priority = 0)
	public void ChallengeTest1() throws InterruptedException {
		// System.setProperty("webdriver.chrome.driver", "/src/test/java/org/eric");

		wd.get(BasicRepository.googleURLText);
		wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		WebElement search = wd.findElement(BasicRepository.googleTextBox);
		search.sendKeys(BasicRepository.spaceLabsSoundText);
		search.sendKeys(Keys.ENTER);

		WebElement webelem = new WebDriverWait(wd, 5)
				.until(ExpectedConditions.elementToBeClickable(BasicRepository.spaceLabsGoogleLink));

		Assert.assertTrue(webelem.isDisplayed());

	}

	@Test(priority = 1)
	public void ChallengeTest2() {
		wd.get(BasicRepository.googleURLText);
		wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		WebElement search = wd.findElement(BasicRepository.googleTextBox);
		search.sendKeys(BasicRepository.spaceLabsSoundText);
		search.sendKeys(Keys.ENTER);
		WebDriverWait waitPageLoad = new WebDriverWait(wd, 10);
        waitPageLoad.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState !== 'loading'"));

		List<WebElement> anchorList = wd.findElements(By.tagName("a"));

		anchorList.forEach(x -> {
			if (x.getText().contains(BasicRepository.safeNSoundText)) {
				System.out.println(x.getAttribute("href"));
			}
		});
	}
}
