package shopGophotoweb.adminPages;


import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.Browser;
import webdriver.elements.CheckBox;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.findElements;

public class DeliveryMethodsPage extends BaseForm {

    String locCheckboxDeliveryMethod="//a[contains(text(),'%s')]/../../div[contains(@class,'display')]";
    public DeliveryMethodsPage(){super(By.xpath("//div[contains(@class,'button-add')]"),"Delivery methods page");}

    public void checkDeliveryMethodVisible(String deliveryMethodName){
        CheckBox chkDeliveryMethodVisibility=new CheckBox(By.xpath(String.format(locCheckboxDeliveryMethod,deliveryMethodName)),"Delivery method visibility checkbox");
        chkDeliveryMethodVisibility.check();
    }
    public void unCheckDeliveryMethodVisible(String deliveryMethodName){
        CheckBox chkDeliveryMethodVisibility=new CheckBox(By.xpath(String.format(locCheckboxDeliveryMethod,deliveryMethodName)),"Delivery method visibility checkbox");
        chkDeliveryMethodVisibility.uncheck();
    }


}
