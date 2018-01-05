package shopGophotoweb.pages;

import org.openqa.selenium.By;
import webdriver.elements.Button;


public class Menu {
    private static Button btnShop=new Button(By.xpath("//a[contains(text(),'МАГАЗИН')][1]"),"Go to shop catalog button");
    private static Button btnGoToCart = new Button(By.xpath("//a[@data-id='shop-cart-widget']"),"goToCart");

    public static void goToShopCatalog(){
        btnShop.waitForIsElementPresent();
        btnShop.click();
    }

    public static void goToCart()
    {
        btnGoToCart.click();
    }

}
