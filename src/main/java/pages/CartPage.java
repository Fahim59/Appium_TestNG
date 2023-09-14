package pages;

import base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CartPage extends BaseClass {
    @AndroidFindBy(uiAutomator = "text(\"Sauce Labs Onesie\")") private MobileElement productTitle;
    @AndroidFindBy(xpath = "//*[@content-desc = 'product price'][1]") private MobileElement productPrice;

    @AndroidFindBy(xpath = "//*[@content-desc = 'total number'][1]") private MobileElement totalItem;
    @AndroidFindBy(xpath = "//*[@content-desc = 'total price'][1]") private MobileElement totalAmount;

    @AndroidFindBy(xpath = "//*[@content-desc = 'Proceed To Checkout button'][1]") private MobileElement proceedBtn;

    @AndroidFindBy(xpath = "//*[@content-desc = 'Full Name* input field'][1]") private MobileElement nameField;
    @AndroidFindBy(xpath = "//*[@content-desc = 'Address Line 1* input field'][1]") private MobileElement addressField;
    @AndroidFindBy(xpath = "//*[@content-desc = 'City* input field'][1]") private MobileElement cityField;
    @AndroidFindBy(xpath = "//*[@content-desc = 'State/Region input field'][1]") private MobileElement stateField;
    @AndroidFindBy(xpath = "//*[@content-desc = 'Zip Code* input field'][1]") private MobileElement zipField;
    @AndroidFindBy(xpath = "//*[@content-desc = 'Country* input field'][1]") private MobileElement countryField;

    @AndroidFindBy(xpath = "//*[@content-desc = 'To Payment button'][1]") private MobileElement paymentBtn;

    @AndroidFindBy(xpath = "//*[@content-desc = 'Full Name* input field'][1]") private MobileElement cardNameField;
    @AndroidFindBy(xpath = "//*[@content-desc = 'Card Number* input field'][1]") private MobileElement cardField;
    @AndroidFindBy(xpath = "//*[@content-desc = 'Expiration Date* input field'][1]") private MobileElement expiryField;
    @AndroidFindBy(xpath = "//*[@content-desc = 'Security Code* input field'][1]") private MobileElement scodeField;

    @AndroidFindBy(xpath = "//*[@content-desc = 'Review Order button'][1]") private MobileElement reviewOrderBtn;

    @AndroidFindBy(xpath = "//*[@content-desc = 'Place Order button'][1]") private MobileElement placeOrderBtn;

    @AndroidFindBy(uiAutomator = "text(\"Checkout Complete\")") private MobileElement successMessage;

    public String getProductTitle(){
        return getText(productTitle);
    }

    public String getProductPrice(){
        return getText(productPrice);
    }

    public String getTotalItem(){
        return getText(totalItem);
    }

    public String getTotalPrice(){
        return getText(totalAmount);
    }

    public CartPage clickProceedButton(){
        click_Element(proceedBtn);
        return this;
    }

    public CartPage enterName(String name){
        send_Keys(nameField, name);
        return this;
    }
    public CartPage enterAddress(String address){
        send_Keys(addressField, address);
        return this;
    }
    public CartPage enterCity(String city){
        send_Keys(cityField, city);
        return this;
    }
    public CartPage enterState(String state){
        send_Keys(stateField, state);
        return this;
    }
    public CartPage enterZip(String zip){
        scroll_down_text_findElement("Zip Code*");

        send_Keys(zipField, zip);
        return this;
    }
    public CartPage enterCountry(String country){
        send_Keys(countryField, country);
        return this;
    }

    public CartPage setCheckoutDetails(String name, String address,String city, String state, String zip, String country){
        return enterName(name).enterAddress(address).
                enterCity(city).enterState(state).enterZip(zip).enterCountry(country);
    }

    public CartPage clickPaymentButton(){
        click_Element(paymentBtn);
        return this;
    }

    public CartPage enterCardName(String name){
        send_Keys(cardNameField, name);
        return this;
    }
    public CartPage enterCardNumber(String cnum){
        send_Keys(cardField, cnum);
        return this;
    }
    public CartPage enterExpiryNumber(String exp){
        send_Keys(expiryField, exp);
        return this;
    }
    public CartPage enterSecurityCode(String scode){
        send_Keys(scodeField, scode);
        scroll_down_text_findElement("My billing address is the same as my shipping address.");
        return this;
    }

    public CartPage setPaymentDetails(String name, String cnum, String exp,String scode){
        return enterCardName(name).enterCardNumber(cnum).enterExpiryNumber(exp).enterSecurityCode(scode);
    }

    public CartPage clickReviewButton(){
        click_Element(reviewOrderBtn);
        return this;
    }

    public CartPage clickPlaceOrderButton(){
        click_Element(placeOrderBtn);
        return this;
    }

    public boolean successMessage(){
        return successMessage.isDisplayed();
    }
}