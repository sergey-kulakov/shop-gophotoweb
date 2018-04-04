package shopGophotoweb.tests;


import org.testng.annotations.BeforeMethod;
import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.*;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;

public class ST2_001 extends BaseTest {
    @BeforeMethod
    public void setPreconditions(){

        logStep(1);
        browser.navigate(Browser.getAdminPageUrl());
        LoginPage loginPage=new LoginPage();
        loginPage.login();

        logStep(2);
        AdminMainPage adminMainPage = new AdminMainPage();
        adminMainPage.goToShop();

        logStep(3);
        Utilites.goToMenuName("МАГАЗИН");
        AdminProductsPage adminProductsPage=new AdminProductsPage();
        Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
        Utilites.goToSidebarItem(Utilites.SidebarItems.оплаты);

        logStep(4);
        PaymetsMethodsPage paymetsMethodsPage=new PaymetsMethodsPage();
        paymetsMethodsPage.checkPaymentMethodVisible(PaymetsMethodsPage.PaymentMethods.МОЙ_ВИД_ОПЛАТЫ_С_КОМИССИЕЙ_1);

        logStep(5);
        Utilites.goToSidebarItem(Utilites.SidebarItems.доставки);
        DeliveryMethodsPage deliveryMethodsPage=new DeliveryMethodsPage();
        deliveryMethodsPage.unCheckDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Курьер);
        deliveryMethodsPage.unCheckDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Самовывоз);
    }

    @Override
    public void runTest() throws InterruptedException {
        logStep(1);
        CatalogPage catalogPage=new CatalogPage();
        catalogPage.goToProductPage("product1");

        logStep(2);
        ProductPage productPage=new ProductPage();
        productPage.addProductToCart();
        Menu.goToCart();

        logStep(3);
        CartPage cartPage=new CartPage();
        cartPage.fillInFields("name","lastName","123@123.123");

        logStep(4);
        cartPage.clickSubmit();
        assertTrue(cartPage.isPaymentErrorDisplayed());

        logStep(5);
        cartPage.selectPaymentMethod(CartPage.PaymentMethods.МОЙ_ВИД_ОПЛАТЫ_С_КОМИССИЕЙ_1);
        assertEquals(cartPage.getTotalPrice(),"1 010 p.");

        logStep(6);
        cartPage.clickSubmit();
        SuccessPage successPage=new SuccessPage();
        assertTrue(successPage.isThanksForOrderMessageDisplayed());
        String orderNumber=successPage.getOrderNumber();

        logStep(7);
        browser.navigate(Browser.getAdminPageUrl());
        AdminMainPage adminMainPage = new AdminMainPage();
        adminMainPage.goToShop();

        logStep(8);
        Utilites.goToMenuName("МАГАЗИН");
        AdminProductsPage adminProductsPage=new AdminProductsPage();
        Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
        Utilites.goToSidebarItem(Utilites.SidebarItems.Заказы);;
        OrdersPage ordersPage=new OrdersPage();
        assertEquals(ordersPage.getOrderTotalPrice(orderNumber),"1 010 p.");
    }
}
