package shopGophotoweb.tests;

import org.testng.annotations.BeforeMethod;
import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.*;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class ST3_005 extends BaseTest {
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
        paymetsMethodsPage.checkPaymentMethodVisible(PaymetsMethodsPage.PaymentMethods.МОЙ_ВИД_ОПЛАТЫ_С_КОМИССИЕЙ_1);
        paymetsMethodsPage.checkPaymentMethodVisible(PaymetsMethodsPage.PaymentMethods.ЧЕРЕЗ_СИСТЕМУ_ЯНДЕКС_ДЕНЬГИ_С_КОММИСИЕЙ_3);

        logStep();
        Utilites.goToSidebarItem("Методы доставки");
        DeliveryMethodsPage deliveryMethodsPage=new DeliveryMethodsPage();
        deliveryMethodsPage.checkDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Курьер);
        deliveryMethodsPage.checkDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Самовывоз);
        deliveryMethodsPage.checkDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Почта);



    }
    @Override
    public void runTest() throws InterruptedException {

        logStep(1);
        CatalogPage catalogPage=new CatalogPage();
        catalogPage.goToProductPage("product1");
        ProductPage productPage1=new ProductPage();
        productPage1.addProductToCart();
        productPage1.goToCatalogByBreadecrumbs();


        logStep(2);
        catalogPage.goToProductPage("product10");
        ProductPage productPage10=new ProductPage();
        productPage10.addProductToCart();
        Menu.goToCart();

        logStep(3);
        CartPage cartPage=new CartPage();
        cartPage.fillInFields("test", "test", "tt@tt.tt");
        logger.info("Expected result: total price = 1 000 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertTrue(cartPage.isDeliveryMethodDisplayed(CartPage.DeliveryMethods.Самовывоз));
        assertTrue(cartPage.isDeliveryMethodDisplayed(CartPage.DeliveryMethods.Почта));
        assertFalse(cartPage.isDeliveryMethodDisplayed(CartPage.DeliveryMethods.Курьер));

        logStep(4);
        cartPage.selectDeliveryMethod(CartPage.DeliveryMethods.Самовывоз);
        logger.info("Expected result: total price = 2 525 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertTrue(cartPage.isPaymentMethodDisplayed(CartPage.PaymentMethods.МОЙ_ВИД_ОПЛАТЫ_С_КОМИССИЕЙ_1));
        assertTrue(cartPage.isPaymentMethodDisplayed(CartPage.PaymentMethods.ЧЕРЕЗ_СИСТЕМУ_ЯНДЕКС_ДЕНЬГИ_С_КОММИСИЕЙ_3));
        assertEquals(cartPage.getTotalPrice(),"2 525 p.");

        logStep(5);
        cartPage.selectDeliveryMethod(CartPage.DeliveryMethods.Почта);
        logger.info("Expected result: total price = 3 000 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"3 000 p.");

        logStep(6);
        cartPage.selectDeliveryMethod(CartPage.DeliveryMethods.Курьер);
        logger.info("Expected result: total price = 3 535 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"3 535 p.");
        cartPage.clickSubmit();

        logStep();
        SuccessPage successPage=new SuccessPage();
        assertTrue(successPage.isThanksForOrderMessageDisplayed());
        String orderNumber=successPage.getOrdrerNumber();
        logger.info("The order was completed");

        logStep();
        browser.navigate(Browser.getAdminPageUrl());
        AdminMainPage adminMainPage = new AdminMainPage();
        adminMainPage.goToShop();
        logStep();
        Utilites.goToMenuName("МАГАЗИН");
        AdminProductsPage adminProductsPage=new AdminProductsPage();
        adminProductsPage.goToOrdersPage();
        OrdersPage ordersPage=new OrdersPage();
        assertEquals(ordersPage.getOrderTotalPrice(orderNumber),"3 535 p.");
    }
}