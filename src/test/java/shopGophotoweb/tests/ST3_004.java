package shopGophotoweb.tests;

import org.testng.annotations.BeforeMethod;
import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.CartPage;
import shopGophotoweb.pages.CatalogPage;
import shopGophotoweb.pages.ProductPage;
import shopGophotoweb.pages.SuccessPage;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by sergey on 27.04.2017.
 */
public class ST3_004 extends BaseTest {
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
        deliveryMethodsPage.unCheckDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Курьер);
        deliveryMethodsPage.checkDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Самовывоз);
        deliveryMethodsPage.checkDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Почта);



    }
    @Override
    public void runTest() throws InterruptedException {

        logStep();
        CatalogPage catalogPage=new CatalogPage();
        catalogPage.goToProductPage("product1");
        ProductPage productPage=new ProductPage();
        productPage.addProductToCart();
        productPage.goToCart();

        logStep();
        CartPage cartPage=new CartPage();
        cartPage.fillInFields("test", "test", "tt@tt.tt");
        logger.info("Expected result: total price = 1 000 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"1 000 p.");
        assertTrue(cartPage.isDeliveryMethodDisplayed(CartPage.DeliveryMethods.Самовывоз));
        assertTrue(cartPage.isDeliveryMethodDisplayed(CartPage.DeliveryMethods.Почта));
        assertFalse(cartPage.isDeliveryMethodDisplayed(CartPage.DeliveryMethods.Курьер));

        logStep();
        cartPage.clickSubmit();
        assertTrue(cartPage.isPaymentErrorDisplayed());

        logStep();
        cartPage.selectDeliveryMethod(CartPage.DeliveryMethods.Самовывоз);
        logger.info("Expected result: total price = 1 010 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"1 010 p.");

        logStep();
        cartPage.selectDeliveryMethod(CartPage.DeliveryMethods.Почта);
        logger.info("Expected result: total price = 1 500 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"1 500 p.");
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
        assertEquals(ordersPage.getOrderTotalPrice(orderNumber),"1 500 p.");
    }
}
