package shopGophotoweb.tests;

import org.testng.annotations.BeforeMethod;
import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.*;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;

public class ST5_002 extends BaseTest {
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

        //переключаемся в админку и меняем стоимость товара
        logStep(4);
        Browser.switchWindow();
        browser.navigate(Browser.getAdminPageUrl());
        AdminMainPage adminMainPage=new AdminMainPage();
        adminMainPage.goToShop();
        AdminProductsPage adminProductsPage=new AdminProductsPage();
        adminProductsPage.goToProductPage("product1");

        AdminProductPage adminProductPage=new AdminProductPage();
        adminProductPage.setPrice("900");

        //переключаемся на сайт
        logStep(5);
        Browser.switchWindow();
        cartPage.clickSubmit();

        assertTrue(cartPage.isErrorTotalOrderSumChangedDisplayed());
        logger.info("Expected result: total price = 1 919 pуб.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"1 919 pуб.");

        logStep(6);
        cartPage.clickSubmit();
        SuccessPage successPage=new SuccessPage();
        successPage.isThanksForOrderMessageDisplayed();
        String orderNumber=successPage.getOrderNumber();
        logger.info("The order was completed");

        browser.navigate(Browser.getAdminPageUrl());
        adminMainPage.goToShop();
        Utilites.goToMenuName("МАГАЗИН");
        Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
        Utilites.goToSidebarItem(Utilites.SidebarItems.Заказы);
        OrdersPage ordersPage=new OrdersPage();
        assertEquals(ordersPage.getOrderTotalPrice(orderNumber),"1 919 pуб.");

        logStep("Postconditions");
        Browser.switchWindow();
        adminProductPage.setPrice("1000");


    }}