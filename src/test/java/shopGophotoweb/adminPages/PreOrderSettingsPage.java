package shopGophotoweb.adminPages;


import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.CheckBox;

public class PreOrderSettingsPage extends BaseForm{
    private CheckBox chkAcrivatePreOrder=new CheckBox(By.xpath("//div[@class=\"mb20 input-block w560\"]"),"Activate preorder checkbox");
    private Button btnSubmit=new Button(By.xpath("//input[@value='Сохранить']"),"Submit button");
    public PreOrderSettingsPage(){
        super(By.xpath("//label[@for='on_pre_order']"),"PreOrder page");
    }
    public void activatePreOrder(){
        chkAcrivatePreOrder.check();
        submit();
    }
    public void deActivatePreOrder(){
        chkAcrivatePreOrder.uncheck();
        submit();
    }
    private void submit(){
        btnSubmit.click();
    }
}
