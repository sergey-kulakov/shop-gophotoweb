package shopGophotoweb.tests;

import org.testng.annotations.BeforeMethod;
import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.*;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;

public class ST4_002 extends BaseTest {
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
        Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
        Utilites.goToSidebarItem(Utilites.SidebarItems.оплаты);

        logStep();
        PaymetsMethodsPage paymetsMethodsPage=new PaymetsMethodsPage();
        paymetsMethodsPage.unCheckAllMethods();
        paymetsMethodsPage.checkPaymentMethodVisible(PaymetsMethodsPage.PaymentMethods.МОЙ_ВИД_ОПЛАТЫ_С_КОМИССИЕЙ_1);
        paymetsMethodsPage.checkPaymentMethodVisible(PaymetsMethodsPage.PaymentMethods.ЧЕРЕЗ_СИСТЕМУ_ЯНДЕКС_ДЕНЬГИ_С_КОММИСИЕЙ_3);

        logStep();
        Utilites.goToSidebarItem(Utilites.SidebarItems.доставки);
        DeliveryMethodsPage deliveryMethodsPage=new DeliveryMethodsPage();
        deliveryMethodsPage.unCheckAllMethods();


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
        cartPage.fillInFields("test", "test", "tt@tt.tt");

        logStep(3);
        logger.info("Expected result: total price = 1 000 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"1 000 p.");


        //переключаемся в админку и скрываем метод доставки
        logStep(4);
        cartPage.clickSubmit();
        assertTrue(cartPage.isPaymentErrorDisplayed());

        logStep(5);
        cartPage.selectPaymentMethod(CartPage.PaymentMethods.ЧЕРЕЗ_СИСТЕМУ_ЯНДЕКС_ДЕНЬГИ_С_КОММИСИЕЙ_3);
        assertEquals(cartPage.getTotalPrice(),"1 030 p.");

        //Uncheck payment method
        logStep(6);
        Browser.switchWindow();
        browser.navigate(Browser.getAdminPageUrl());
        AdminMainPage adminMainPage=new AdminMainPage();
        adminMainPage.goToShop();

        Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
        Utilites.goToSidebarItem(Utilites.SidebarItems.оплаты);
        PaymetsMethodsPage paymentMethods= new PaymetsMethodsPage();
        paymentMethods.unCheckPaymentMethodVisible(PaymetsMethodsPage.PaymentMethods.ЧЕРЕЗ_СИСТЕМУ_ЯНДЕКС_ДЕНЬГИ_С_КОММИСИЕЙ_3);

        //переключаемся на сайт
        //Shich to site
        logStep(7);
        Browser.switchWindow();
        cartPage.clickSubmit();

        assertTrue(cartPage.isErrorPaymentMethodUnavailableDislayed());
        assertTrue(cartPage.isErrorTotalOrderSumChangedDisplayed());

        // Switch to 2 payment method
        logStep(8);
        cartPage.selectPaymentMethod(CartPage.PaymentMethods.МОЙ_ВИД_ОПЛАТЫ_С_КОМИССИЕЙ_1);

        //Uncheck second payment method
        logStep(9);
        Browser.switchWindow();
        Utilites.goToSidebarItem(Utilites.SidebarItems.оплаты);
        paymentMethods.unCheckPaymentMethodVisible(PaymetsMethodsPage.PaymentMethods.МОЙ_ВИД_ОПЛАТЫ_С_КОМИССИЕЙ_1);

       //Switch to site
        logStep(10);
        Browser.switchWindow();
        cartPage.clickSubmit();
        assertTrue(cartPage.isErrorPaymentMethodUnavailableDislayed());
        assertTrue(cartPage.isErrorTotalOrderSumChangedDisplayed());
        logger.info("Expected result: total price = 1 000 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"1 000 p.");

        logStep(11);
        cartPage.clickSubmit();
        SuccessPage successPage=new SuccessPage();
        successPage.isThanksForOrderMessageDisplayed();
        String orderNumber=successPage.getOrderNumber();
        logger.info("The order was completed");

        logStep(12);
        browser.navigate(Browser.getAdminPageUrl());
        adminMainPage.goToShop();
        logStep(13);
        Utilites.goToMenuName("МАГАЗИН");
        Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
        Utilites.goToSidebarItem(Utilites.SidebarItems.Заказы);
        OrdersPage ordersPage=new OrdersPage();
        assertEquals(ordersPage.getOrderTotalPrice(orderNumber),"1 000 p.");


    }}