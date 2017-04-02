package demo.tests;

import demo.pages.CartPage;
import demo.pages.CatalogPage;
import demo.pages.ProductPage;
import demo.pages.SuccessPage;
import webdriver.BaseTest;
import webdriver.Browser;

import static org.testng.Assert.assertTrue;

/**
 * Created by Sergey on 20.03.2017.
 */
public class ST002 extends BaseTest {
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
