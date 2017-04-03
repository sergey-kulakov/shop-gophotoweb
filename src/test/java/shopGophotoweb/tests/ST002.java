package shopGophotoweb.tests;

import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.CartPage;
import shopGophotoweb.pages.CatalogPage;
import shopGophotoweb.pages.ProductPage;
import shopGophotoweb.pages.SuccessPage;
import org.testng.annotations.BeforeMethod;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;


public class ST002 extends BaseTest {
    @BeforeMethod
    public void setPreconditions(){

        logStep();
        browser.navigate(Browser.getAdminPageUrl());
        LoginPage loginPage=new LoginPage();
        loginPage.login();

        logStep();
        AdminMainPage adminMainPage = new AdminMainPage();
        adminMainPage.goToShop();
        logStep();
        Utilites.goToMenuName("МАГАЗИН");
        AdminProductsPage adminProductsPage=new AdminProductsPage();
        adminProductsPage.goToSettingsPage();

        logStep();
        SettingsPage settingsPage=new SettingsPage();
        Utilites.goToSidebarItem("Методы оплаты");

        logStep();
        PaymetsMethodsPage paymetsMethodsPage=new PaymetsMethodsPage();
        paymetsMethodsPage.unCheckPaymentMethodVisible("Мой вид оплаты");

        logStep();
        Utilites.goToSidebarItem("Методы доставки");
        DeliveryMethodsPage deliveryMethodsPage=new DeliveryMethodsPage();
        deliveryMethodsPage.unCheckDeliveryMethodVisible("Курьер");
        deliveryMethodsPage.unCheckDeliveryMethodVisible("Самовывоз");
    }
    @Override
    public void runTest() throws InterruptedException {
        logStep();
        CatalogPage catalogPage=new CatalogPage();
        catalogPage.goToProductPage("product1");

        ProductPage product1Page=new ProductPage();
        product1Page.addProductToCart();
        product1Page.goToCatalog();

        logStep();
        catalogPage.goToProductPage("product2");
        ProductPage product2Page=new ProductPage();
        product2Page.addProductToCart();
        product2Page.goToCatalog();

        logStep();
        catalogPage.goToProductPage("product3");
        ProductPage product3Page=new ProductPage();
        product3Page.addProductToCart();
        product3Page.goToCart();

        logStep();
        CartPage cartPage = new CartPage();
        assertEquals(cartPage.getTotalPrice(),"3 500 p.");

        logStep();
        cartPage.deleteSomeProduct("product2");
        assertEquals(cartPage.getTotalPrice(),"1 500 p.");

        logStep();
        cartPage.setProductCount("product1","5");
        assertEquals(cartPage.getTotalPrice(),"5 500 p.");

        logStep();
        cartPage.setProductCount("product3","10");
        assertEquals(cartPage.getTotalPrice(),"10 000 p.");

        logStep();
        cartPage.fillInFields("test", "test", "tt@tt.tt");
        cartPage.clickSubmit();

        logStep();
        SuccessPage successPage=new SuccessPage();
        assertTrue(successPage.checkThanksForOrderMessage());


    }
}
