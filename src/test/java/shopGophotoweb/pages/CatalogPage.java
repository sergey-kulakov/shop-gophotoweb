package shopGophotoweb.pages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;


public class CatalogPage extends BaseForm {
String productLocator="//div[contains(text(),'%s')]";

    public CatalogPage(){
        super(By.className("shop-products"),"Catalog Page");
    }

    public void goToProductPage(String productName){

        Button btnProduct=new Button(By.xpath(String.format(productLocator,productName)),"btnProduct");
        btnProduct.click();
    }
}
