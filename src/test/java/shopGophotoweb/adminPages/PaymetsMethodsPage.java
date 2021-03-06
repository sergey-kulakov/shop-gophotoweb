package shopGophotoweb.adminPages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.CheckBox;
import webdriver.elements.ElemetsList;
import webdriver.elements.Label;


public class PaymetsMethodsPage extends BaseForm {
    private String locCheckboxPaymetnMethod="//a[contains(text(),'%S')]/../../div[contains(@class,'edit')]";
    private ElemetsList lstAllMethods=new ElemetsList(By.xpath("//a[contains(text(),'')]/../../div[contains(@class,'edit')]"),"List payments methods");
    private String locPaymentMethodName="//a[contains(text(),'%s')]";
    public enum PaymentMethods{МОЙ_ВИД_ОПЛАТЫ_С_КОМИССИЕЙ_1,ЧЕРЕЗ_СИСТЕМУ_ЯНДЕКС_ДЕНЬГИ_С_КОММИСИЕЙ_3}
    public PaymetsMethodsPage(){super(By.xpath("//div[@class='payment']"),"Payments methods page");}

    public void checkPaymentMethodVisible(PaymetsMethodsPage.PaymentMethods paymentMethodName){
         CheckBox chkPaymentMethodVisibility=new CheckBox(By.xpath(String.format(locCheckboxPaymetnMethod,paymentMethodName)),"Payment method visibility checkbox");
         chkPaymentMethodVisibility.check();
    }
    public void unCheckPaymentMethodVisible(PaymetsMethodsPage.PaymentMethods paymentMethodName){
        CheckBox chkPaymentMethodVisibility=new CheckBox(By.xpath(String.format(locCheckboxPaymetnMethod,paymentMethodName)),"Payment method visibility checkbox");
        chkPaymentMethodVisibility.uncheck();
    }
    public void unCheckAllMethods(){
        lstAllMethods.unCheckAllMethods();
    }

    public void goToPaymentMethod(PaymentMethods paymentMethodName){
        Label lblPaymentMethodName=new Label(By.xpath(String.format(locPaymentMethodName,paymentMethodName)),"Payment method link");
        lblPaymentMethodName.click();

    }
}
