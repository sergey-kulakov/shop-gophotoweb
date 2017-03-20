package demo.tests;

import demo.pages.CartPage;
import demo.pages.CatalogPage;
import demo.pages.ProductPage;
import demo.pages.SuccessPage;
import webdriver.BaseTest;
import static org.testng.Assert.*;

/**
 * Created by Sergey on 20.03.2017.
 */
public class ST001 extends BaseTest {
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
        cartPage.fillInFields("test", "test", "tt@tt.tt");
        cartPage.clickSubmit();

        logStep();
        SuccessPage successPage=new SuccessPage();
        assertTrue(successPage.checkThanksForOrderMessage());
    }
}
