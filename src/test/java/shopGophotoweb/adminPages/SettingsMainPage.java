package shopGophotoweb.adminPages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

public class SettingsMainPage extends BaseForm{
    private String locCurrency = "//span[contains(text(),%s)]/../..";
    private Button btnSubmit=new Button(By.xpath("//input[@value='Сохранить']"),"Submit button");
    public SettingsMainPage(){super(By.xpath("//input[@name='data[emailForCustomers]']"),"Settings main page");}

    public void selectOfCurrency(String currencyName){
        Button btnCurrency=new Button(By.xpath(String.format(locCurrency,currencyName)),"Currency link");
        btnCurrency.waitForIsElementPresent();
        btnCurrency.click();
    }
    public void  clickSave(){
        btnSubmit.click();
    }
}
