package shopGophotoweb.pages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;


public class SuccessPage extends BaseForm {
    private Label lblThanksForOrderMessage=new Label(By.xpath("//p[contains(text(),'Спасибо за покупку!')]"), "thanksForOrderMessage");


    public SuccessPage(){
        super(By.className("shop-order-title"),"Success Page");
    }


    public boolean isThanksForOrderMessageDisplayed(){
        lblThanksForOrderMessage.waitForIsElementPresent();
        return lblThanksForOrderMessage.isPresent();
    }



    public String getOrderNumber(){

        lblThanksForOrderMessage.waitForIsElementPresent();
        String textMessage=lblThanksForOrderMessage.getText();
        System.out.println("Сообщение о заказе "+textMessage);
        String orderNumber=textMessage.substring(textMessage.length()-6, textMessage.length());
        System.out.println("Номер заказа "+orderNumber);
        return orderNumber;

    }
}
