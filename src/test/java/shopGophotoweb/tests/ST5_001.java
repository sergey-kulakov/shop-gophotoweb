package shopGophotoweb.tests;

import org.testng.annotations.BeforeMethod;
import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.*;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;

public class ST5_001 extends BaseTest {
    /*@BeforeSuite
    public void fixPreconditions()
    {

        logStep();
        browser.navigate(Browser.getAdminPageUrl());
        LoginPage loginPage=new LoginPage();
        loginPage.login();

        logStep();
        AdminMainPage adminMainPage = new AdminMainPage();
        adminMainPage.goToShop();

        AdminProductsPage adminProductsPage=new AdminProductsPage();
        adminProductsPage.goToSettingsPage();


        Utilites.goToSidebarItem(Utilites.SidebarItems.доставки);
        DeliveryMethodsPage deliveryMethodsPage=new DeliveryMethodsPage();
        deliveryMethodsPage.goToDeliveryMethod(DeliveryMethodsPage.DeliveryMethods.Курьер);
        DeliveryMethodPage deliveryMethodPage=new DeliveryMethodPage();
        deliveryMethodPage.setDeliveryTax("1000");
        deliveryMethodPage.setAdditionalDeliveryTax("10");
        deliveryMethodPage.checkPaymentMethodVisible(PaymetsMethodsPage.PaymentMethods.МОЙ_ВИД_ОПЛАТЫ_С_КОМИССИЕЙ_1);
        deliveryMethodPage.checkPaymentMethodVisible(PaymetsMethodsPage.PaymentMethods.ЧЕРЕЗ_СИСТЕМУ_ЯНДЕКС_ДЕНЬГИ_С_КОММИСИЕЙ_3);
        deliveryMethodPage.clickSave();
        Utilites.logout();
    }*/

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

        logStep();
        Utilites.goToSidebarItem(Utilites.SidebarItems.доставки);
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
        cartPage.fillInFields("test", "test", "tt@tt.tt");

        logStep(3);
        logger.info("Expected result: total price = 2 020 pуб.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"2 020 pуб.");


            //переключаемся в админку и меняем стоимость доставки
            logStep(4);
            Browser.switchWindow();
            browser.navigate(Browser.getAdminPageUrl());
            AdminMainPage adminMainPage=new AdminMainPage();
            adminMainPage.goToShop();
            Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
            Utilites.goToSidebarItem(Utilites.SidebarItems.доставки);
            DeliveryMethodsPage deliveryMethodsPage=new DeliveryMethodsPage();
            deliveryMethodsPage.goToDeliveryMethod(DeliveryMethodsPage.DeliveryMethods.Курьер);
            DeliveryMethodPage deliveryMethodPage=new DeliveryMethodPage();
            deliveryMethodPage.setDeliveryTax("0");
            deliveryMethodPage.setAdditionalDeliveryTax("0");
            deliveryMethodPage.clickSave();

            //переключаемся на сайт
            logStep(5);
            Browser.switchWindow();
            cartPage.clickSubmit();

            assertTrue(cartPage.isErrorTotalOrderSumChangedDisplayed());
            logger.info("Expected result: total price = 1 010 pуб.");
            logger.info("Actual result: total price = "+cartPage.getTotalPrice());
            assertEquals(cartPage.getTotalPrice(),"1 010 pуб.");

            cartPage.clickSubmit();
            SuccessPage successPage=new SuccessPage();
            successPage.isThanksForOrderMessageDisplayed();
            String orderNumber=successPage.getOrderNumber();
            logger.info("The order was completed");
            browser.navigate(Browser.getAdminPageUrl());
            adminMainPage.goToShop();
            logStep();
            Utilites.goToMenuName("МАГАЗИН");
            Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
            Utilites.goToSidebarItem(Utilites.SidebarItems.Заказы);
            OrdersPage ordersPage=new OrdersPage();
            assertEquals(ordersPage.getOrderTotalPrice(orderNumber),"1 010 pуб.");



        logStep("Postconditions");
        Browser.switchWindow();
        deliveryMethodPage.setDeliveryTax("1000");
        deliveryMethodPage.setAdditionalDeliveryTax("10");
        deliveryMethodPage.clickSave();



    }}