package shopGophotoweb.pages;


import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

public class PreOrderPage extends BaseForm {
    private TextBox txbName=new TextBox(By.xpath("//label[contains(text(),'Имя')]/preceding-sibling::input"),"Name input");
    private TextBox txbEmail=new TextBox(By.xpath("//label[contains(text(),'Email')]/preceding-sibling::input"),"Email input");
    Button btnCompletePreorder=new Button(By.xpath("//button[@name='data[btn-submit]']"),"Button complete order");
    Label lblPreOrderFormError=new Label(By.className("pre-order-error"),"Unfilled form error");
    public PreOrderPage(){
        super(By.className("pre_order_form"),"PreOrderPage");
    }

    public void fillPreOrderForm(String name, String email){
        txbName.setText(name);
        txbEmail.setText(email);
    }
    public void clickCompleteButton(){
        btnCompletePreorder.click();
    }
    public boolean isPreOrderFormErrorDisplayd(){
        lblPreOrderFormError.waitForIsElementPresent();
        return  lblPreOrderFormError.isPresent();
    }
}
