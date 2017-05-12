package shopGophotoweb.tests;

import shopGophotoweb.pages.CartPage;
import shopGophotoweb.pages.CatalogPage;
import shopGophotoweb.pages.ProductPage;
import webdriver.BaseTest;

import static org.testng.Assert.assertTrue;


public class ST7_019 extends BaseTest {

    @Override
    public void runTest() throws InterruptedException {

        logStep();
        CatalogPage catalogPage=new CatalogPage();
        catalogPage.goToProductPage("product1");
        ProductPage product1Page=new ProductPage();
        product1Page.addProductToCart();
        product1Page.goToCart();

        logStep();
        CartPage cartPage=new CartPage();
        cartPage.fillInFields("test", "test", "tt@tt.tt");

        logStep();
        logger.info("Expected result: total price = 2 020 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"2 020 p.");

        logStep();
        cartPage.applyPromoCode("19");
        assertTrue(cartPage.isPromocodeErrorDisplayed());

        logStep();
        cartPage.applyPromoCode("19180238213!\"№;%:?'*()Ю><!#%^9");
        assertTrue(cartPage.isPromocodeErrorDisplayed());

        logStep();
        cartPage.deleteSomeProduct("product1");
        assertTrue(cartPage.isEmptyCartMessageDisplayed());

    }
}
