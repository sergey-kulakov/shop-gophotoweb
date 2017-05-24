package shopGophotoweb.adminPages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;


public class AdminProductsPage extends BaseForm {
    String locProduct="//a[contains(text(),'%s')]";
    private Button btnSettings=new Button(By.xpath("//ul[@class='nav navbar-nav']/li/a[contains(text(),'Настройки')]"), "Settings button");
    private Button btnOrders=new Button(By.xpath("//ul[@class='nav navbar-nav']/li/a[contains(text(),'Заказы')]"), "Settings button");
    private Button btnAddProduct=new Button(By.xpath("//span[contains(text(),'Добавить товар')]"),"Add product button");
    private TextBox tbxName=new TextBox(By.xpath("//input[@name='data[name]']"),"Product name textbox");
    private TextBox tbxUrl=new TextBox(By.xpath("//input[@name='data[url]']"),"Product url textbox");
    private Button btnSubmit=new Button(By.xpath("//input[@value='Добавить товар']"),"Submit button in popup");

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
    public void addProduct(String productName){
        btnAddProduct.waitForIsElementPresent();
        btnAddProduct.click();
        tbxName.waitForIsElementPresent();
        tbxName.setText(productName);
        tbxUrl.setText(productName);
        btnSubmit.click();

    }
}
