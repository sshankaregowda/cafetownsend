package Cafe;

import functions.CommonFunctions;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * File Name         Created by             Date
 * Base.java		 Shruthi		 	24/10/2017
 * <p>
 * <p>
 * This Base.java contains methods for all basic actions on the webpage
 */
public class Base
{
    //creating properties object to fetch the data from mercury-tours.properties file
    protected static Properties properties;

    //Login Credentials
    protected static String USERNAME;
    protected static String PASSWORD;
    protected static String LOGIN_URL;

    //WebDriver Instance
    protected static WebDriver driver;

    //Browser & OS Type
    protected static String BROWSER_TYPE;
    protected static String OPERATING_SYSTEM;

    //variable to store the page title
    protected String title;

    //Reference variable for CommonFunctions class
    protected CommonFunctions commonFunctions;

    //creating log object to print the logs
    Logger log = Logger.getLogger(Base.class);

    @BeforeSuite(description = "fetching the values from properties file and launching the Cafe Town Send URL")
    public void beforeSuite() {

        try {
            BasicConfigurator.configure();
            log.info("Loading the properties file,getting login and browser details");
            properties = new Properties();
            properties.load(new FileInputStream(filePath("cafetownsend.properties")));

            //Fetching the login and browser details from Properties file
            LOGIN_URL = properties.getProperty("LOGINURL");
            USERNAME = properties.getProperty("USERNAME");
            PASSWORD = properties.getProperty("PASSWORD");
            BROWSER_TYPE = properties.getProperty("BROWSERTYPE");
            OPERATING_SYSTEM = properties.getProperty("OPERATINGSYSTEM");

            log.info("creating the driver object to drive the specified browser type");
            driver = getWebDriver();

            log.info("Launching the cafe town send URL");
            driver.get(LOGIN_URL);
            driver.manage().window().maximize();

            //Amount of time to wait for an asynchronous script to finish execution before throwing an error
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        } catch (Exception e) {
            log.error("Exception found: ", e);
        }
    }

    //Obtaining the file path from user directory
    public String filePath(String file) throws FileNotFoundException {
        String BasePath = System.getProperty("user.dir") + "//";
        String ResFolder = BasePath + "Resources//" + file + "//";
        return ResFolder;
    }

    //Method to get driver based on browser type to be tested
    protected WebDriver getWebDriver() {
        try {

            if (OPERATING_SYSTEM.equalsIgnoreCase("mac")) {

                if (BROWSER_TYPE.equalsIgnoreCase("firefox")) {
                    System.setProperty("webdriver.gecko.driver", filePath("geckodriver"));
                    driver = new FirefoxDriver();
                } else if (BROWSER_TYPE.equalsIgnoreCase("chrome")) {
                    System.setProperty("webdriver.chrome.driver", filePath("chromedriver"));
                    driver = new ChromeDriver();
                } else {
                    System.out.println("No Browser Type provided");
                }
            } else if (OPERATING_SYSTEM.equalsIgnoreCase("windows")) {
                if (BROWSER_TYPE.equalsIgnoreCase("firefox")) {
                    System.setProperty("webdriver.gecko.driver", filePath("geckodriver.exe"));
                    driver = new FirefoxDriver();
                } else if (BROWSER_TYPE.equalsIgnoreCase("chrome")) {
                    System.setProperty("webdriver.chrome.driver", filePath("chromedriver.exe"));
                    driver = new ChromeDriver();
                } else {
                    System.out.println("No Browser Type provided");
                }
            } else {
                System.out.println("No operating system provided");

            }
        } catch (Exception e) {
            log.error("Exception found: ", e);
        }
        return driver;
    }

    @AfterSuite(description = "Quit the browser")
    public void afterSuite() {
        driver.quit();
    }}
