package tests;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;
import utils.ConfigLoader;

public class ProductTest extends BaseClass {
    private ProductPage productPage;
    private HomePage homePage;

    String productName;
    String productPrice;

    @BeforeClass
    public void beforeClass() throws Exception {
        productName = new ConfigLoader().parseStringXML().get("product_name");
        productPrice = new ConfigLoader().parseStringXML().get("product_price");
    }

    @BeforeMethod
    public void beforeMethod() {
        homePage = new HomePage();
        productPage = new ProductPage();
    }

    @Test
    public void validate_product() {
        scroll_down_text_findElement("Sauce Labs Onesie");

        homePage.clickProductTitle();

        String title = productPage.getProductTitle();
        Assert.assertEquals(title, productName);

        String price = productPage.getProductPrice();
        Assert.assertEquals(price, productPrice);
    }
}
