package demo.pages;

import org.openqa.selenium.By;
import webdriver.BaseForm;

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

    public CartPage(){
        super(By.xpath("//div[contains(@class,'shop-cart-title')]"),"Cart Page");
    }

    public void setProductCount( String productId,String count) throws InterruptedException{
        Thread.sleep(1000);
        TextBox countProduct=new TextBox(By.xpath(".//tr[@data-sku='"+productId+"']/td[3]/input"), "countProduct");
        countProduct.setText(count);
        Thread.sleep(1000);
    }
	/*public void setProduct2Count(String count){
		countProduct2.setText(count);
	}
	public void setProduct3Count(String count){
		countProduct3.setText(count);
	}*/

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
    public String getTotalPrice() throws InterruptedException{
        Thread.sleep(1000);
        return totalPriceSum.getText();
    }
}
