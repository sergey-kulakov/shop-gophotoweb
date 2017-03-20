package webdriver.elements;
import webdriver.Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class Label extends BaseElement{
    public Label(final By locator, final String name) {
        super(locator, name);
    }

    public Label(String string, String name) {
        super(string, name);
    }


    public Label(By locator) {
        super(locator);
    }

    protected String getElementType() {
        return getLoc("loc.label");
    }

    public java.util.List<String> getAllLinksFromList() {
        java.util.List<WebElement> lst = browser.getDriver().findElements(locator);
        java.util.List<String> strings = new ArrayList<>();
        for (WebElement e : lst) {
            strings.add(e.getAttribute("href"));


        }
        return strings;
    }

}
