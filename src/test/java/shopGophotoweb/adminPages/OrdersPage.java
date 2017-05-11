package shopGophotoweb.adminPages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;


public class OrdersPage extends BaseForm {
    private String locOrderTotalPrice="//span[contains(text(),'%s')]/../../div[6]";
    public OrdersPage(){
        super(By.xpath("//span[contains(text(),'Добавить заказ')]"),"Orders page");
    }
    public  String getOrderTotalPrice(String orderNumber){
        Label orderTotalPrice=new Label(By.xpath(String.format(locOrderTotalPrice,orderNumber)),"order total price");
        orderTotalPrice.waitForIsElementPresent();
        return orderTotalPrice.getText();

    }
}
