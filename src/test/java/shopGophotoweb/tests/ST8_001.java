package shopGophotoweb.tests;

import org.testng.annotations.BeforeMethod;
import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.*;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;


public class ST8_001 extends BaseTest {
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
        deliveryMethodsPage.checkDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Курьер);




    }
    @Override
    public void runTest() throws InterruptedException {

        logStep(1);
        CatalogPage catalogPage=new CatalogPage();
        catalogPage.goToProductPage("product8");
        ProductPage productPage=new ProductPage();
        assertEquals(productPage.getLblPrice(),"от  0 pуб.");


        logStep(2);
        productPage.addProductToCart();
        assertTrue(productPage.isLblUnselectedItemErrorDisplayed());

        logStep(3);
        productPage.setSelectValueLocator("Синий");
        assertEquals(productPage.getLblPrice(),"1 000 pуб.");

        logStep(4);
        productPage.addProductToCart();

        logStep(5);
        productPage.setSelectValueLocator("Красный");
        assertEquals(productPage.getLblOldPrice(),"2 000 pуб.");
        assertEquals(productPage.getLblDiscount(),"1 550 pуб.");

        logStep(6);
        productPage.addProductToCart();

        logStep(7);
        productPage.setSelectValueLocator("Серый");
        assertEquals(productPage.getAddButtonText(),"ОФОРМИТЬ ЗАКАЗ");

        logStep(8);
        productPage.setSelectValueLocator("Оранжевый");
        assertEquals(productPage.getLblPrice(),"0 pуб.");

        logStep(9);
        productPage.addProductToCart();


        logStep(10);
        Menu.goToCart();
        CartPage cartPage=new CartPage();
   //     cartPage.selectDeliveryMethod(CartPage.DeliveryMethods.Курьер);
        logger.info("Expected result: total price = 3 605.70 pуб.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"3 605.70 pуб.");

        logStep(11);
        cartPage.fillInFields("test", "test", "tt@tt.tt");


        logStep(12);
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
        assertEquals(ordersPage.getOrderTotalPrice(orderNumber),"3 605.70 pуб.");
    }
}
