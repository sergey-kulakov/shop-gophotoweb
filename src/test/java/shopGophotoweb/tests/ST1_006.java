package shopGophotoweb.tests;


import org.testng.annotations.BeforeMethod;
import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.*;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;

public class ST1_006 extends BaseTest{
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
        preOrderPage.activatePreOrder();
    }

    @Override
    public void runTest(){
        logStep();
        CatalogPage catalogPage=new CatalogPage();
        catalogPage.goToProductPage("product4");
        ProductPage productPage=new ProductPage();
        productPage.goToPreOrder();

        logStep();
        PreOrderPage preOrderPage=new PreOrderPage();
        preOrderPage.clickCompleteButton();
        assertTrue(preOrderPage.isPreOrderFormErrorDisplayd());

        logStep();
        preOrderPage.fillPreOrderForm();
        preOrderPage.clickCompleteButton();
        PreOrderSuccessPage preOrderSuccessPage=new PreOrderSuccessPage();
        assertTrue(preOrderSuccessPage.isThanksForPreOrderMessageDisplayed());

    }
}
