package base;

import factory.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
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
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
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

    @BeforeTest()
    public void initialize_driver() throws Exception {
        driver = DriverFactory.initializeDriver();

        logger.info("initialize_driver");
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

    public static void Scroll_Down_Text_FindElement(String text) {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
    }
    
    public static void TapElementByXpath(String xpath){
        TouchAction tap = new TouchAction<>(driver);

        WebElement element = driver.findElement(By.xpath(xpath));
        tap.tap(new TapOptions().withElement(ElementOption.element(element))).perform();
    }

    public static void TapElementById(String id){
        TouchAction tap = new TouchAction<>(driver);

        WebElement element = driver.findElement(By.id(id));
        tap.tap(new TapOptions().withElement(ElementOption.element(element))).perform();
    }

    public static void LongPressElementByXpath(String xpath){
        TouchAction tap = new TouchAction<>(driver);

        WebElement peopleName = driver.findElement(By.xpath(xpath));
        tap.longPress(LongPressOptions.longPressOptions().withElement(ElementOption
                .element(peopleName)).withDuration(Duration.ofSeconds(3))).release().perform();
    }

    public static void SwipeElementByXpath(String fromXpath, String toXpath){
        TouchAction tap = new TouchAction<>(driver);

        WebElement from = driver.findElement(By.xpath(fromXpath));
        WebElement to = driver.findElement(By.xpath(toXpath));

        tap.longPress(LongPressOptions.longPressOptions().withElement(ElementOption
                        .element(from)).withDuration(Duration.ofSeconds(1))).moveTo(ElementOption.element(to))
                .release().perform();
    }

    public static void Drag_DropElementByXpath(String fromXpath, String toXpath){
        TouchAction tap = new TouchAction<>(driver);

        WebElement from = driver.findElement(By.xpath(fromXpath));
        WebElement to = driver.findElement(By.xpath(toXpath));

        tap.longPress(element(from)).moveTo(element(to)).release().perform();
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
            //driver.quit();
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
