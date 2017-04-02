package demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import webdriver.BaseForm;

import webdriver.Browser;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

public class CartPage extends BaseForm {
    private TextBox name = new TextBox(By.name("data[fields][2702645]"), "name");
    private TextBox lastName  = new TextBox(By.name("data[fields][2702648]"), "lastname");
    private TextBox email = new TextBox(By.name("data[fields][2702651]"), "email");
    private Button complete = new Button(By.name("data[btn-submit]"), "submitButton");
    private Label skuQanityError=new Label(By.xpath(".//*[@id='shop-sku-quantity-error']"),"skuQanityError");
    private Label totalPriceSum=new Label(By.xpath(".//*[@id='totalPriceWithDelivery']"),"totalPriceSum");
    private  Label formError=new Label(By.xpath("//div[contains(@class,\"error-input\")]"),"cart form error");
    private String locDeleteSomeProduct="//a[contains(text(),'%s')]/../../td[contains(@class,'shop-cart-tbl-close')]/a";
    private String locCountSomeProduct="//a[contains(text(),'%s')]/../../td[contains(@class,'shop-cart-tbl-center skuCountCell')]/input";

    public CartPage(){
        super(By.xpath("//div[contains(@class,'shop-cart-title')]"),"Cart Page");
    }


   public void setProductCount(String productName, String count) throws InterruptedException {
       TextBox txbProductCount=new TextBox(By.xpath(String.format(locCountSomeProduct,productName)),"product count input");
       txbProductCount.setText(count);
       Thread.sleep(1000);

   }


    public void fillInFields(String nameText, String lastNameText, String emailText)
    {
        name.setText(nameText);
        lastName.setText(lastNameText);
        email.setText(emailText);

    }
    public void clickSubmit()
    {
        complete.click();

    }
    public static void deleteProductFromCartButton(String productId) throws InterruptedException{

    //    BaseTest.GoToURL("/shop/cart/sku/"+productId+"/delete");


    }
    public boolean isSkuQanityErrorDisplayd(){

        return skuQanityError.isPresent();
    }
    public boolean isFormErrorDisplayd(){
        System.out.println(formError.isPresent());
        formError.waitForIsElementPresent();
        return  formError.isPresent();
    }
    public String getTotalPrice() throws InterruptedException{

        totalPriceSum.waitForIsElementPresent();

        return totalPriceSum.getText();
    }
    public void deleteSomeProduct(String productName){
        Button btnDeleteSomeProduct=new Button(By.xpath(String.format(locDeleteSomeProduct,productName)),"delete product button");
        btnDeleteSomeProduct.click();
        Browser.checkAlert();

    }
}
