package shopGophotoweb.tests;

import org.testng.annotations.BeforeMethod;
import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.*;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;

public class ST4_004 extends BaseTest {
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
        paymetsMethodsPage.checkPaymentMethodVisible(PaymetsMethodsPage.PaymentMethods.МОЙ_ВИД_ОПЛАТЫ_С_КОМИССИЕЙ_1);

        logStep();
        Utilites.goToSidebarItem("Методы доставки");
        DeliveryMethodsPage deliveryMethodsPage=new DeliveryMethodsPage();
        deliveryMethodsPage.unCheckAllMethods();
        deliveryMethodsPage.checkDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Курьер);

        logStep();
        adminMainPage.goToSite();
        Browser.switchWindow();


    }
    @Override
    public void runTest() throws InterruptedException {

        logStep(1);
        CatalogPage catalogPage=new CatalogPage();
        catalogPage.goToProductPage("product1");
        ProductPage productPage=new ProductPage();
        productPage.addProductToCart();
        Menu.goToCart();

        logStep(2);
        CartPage cartPage=new CartPage();
        cartPage.selectDeliveryMethod(CartPage.DeliveryMethods.Курьер);
        cartPage.fillInFields("test", "test", "tt@tt.tt");

        logStep(3);
        logger.info("Expected result: total price = 2 020 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"2 020 p.");

        //переключаемся в админку и скрываем метод доставки
        logStep(4);
        Browser.switchWindow();
        browser.navigate(Browser.getAdminPageUrl());
        AdminMainPage adminMainPage=new AdminMainPage();
        adminMainPage.goToShop();
        AdminProductsPage adminProductsPage=new AdminProductsPage();
        adminProductsPage.goToSettingsPage();

        Utilites.goToSidebarItem("Методы доставки");
        DeliveryMethodsPage deliveryMethodsPage=new DeliveryMethodsPage();
        deliveryMethodsPage.goToDeliveryMethod(DeliveryMethodsPage.DeliveryMethods.Курьер);

        DeliveryMethodPage deliveryMethodPage=new DeliveryMethodPage();
        deliveryMethodPage.unCheckPaymentMethodVisible(PaymetsMethodsPage.PaymentMethods.МОЙ_ВИД_ОПЛАТЫ_С_КОМИССИЕЙ_1);

        //переключаемся на сайт
        logStep(5);
        Browser.switchWindow();
        cartPage.clickSubmit();
        assertTrue(cartPage.isErrorPaymentMethodUnavailableDislayed());


        logStep(6);
        logger.info("Expected result: total price = 2 000 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"2 000 p.");
        cartPage.clickSubmit();

        SuccessPage successPage=new SuccessPage();
        successPage.isThanksForOrderMessageDisplayed();
        String orderNumber=successPage.getOrdrerNumber();
        logger.info("The order was completed");

        logStep(7);
        browser.navigate(Browser.getAdminPageUrl());
        adminMainPage.goToShop();
        Utilites.goToMenuName("МАГАЗИН");
        adminProductsPage.goToOrdersPage();
        OrdersPage ordersPage=new OrdersPage();
        assertEquals(ordersPage.getOrderTotalPrice(orderNumber),"2 000 p.");

        Browser.switchWindow();
        deliveryMethodPage.checkPaymentMethodVisible(PaymetsMethodsPage.PaymentMethods.МОЙ_ВИД_ОПЛАТЫ_С_КОМИССИЕЙ_1);


    }}