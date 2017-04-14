package webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by sergey on 14.04.2017.
 */
public class ElemetsList extends BaseElement {
    /**
     * @param locator
     * @param name
     */
    public ElemetsList(final By locator, final String name) {
        super(locator, name);
    }

    public ElemetsList(String string, String name) {
        super(string, name);
    }


    public ElemetsList(By locator) {
        super(locator);
    }
    protected String getElementType() {
        return getLoc("loc.checkbox");
    }

    public void unCheckAllMethods() {

        List<WebElement> deliveryMethods = getElement().findElements(locator);
        for (WebElement deliveryMethod : deliveryMethods) {

            String atribute =deliveryMethod.findElement(By.xpath("input")).getAttribute("checked");
               System.out.println(atribute);
            if(atribute!=null){
                deliveryMethod.findElement(By.xpath("label")).click();
            }
        }
    }
}
