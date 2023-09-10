package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BaseClass {
    protected static AndroidDriver<AndroidElement> driver;

    public BaseClass(){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
