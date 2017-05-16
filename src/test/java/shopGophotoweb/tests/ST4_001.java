package shopGophotoweb.tests;

import org.testng.annotations.BeforeMethod;
import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.CartPage;
import shopGophotoweb.pages.CatalogPage;
import shopGophotoweb.pages.ProductPage;
import shopGophotoweb.pages.SuccessPage;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;

public class ST4_001 extends BaseTest {
    @BeforeMethod
    public void setPreconditions() throws InterruptedException {

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
        paymetsMethodsPage.unCheckAllMethods();

        logStep();
        Utilites.goToSidebarItem("Методы доставки");
        DeliveryMethodsPage deliveryMethodsPage=new DeliveryMethodsPage();
        deliveryMethodsPage.unCheckAllMethods();
        deliveryMethodsPage.checkDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Курьер);
        deliveryMethodsPage.checkDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Самовывоз);

        logStep();
        adminMainPage.goToSite();
        Browser.switchWindow();




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
        cartPage.selectDeliveryMethod(CartPage.DeliveryMethods.Курьер);
        cartPage.fillInFields("test", "test", "tt@tt.tt");
        logger.info("Expected result: total price = 2 000 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"2 000 p.");

        //переключаемся в админку и скрываем метод доставки
        logStep();
        Browser.switchWindow();
        browser.navigate(Browser.getAdminPageUrl());
        AdminMainPage adminMainPage=new AdminMainPage();
        adminMainPage.goToShop();
        AdminProductsPage adminProductsPage=new AdminProductsPage();
        adminProductsPage.goToSettingsPage();

        logStep();
        Utilites.goToSidebarItem("Методы доставки");
        DeliveryMethodsPage deliveryMethodsPage=new DeliveryMethodsPage();
        deliveryMethodsPage.unCheckDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Курьер);

        //переключаемся на сайт
        logStep();
        Browser.switchWindow();
        cartPage.clickSubmit();

        logStep();
        assertTrue(cartPage.isErrorPaymentMethodUnavailableDislayed());
        assertTrue(cartPage.isErrorTotalOrderSumChangedDisplayed());

        logStep();
        cartPage.selectDeliveryMethod(CartPage.DeliveryMethods.Самовывоз);

        logStep();
        logger.info("Expected result: total price = 1 000 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"1 000 p.");


        //переключаемся в админку и скрываем метод доставки
        logStep();
        Browser.switchWindow();
        deliveryMethodsPage.unCheckDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Самовывоз);

        //переключаемся на сайт
        logStep();
        Browser.switchWindow();
        cartPage.clickSubmit();

        logStep();
        assertTrue(cartPage.isErrorPaymentMethodUnavailableDislayed());


        logStep();
        cartPage.clickSubmit();
        SuccessPage successPage=new SuccessPage();
        successPage.isThanksForOrderMessageDisplayed();
        String orderNumber=successPage.getOrdrerNumber();
        logger.info("The order was completed");

        logStep();
        browser.navigate(Browser.getAdminPageUrl());
        adminMainPage.goToShop();
        logStep();
        Utilites.goToMenuName("МАГАЗИН");
        adminProductsPage.goToOrdersPage();
        OrdersPage ordersPage=new OrdersPage();
        assertEquals(ordersPage.getOrderTotalPrice(orderNumber),"1 000 p.");


    }}