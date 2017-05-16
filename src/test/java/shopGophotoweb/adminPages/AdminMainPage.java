package shopGophotoweb.adminPages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;


public class AdminMainPage extends BaseForm {

    private Button btnShop=new Button(By.xpath("//span[contains (text(),\"МАГАЗИН\")]/.."),"Shop button");
    private Label lblGoToSite=new Label(By.xpath("//span[contains(text(),'Перейти на сайт')]"),"Go to site link");

    public AdminMainPage(){super(By.id("btnAddPage"),"Main site admin page");}

    public void goToShop(){
        btnShop.click();
    }
    public void goToSite(){
        lblGoToSite.waitForIsElementPresent();
        lblGoToSite.click();
    }
}
