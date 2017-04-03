package shopGophotoweb.pages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.*;

public class ProductPage extends BaseForm {
    private Button btnAddToCart = new Button(By.id("skuadd"), "addToCartButton");
    private Button btnGoToCart = new Button(By.xpath("//a[contains(text(),'Ваша корзина')]"),"goToCart");
    private Button btnGoToCatalog=new Button(By.id("shop-product-lnk-back"),"back to catalog button");

    public ProductPage(){
        super(By.xpath("//div[contains(@class,'page shop-product')]"),"Product Page");
    }

    public void addProductToCart() throws InterruptedException {
        btnAddToCart.click();
        Thread.sleep(2000);
            }

    public void goToCart()
    {
        btnGoToCart.click();
    }
    public void goToCatalog(){btnGoToCatalog.click();}

    public String  getAddButtonText(){
        System.out.println(btnAddToCart.getText());
        return btnAddToCart.getText();
    }
}
