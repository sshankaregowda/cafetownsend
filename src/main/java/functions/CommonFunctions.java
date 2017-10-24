package functions;

import Cafe.Base;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * File Name                     Created by             Date
 * CommonFunctions.java		      Shruthi		 	    24/10/2017
 * <p>
 * <p>
 * This CommonFunctions.java contains methods which perform click(),getText() etc.....These functions are used more frequently hence its declared at one place and accessed as many times as we want
 */

public class CommonFunctions extends Base{

    //creating log object to print the logs
    Logger log = Logger.getLogger(CommonFunctions.class);

    //create webelement instance
    WebElement element;

    //Method used to send the keys - sendkeys()
    public void clearAndType(By by, String text) {
        try {
            waitElementPresent(by);
            element = driver.findElement(by);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            log.error("Exception Found - ", e);
        }
    }

    //Method used to click - click()
    public void click(By by) {
        try {
            waitElementPresent(by);
            driver.findElement(by).click();
        } catch (Exception e) {
            log.error("Exception Found - ", e);
        }

    }

    //Method to wait until element is present for specific period of time
    public void waitElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            log.error("Exception Found - ", e);
        }
    }

    //Method to get the text from the element
    public String getText(By by) {
        return driver.findElement(by).getText();

    }

    //Method to select the element from dropdown
    public void selectDropDown(By by, String visibleText) {
        try {
            waitElementPresent(by);
            new Select(driver.findElement(by)).selectByValue(visibleText);
        } catch (Exception e) {
            log.error("Exception Found - ", e);

        }

    }

    //Method to wait for the page title
    public void waitForTitle(String title) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.titleContains(title));
        } catch (Exception e) {
            log.error("Exception Found - ", e);

        }
    }

    //Method to get the page title
    public String getTitle() {
        return driver.getTitle();
    }


}
