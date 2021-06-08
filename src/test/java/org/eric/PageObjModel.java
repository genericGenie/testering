package org.eric;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import java.util.List;

public class PageObjModel {
    protected WebDriver driver;
    public PageObjModel(WebDriver wd) {
        driver = wd;
    }

    public void navigate(String url) {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public WebElement waitUntilElementClickable(int seconds, By elem) {
        return new WebDriverWait(driver, seconds)
				.until(ExpectedConditions.elementToBeClickable(elem));
    }

    public void waitUntilPageLoad(int seconds) {
        WebDriverWait waitPageLoad = new WebDriverWait(driver, seconds);
        waitPageLoad.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState !== 'loading'"));
    }

    public void pressEnterKeyAfterTypingStringInElement(By path, String text) {
        WebElement we = driver.findElement(path);
        we.sendKeys(text);
        we.sendKeys(Keys.ENTER);
    }

    public void validateWebElementExist(WebElement we) {
        Assert.assertTrue(we.isDisplayed());
    }

    public void printAllURLsContainingText(String text) {
        List<WebElement> anchorList = driver.findElements(By.tagName("a"));

		anchorList.forEach(x -> {
			if (x.getText().contains(text)) {
				System.out.println(x.getAttribute("href"));
			}
		});
    }
    

}
