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

    @AndroidFindBy(id = "android:id/hours") private MobileElement hourTxt;
    @AndroidFindBy(id = "android:id/minutes") private MobileElement minuteTxt;
    @AndroidFindBy(id = "android:id/am_label") private MobileElement amLevel;
    @AndroidFindBy(id = "android:id/pm_label") private MobileElement pmLevel;

    @AndroidFindBy(uiAutomator = "text(\"Drag and Drop\")") private MobileElement dragAndDropMenu;
    @AndroidFindBy(xpath = "(.//*[@class='android.view.View'])[1]") private MobileElement fromElement;
    @AndroidFindBy(xpath = "(.//*[@class='android.view.View'])[2]") private MobileElement toElement;

    @AndroidFindBy(uiAutomator = "text(\"WebView\")") private MobileElement webViewMenu;

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

    public String getHourText(){
        return getText(hourTxt);
    }

    public String getMinuteText(){
        return getText(minuteTxt);
    }

    public String isAmActive() {
        return amLevel.getAttribute("checked");
    }

    public String isPmActive() {
        return pmLevel.getAttribute("checked");
    }

    public ViewPage clickDragAndDropMenu() {
        click_Element(dragAndDropMenu);
        return this;
    }

    public ViewPage dragAndDrop() {
        drag_drop_Element(fromElement, toElement);
        return this;
    }

    public ViewPage clickWebViewMenu() {
        click_Element(webViewMenu);
        return this;
    }
}
