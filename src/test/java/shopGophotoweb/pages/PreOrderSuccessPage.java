package shopGophotoweb.pages;


import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;

public class PreOrderSuccessPage extends BaseForm {
    private Label lblThanksForPreOrderMessage=new Label(By.xpath(".//p[contains(text(),'Благодарим вас за оформление заказа!')]"), "thanksForPreOrderMessage");

    public PreOrderSuccessPage(){
        super(By.xpath("//section[@class='pre_order_form success']"),"Preorder success page");
    }

    public boolean isThanksForPreOrderMessageDisplayed(){
        lblThanksForPreOrderMessage.waitForIsElementPresent();
        return lblThanksForPreOrderMessage.isPresent();
    }
}
