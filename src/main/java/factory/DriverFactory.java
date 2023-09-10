package factory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.ConfigLoader;

import java.io.File;
import java.net.URL;

public class DriverFactory {
    //protected static AndroidDriver<AndroidElement> driver;
    protected static ThreadLocal <AndroidDriver> driver = new ThreadLocal<>();


    public static AndroidDriver initializeDriver() throws Exception {
        try{
            String android_application = new ConfigLoader().initializeProperty().getProperty("Android_Application");

            String emulator = new ConfigLoader().initializeProperty().getProperty("Emulator");

            String platform = new ConfigLoader().initializeProperty().getProperty("Platform");
            String platformVersion = new ConfigLoader().initializeProperty().getProperty("PlatformVersion");
            String device = new ConfigLoader().initializeProperty().getProperty("Device");
            String android_real_udId = new ConfigLoader().initializeProperty().getProperty("Android_Real_UdId");
            String android_emu_udid = new ConfigLoader().initializeProperty().getProperty("Android_Emu_UdId");

            File folder;

            DesiredCapabilities caps = new DesiredCapabilities();

            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, device);

            switch (platform){
                case "Android":
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

                    if(emulator.equalsIgnoreCase("true")){
                        //caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
                        caps.setCapability(MobileCapabilityType.UDID, android_emu_udid);

                        caps.setCapability("avd", "Emulator");
                        caps.setCapability("avdLaunchTimeout", 180000);
                    }
                    else{
                        caps.setCapability(MobileCapabilityType.UDID, android_real_udId);
                    }

                    folder = new File("src/test/resources", android_application);
                    caps.setCapability(MobileCapabilityType.APP, folder.getAbsolutePath());

                    break;

                case "iOS":
                    System.out.println("iOS setting is missing");
                    break;

                default:
                    throw new Exception("Invalid Platform Name " +platform);
            }

            //driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
            driver.set(new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps));
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }

        return driver.get();
    }
}