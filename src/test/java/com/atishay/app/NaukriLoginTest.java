package com.atishay.app;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Atishay on 6/7/2015.
 */
public class NaukriLoginTest extends BaseTest {
    private final String USER_NAME = "atishay_shukla2@yahoo.co.in";
    private final String PASSWORD = "9670094920";

    @Test
    public void SuccessfulLogin(){
        NaukriLoginPage naukriLoginPage = new NaukriLoginPage(driver);
        naukriLoginPage.clickLoginButton();
        naukriLoginPage.selectEmailIDAsSelector();
        naukriLoginPage.setEmailIDInputField(USER_NAME);
        naukriLoginPage.setPasswordInputField(PASSWORD);
        naukriLoginPage.ClickLoginButtonOnCredsField();
        Assert.assertEquals(naukriLoginPage.getTitle(),"Mynaukri");
    }
}
