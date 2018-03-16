package shopGophotoweb.tests;

import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.*;
import org.testng.annotations.BeforeMethod;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.*;


public class ST6_001 extends BaseTest {
    @BeforeMethod
    public void setPreconditions(){

        logStep(1);
        browser.navigate(Browser.getAdminPageUrl());
        LoginPage loginPage=new LoginPage();
        loginPage.login();

        logStep(2);
        AdminMainPage adminMainPage = new AdminMainPage();
        adminMainPage.goToShop();

        logStep(3);
        Utilites.goToMenuName("МАГАЗИН");
        AdminProductsPage adminProductsPage=new AdminProductsPage();
        Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
        Utilites.goToSidebarItem(Utilites.SidebarItems.оплаты);

        logStep(4);
        PaymetsMethodsPage paymetsMethodsPage=new PaymetsMethodsPage();
        paymetsMethodsPage.unCheckAllMethods();
        paymetsMethodsPage.checkPaymentMethodVisible(PaymetsMethodsPage.PaymentMethods.МОЙ_ВИД_ОПЛАТЫ_С_КОМИССИЕЙ_1);

        logStep(5);
        Utilites.goToSidebarItem(Utilites.SidebarItems.доставки);
        DeliveryMethodsPage deliveryMethodsPage=new DeliveryMethodsPage();
        deliveryMethodsPage.unCheckAllMethods();
        deliveryMethodsPage.checkDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Самовывоз);
    }

    @Override
    public void runTest() throws InterruptedException {
        logStep(1);
        CatalogPage catalogPage=new CatalogPage();
        catalogPage.goToProductPage("product7");

        logStep(2);
        ProductPage productPage=new ProductPage();
        productPage.addProductToCart();
        Menu.goToCart();

        logStep(3);
        CartPage cartPage = new CartPage();
        logger.info("Cart page appeared");
        cartPage.clickSubmit();
        assertTrue(cartPage.isFormErrorDisplayed());
        logger.info("The order was not completed. Error is displayed.");

        logStep(4);
        cartPage.fillInFields("test", "test", "tt@tt.tt");
        assertEquals(cartPage.getTotalPrice(),"0 p.");
        cartPage.clickSubmit();

        logStep(5);
        SuccessPage successPage=new SuccessPage();
        assertTrue(successPage.isThanksForOrderMessageDisplayed());
        String orderNumber=successPage.getOrderNumber();
        logger.info("The order was completed");

        logStep(6);
        browser.navigate(Browser.getAdminPageUrl());
        AdminMainPage adminMainPage = new AdminMainPage();
        adminMainPage.goToShop();

        logStep(7);
        Utilites.goToMenuName("МАГАЗИН");
        Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
        Utilites.goToSidebarItem(Utilites.SidebarItems.Заказы);
        OrdersPage ordersPage=new OrdersPage();
        assertEquals(ordersPage.getOrderTotalPrice(orderNumber),"0 p.");

    }
}
