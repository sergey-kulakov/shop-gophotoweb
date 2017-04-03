package shopGophotoweb.tests;

import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.CartPage;
import shopGophotoweb.pages.CatalogPage;
import shopGophotoweb.pages.ProductPage;
import shopGophotoweb.pages.SuccessPage;
import org.testng.annotations.BeforeMethod;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;


public class ST005 extends BaseTest {
    @BeforeMethod
    public void setPreconditions(){

        logStep();
        browser.navigate(Browser.getAdminPageUrl());
        LoginPage loginPage=new LoginPage();
        loginPage.login();

        logStep();
        AdminMainPage adminMainPage = new AdminMainPage();
        adminMainPage.goToShop();

        AdminProductsPage adminProductsPage=new AdminProductsPage();
        adminProductsPage.goToSettingsPage();

        logStep();
        SettingsPage settingsPage=new SettingsPage();
        Utilites.goToSidebarItem("Методы оплаты");

        logStep();
        PaymetsMethodsPage paymetsMethodsPage=new PaymetsMethodsPage();
        paymetsMethodsPage.checkPaymentMethodVisible("Мой вид оплаты");

        logStep();
        Utilites.goToSidebarItem("Методы доставки");
        DeliveryMethodsPage deliveryMethodsPage=new DeliveryMethodsPage();
        deliveryMethodsPage.checkDeliveryMethodVisible("Курьер");
        deliveryMethodsPage.checkDeliveryMethodVisible("Самовывоз");



    }
    @Override
    public void runTest() throws InterruptedException {

        logStep();
        CatalogPage catalogPage=new CatalogPage();
        catalogPage.goToProductPage("product1");

        ProductPage product1Page=new ProductPage();
        product1Page.addProductToCart();
        product1Page.goToCatalog();

        logStep();
        catalogPage.goToProductPage("product3");
        ProductPage product3Page=new ProductPage();
        product3Page.addProductToCart();
        product3Page.goToCart();

        logStep();
        CartPage cartPage=new CartPage();
        cartPage.fillInFields("test", "test", "tt@tt.tt");

        logStep();
        cartPage.selectDeliveryMethod("Курьер");
        logger.info("Expected result: total price = 2 535.10 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"2 535.10 p.");

        logStep();
        cartPage.setProductCount("product1","3");
        logger.info("Expected result: total price = 10 100 p.4 575.30 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"4 575.30 p.");

        logStep();
        cartPage.setProductCount("product3","4");
        logger.info("Expected result: total price = 6 120.60 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"6 120.60 p.");

        logStep();
        cartPage.applyPromoCode("3");
        assertTrue(cartPage.isPromocodeErrorDisplayed());
        logger.info("Promocode error displayed");

        logStep();
        cartPage.setProductCount("product1","5");
        logger.info("Expected result: total price = 8 160.80 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"8 160.80 p.");

        logStep();
        cartPage.applyPromoCode("3");
        assertTrue(cartPage.isPromocodeErrorDisplayed());
        logger.info("Promocode error displayed");

        logStep();
        cartPage.setProductCount("product1","6");
        logger.info("Expected result: total price = 9 180.90 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"9 180.90 p.");

        logStep();
        cartPage.applyPromoCode("3");
        logger.info("Expected result: total price = 8 675.90 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"8 675.90 p.");

        logStep();
        cartPage.clickSubmit();
        SuccessPage successPage=new SuccessPage();
        assertTrue(successPage.checkThanksForOrderMessage());
        logger.info("The order was completed");
    }
}
