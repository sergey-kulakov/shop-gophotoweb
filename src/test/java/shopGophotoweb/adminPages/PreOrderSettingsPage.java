package shopGophotoweb.adminPages;


import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.CheckBox;

public class PreOrderSettingsPage extends BaseForm{
    private CheckBox chkAcrivatePreOrder=new CheckBox(By.xpath("//span[@class='fl b-active']"),"Activate preorder checkbox");
    public PreOrderSettingsPage(){
        super(By.xpath("//span[contains(text(),'Активировать возможность продавать товары под заказ')]"),"PreOrder page");
    }
    public void activatePreOrder(){
        chkAcrivatePreOrder.check();
    }
    public void deActivatePreOrder(){
        chkAcrivatePreOrder.uncheck();
    }
}