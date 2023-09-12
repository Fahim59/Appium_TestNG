package tests;

import base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.time.Duration;
import java.util.Set;

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

    @Test(description = "Basic click and send keys")
    public void test_case_1() {
        homePage.clickPreferenceMenu();

        preferencePage.clickPreferenceDependency();
        preferencePage.clickWifiCheckbox();
        preferencePage.clickWifiSettings();
        preferencePage.enterWifiName("Fahim Wifi");
        preferencePage.clickOkBtn();

        logger.info("Test case 1");
    }

    @Test(description = "Click using UIAutomator")
    public void test_case_2() {
        homePage.clickViewMenu();

        viewPage.clickAnimationMenu();
        viewPage.clickThreeDTransitionMenu();

        viewPage.clickLyonMenu();

        logger.info("Test case 2");
    }

    @Test(description = "Tap and Long press")
    public void test_case_3() {
        homePage.clickViewMenu();

        viewPage.tapExpandListMenu();
        viewPage.tapCustomAdapter();

        viewPage.longPressPeoplesName();
        viewPage.isDisplayedMenu();

        Assert.assertTrue(viewPage.isDisplayedMenu());

        logger.info("Test case 3");
    }

    @Test(description = "Swipe")
    public void test_case_4() {
        homePage.clickViewMenu();

        viewPage.tapDateWidgetsMenu();
        viewPage.tapInline();
        viewPage.tapNineBtn();

        viewPage.swipeBtn();

        if(viewPage.isAmActive().equalsIgnoreCase("true")){
            logger.info("Time is- " +viewPage.getHourText() + ":" +viewPage.getMinuteText() +" AM");
        }
        else if(viewPage.isPmActive().equalsIgnoreCase("true")){
            logger.info("Time is- " +viewPage.getHourText() + ":" +viewPage.getMinuteText() +" PM");
        }
    }

    @Test(description = "Drag and Drop")
    public void test_case_5() {
        homePage.clickViewMenu();

        viewPage.clickDragAndDropMenu();
        viewPage.dragAndDrop();

        logger.info("Test case 5");
    }

    @Test(description = "Interact with apps")
    public void test_case_6() throws InterruptedException {
//        homePage.clickViewMenu();

//        small_wait(5000);
//        driver.runAppInBackground(Duration.ofMillis(5000));

//        System.out.println(driver.isAppInstalled("io.appium.android.apis"));

//        driver.terminateApp("io.appium.android.apis");

//        driver.lockDevice();
//        System.out.println(driver.isDeviceLocked());
//        small_wait(2000);

//        driver.unlockDevice();
//        System.out.println(driver.isDeviceLocked());

        driver.pressKey(new KeyEvent().withKey(AndroidKey.HOME));
        driver.pressKey(new KeyEvent().withKey(AndroidKey.CALCULATOR));
    }

    @Test(description = "Web View")
    public void test_case_7() throws InterruptedException {
        homePage.clickViewMenu();

        Scroll_Down_Text_FindElement("WebView");
        viewPage.clickWebViewMenu();

        Set<String> contexHandles = driver.getContextHandles();
        for(String contexHandle : contexHandles){
            System.out.println(contexHandle);
        }

        small_wait(1500);

        driver.context("WEBVIEW");

        System.out.println(driver.findElementByXPath("//a[contains(@href,'linked.html')]").getText());

        driver.context("NATIVE_APP");
    }

    @Test(description = "Chrome Driver")
    public void test_case_8() throws InterruptedException {
        driver.get("https://askomdch.com/");

        small_wait(2000);

        driver.findElementByXPath("//button[@class='menu-toggle main-header-menu-toggle ast-mobile-menu-trigger-minimal']").click();

        driver.findElementByLinkText("Men").click();

        small_wait(1500);

        driver.findElementByXPath("//a[@aria-label='Add “Basic Blue Jeans” to your cart']").click();
        small_wait(1000);
        driver.findElementByXPath("//a[@title='View cart']").click();
    }
}