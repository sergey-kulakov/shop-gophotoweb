package shopGophotoweb.tests;

import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.*;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;


public class ST7_003 extends BaseTest {

    @Override
    public void runTest() throws InterruptedException {

        logStep(1);
        CatalogPage catalogPage=new CatalogPage();
        catalogPage.goToProductPage("product1");

        ProductPage product1Page=new ProductPage();
        product1Page.addProductToCart();
        product1Page.goToCatalogByBreadecrumbs();

        logStep(2);
        catalogPage.goToProductPage("product3");
        ProductPage product3Page=new ProductPage();
        product3Page.addProductToCart();
        Menu.goToCart();

        logStep(3);
        CartPage cartPage=new CartPage();
        cartPage.fillInFields("test", "test", "tt@tt.tt");

        logStep(4);
        cartPage.selectDeliveryMethod(CartPage.DeliveryMethods.Курьер);
        logger.info("Expected result: total price = 2 535.10 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"2 535.10 p.");

        logStep(5);
        cartPage.setProductCount("product1","3");
        logger.info("Expected result: total price = 10 100 p.4 575.30 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"4 575.30 p.");

        logStep(6);
        cartPage.setProductCount("product3","4");
        logger.info("Expected result: total price = 6 120.60 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"6 120.60 p.");

        logStep(7);
        cartPage.applyPromoCode("3");
        assertTrue(cartPage.isPromocodeErrorDisplayed());
        logger.info("Promocode error displayed");

        logStep(8);
        cartPage.setProductCount("product1","5");
        logger.info("Expected result: total price = 8 160.80 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"8 160.80 p.");

        logStep(9);
        cartPage.applyPromoCode("3");
        assertTrue(cartPage.isPromocodeErrorDisplayed());
        logger.info("Promocode error displayed");

        logStep(10);
        cartPage.setProductCount("product1","6");
        logger.info("Expected result: total price = 9 180.90 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"9 180.90 p.");

        logStep(11);
        cartPage.applyPromoCode("3");
        logger.info("Expected result: total price = 8 675.90 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"8 675.90 p.");

        logStep(12);
        cartPage.clickSubmit();
        SuccessPage successPage=new SuccessPage();
        assertTrue(successPage.isThanksForOrderMessageDisplayed());
        String orderNumber=successPage.getOrderNumber();
        logger.info("The order was completed");

        logStep(13);
        browser.navigate(Browser.getAdminPageUrl());
        LoginPage loginPage=new LoginPage();
        loginPage.login();
        AdminMainPage adminMainPage = new AdminMainPage();
        adminMainPage.goToShop();

        logStep(14);
        Utilites.goToMenuName("МАГАЗИН");
        Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
        Utilites.goToSidebarItem(Utilites.SidebarItems.Заказы);
        OrdersPage ordersPage=new OrdersPage();
        assertEquals(ordersPage.getOrderTotalPrice(orderNumber),"8 675.90 p.");
    }
}
