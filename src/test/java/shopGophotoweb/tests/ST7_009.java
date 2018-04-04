package shopGophotoweb.tests;

import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.*;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;


public class ST7_009 extends BaseTest {

    @Override
    public void runTest() throws InterruptedException {

        logStep(1);
        CatalogPage catalogPage=new CatalogPage();
        catalogPage.goToProductPage("product1");

        ProductPage product1Page=new ProductPage();
        product1Page.addProductToCart();
        Menu.goToCart();

        logStep(2);
        CartPage cartPage=new CartPage();
        cartPage.fillInFields("test", "test", "tt@tt.tt");

        logStep(3);
        cartPage.setProductCount("product1","5");
        logger.info("Expected result: total price = 6 100.40 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"6 100.40 p.");


        logStep(4);
        cartPage.applyPromoCode("9");
        assertTrue(cartPage.isPromocodeErrorDisplayed());

        logStep(5);
        cartPage.setProductCount("product1","6");
        logger.info("Expected result: total price = 7 120.50 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"7 120.50 p.");

        logStep(6);
        cartPage.applyPromoCode("9");
        logger.info("Expected result: total price = 6 211.50 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"6 211.50 p.");

        logStep(7);
        cartPage.clickSubmit();
        SuccessPage successPage=new SuccessPage();
        assertTrue(successPage.isThanksForOrderMessageDisplayed());
        String orderNumber=successPage.getOrderNumber();
        logger.info("The order was completed");

        logStep(8);
        browser.navigate(Browser.getAdminPageUrl());
        LoginPage loginPage=new LoginPage();
        loginPage.login();
        AdminMainPage adminMainPage = new AdminMainPage();
        adminMainPage.goToShop();

        logStep(9);
        Utilites.goToMenuName("МАГАЗИН");
        Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
        Utilites.goToSidebarItem(Utilites.SidebarItems.Заказы);
        OrdersPage ordersPage=new OrdersPage();
        assertEquals(ordersPage.getOrderTotalPrice(orderNumber),"6 211.50 p.");
    }
}
