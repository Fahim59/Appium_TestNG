package factory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;
import utils.ConfigLoader;

import java.io.File;
import java.net.URL;

public class DriverFactory {
    //protected static AndroidDriver<AndroidElement> driver;
    protected static ThreadLocal <AndroidDriver> driver = new ThreadLocal<>();

    public static AndroidDriver initializeDriver(String platform, String device, String automation, String emulator, String isInstalled,
                                                 String udid, String appPackage, String appActivity) throws Exception {
        try{
            DesiredCapabilities caps = new DesiredCapabilities();
            File folder;

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
                            folder = new File("src/test/resources", "ApiDemos-debug.apk");
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