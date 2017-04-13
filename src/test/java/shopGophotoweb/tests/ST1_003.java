package shopGophotoweb.tests;


import org.testng.annotations.BeforeMethod;
import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.CatalogPage;
import shopGophotoweb.pages.ProductPage;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;

public class ST1_003 extends BaseTest{
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
        settingsPage.goToPreorder();
        PreOrderSettingsPage preOrderPage=new PreOrderSettingsPage();
        preOrderPage.deActivatePreOrder();
    }

    @Override
    public void runTest() throws InterruptedException {
        logStep();
        CatalogPage catalogPage=new CatalogPage();
        catalogPage.goToProductPage("product4");

        logStep();
        ProductPage productPage=new ProductPage();
        productPage.isOutOfStockButtonDisplayed();
        logger.info("Button Out Of Stock displayed");

        logStep();
        productPage.addProductToCart();
        assertTrue(productPage.isOutOfStockButtonDisplayed());
        logger.info("Button Out Of Stock displayed");
    }
}
