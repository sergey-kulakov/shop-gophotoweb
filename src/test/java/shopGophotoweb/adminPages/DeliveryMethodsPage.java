package shopGophotoweb.adminPages;


import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.CheckBox;
import webdriver.elements.ElemetsList;
import webdriver.elements.Label;

public class DeliveryMethodsPage extends BaseForm {

    private String locCheckboxDeliveryMethod="//a[contains(text(),'%s')]/../../div[contains(@class,'display')]";
    private String locDeliveryMethodName="//a[contains(text(),'%s')]";
    private ElemetsList lstAllMethods=new ElemetsList(By.xpath("//a[contains(text(),'')]/../../div[contains(@class,'display')]"),"List of methods checkboxes");
    public enum DeliveryMethods{Курьер,Самовывоз,Почта}
    public DeliveryMethodsPage(){super(By.xpath("//div[contains(@class,'button-add')]"),"Delivery methods page");}

    public void checkDeliveryMethodVisible(DeliveryMethods deliveryMethodName){
        CheckBox chkDeliveryMethodVisibility=new CheckBox(By.xpath(String.format(locCheckboxDeliveryMethod,deliveryMethodName)),"Delivery method visibility checkbox");
        chkDeliveryMethodVisibility.check();
    }
    public void unCheckDeliveryMethodVisible(DeliveryMethods deliveryMethodName){
        CheckBox chkDeliveryMethodVisibility=new CheckBox(By.xpath(String.format(locCheckboxDeliveryMethod,deliveryMethodName)),"Delivery method visibility checkbox");
        chkDeliveryMethodVisibility.uncheck();
    }

    public void unCheckAllMethods(){
        lstAllMethods.unCheckAllMethods();
    }

    public void goToDeliveryMethod(DeliveryMethods deliveryMethodName){
        Label lblDeliveryMethodName=new Label(By.xpath(String.format(locDeliveryMethodName,deliveryMethodName)),"Delivery method link");
        lblDeliveryMethodName.click();

    }

}
