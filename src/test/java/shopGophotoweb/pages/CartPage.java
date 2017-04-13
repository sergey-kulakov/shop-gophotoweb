package shopGophotoweb.pages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.Browser;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

public class CartPage extends BaseForm {
    private TextBox txbName = new TextBox(By.name("data[fields][2702645]"), "Name in order form");
    private TextBox txbLastame  = new TextBox(By.name("data[fields][2702648]"), "Lastname in order form");
    private TextBox txbEmail = new TextBox(By.name("data[fields][2702651]"), "Email in order form");
    private Button btnSubmit = new Button(By.name("data[btn-submit]"), "Submit button");
    private Label lblSkuQanityError=new Label(By.xpath(".//*[@id='shop-sku-quantity-error']"),"skuQanityError");
    private Label lblTextBoxSkuCountError=new Label(By.xpath("//input[contains(@class,'textbox skuCount error-field')]"));
    private Label totalPriceSum=new Label(By.xpath(".//*[@id='totalPriceWithDelivery']"),"totalPriceSum");
    private  Label formError=new Label(By.xpath("//div[contains(@class,\"error-input\")]"),"cart form error");
    private String locDeleteSomeProduct="//a[contains(text(),'%s')]/../../td[contains(@class,'shop-cart-tbl-close')]/a";
    private String locCountSomeProduct="//a[contains(text(),'%s')]/../../td[contains(@class,'shop-cart-tbl-center skuCountCell')]/input";
    private Label lblPaymentError=new Label(By.id("shop-payment-error"),"Payment error");
    private Label lblDeliveryError=new Label(By.id("shop-delivery-error"),"Delivery error");
    private  Label lblPromoCodeError=new Label(By.id("shop-promo-msg"),"Promo code error ");
    private Label lblPromocode=new Label(By.id("shop-promo-link"),"Promo code link");
    private TextBox txbPromocode=new TextBox(By.id("shop-promo-code"),"Promo code textbox");
    private Button btnApplyPromocode=new Button(By.id("shop-apply-promo"),"Apply promo code button");
    private String locDeliveryPaymentMethod="//label[contains(text(),'%s')]/preceding-sibling::input";


    public CartPage(){
        super(By.xpath("//div[contains(@class,'shop-cart-title')]"),"Cart Page");
    }


   public void setProductCount(String productName, String count) throws InterruptedException {
       TextBox txbProductCount=new TextBox(By.xpath(String.format(locCountSomeProduct,productName)),"product count input");

       txbProductCount.setText(count);
       Thread.sleep(3000);

   }


    public void fillInFields(String nameText, String lastNameText, String emailText)
    {
        txbName.setText(nameText);
        txbLastame.setText(lastNameText);
        txbEmail.setText(emailText);

    }
    public void clickSubmit()
    {
        btnSubmit.click();

    }
    public static void deleteProductFromCartButton(String productId) throws InterruptedException{

    //    BaseTest.GoToURL("/shop/cart/sku/"+productId+"/delete");


    }
    public boolean isSkuQanityErrorDisplayed(){

        return lblSkuQanityError.isPresent();
    }

    public boolean isTextBoxSkuCountErrorDisplayed(){
        lblTextBoxSkuCountError.waitForIsElementPresent();
        return lblTextBoxSkuCountError.isPresent();
    }
    public boolean isFormErrorDisplayed(){
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
    public boolean isPaymentErrorDisplayed(){
        lblPaymentError.waitForIsElementPresent();
        return lblPaymentError.isPresent();
    }
    public boolean isDeliveryErrorDisplayed(){
        lblDeliveryError.waitForIsElementPresent();
        return lblDeliveryError.isPresent();
    }
    public boolean isPromocodeErrorDisplayed(){
        lblPromoCodeError.waitForIsElementPresent();
        return lblPromoCodeError.isPresent();
    }
    public void selectDeliveryOrPaymentMethod(String deliveryMethodName) throws InterruptedException {
        Button btnDeliveryMethod=new Button(By.xpath(String.format(locDeliveryPaymentMethod,deliveryMethodName)),"Dilivery methods radiobatton");
        btnDeliveryMethod.click();
        Thread.sleep(1000);

    }


    public void applyPromoCode(String promocod) throws InterruptedException {
        if(lblPromocode.isPresent()){
        lblPromocode.click();
        }
        txbPromocode.setText(promocod);
        btnApplyPromocode.click();
        Thread.sleep(1000);
    }
}
