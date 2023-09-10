package base;

import factory.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.ServerSocket;

public class BaseClass {
    protected static AndroidDriver<AndroidElement> driver;
    private static AppiumDriverLocalService server;

    public BaseClass(){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @BeforeSuite
    public void beforeSuite() throws Exception {
        ThreadContext.put("ROUTINGKEY", "ServerLogs");
        server = AppiumDriverLocalService.buildDefaultService();

        if(!checkIfAppiumServerIsRunning(4723)) {
            server.start();
            server.clearOutPutStreams();

            //logger.info("Appium server started");
        }
        else {
            //logger.info("Appium server already running");
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

        //logger.info("initialize_driver");
    }

    public AndroidDriver getDriver(){
        return driver;
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        if(server.isRunning()){
            server.stop();
            //logger.info("Appium server stopped");
        }
    }
}
