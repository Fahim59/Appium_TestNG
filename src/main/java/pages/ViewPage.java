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

    @AndroidFindBy(uiAutomator = "text(\"Date Widgets\")") private MobileElement dateWidgetsMenu;
    @AndroidFindBy(uiAutomator = "text(\"2. Inline\")") private MobileElement inline;
    @AndroidFindBy(xpath = "//*[@content-desc='9']") private MobileElement nineBtn;
    @AndroidFindBy(xpath = "//*[@content-desc='15']") private MobileElement fifteenBtn;
    @AndroidFindBy(xpath = "//*[@content-desc='45']") private MobileElement fortyFiveBtn;

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

    public ViewPage tapDateWidgetsMenu() {
        tap_Element(dateWidgetsMenu);
        return this;
    }

    public ViewPage tapInline() {
        tap_Element(inline);
        return this;
    }

    public ViewPage tapNineBtn() {
        tap_Element(nineBtn);
        return this;
    }

    public ViewPage swipeBtn() {
        swipe_Element(fifteenBtn,fortyFiveBtn);
        return this;
    }
}
