package demo.tests;

import demo.pages.CartPage;
import demo.pages.CatalogPage;
import demo.pages.ProductPage;
import webdriver.BaseTest;

/**
 * Created by Sergey on 20.03.2017.
 */
public class ST002 extends BaseTest {
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
    }
}
