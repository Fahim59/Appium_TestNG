package tests;

import base.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.ConfigLoader;

public class ProductTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(ProductTest.class);

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

    @Test(description = "This is for validate product details scenario")
    public void validate_product() {
        scroll_down_text_findElement(productName);

        homePage.clickProductTitle();

        String title = productPage.getProductTitle();
        Assert.assertEquals(title, productName);
        logger.info("Product Title Matched");

        String price = productPage.getProductPrice();
        Assert.assertEquals(price, productPrice);
        logger.info("Product Price Matched");
    }

    @Test(description = "This is for order product scenario")
    public void order_product() {
        scroll_down_text_findElement("Product Highlights");

        productPage.clickPlusButton();
        productPage.clickAddToCartButton();
        homePage.clickCartButton();

        logger.info("Cart Button Clicked");
    }
}
