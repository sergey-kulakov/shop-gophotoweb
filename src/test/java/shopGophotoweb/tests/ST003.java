package shopGophotoweb.tests;

import shopGophotoweb.adminPages.*;
import shopGophotoweb.adminPages.AdminMainPage;
import shopGophotoweb.pages.CartPage;
import shopGophotoweb.pages.CatalogPage;
import shopGophotoweb.pages.ProductPage;
import shopGophotoweb.pages.SuccessPage;
import org.testng.annotations.BeforeMethod;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;


public class ST003 extends BaseTest {

    @BeforeMethod
    public void setpreconditions(){

        logStep();
        browser.navigate(Browser.getAdminPageUrl());
        LoginPage loginPage=new LoginPage();
        loginPage.login();

        logStep();
        AdminMainPage adminMainPage = new AdminMainPage();
        adminMainPage.goToShop();

        logStep();
        AdminProductsPage adminProductsPage=new AdminProductsPage();
        adminProductsPage.goToProductPage("product1");

        logStep();
        AdminProductPage adminProductPage=new AdminProductPage();
        adminProductPage.setProductCount("1000");

        logStep();
        Utilites.goToMenuName("МАГАЗИН");
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
        CartPage cartPage=new CartPage();
        cartPage.setProductCount("product1","1001");
        cartPage.clickSubmit();
        assertTrue(cartPage.isSkuQanityErrorDisplayd());
        assertTrue(cartPage.isTextBoxSkuCountErrorDisplayed());

        logStep();
        cartPage.setProductCount("product1","0");
        cartPage.clickSubmit();
        assertTrue(cartPage.isTextBoxSkuCountErrorDisplayed());

        logStep();
        cartPage.setProductCount("product1","1");
        cartPage.clickSubmit();

        logStep();
        cartPage.fillInFields("test", "test", "tt@tt.tt");
        cartPage.clickSubmit();

        logStep();
        SuccessPage successPage=new SuccessPage();
        assertTrue(successPage.checkThanksForOrderMessage());
    }
}