package shopGophotoweb.adminPages;

import org.openqa.selenium.By;
import webdriver.elements.Button;


public class Utilites {
    private static String locMenuItem="//ul[@class='clearfix']/li/a/span[contains(text(),'%s')]";
    private static String locSidear="//a[contains(text(),'%s')]";
    private static Button btnLogout=new Button(By.xpath("//a[contains(@href,'logout')]/span[contains(text(),'Выход')]"),"Logout button");
    public enum SidebarItems{Каталог,Товары,Категории,Лэйблы,Продажи,Заказы,Промо, оплаты,доставки,Оформление,предзаказа}

    public static void goToMenuName(String menuItemName){
        Button btnMenuItem=new Button(By.xpath(String.format(locMenuItem,menuItemName)),"Menu item with name"+menuItemName);
        btnMenuItem.waitForIsElementPresent();
        btnMenuItem.click();
    }

    public static void goToSidebarItem(SidebarItems itemName){
        Button btnSidebar=new Button(By.xpath(String.format(locSidear,itemName)),"Sidebar item");
        btnSidebar.waitForIsElementPresent();
        btnSidebar.click();
    }
    public static void logout(){
        btnLogout.waitForIsElementPresent();
        btnLogout.click();
    }
}
