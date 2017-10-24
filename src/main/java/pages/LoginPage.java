package pages;

import org.openqa.selenium.By;

/**
 * File Name                     Created by             Date
 * LoginPage.java		           Shruthi		 	    24/10/2017
 * <p>
 * <p>
 * This LoginPage.java contains page objects of LoginPage and the getters for those page objects
 */

public class LoginPage {
    //WebElements in Login page
    private By usernameTxtBox = By.xpath(".//input[@class='ng-dirty ng-valid ng-valid-required']");
    private By passwordTxtBox = By.xpath(".//input[@class='ng-pristine ng-valid ng-valid-required']");
    private By loginBtn = By.xpath(".//button[@class='main-button']");

    public By getUsernameTxtBox() {
        return usernameTxtBox;
    }

    public By getPasswordTxtBox() {
        return passwordTxtBox;
    }

    public By getLoginBtn() {
        return loginBtn;
    }
}
