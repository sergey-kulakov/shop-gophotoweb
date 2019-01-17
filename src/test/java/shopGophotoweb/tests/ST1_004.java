package shopGophotoweb.tests;

import org.testng.annotations.BeforeMethod;
import shopGophotoweb.adminPages.*;
import shopGophotoweb.pages.*;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;


public class ST1_004 extends BaseTest {

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
        AdminProductsPage adminProductsPage=new AdminProductsPage();
        adminProductsPage.goToProductPage("product1");

        logStep(4);
        AdminProductPage adminProductPage=new AdminProductPage();
        adminProductPage.setProductCount("1000");

        logStep(5);
        Utilites.goToMenuName("МАГАЗИН");
        Utilites.goToSidebarItem(Utilites.SidebarItems.Продажи);
        Utilites.goToSidebarItem(Utilites.SidebarItems.оплаты);

        logStep();
        PaymetsMethodsPage paymetsMethodsPage=new PaymetsMethodsPage();
        paymetsMethodsPage.unCheckAllMethods();

        logStep();
        Utilites.goToSidebarItem(Utilites.SidebarItems.доставки);
        DeliveryMethodsPage deliveryMethodsPage=new DeliveryMethodsPage();
        deliveryMethodsPage.unCheckAllMethods();






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
        cartPage.setProductCount("product1","1001");
        cartPage.clickSubmit();
        assertTrue(cartPage.isSkuQanityErrorDisplayed());
        assertTrue(cartPage.isTextBoxSkuCountErrorDisplayed());
        logger.info("Qantity Error Displayed");

        logStep(4);
        cartPage.setProductCount("product1","0");
        cartPage.clickSubmit();
        logger.info("Expected result: total price = 1 000 pуб.");
        logger.info("Actual result: total price = "+cartPage.getTotalPrice());
        assertEquals(cartPage.getTotalPrice(),"1 000 pуб.");


        cartPage.clickSubmit();
        assertTrue(cartPage.isFormErrorDisplayed());

        logStep(5);
        cartPage.fillInFields("test", "test", "tt@tt.tt");
        cartPage.clickSubmit();

        logStep(6);
        SuccessPage successPage=new SuccessPage();
        assertTrue(successPage.isThanksForOrderMessageDisplayed());
        logger.info("The order was completed");
    }
}