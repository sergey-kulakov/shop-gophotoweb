package demo.adminPages;

import org.openqa.selenium.By;
import webdriver.elements.Button;

/**
 * Created by Sergey on 02.04.2017.
 */
public class Utilites {
    static String locMenuItem="//ul[@class='clearfix']/li/a/span[contains(text(),'%s')]";

    public static void goToMenuName(String menuItemName){
        Button btnMenuItem=new Button(By.xpath(String.format(locMenuItem,menuItemName)),"Menu item with name"+menuItemName);
    }
}
