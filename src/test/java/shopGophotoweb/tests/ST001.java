package shopGophotoweb.tests;

import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.CartPage;
import shopGophotoweb.pages.CatalogPage;
import shopGophotoweb.pages.ProductPage;
import shopGophotoweb.pages.SuccessPage;
import org.testng.annotations.BeforeMethod;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.*;


public class ST001 extends BaseTest {
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

        logStep();
        ProductPage productPage=new ProductPage();
        productPage.addProductToCart();
        productPage.goToCart();

        logStep();
        CartPage cartPage = new CartPage();
        cartPage.clickSubmit();


        assertTrue(cartPage.isFormErrorDisplayd());


        logStep();

        cartPage.fillInFields("test", "test", "tt@tt.tt");
        cartPage.clickSubmit();

        logStep();
        SuccessPage successPage=new SuccessPage();
        assertTrue(successPage.checkThanksForOrderMessage());
    }
}
