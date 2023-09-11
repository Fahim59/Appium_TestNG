package base;

import factory.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.ConfigLoader;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static io.appium.java_client.touch.offset.ElementOption.element;

public class BaseClass {
    protected static AndroidDriver<AndroidElement> driver;
    private static AppiumDriverLocalService server;

    private static final Logger logger = LogManager.getLogger(BaseClass.class);

    public BaseClass(){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @BeforeSuite
    public void start_appium_server() {
        ThreadContext.put("ROUTINGKEY", "ServerLogs");
        server = AppiumDriverLocalService.buildDefaultService();

        if(!checkIfAppiumServerIsRunning(4723)) {
            server.start();
            server.clearOutPutStreams();

            logger.info("Appium server started");
        }
        else {
            logger.info("Appium server already running");
        }
    }

    public boolean checkIfAppiumServerIsRunning(int port) {
        boolean isAppiumServerRunning = false;
        ServerSocket socket;
        try {
            socket = new ServerSocket(port);
            socket.close();
        }
        catch (IOException e) {
            isAppiumServerRunning = true;
        }
        return isAppiumServerRunning;
    }

//    @BeforeTest()
//    public void initialize_driver() throws Exception {
//        driver = DriverFactory.initializeDriver();
//
//        logger.info("initialize_driver");
//    }

    @BeforeTest()
    public void initialize_driver() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        File folder;

        String emulator = "true";

        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus_5");

        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        if(emulator.equalsIgnoreCase("true")){
            caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");

            caps.setCapability("avd", "Emulator");
            caps.setCapability("avdLaunchTimeout", 180000);
        }
        else{
            caps.setCapability(MobileCapabilityType.UDID, "177cc4e3");
        }

//        folder = new File("src/test/resources", "ApiDemos-debug.apk");
//        caps.setCapability(MobileCapabilityType.APP, folder.getAbsolutePath());

        caps.setCapability("appPackage","io.appium.android.apis");
        caps.setCapability("appActivity","io.appium.android.apis.ApiDemos");

//        caps.setCapability("unlockType","pin");
//        caps.setCapability("unlockKey","0000");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
    }

    public AndroidDriver getDriver(){
        return driver;
    }

    public String dateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void small_wait(int msec) throws InterruptedException {
        Thread.sleep(msec);
    }

    public void wait_for_visibility(MobileElement e) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void send_Keys(MobileElement element, String txt) {
        wait_for_visibility(element);
        String value = element.getAttribute("text");

        if (value.isEmpty()) {
            element.sendKeys(txt);
        }
        else {
            element.clear();
            element.sendKeys(txt);
        }
    }

    public void click_Element(MobileElement element) {
        wait_for_visibility(element);
        element.click();
    }

    public String getText(MobileElement element) {
        wait_for_visibility(element);
        return element.getAttribute("text");
    }

    public void tap_Element(MobileElement element) {
        TouchAction tap = new TouchAction<>(driver);
        wait_for_visibility(element);
        tap.tap(new TapOptions().withElement(ElementOption.element(element))).perform();
    }

    public void longPress_Element(MobileElement element) {
        TouchAction tap = new TouchAction<>(driver);
        wait_for_visibility(element);
        tap.longPress(LongPressOptions.longPressOptions().withElement(ElementOption
                .element(element)).withDuration(Duration.ofSeconds(3))).release().perform();
    }

    public void swipe_Element(MobileElement elementFrom, MobileElement elementTo) {
        TouchAction tap = new TouchAction<>(driver);
        wait_for_visibility(elementFrom);wait_for_visibility(elementTo);

        tap.longPress(LongPressOptions.longPressOptions().withElement(ElementOption
                        .element(elementFrom)).withDuration(Duration.ofSeconds(1)))
                .moveTo(ElementOption.element(elementTo)).release().perform();
    }

    public void drag_drop_Element(MobileElement elementFrom, MobileElement elementTo) {
        TouchAction tap = new TouchAction<>(driver);
        wait_for_visibility(elementFrom);wait_for_visibility(elementTo);

        tap.longPress(element(elementFrom)).moveTo(element(elementTo)).release().perform();
    }

    public static void Scroll_Down_Text_FindElement(String text) {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
    }

    public static void Select_Scroll_Down(String id, String text, String attribute) {
        TouchAction tap = new TouchAction<>(driver);

        WebElement element = driver.findElement(By.id(id));
        tap.tap(new TapOptions().withElement(ElementOption.element(element))).perform();

        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");

        driver.findElementByAndroidUIAutomator(""+attribute+"(\""+text+"\")").click();
    }

    @AfterTest()
    public void quit_driver() {
        try {
            driver.quit();
            //Runtime.getRuntime().exec("adb emu kill");

            File logFile = new File("Log Result/test.log");
            File outputFile = new File("Log Result/test_output.txt");

            String logContents = FileUtils.readFileToString(logFile, "UTF-8");
            FileUtils.writeStringToFile(outputFile, logContents, "UTF-8");
        }
        catch (Exception e) {
            System.out.println("Log save failed" +e);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void stop_appium_server() {
        if(server.isRunning()){
            server.stop();
            logger.info("Appium server stopped");
        }
    }
}
