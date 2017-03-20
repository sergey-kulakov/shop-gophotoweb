package webdriver.elements;

import org.openqa.selenium.By;

/**
 * Created by Sergey on 18.03.2017.
 */
public class CheckBox extends BaseElement {
    public CheckBox(final By locator, final String name) {
        super(locator, name);
    }

    public CheckBox(String string, String name) {
        super(string, name);
    }


    public CheckBox(By locator) {
        super(locator);
    }

    protected String getElementType() {
        return getLoc("loc.lcheckbox");
    }
}
