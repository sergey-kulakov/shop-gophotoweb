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


public class ST3_002 extends BaseTest {
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

        logStep();
        ProductPage productPage=new ProductPage();
        productPage.addProductToCart();
        productPage.goToCart();

        logStep();
        CartPage cartPage=new CartPage();
        cartPage.fillInFields("test", "test", "tt@tt.tt");
        logger.info("Expected result: total price = 1 000 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"1 000 p.");

        logStep();
        cartPage.clickSubmit();
        assertTrue(cartPage.isPaymentErrorDisplayed());

        logStep();
        cartPage.selectDeliveryMethod("Самовывоз");
        logger.info("Expected result: total price = 1 100 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"1 010 p.");

        logStep();
        cartPage.selectDeliveryMethod("Курьер");
        logger.info("Expected result: total price = 2 200 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"2 020 p.");

        logStep();
        cartPage.setProductCount("product1","9");
        logger.info("Expected result: total price = 10 180.80 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"10 180.80 p.");
        cartPage.clickSubmit();

        logStep();
        cartPage.setProductCount("product1","10");
        logger.info("Expected result: total price = 10 100 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"10 100 p.");
        cartPage.clickSubmit();

        logStep();
        SuccessPage successPage=new SuccessPage();
        assertTrue(successPage.isThanksForOrderMessageDisplayed());
        logger.info("The order was completed");
    }
}
