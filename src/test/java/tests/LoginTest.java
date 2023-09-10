package tests;

import base.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.ConfigLoader;

import java.io.InputStream;

public class LoginTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(BaseClass.class);

    private LoginPage loginPage;
    private HomePage homePage;

    InputStream data;
    JSONObject loginDetails;
    String errMessage;

    @BeforeClass
    public void beforeClass() throws Exception {
        try{
            errMessage = new ConfigLoader().parseStringXML().get("invalid_credential");

            String file = "login.json";
            data = getClass().getClassLoader().getResourceAsStream(file);

            JSONTokener tokener = new JSONTokener(data);
            loginDetails = new JSONObject(tokener);
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        finally {
            if(data != null){
                data.close();
            }
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        homePage = new HomePage();
        loginPage = new LoginPage();
    }

    @Test
    public void login_with_valid_credentials() throws InterruptedException {
        homePage.clickMenuButton();
        homePage.clickLoginMenuButton();

        loginPage.login(loginDetails.getJSONObject("validCredential").getString("username"),
                loginDetails.getJSONObject("validCredential").getString("password"));

        logger.info("login_with_valid_credentials");

        //small_wait(1000);
        //homePage.logout();
    }

    @Test
    public void login_with_invalid_username() {
        loginPage.login(loginDetails.getJSONObject("invalidUsername").getString("username"),
                loginDetails.getJSONObject("invalidUsername").getString("password"));

        Assert.assertEquals(errMessage, String.valueOf(homePage.getErrorMessage()));

        loginPage.clearField();

        logger.info("login_with_invalid_username");
    }

    @Test
    public void login_with_invalid_password() {
        loginPage.login(loginDetails.getJSONObject("invalidPassword").getString("username"),
                loginDetails.getJSONObject("invalidPassword").getString("password"));

        Assert.assertEquals(errMessage, String.valueOf(homePage.getErrorMessage()));

        loginPage.clearField();

        logger.info("login_with_invalid_password");
    }
}