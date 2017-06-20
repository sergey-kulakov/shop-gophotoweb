package shopGophotoweb.adminPages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.CheckBox;


public class DeliveryMethodPage extends BaseForm {
    private String locCheckboxPaymetnMethod="//span[contains(text(),'%S')]/preceding-sibling::span";
    private Button btnSubmit=new Button(By.xpath("//input[@value='Сохранить']"),"Submit button");
    public DeliveryMethodPage(){super(By.xpath("//div[contains(text(),'Название метода доставки')]"),"Delivery methods page");}

    public void checkPaymentMethodVisible(PaymetsMethodsPage.PaymentMethods paymentMethodName){
        CheckBox chkPaymentMethodVisibility=new CheckBox(By.xpath(String.format(locCheckboxPaymetnMethod,paymentMethodName)),"Payment method visibility checkbox");
        chkPaymentMethodVisibility.waitForIsElementPresent();
        chkPaymentMethodVisibility.check();
        btnSubmit.click();
    }
    public void unCheckPaymentMethodVisible(PaymetsMethodsPage.PaymentMethods paymentMethodName){
        String x=String.format(locCheckboxPaymetnMethod,paymentMethodName);
        System.out.println(x);
        CheckBox chkPaymentMethodVisibility=new CheckBox(By.xpath(x),"Payment method visibility checkbox");
        System.out.println("test");
        chkPaymentMethodVisibility.waitForIsElementPresent();
        chkPaymentMethodVisibility.uncheck();
        btnSubmit.click();
    }
}
