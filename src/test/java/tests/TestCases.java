package tests;

import base.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

public class TestCases extends BaseClass {
    private static final Logger logger = LogManager.getLogger(TestCases.class);

    private HomePage homePage;
    private PreferencePage preferencePage;
    private ViewPage viewPage;

    @BeforeMethod
    public void beforeMethod() {
        homePage = new HomePage();
        preferencePage = new PreferencePage();
        viewPage = new ViewPage();
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
        homePage.clickViewMenu();

        viewPage.clickAnimationMenu();
        viewPage.clickThreeDTransitionMenu();

        viewPage.clickLyonMenu();

        logger.info("Test case 2");
    }

    @Test
    public void test_case_3() {
        homePage.clickViewMenu();

        viewPage.tapExpandListMenu();
        viewPage.tapCustomAdapter();

        viewPage.longPressPeoplesName();
        viewPage.isDisplayedMenu();

        Assert.assertTrue(viewPage.isDisplayedMenu());

        logger.info("Test case 3");
    }

    @Test
    public void test_case_4() {
        homePage.clickViewMenu();

        viewPage.tapDateWidgetsMenu();
        viewPage.tapInline();
        viewPage.tapNineBtn();

        viewPage.swipeBtn();

        logger.info("Test case 4");
    }
}