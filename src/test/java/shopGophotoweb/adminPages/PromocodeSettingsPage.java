package shopGophotoweb.adminPages;


import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.TextBox;

public class PromocodeSettingsPage extends BaseForm{
    private TextBox txbAmoutMoreThen=new TextBox(By.xpath("//input[contains(@name,'[amount_more_then]')]"),"Amount more then textbox");
    private Button btnSubmit=new Button(By.xpath("//input[@value='Сохранить']"),"Submit button");
    public PromocodeSettingsPage(){super(By.xpath("//input[@value='Создать копию промо-кода']"),"Promocode settings page");}

    public void setAmoutMoreThen(String sum){
        txbAmoutMoreThen.waitForIsElementPresent();
        txbAmoutMoreThen.setText(sum);
        btnSubmit.click();
    }
}
