package shopGophotoweb.tests;

import org.testng.annotations.BeforeMethod;
import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.*;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;

public class ST4_001 extends BaseTest {
    @BeforeMethod
    public void setPreconditions() throws InterruptedException {

        logStep(1);
        browser.navigate(Browser.getAdminPageUrl());
        LoginPage loginPage=new LoginPage();
        loginPage.login();

        logStep(2);
        AdminMainPage adminMainPage = new AdminMainPage();
        adminMainPage.goToShop();

        AdminProductsPage adminProductsPage=new AdminProductsPage();
        Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
        Utilites.goToSidebarItem(Utilites.SidebarItems.оплаты);

        logStep(3);
        PaymetsMethodsPage paymetsMethodsPage=new PaymetsMethodsPage();
        paymetsMethodsPage.unCheckAllMethods();

        logStep(4);
        Utilites.goToSidebarItem(Utilites.SidebarItems.доставки);
        DeliveryMethodsPage deliveryMethodsPage=new DeliveryMethodsPage();
        deliveryMethodsPage.unCheckAllMethods();
        deliveryMethodsPage.checkDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Курьер);
        deliveryMethodsPage.checkDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Самовывоз);

        logStep(5);
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
        logger.info("Expected result: total price = 2 000 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"2 000 p.");

        //переключаемся в админку и скрываем метод доставки
        logStep(3);
        Browser.switchWindow();
        browser.navigate(Browser.getAdminPageUrl());
        AdminMainPage adminMainPage=new AdminMainPage();
        adminMainPage.goToShop();
        AdminProductsPage adminProductsPage=new AdminProductsPage();

        logStep(4);
        Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
        Utilites.goToSidebarItem(Utilites.SidebarItems.доставки);
        DeliveryMethodsPage deliveryMethodsPage=new DeliveryMethodsPage();
        deliveryMethodsPage.unCheckDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Курьер);


        //переключаемся на сайт
        Browser.switchWindow();
        cartPage.clickSubmit();
        assertTrue(cartPage.isErrorDeliveryMethodUnavailableDislayed());
        assertTrue(cartPage.isErrorTotalOrderSumChangedDisplayed());

        logger.info("Expected result: total price = 1 000 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"1 000 p.");


        logStep(5);
        //переключаемся в админку и скрываем метод доставки
        Browser.switchWindow();
        deliveryMethodsPage.unCheckDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Самовывоз);

        //переключаемся на сайт
        logStep(6);
        Browser.switchWindow();
        cartPage.clickSubmit();

        logStep();
        assertTrue(cartPage.isErrorDeliveryMethodUnavailableDislayed());


        logStep();
        cartPage.clickSubmit();
        SuccessPage successPage=new SuccessPage();
        successPage.isThanksForOrderMessageDisplayed();
        String orderNumber=successPage.getOrderNumber();
        logger.info("The order was completed");

        logStep();
        browser.navigate(Browser.getAdminPageUrl());
        adminMainPage.goToShop();
        logStep();
        Utilites.goToMenuName("МАГАЗИН");
        Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
        Utilites.goToSidebarItem(Utilites.SidebarItems.Заказы);
        OrdersPage ordersPage=new OrdersPage();
        assertEquals(ordersPage.getOrderTotalPrice(orderNumber),"1 000 p.");


    }}