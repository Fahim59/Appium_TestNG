package pages;

import base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductPage extends BaseClass {
    @AndroidFindBy(uiAutomator = "text(\"Sauce Labs Onesie\")") private MobileElement productTitle;
    @AndroidFindBy(xpath = "//*[@content-desc = 'product price'][1]") private MobileElement productPrice;

    @AndroidFindBy(xpath = "//*[@content-desc = 'Add To Cart button'][1]") private MobileElement addToCartBtn;
    @AndroidFindBy(xpath = "//*[@content-desc = 'counter plus button'][1]") private MobileElement plusBtn;

    public String getProductTitle(){
        return getText(productTitle);
    }

    public String getProductPrice(){
        return getText(productPrice);
    }

    public ProductPage clickAddToCartButton(){
        click_Element(addToCartBtn);
        return this;
    }

    public ProductPage clickPlusButton(){
        click_Element(plusBtn);
        return this;
    }
}
