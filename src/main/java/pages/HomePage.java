package pages;

import base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage extends BaseClass {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Preference']") private MobileElement preferenceMenu;

    public HomePage clickPreferenceMenu(){
        click_Element(preferenceMenu);
        return this;
    }
}
