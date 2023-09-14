package base;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ConfigLoader;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.net.ServerSocket;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class BaseClass {
    protected static AndroidDriver<AndroidElement> driver;
    private static AppiumDriverLocalService server;

    public static String decode_passw,email,port,mail;

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

    @Parameters({"platform","device","automation","emulator","isInstalled","udid","appPackage","appActivity"})
    @BeforeTest()
    public void initialize_driver(String platform, String device, String automation, String emulator, String isInstalled,
                                  String udid, String appPackage, String appActivity) throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();
        File folder;

        String application = new ConfigLoader().initializeProperty().getProperty("Android_Application");

        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, device);

        switch (platform){
            case "Android":
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, automation);

                if(emulator.equalsIgnoreCase("true")){
                    caps.setCapability(MobileCapabilityType.UDID, udid);

                    caps.setCapability("avd", "Emulator");
                    caps.setCapability("avdLaunchTimeout", 180000);
                }
                else{
                    caps.setCapability(MobileCapabilityType.UDID, udid);
                }

                switch (isInstalled){
                    case "true":
                        caps.setCapability("appPackage", appPackage);
                        caps.setCapability("appActivity", appActivity);

                    case "false":
                        folder = new File("src/test/resources", application);
                        caps.setCapability(MobileCapabilityType.APP, folder.getAbsolutePath());
                        break;

                    default:
                        throw new Exception("Invalid Information " +isInstalled);
                }
                break;

            case "iOS":
                System.out.println("iOS setting is missing");
                break;

            default:
                throw new Exception("Invalid Platform Name " +platform);
        }

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
    }

    @Parameters({"platform","device","automation","emulator","isInstalled","udid","appPackage","appActivity"})
    @BeforeTest(enabled = false)
    public void initialize_Chromedriver(String platform, String device, String automation, String emulator, String isInstalled,
                                        String udid, String appPackage, String appActivity) throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, device);

        caps.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
        //caps.setCapability("chromedriverExecutable","");

        switch (platform){
            case "Android":
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, automation);

                if(emulator.equalsIgnoreCase("true")){
                    caps.setCapability(MobileCapabilityType.UDID, udid);

                    caps.setCapability("avd", "Emulator");
                    caps.setCapability("avdLaunchTimeout", 180000);
                }
                else{
                    caps.setCapability(MobileCapabilityType.UDID, udid);
                }

            case "iOS":
                System.out.println("iOS setting is missing");
                break;

            default:
                throw new Exception("Invalid Platform Name " +platform);
        }

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

    public void scroll_down_text_findElement(String text) {
        //driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
    }

    public void select_scroll_down(MobileElement element, String text, String attribute) {
        TouchAction tap = new TouchAction<>(driver);
        wait_for_visibility(element);
        tap.tap(new TapOptions().withElement(ElementOption.element(element))).perform();

        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));").click();

        //driver.findElementByAndroidUIAutomator(""+attribute+"(\""+text+"\")").click();
    }

    public static void send_email() {
        try{
            String TestFile = "E:\\Intellij Files\\Appium_TestNG\\Extra\\Email.txt";
            FileReader FR = new FileReader(TestFile);
            BufferedReader BR = new BufferedReader(FR);
            String content = "";

            for (int i = 0; (content = BR.readLine()) != null; i++) {
                String[] arrOfStr = content.split("\\| ", 4);

                decode_passw = arrOfStr[0];
                email = arrOfStr[1];
                port = arrOfStr[2];
                mail = arrOfStr[3];
            }
        }
        catch (Exception e){
            System.err.println("Error: " + Arrays.toString(e.getStackTrace()));
        }

        String decode_pass = decode_passw;
        String password = new String(Base64.getDecoder().decode(decode_pass.getBytes()));

        final String from = email; //For Yahoo, it should be a yahoo mail

        final String p1 = "mrahaman59@yahoo.com";
        //final String p2 = "sbappy88@gmail.com";
        //final String p3 = "parul@erainfotechbd.com";
        //final String p4 = "tauhid@erainfotechbd.com";

        String host = "smtp.gmail.com";                   //smtp.mail.yahoo.com
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, password);
            }
        });

        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            Multipart multipartObject = new MimeMultipart();

            message.setFrom(new InternetAddress(from));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(p1));
            //message.addRecipient(Message.RecipientType.TO, new InternetAddress(p2));
            //message.addRecipient(Message.RecipientType.TO, new InternetAddress(p3));
            //message.addRecipient(Message.RecipientType.BCC, new InternetAddress(p4));

            message.setSubject("Test Execution Result Report"); //Mail Subject

            BodyPart emailBody = new MimeBodyPart();
            emailBody.setText("Dear Sir/Ma'am, " + "\n" + "Here is test result execution report." + "\n" + "\n" + "Test Executed By-" + "\n" + "Mustafizur Rahman");

            BodyPart attachment = new MimeBodyPart();
            String filename = "E:\\Intellij Files\\Appium_TestNG\\Reports\\Html Reports\\Extent.html";
            DataSource source = new FileDataSource(filename);
            attachment.setDataHandler(new DataHandler(source));
            attachment.setFileName(filename);

            multipartObject.addBodyPart(emailBody); //Mail Body
            multipartObject.addBodyPart(attachment); // Attachment

            message.setContent(multipartObject);

            System.out.println("Sending............");
            Transport.send(message);
            System.out.println("Email Sent Successfully....");
        }
        catch (MessagingException mex) {
            mex.printStackTrace();
            System.out.println("Email Sent Failed....");
        }
    }

    @AfterTest()
    public void quit_driver() {
        try {
            driver.quit();
            send_email();
            //Runtime.getRuntime().exec("adb emu kill");

            File logFile = new File("Log Result/test.log");
            File outputFile = new File("Log Result/test_output.txt");

            String logContents = FileUtils.readFileToString(logFile, "UTF-8");
            FileUtils.writeStringToFile(outputFile, logContents, "UTF-8");
        }
        catch (Exception e) {
            logger.error("Log save failed -" +e);
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
