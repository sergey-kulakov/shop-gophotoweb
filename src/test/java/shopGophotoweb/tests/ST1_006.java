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
        Utilites.goToSidebarItem(Utilites.SidebarItems.Оформление);
        Utilites.goToSidebarItem(Utilites.SidebarItems.предзаказа);
        PreOrderSettingsPage preOrderPage=new PreOrderSettingsPage();
        preOrderPage.activatePreOrder();
    }

    @Override
    public void runTest(){
        logStep(1);
        CatalogPage catalogPage=new CatalogPage();
        catalogPage.goToProductPage("product4");
        ProductPage productPage=new ProductPage();
        productPage.goToPreOrder();

        logStep(2);
        PreOrderPage preOrderPage=new PreOrderPage();
        preOrderPage.clickCompleteButton();
        assertTrue(preOrderPage.isPreOrderFormErrorDisplayd());

        logStep(2);
        preOrderPage.fillPreOrderForm("name","test@test.test");
        preOrderPage.clickCompleteButton();
        PreOrderSuccessPage preOrderSuccessPage=new PreOrderSuccessPage();
        assertTrue(preOrderSuccessPage.isThanksForPreOrderMessageDisplayed());

    }
}
