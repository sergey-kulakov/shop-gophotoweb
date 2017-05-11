package shopGophotoweb.tests;

import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.CartPage;
import shopGophotoweb.pages.CatalogPage;
import shopGophotoweb.pages.ProductPage;
import shopGophotoweb.pages.SuccessPage;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;


public class ST7_003 extends BaseTest {

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
        cartPage.selectDeliveryMethod(CartPage.DeliveryMethods.Курьер);
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
        assertEquals(ordersPage.getOrderTotalPrice(orderNumber),"8 675.90 p.");
    }
}
