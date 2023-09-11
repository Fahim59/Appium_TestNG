package pages;

import base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ViewPage extends BaseClass {
    //UI Automator -> "attribute(value)"

    @AndroidFindBy(uiAutomator = "text(\"Animation\")") private MobileElement animationMenu;
    @AndroidFindBy(uiAutomator = "text(\"3D Transition\")") private MobileElement threeDTransition;
    @AndroidFindBy(uiAutomator = "text(\"Lyon\")") private MobileElement lyonMenu;

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
}
