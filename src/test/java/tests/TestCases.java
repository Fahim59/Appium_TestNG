package tests;

import base.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;
import pages.*;

public class TestCases extends BaseClass {
    private static final Logger logger = LogManager.getLogger(TestCases.class);

    private HomePage homePage;
    private PreferencePage preferencePage;

    @BeforeMethod
    public void beforeMethod() {
        homePage = new HomePage();
        preferencePage = new PreferencePage();
    }

    @Test
    public void test_case_1() {
        homePage.clickPreferenceMenu();

        preferencePage.clickPreferenceDependency();
        preferencePage.clickWifiCheckbox();
        preferencePage.clickWifiSettings();
        preferencePage.enterWifiName("Fahim Wifi");
        preferencePage.clickOkBtn();

        logger.info("Test case 1");
    }

    @Test
    public void test_case_2() {

    }
}