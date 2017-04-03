package shopGophotoweb.adminPages;

import org.openqa.selenium.By;
import webdriver.elements.Button;

/**
 * Created by Sergey on 02.04.2017.
 */
public class Utilites {
    static String locMenuItem="//ul[@class='clearfix']/li/a/span[contains(text(),'%s')]";
    static String locSidear="//ul[@class='nav navbar-nav']/li/a[contains(text(),'%s')]";

    public static void goToMenuName(String menuItemName){
        Button btnMenuItem=new Button(By.xpath(String.format(locMenuItem,menuItemName)),"Menu item with name"+menuItemName);
        btnMenuItem.click();
    }

    public static void goToSidebarItem(String itemName){
        Button btnSidebar=new Button(By.xpath(String.format(locSidear,itemName)),"Sidebar item");
        btnSidebar.click();
    }
}
