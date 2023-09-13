package pages;

import base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductPage extends BaseClass {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Sauce Labs Backpack']") private MobileElement slbTitle;
    @AndroidFindBy(xpath = "//*[@content-desc = 'product price'][1]") private MobileElement slbPrice;
    @AndroidFindBy(xpath = "//*[@content-desc = 'product description'][1]") private MobileElement sblDescription;

    @AndroidFindBy(xpath = "//*[@content-desc = 'Add To Cart button'][1]") private MobileElement addToCartBtn;
    @AndroidFindBy(xpath = "//*[@content-desc = 'counter plus button'][1]") private MobileElement plusBtn;

    public String getSLBTitle(){
        return getText(slbTitle);
    }

    public String getSLBPrice(){
        return getText(slbPrice);
    }

    public String getSLBDescription(){
        return getText(sblDescription);
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
