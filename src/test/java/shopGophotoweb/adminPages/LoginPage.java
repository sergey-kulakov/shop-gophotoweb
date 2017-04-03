package shopGophotoweb.adminPages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.TextBox;

/**
 * Created by Sergey on 02.04.2017.
 */
public class LoginPage extends BaseForm {
    private TextBox txbLogin=new TextBox(By.id("login-name-input"),"Login textbox");
    private TextBox txbPassword=new TextBox(By.xpath("//input[contains(@type,'password')]"),"Password textbox");
    private Button btnSubmit=new Button(By.xpath("//input[@type='submit']"),"Submit button");

    public LoginPage(){super(By.className("login-form"),"Login page");}

    public void login(){
        txbLogin.setText("test@test-gophotoweb.ru");
        txbPassword.setText("4o9rYVr8ov");
        btnSubmit.click();
    }
}
