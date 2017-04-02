package demo.adminPages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;


public class AdminProductsPage extends BaseForm {
    String locProduct="//a[contains(text(),'%s')]";
    private Button btnSettings=new Button(By.xpath("//ul[@class='nav navbar-nav']/li/a[contains(text(),'Настройки')]"), "Settings button");

    public AdminProductsPage(){super(By.xpath("//span[contains(text(),'Добавить товар')]"),"Products page");}

    public void goToProductPage(String productName){
        Label lblProduct=new Label(By.xpath(String.format(locProduct,productName)),"Product link");
        lblProduct.click();
    }
    public void goToSettingsPage(){
        btnSettings.click();
    }
}
