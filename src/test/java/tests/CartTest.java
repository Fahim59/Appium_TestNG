package tests;

import base.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.ConfigLoader;

import java.io.FileReader;

public class CartTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    private CartPage cartPage;

    FileReader data;
    JSONObject checkoutDetails;

    String productName;
    String productPrice;

    @BeforeClass
    public void beforeClass() throws Exception {
        try {
            productName = new ConfigLoader().parseStringXML().get("product_name");
            productPrice = new ConfigLoader().parseStringXML().get("product_price");

            String file = "src/test/resources/data.json";
            data = new FileReader(file);

            JSONTokener tokener = new JSONTokener(data);
            checkoutDetails = new JSONObject(tokener);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            if (data != null) {
                data.close();
            }
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        cartPage = new CartPage();
    }

    @Test
    public void check_product_details() throws InterruptedException {
        small_wait(1500);

        String title = cartPage.getProductTitle();
        Assert.assertEquals(title, productName);

        String price = cartPage.getProductPrice();
        Assert.assertEquals(price, productPrice);

        String unitPrice = price.replaceAll("[^0-9.]", "");
        String totalItem = cartPage.getTotalItem().replaceAll("[^0-9.]", "");
        String totalPrice = cartPage.getTotalPrice().replaceAll("[^0-9.]", "");

        String tPrice = String.valueOf(Double.parseDouble(unitPrice) * Double.parseDouble(totalItem));

        Assert.assertEquals(tPrice, totalPrice);

        cartPage.clickProceedButton();

        logger.info("Proceed Button Clicked");
    }

    @Test
    public void enter_checkout_details() throws InterruptedException {
        small_wait(2000);

        JSONObject data = checkoutDetails.getJSONObject("checkoutDetails");

        cartPage.setCheckoutDetails(data.getString("name"),data.getString("address"),data.getString("city"),
                data.getString("state"),data.getString("zip"),data.getString("country"));

        cartPage.clickPaymentButton();

        logger.info("Entered Checkout Details");
    }
}
