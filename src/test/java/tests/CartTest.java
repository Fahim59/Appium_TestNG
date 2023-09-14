package tests;

import base.BaseClass;
import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;
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

    Faker faker = new Faker();
    String fullName, address, city, state, zipCode, country;

    @BeforeClass
    public void beforeClass() throws Exception {
        try {
            productName = new ConfigLoader().parseStringXML().get("product_name");
            productPrice = new ConfigLoader().parseStringXML().get("product_price");

            fullName = faker.name().fullName();
            address = faker.address().fullAddress();
            city = faker.address().city();
            state = faker.address().state();
            zipCode = faker.address().zipCode();
            country = faker.address().country();

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

        cartPage.setCheckoutDetails(fullName, address, city, state, zipCode, country);

        cartPage.clickPaymentButton();

        logger.info("Entered Checkout Details");

        small_wait(1500);
        cartPage.setPaymentDetails(fullName, data.getString("card"),
                data.getString("expiry"),data.getString("security"));

        cartPage.clickReviewButton();

        scroll_down_text_findElement("Estimated to arrive within 3 weeks.");

        cartPage.clickPlaceOrderButton();

        small_wait(1000);

        if(cartPage.successMessage()) {
            logger.info("Product Order Successful");
        }
        else {
            logger.error("Product Order Failed");
            Assert.fail();
        }
    }
}
