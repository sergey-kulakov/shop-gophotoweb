package shopGophotoweb.adminPages;


import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

public class PromocodesPage extends BaseForm{
    String locPromocode="//b[.='%s']/..";
    public PromocodesPage(){super(By.xpath("//span[contains(text(),'Добавить промо-код')]"),"Promocodes page");}

    public void goToPromocodeSettings(String promocodeName){
        Button btnPromocode=new Button(By.xpath(String.format(locPromocode,promocodeName)),"Promocode link");
        btnPromocode.waitForIsElementPresent();
        btnPromocode.click();
    }
}
