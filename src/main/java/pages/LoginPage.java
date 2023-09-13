package pages;

import base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseClass {

    @AndroidFindBy(xpath = "//*[@content-desc='Username input field']") private MobileElement usernameTextField;
    @AndroidFindBy(xpath = "//*[@content-desc='Password input field']") private MobileElement passwordTextField;
    @AndroidFindBy(xpath = "//*[@content-desc='Login button']") private MobileElement loginBtn;

    public LoginPage enterUserName(String user){
        send_Keys(usernameTextField, user);
        return this;
    }

    public LoginPage enterPassword(String pass){
        send_Keys(passwordTextField, pass);
        return this;
    }

    public LoginPage clickLoginBtn(){
        click_Element(loginBtn);
        loginBtn.click();
        return this;
    }

    public LoginPage login(String id, String pass){
        return enterUserName(id).enterPassword(pass).clickLoginBtn();
    }
}
