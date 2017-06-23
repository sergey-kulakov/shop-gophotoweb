package shopGophotoweb.adminPages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.TextBox;


public class PaymentMethodPage extends BaseForm {
    private TextBox txbPercentage=new TextBox(By.xpath("//div[contains(text(),'Наценка')]/input"),"Percentage of payment method");
    private Button btnSubmit=new Button(By.xpath("//input[@value='Сохранить']"),"Submit button");
    public PaymentMethodPage(){super(By.xpath("//div[contains(text(),'Название оплаты')]"),"Particular payment method page");}

    public void setPercentage(String percentage){
        txbPercentage.setText(percentage);
        btnSubmit.click();
    }
}
