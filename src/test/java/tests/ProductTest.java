package tests;

import base.BaseClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;

public class ProductTest extends BaseClass {
    private ProductPage productPage;
    private HomePage homePage;

    @BeforeMethod
    public void beforeMethod() {
        homePage = new HomePage();
        productPage = new ProductPage();
    }

    @Test
    public void validate_product() {
        scroll_down_text_findElement("Sauce Labs Onesie");

        homePage.clickProductTitle();
    }
}
