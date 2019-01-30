package shopGophotoweb.tests;


import org.testng.annotations.BeforeMethod;
import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.*;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;

public class ST2_004 extends BaseTest {
    @BeforeMethod
    public void setPreconditions() {

        logStep(1);
        browser.navigate(Browser.getAdminPageUrl());
        LoginPage loginPage = new LoginPage();
        loginPage.login();

        logStep(2);
        AdminMainPage adminMainPage = new AdminMainPage();
        adminMainPage.goToShop();

        logStep(3);
        AdminProductsPage adminProductsPage = new AdminProductsPage();
        Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
        Utilites.goToSidebarItem(Utilites.SidebarItems.оплаты);

        logStep(4);
        PaymetsMethodsPage paymetsMethodsPage = new PaymetsMethodsPage();
        paymetsMethodsPage.unCheckAllMethods();


        logStep(5);
        Utilites.goToSidebarItem(Utilites.SidebarItems.доставки);
        DeliveryMethodsPage deliveryMethodsPage = new DeliveryMethodsPage();
        deliveryMethodsPage.unCheckAllMethods();
        deliveryMethodsPage.checkDeliveryMethodVisible(DeliveryMethodsPage.DeliveryMethods.Курьер);
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
        cartPage.fillInFields("name","lastName","123@123.123");


        logStep(3);
        cartPage.selectDeliveryMethod(CartPage.DeliveryMethods.Курьер);
        assertEquals(cartPage.getTotalPrice(),"2 000 pуб.");

        logStep(4);
        cartPage.setProductCount("product1","9");
        assertEquals(cartPage.getTotalPrice(),"10 080 pуб.");

        logStep(5);
        cartPage.setProductCount("product1","10");
        assertEquals(cartPage.getTotalPrice(),"10 000 pуб.");

        logStep(6);
        cartPage.clickSubmit();
        SuccessPage successPage=new SuccessPage();
        assertTrue(successPage.isThanksForOrderMessageDisplayed());
        String orderNumber=successPage.getOrderNumber();
        logger.info("The order was completed");

        logStep(7);
        browser.navigate(Browser.getAdminPageUrl());
        AdminMainPage adminMainPage = new AdminMainPage();
        adminMainPage.goToShop();
        logStep(8);
        Utilites.goToMenuName("МАГАЗИН");
        AdminProductsPage adminProductsPage=new AdminProductsPage();
        Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
        Utilites.goToSidebarItem(Utilites.SidebarItems.Заказы);
        OrdersPage ordersPage=new OrdersPage();
        assertEquals(ordersPage.getOrderTotalPrice(orderNumber),"10 000 pуб.");
    }
}
