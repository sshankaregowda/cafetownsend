package tests;

import Cafe.Base;
import functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.lang.reflect.Method;

public class LoginTest extends Base {

    //creating log object to print the logs
    Logger log = Logger.getLogger(LoginTest.class);

    private LoginPage loginPage;

    @BeforeClass(description = "Instantiating the loginPage object")
    public void beforeClass() {
        loginPage = new LoginPage();
        commonFunctions = new CommonFunctions();

    }

    @BeforeMethod(description = "get the testMethod name")
    public void nameBefore(Method method) {
        log.info("Test name: " + method.getName());
    }

    @Test(description = "Check cafe town send login page is loaded or not")
    public void testCafeTownSendloginPageLoading() {
        //commonFunctions.waitForTitle("Welcome: Mercury Tours");//waits for the title "Welcome: Mercury Tours" which in turn confirms Mercury-Tours welcome page is loaded
        title = commonFunctions.getTitle();
        System.out.println(title);
        //Assert.assertEquals(title, "Welcome: Mercury Tours");
        log.info("Cafe town send login page is opened");

    }

    @Test(dependsOnMethods = "testCafeTownSendloginPageLoading", description = "Check Login with username and password by clicking Login button")
    public void testLogin() {
        log.info("Entering the username and password");
        commonFunctions.clearAndType(loginPage.getUsernameTxtBox(), USERNAME);//Input username, password and click Sign In button
        commonFunctions.clearAndType(loginPage.getPasswordTxtBox(), PASSWORD);
        commonFunctions.click(loginPage.getLoginBtn());
        log.info("Signing in.......");

        //commonFunctions.waitForTitle("Find a Flight: Mercury Tours:");//waits for the title "Find a Flight: Mercury Tours:" which in turn confirms Find a Flight page is loaded
       // title = commonFunctions.getTitle();
        //Assert.assertEquals(title, "Find a Flight: Mercury Tours:");
        //log.info("Flight Finder page is loaded successfully");

    }
}
