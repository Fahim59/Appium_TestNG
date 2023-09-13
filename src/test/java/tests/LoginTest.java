package tests;

import base.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.*;
import pages.*;
import java.io.FileReader;

public class LoginTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    private LoginPage loginPage;
    private HomePage homePage;

    FileReader data;
    JSONObject loginDetails;

    @BeforeClass
    public void beforeClass() throws Exception {
        try {
            String file = "src/test/resources/data.json";
            data = new FileReader(file);

            JSONTokener tokener = new JSONTokener(data);
            loginDetails = new JSONObject(tokener);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            if (data != null) {
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
}