package webdriver.elements;

import org.openqa.selenium.By;
import webdriver.Browser;

import java.util.List;


public class CheckBox extends BaseElement {
    public CheckBox(final By locator, final String name) {
        super(locator, name);
    }

    public CheckBox(String string, String name) {
        super(string, name);
    }


    public CheckBox(By locator) {
        super(locator);
    }

    protected String getElementType() {
        return getLoc("loc.checkbox");
    }

   public void check(){

       String atribute =getElement().findElement(By.xpath("input")).getAttribute("checked");
    //   System.out.println(atribute);
       if(atribute==null){
       element.findElement(By.xpath("label")).click();
       }
      }

    public void uncheck(){

        String atribute =getElement().findElement(By.xpath("input")).getAttribute("checked");
        //   System.out.println(atribute);
        if(atribute!=null){
            element.findElement(By.xpath("label")).click();
        }
    }
    public void unCheckAllMethods(){

        List<CheckBox> deliveryMethods= getElement().findElements(By.xpath("//a[contains(text(),'%s')]/../../div[contains(@class,'display')]"));
        for(CheckBox deliveryMethod:deliveryMethods){
            deliveryMethod.uncheck();
        }
        CheckBox chkDeliveryMethodVisibility=new CheckBox(By.xpath(String.format(locCheckboxDeliveryMethod,deliveryMethodName)),"Delivery method visibility checkbox");
        chkDeliveryMethodVisibility.uncheck();
    }
}
