package demo.tests;

import demo.adminPages.*;
import demo.adminPages.AdminMainPage;
import demo.pages.CartPage;
import demo.pages.CatalogPage;
import demo.pages.ProductPage;
import demo.pages.SuccessPage;
import org.testng.annotations.BeforeMethod;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;


public class ST003 extends BaseTest {

    @BeforeMethod
    public void setCoutnOfProduct(){

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
        assertTrue(cartPage.isTextBoxSkuCountErrorDisplayd());

        logStep();
        cartPage.setProductCount("product1","0");
        cartPage.clickSubmit();
        assertTrue(cartPage.isTextBoxSkuCountErrorDisplayd());

        logStep();
        cartPage.setProductCount("product1","1");
        assertFalse(cartPage.isSkuQanityErrorDisplayd());
        assertFalse(cartPage.isTextBoxSkuCountErrorDisplayd());
        cartPage.clickSubmit();

        logStep();
        cartPage.fillInFields("test", "test", "tt@tt.tt");
        cartPage.clickSubmit();

        logStep();
        SuccessPage successPage=new SuccessPage();
        assertTrue(successPage.checkThanksForOrderMessage());
    }
}