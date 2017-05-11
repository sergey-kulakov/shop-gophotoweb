package shopGophotoweb.tests;

import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.CartPage;
import shopGophotoweb.pages.CatalogPage;
import shopGophotoweb.pages.ProductPage;
import shopGophotoweb.pages.SuccessPage;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;


public class ST7_015 extends BaseTest {

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

        logger.info("Expected result: total price = 2 020 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"2 020 p.");

        logStep();
        cartPage.applyPromoCode("15");
        logger.info("Expected result: total price = 1 010 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"1 010 p.");

        logStep();
        cartPage.setProductCount("product1","5");
        logger.info("Expected result: total price = 5 050 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"5 050 p.");


        logStep();
        cartPage.clickSubmit();
        SuccessPage successPage=new SuccessPage();
        assertTrue(successPage.isThanksForOrderMessageDisplayed());
        String orderNumber=successPage.getOrdrerNumber();
        logger.info("The order was completed");

        logStep();
        browser.navigate(Browser.getAdminPageUrl());
        LoginPage loginPage=new LoginPage();
        loginPage.login();
        AdminMainPage adminMainPage = new AdminMainPage();
        adminMainPage.goToShop();
        logStep();
        Utilites.goToMenuName("МАГАЗИН");
        AdminProductsPage adminProductsPage=new AdminProductsPage();
        adminProductsPage.goToOrdersPage();
        OrdersPage ordersPage=new OrdersPage();
        assertEquals(ordersPage.getOrderTotalPrice(orderNumber),"5 050 p.");
    }
}
