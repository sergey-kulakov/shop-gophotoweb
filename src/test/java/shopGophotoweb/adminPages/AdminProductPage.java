package shopGophotoweb.adminPages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.TextBox;


public class AdminProductPage extends BaseForm {
    private TextBox txbCount=new TextBox(By.xpath("//input[contains(@name,'amount')]"),"Input count of products");
    private Button btnSave=new Button(By.xpath("//input[contains(@value,'Сохранить')]"),"Save button");

    public AdminProductPage(){super(By.xpath("//div[contains(text(),'Основные')]"),"Product page");}

    public void setProductCount(String count){
        txbCount.setText(count);
        btnSave.click();
    }
}
