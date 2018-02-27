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
        preOrderPage.deActivatePreOrder();
    }

    @Override
    public void runTest() throws InterruptedException {
        logStep(1);
        CatalogPage catalogPage=new CatalogPage();
        catalogPage.goToProductPage("product4");

        logStep(2);
        ProductPage productPage=new ProductPage();
        productPage.isOutOfStockButtonDisplayed();
        logger.info("Button Out Of Stock displayed");

        logStep(3);
        productPage.addProductToCart();
        assertTrue(productPage.isOutOfStockButtonDisplayed());
        logger.info("Button Out Of Stock displayed");
    }
}
