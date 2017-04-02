package demo.adminPages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

/**
 * Created by Sergey on 02.04.2017.
 */
public class SettingsPage extends BaseForm {
    private Button btnPaymentsMethods=new Button(By.linkText("МЕТОДЫ ОПЛАТЫ"),"Payments methods button");
    private Button btnDeliveryMethods=new Button(By.linkText("МЕТОДЫ ДОСТАВКИ"),"Delivery methods button");
    public SettingsPage(){super(By.linkText("ОБЩИЕ"),"Settings Page");}

    public void goToPaymentsMethods(){
        btnPaymentsMethods.click();
    }
    public void goToDeliveryMethods(){
        btnDeliveryMethods.click();
    }
}
