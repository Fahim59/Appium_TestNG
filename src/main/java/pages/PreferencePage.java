package pages;

import base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class PreferencePage extends BaseClass {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = '3. Preference dependencies']") private MobileElement preferenceDependencies;

    @AndroidFindBy(id = "android:id/checkbox") private MobileElement wifiCheckbox;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'WiFi settings']") private MobileElement wifiSettings;

    @AndroidFindBy(id = "android:id/edit") private MobileElement wifiNameField;
    @AndroidFindBy(xpath = "//android.widget.Button[@text = 'OK']") private MobileElement okBtn;

    public PreferencePage clickPreferenceDependency(){
        click_Element(preferenceDependencies);
        return this;
    }

    public PreferencePage clickWifiCheckbox(){
        if (!wifiCheckbox.isSelected()) {
            click_Element(wifiCheckbox);
        }
        return this;
    }

    public PreferencePage clickWifiSettings(){
        click_Element(wifiSettings);
        return this;
    }

    public PreferencePage enterWifiName(String name){
        send_Keys(wifiNameField, name);
        return this;
    }

    public PreferencePage clickOkBtn(){
        click_Element(okBtn);
        return this;
    }
}
