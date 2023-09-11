package pages;

import base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ViewPage extends BaseClass {
    //UI Automator -> "attribute(value)"
    //"Tap Element" instead of Click

    @AndroidFindBy(uiAutomator = "text(\"Animation\")") private MobileElement animationMenu;
    @AndroidFindBy(uiAutomator = "text(\"3D Transition\")") private MobileElement threeDTransition;
    @AndroidFindBy(uiAutomator = "text(\"Lyon\")") private MobileElement lyonMenu;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Expandable Lists']") private MobileElement expandListMenu;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = '1. Custom Adapter']") private MobileElement customAdapter;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'People Names']") private MobileElement peoplesName;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Sample menu']") private MobileElement sampleMenu;

    public ViewPage clickAnimationMenu() {
        click_Element(animationMenu);
        return this;
    }

    public ViewPage clickThreeDTransitionMenu() {
        click_Element(threeDTransition);
        return this;
    }

    public ViewPage clickLyonMenu() {
        click_Element(lyonMenu);
        return this;
    }

    public ViewPage tapExpandListMenu() {
        tap_Element(expandListMenu);
        return this;
    }

    public ViewPage tapCustomAdapter() {
        tap_Element(customAdapter);
        return this;
    }

    public ViewPage longPressPeoplesName() {
        longPress_Element(peoplesName);
        return this;
    }

    public boolean isDisplayedMenu() {
        return sampleMenu.isDisplayed();
    }
}
