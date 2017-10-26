package shopGophotoweb.pages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.*;

public class ProductPage extends BaseForm {
    private Button btnAddToCart = new Button(By.id("skuadd"), "addToCartButton");
    private  Button btnOutOfStock=new Button(By.xpath(".//a[contains(text(),'Нет в наличии')]"),"Out of stock button");
    private  Button btnPreOrder=new Button(By.xpath("//a[contains(text(),\"Оформить заказ\")]"),"PreOrder button");
    private Label lblDiscount=new Label(By.xpath("//span[@class='product-price-discount']"),"Discount label");
    private  Label lblOldPrice=new Label(By.xpath("//div[@class='price']/span[contains(@class,'product-price-old')]"),"Old price");
    private Label lblPrice=new Label(By.xpath("//div[@class='price']/span[contains(@class,'product-price-min')]"),"Price label");
    private  Label lblUnselectedItemError=new Label(By.xpath("//div[@class='error'][@data-prefix='Выберите']"),"Unselected item error");
    //private  ElemetsList listOptions=new ElemetsList(By.xpath("//select[@class=\"options\"]"),"List options");
    private  String selectValueLocator="//select[@class='options']/option[@value='%s']";

    public ProductPage(){
        super(By.xpath("//div[contains(@class,'page shop-product')]"),"Product Page");
    }


    public void addProductToCart() throws InterruptedException {
        btnAddToCart.click();
        Thread.sleep(2000);
            }




    public String  getAddButtonText(){
        System.out.println(btnAddToCart.getText());
        return btnAddToCart.getText();
    }
    public boolean isOutOfStockButtonDisplayed(){
        btnOutOfStock.waitForIsElementPresent();
        return btnOutOfStock.isPresent();
    }
    public void goToPreOrder(){
        btnPreOrder.waitForIsElementPresent();
        btnPreOrder.click();
    }
    public String getLblDiscount(){
        lblDiscount.waitForIsElementPresent();
        return lblDiscount.getText();
    }
    public String getLblOldPrice(){
        lblOldPrice.waitForIsElementPresent();
        return lblOldPrice.getText();
    }
    public String getLblPrice(){
        lblPrice.waitForIsElementPresent();
        return lblPrice.getText();
    }
    public boolean isLblUnselectedItemErrorDisplayed(){
        return lblUnselectedItemError.isPresent();
    }

    public void setSelectValueLocator(String value){
        Label selectValue =new Label(By.xpath(String.format(selectValueLocator,value)),"Value of select");
        selectValue.waitForIsElementPresent();
        selectValue.click();
    }
}
