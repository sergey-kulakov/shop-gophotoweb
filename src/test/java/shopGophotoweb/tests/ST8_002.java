package shopGophotoweb.tests;

import org.testng.annotations.BeforeMethod;
import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.*;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;


public class ST8_002 extends BaseTest {
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
        Utilites.goToSidebarItem(Utilites.SidebarItems.Оформление);
        Utilites.goToSidebarItem(Utilites.SidebarItems.предзаказа);
        PreOrderSettingsPage preOrderPage=new PreOrderSettingsPage();
        preOrderPage.activatePreOrder();

        logStep();
        Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
        Utilites.goToSidebarItem(Utilites.SidebarItems.оплаты);
        PaymetsMethodsPage paymetsMethodsPage=new PaymetsMethodsPage();
        paymetsMethodsPage.unCheckAllMethods();
        paymetsMethodsPage.checkPaymentMethodVisible(PaymetsMethodsPage.PaymentMethods.МОЙ_ВИД_ОПЛАТЫ_С_КОМИССИЕЙ_1);

        logStep();
        Utilites.goToSidebarItem(Utilites.SidebarItems.доставки);
        DeliveryMethodsPage deliveryMethodsPage=new DeliveryMethodsPage();
        deliveryMethodsPage.unCheckAllMethods();
        deliveryMethodsPage.checkDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Курьер);




    }
    @Override
    public void runTest() throws InterruptedException {

        logStep(1);
        CatalogPage catalogPage=new CatalogPage();
        catalogPage.goToProductPage("product9");
        ProductPage productPage=new ProductPage();
        assertEquals(productPage.getLblPrice(),"от  1 000 p.");


        logStep(2);
        productPage.addProductToCart();
        assertTrue(productPage.isLblUnselectedItemErrorDisplayed());

        logStep(3);
        productPage.setSelectValueLocator("Белый");
        assertEquals(productPage.getLblPrice(),"1 000 p.");

        logStep(4);
        productPage.addProductToCart();
        assertTrue(productPage.isLblUnselectedItemErrorDisplayed());

        logStep(5);
        productPage.setSelectValueLocator("m");
        assertEquals(productPage.getLblOldPrice(),"2 000 p.");
        assertEquals(productPage.getLblDiscount(),"1 500 p.");

        logStep(6);
        productPage.addProductToCart();
  //      assertTrue дописать про счетчик корзины в меню

        logStep(7);
        assertTrue(productPage.isSelectOptionPresent("Белый"));
        assertTrue(productPage.isSelectOptionPresent("Красный"));

        logStep(8);
        productPage.setSelectValueLocator("Красный");
        assertEquals(productPage.getAddButtonText(),"ОФОРМИТЬ ЗАКАЗ");


        logStep(9);
        productPage.setSelectValueLocator("Выберите Размер");
        assertEquals(productPage.getLblPrice(),"5 000 p.");

        logStep(10);
        productPage.setSelectValueLocator("s");
        assertEquals(productPage.getLblPrice(),"5 000 p.");

        logStep(11);
        productPage.addProductToCart();

        logStep(12);
        assertTrue(productPage.isSelectOptionPresent("Белый"));
        assertTrue(productPage.isSelectOptionPresent("Красный"));
        assertTrue(productPage.isSelectOptionPresent("Желтый"));

        logStep(13);
        Menu.goToCart();
        CartPage cartPage=new CartPage();
        logger.info("Expected result: total price = 7 585.10 p.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"7 585.10 p.");

        logStep(14);
        cartPage.fillInFields("test", "test", "tt@tt.tt");




        logStep(15);
        cartPage.clickSubmit();
        SuccessPage successPage=new SuccessPage();
        assertTrue(successPage.isThanksForOrderMessageDisplayed());
        String orderNumber=successPage.getOrderNumber();
        logger.info("The order was completed");

        logStep();
        browser.navigate(Browser.getAdminPageUrl());
        AdminMainPage adminMainPage = new AdminMainPage();
        adminMainPage.goToShop();
        logStep();
        Utilites.goToMenuName("МАГАЗИН");
        Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
        Utilites.goToSidebarItem(Utilites.SidebarItems.Заказы);
        OrdersPage ordersPage=new OrdersPage();
        assertEquals(ordersPage.getOrderTotalPrice(orderNumber),"7 585.10 p.");
    }
}
