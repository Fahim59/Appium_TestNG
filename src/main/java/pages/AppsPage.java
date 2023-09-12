package pages;

import base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AppsPage extends BaseClass {

    @AndroidFindBy(id = "com.android.calculator2:id/digit_5") private MobileElement digit5;
    @AndroidFindBy(id = "com.android.calculator2:id/digit_6") private MobileElement digit6;
    @AndroidFindBy(id = "com.android.calculator2:id/digit_7") private MobileElement digit7;
    @AndroidFindBy(id = "com.android.calculator2:id/digit_8") private MobileElement digit8;
    @AndroidFindBy(id = "com.android.calculator2:id/digit_9") private MobileElement digit9;

    @AndroidFindBy(id = "com.android.calculator2:id/op_add") private MobileElement addBtn;
    @AndroidFindBy(id = "com.android.calculator2:id/eq") private MobileElement equalBtn;

    @AndroidFindBy(id = "com.android.calculator2:id/result") private MobileElement resultText;

    @AndroidFindBy(id = "com.google.android.dialer:id/fab") private MobileElement dialerTab;

    @AndroidFindBy(id = "com.google.android.dialer:id/zero") private MobileElement digitZero;
    @AndroidFindBy(id = "com.google.android.dialer:id/one") private MobileElement digitOne;
    @AndroidFindBy(id = "com.google.android.dialer:id/seven") private MobileElement digitSeven;
    @AndroidFindBy(id = "com.google.android.dialer:id/four") private MobileElement digitFour;
    @AndroidFindBy(id = "com.google.android.dialer:id/two") private MobileElement digitTwo;
    @AndroidFindBy(id = "com.google.android.dialer:id/three") private MobileElement digitThree;

    @AndroidFindBy(id = "com.google.android.dialer:id/search_action_text") private MobileElement addContractBtn;

    @AndroidFindBy(uiAutomator = "text(\"First name\")") private MobileElement nameField;

    @AndroidFindBy(uiAutomator = "text(\"SAVE\")") private MobileElement saveBtn;

    public AppsPage clickDigit5(){
        click_Element(digit5);
        return this;
    }

    public AppsPage clickDigit6(){
        click_Element(digit6);
        return this;
    }

    public AppsPage clickDigit7(){
        click_Element(digit7);
        return this;
    }

    public AppsPage clickDigit8(){
        click_Element(digit8);
        return this;
    }

    public AppsPage clickDigit9(){
        click_Element(digit9);
        return this;
    }

    public AppsPage clickAddBtn(){
        click_Element(addBtn);
        return this;
    }

    public AppsPage clickEqualBtn(){
        click_Element(equalBtn);
        return this;
    }

    public String getResult(){
        return getText(resultText);
    }

    public AppsPage clickDialerTab(){
        click_Element(dialerTab);
        return this;
    }

    public AppsPage clickNumber(MobileElement element){
        click_Element(element);
        return this;
    }

    public AppsPage setNumber(){
        return clickNumber(digitZero).clickNumber(digitOne).clickNumber(digitSeven).clickNumber(digitSeven).
                clickNumber(digitFour).clickNumber(digitTwo).clickNumber(digitZero).clickNumber(digitThree).
                clickNumber(digitOne).clickNumber(digitThree).clickNumber(digitThree);
    }

    public AppsPage clickAddContactBtn(){
        click_Element(addContractBtn);
        return this;
    }

    public AppsPage enterName(){
        send_Keys(nameField,"Tuhin Vai");
        return this;
    }

    public AppsPage clickSaveBtn(){
        click_Element(saveBtn);
        return this;
    }
}
