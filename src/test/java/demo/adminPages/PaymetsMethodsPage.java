package demo.adminPages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.CheckBox;

/**
 * Created by Sergey on 02.04.2017.
 */
public class PaymetsMethodsPage extends BaseForm {
    String locCheckboxPaymetnMethod="//a[contains(text(),'%S')]/../../div[contains(@class,'edit')]/input";

    public PaymetsMethodsPage(){super(By.xpath("//div[@class=\"payment\"]"),"Payments methods page");}

    public void checkPaymentMethodVisible(String paymentMethodName){
        System.out.println(String.format(locCheckboxPaymetnMethod,paymentMethodName));
         CheckBox chkPaymentMethodVisibility=new CheckBox(By.xpath(String.format(locCheckboxPaymetnMethod,paymentMethodName)),"Payment method visibility checkbox");
         chkPaymentMethodVisibility.swichOn();
    }
}
