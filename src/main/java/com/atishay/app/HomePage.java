package com.atishay.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by Atishay on 6/11/2015.
 */
public class HomePage extends AbstractPage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getUniqueElement() {
        return By.cssSelector(".rghtSec.fr.menu.logged > li:nth-child(2)");
    }

    By LogoutIconCss = By.cssSelector(".rghtSec.fr.menu.logged > li:nth-child(2)");
    By LogoutButtonCss = By.cssSelector(".logout");

    public void hoverAndClickLogoutButton(){
        Actions actions = new Actions(driver);
        actions.clickAndHold(driver.findElement(LogoutIconCss)).perform();
        driver.findElement(LogoutButtonCss).click();
    }
}
