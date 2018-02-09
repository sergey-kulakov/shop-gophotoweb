package shopGophotoweb.adminPages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;


public class AdminProductsPage extends BaseForm {
    String locProduct="//a[.='%s']";
    private Button btnSettings=new Button(By.xpath("//ul[@class='nav navbar-nav']/li/a[contains(text(),'Настройки')]"), "Settings button");
    private Button btnOrders=new Button(By.xpath("//ul[@class='nav navbar-nav']/li/a[contains(text(),'Заказы')]"), "Settings button");
    private Button btnPromocodes=new Button(By.xpath("//a[.='Промо-коды']"),"Promocodes button");

    public AdminProductsPage(){super(By.xpath("//span[contains(text(),'Добавить товар')]"),"Products page");}

    public void goToProductPage(String productName){
        Label lblProduct=new Label(By.xpath(String.format(locProduct,productName)),"Product link");
        lblProduct.click();
    }
    public void goToSettingsPage(){
        btnSettings.click();
    }
    public void goToOrdersPage(){
        btnOrders.waitForIsElementPresent();
        btnOrders.click();
    }
    public void goToPromocodes(){
        btnPromocodes.waitForIsElementPresent();
        btnPromocodes.click();
    }
}
